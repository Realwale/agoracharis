package com.agoracharis.notification.model;

import com.agoracharis.notification.constant.NotificationType;
import com.agoracharis.notification.data.OrderConfirmation;
import com.agoracharis.notification.data.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType notificationType;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
    private LocalDateTime notificationDt;
}
