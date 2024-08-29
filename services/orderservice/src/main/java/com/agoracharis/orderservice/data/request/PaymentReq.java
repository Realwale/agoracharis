package com.agoracharis.orderservice.data.request;

import com.agoracharis.orderservice.constant.PaymentMethod;
import com.agoracharis.orderservice.data.response.CustomerResp;

import java.math.BigDecimal;

public record PaymentReq(
        String orderReference,
        BigDecimal amount,
        Integer orderId,
        CustomerResp customer,
        PaymentMethod paymentMethod
) {
}
