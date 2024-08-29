package com.agoracharis.notification.service;


import com.agoracharis.notification.data.OrderConfirmation;
import com.agoracharis.notification.data.PaymentConfirmation;
import com.agoracharis.notification.model.Notification;
import com.agoracharis.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import static com.agoracharis.notification.constant.NotificationType.ORDER_CONFIRMATION;
import static com.agoracharis.notification.constant.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumerService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("<<<<<<<<<<<<<< Consuming message from payment-topic Topic:: %s >>>>>>>>>>>>>",
                paymentConfirmation));

        notificationRepository.save(Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .paymentConfirmation(paymentConfirmation)
                        .notificationDt(LocalDateTime.now())
                .build());

        final String customerName = paymentConfirmation.customerFirstName()+" "+paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.orderReference(),
                paymentConfirmation.amount());



    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmation(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("<<<<<<<<<<<<<< Consuming message from order-topic Topic:: %s >>>>>>>>>>>>>",
                orderConfirmation));

        notificationRepository.save(Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .orderConfirmation(orderConfirmation)
                .notificationDt(LocalDateTime.now())
                .build());

        final String customerName = orderConfirmation.customer().firstName()+" "+orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.orderReference(),
                orderConfirmation.amount(),
                orderConfirmation.product()
                );


    }
}
