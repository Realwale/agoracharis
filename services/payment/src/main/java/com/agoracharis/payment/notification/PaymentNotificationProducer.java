package com.agoracharis.payment.notification;


import com.agoracharis.payment.data.request.PaymentNotificationReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentNotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationReq> kafkaTemplate;

    public void sendPaymentNotification(PaymentNotificationReq paymentNotificationReq){
        log.info("Sending payment notification");

        Message<PaymentNotificationReq> notificationReqMessage = MessageBuilder
                .withPayload(paymentNotificationReq)
                .setHeader(TOPIC, "payment-topic")
                .build();

        kafkaTemplate.send(notificationReqMessage);
    }
}
