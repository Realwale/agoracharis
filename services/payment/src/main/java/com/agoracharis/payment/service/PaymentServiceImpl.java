package com.agoracharis.payment.service;

import com.agoracharis.payment.data.request.PaymentNotificationReq;
import com.agoracharis.payment.data.request.PaymentReq;
import com.agoracharis.payment.model.Payment;
import com.agoracharis.payment.notification.PaymentNotificationProducer;
import com.agoracharis.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    private final PaymentNotificationProducer notificationProducer;

    @Override
    public Integer doPayment(PaymentReq paymentReq) {

        Payment payment = Payment.builder()
                .orderId(paymentReq.orderId())
                .totalAmount(paymentReq.amount())
                .paymentMethod(paymentReq.paymentMethod())
                .build();

        paymentRepository.save(payment);

       notificationProducer.sendPaymentNotification(new PaymentNotificationReq(
               paymentReq.orderReference(),
               paymentReq.amount(),
               paymentReq.customer().firstName(),
               paymentReq.customer().lastName(),
               paymentReq.customer().email(),
               paymentReq.paymentMethod()
               ));

        return payment.getId();
    }
}
