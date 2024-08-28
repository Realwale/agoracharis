package com.agoracharis.orderservice.data.response;

import com.agoracharis.orderservice.constant.PaymentMethod;
import java.math.BigDecimal;

public record OrderResp(
        Integer orderId,
        BigDecimal amount,
        String customerId,
        String reference,
        PaymentMethod paymentMethod

) {
}
