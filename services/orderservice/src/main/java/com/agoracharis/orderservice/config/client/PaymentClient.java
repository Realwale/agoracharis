package com.agoracharis.orderservice.config.client;



import com.agoracharis.orderservice.data.request.PaymentReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    Integer doPayment(@RequestBody PaymentReq paymentReq);
}
