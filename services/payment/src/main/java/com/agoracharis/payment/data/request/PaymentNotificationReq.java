package com.agoracharis.payment.data.request;

import com.agoracharis.payment.constant.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationReq(
        String orderReference,
        BigDecimal amount,
        String customerFirstName,
        String customerLastName,
        String customerEmail,
        PaymentMethod paymentMethod
) {
}
