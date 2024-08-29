package com.agoracharis.payment.service;

import com.agoracharis.payment.data.request.PaymentReq;

public interface PaymentService {
    Integer doPayment(PaymentReq paymentReq);
}
