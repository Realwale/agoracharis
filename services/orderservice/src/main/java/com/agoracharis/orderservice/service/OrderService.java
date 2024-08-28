package com.agoracharis.orderservice.service;

import com.agoracharis.orderservice.data.request.OrderRequest;
import com.agoracharis.orderservice.data.response.OrderLineResp;
import com.agoracharis.orderservice.data.response.OrderResp;

import java.util.List;

public interface OrderService {

    Integer createOrder(OrderRequest orderRequest);

    List<OrderResp> fetchOrders();

    OrderResp fetchOrderById(Integer orderId);

    List<OrderLineResp> fetchAllOrderLineByOrderId(Integer orderId);
}
