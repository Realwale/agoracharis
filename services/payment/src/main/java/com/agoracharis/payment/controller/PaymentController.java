package com.agoracharis.payment.controller;


import com.agoracharis.payment.data.request.PaymentReq;
import com.agoracharis.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Integer> doPayment(@RequestBody @Valid PaymentReq paymentReq){
        return ResponseEntity.ok(paymentService.doPayment(paymentReq));
    }
}
