package com.agoracharis.notification.data;

import com.agoracharis.notification.constant.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        String customerFirstName,
        String customerLastName,
        String customerEmail,
        PaymentMethod paymentMethod
) {
}
