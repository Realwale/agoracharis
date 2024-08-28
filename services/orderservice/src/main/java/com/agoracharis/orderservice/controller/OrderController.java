package com.agoracharis.orderservice.controller;


import com.agoracharis.orderservice.data.request.OrderRequest;
import com.agoracharis.orderservice.data.response.OrderLineResp;
import com.agoracharis.orderservice.data.response.OrderResp;
import com.agoracharis.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResp>> fetchOrders(){
        return ResponseEntity.ok(orderService.fetchOrders());
    }

    @GetMapping("{order-id}")
    public ResponseEntity<OrderResp> fetchOrderById(@PathVariable("order-id")Integer orderId){
        return ResponseEntity.ok(orderService.fetchOrderById(orderId));
    }

    @GetMapping("order-line/{order-id}")
    public ResponseEntity<List<OrderLineResp>> fetchAllOrderLineByOrderId(@PathVariable("order-id")Integer orderId){
        return ResponseEntity.ok(orderService.fetchAllOrderLineByOrderId(orderId));
    }
}
