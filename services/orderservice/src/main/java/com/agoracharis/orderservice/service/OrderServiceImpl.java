package com.agoracharis.orderservice.service;

import com.agoracharis.orderservice.config.client.CustomerFeignClient;
import com.agoracharis.orderservice.config.client.PaymentClient;
import com.agoracharis.orderservice.config.client.ProductClient;
import com.agoracharis.orderservice.config.kafka.OrderProducer;
import com.agoracharis.orderservice.data.request.OrderLineReq;
import com.agoracharis.orderservice.data.request.OrderRequest;
import com.agoracharis.orderservice.data.request.PaymentReq;
import com.agoracharis.orderservice.data.request.PurchaseRequest;
import com.agoracharis.orderservice.config.kafka.OrderConfirmation;
import com.agoracharis.orderservice.data.response.CustomerResp;
import com.agoracharis.orderservice.data.response.OrderLineResp;
import com.agoracharis.orderservice.data.response.OrderResp;
import com.agoracharis.orderservice.exception.ResourceNotFoundException;
import com.agoracharis.orderservice.model.Order;
import com.agoracharis.orderservice.model.OrderLine;
import com.agoracharis.orderservice.repository.OrderLineRepository;
import com.agoracharis.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final CustomerFeignClient customerFeignClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        var customer = this.customerFeignClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found"));

        var product = this.productClient.purchaseProduct(orderRequest.products());

        Order order = Order.builder()
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.totalAmount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();

        var saveOrder = this.orderRepository.save(order);

        for (PurchaseRequest request : orderRequest.products()){
            OrderLineReq orderLineReq = new OrderLineReq(saveOrder.getId(), request.productId(), request.quantity());

            OrderLine orderLine = OrderLine.builder()
                    .productId(orderLineReq.getProductId())
                    .quantity(orderLineReq.getQuantity())
                    .order(Order.builder()
                            .id(orderLineReq.getOrderId())
                            .build())
                    .build();
            orderLineRepository.save(orderLine);

            var paymentReq = new PaymentReq(
                    orderRequest.reference(),
                    orderRequest.totalAmount(),
                    saveOrder.getId(),
                    customer,
                    orderRequest.paymentMethod()
            );
            this.paymentClient.doPayment(paymentReq);

            orderProducer.sendOrder(
                    new OrderConfirmation(
                            orderRequest.reference(),
                            orderRequest.totalAmount(),
                            orderRequest.paymentMethod(),
                            customer,
                            product
                    )
            );

        }

        return saveOrder.getId();
    }

    @Override
    public List<OrderResp> fetchOrders(){
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderResp(
                        order.getId(),
                        order.getTotalAmount(),
                        order.getReference(),
                        order.getCustomerId(),
                        order.getPaymentMethod()
                ))
                .toList();
    }

    @Override
    public OrderResp fetchOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(order -> new OrderResp(
                        order.getId(),
                        order.getTotalAmount(),
                        order.getReference(),
                        order.getCustomerId(),
                        order.getPaymentMethod()
                ))
                .orElseThrow(()-> new ResourceNotFoundException("Order not found with ID "+orderId));
    }

    @Override
    public List<OrderLineResp> fetchAllOrderLineByOrderId(Integer orderId){
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLine -> new OrderLineResp(
                        orderLine.getId(),
                        orderLine.getQuantity()
                )).toList();
    }

}
