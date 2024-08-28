package com.agoracharis.orderservice.config.kafka;

import com.agoracharis.orderservice.constant.PaymentMethod;
import com.agoracharis.orderservice.data.response.CustomerResp;
import com.agoracharis.orderservice.data.response.PurchaseResp;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        CustomerResp customer,
        List<PurchaseResp> product
) {
}
