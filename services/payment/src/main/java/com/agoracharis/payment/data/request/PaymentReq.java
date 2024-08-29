package com.agoracharis.payment.data.request;

import com.agoracharis.payment.constant.PaymentMethod;

import java.math.BigDecimal;

public record PaymentReq(
        String orderReference,
        BigDecimal amount,
        Integer orderId,
        Customer customer,
        PaymentMethod paymentMethod
) {
}
