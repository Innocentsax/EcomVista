package dev.Innocent.notification.model;

import dev.Innocent.notification.enums.NotificationType;
import dev.Innocent.notification.kafka.order.OrderConfirmation;
import dev.Innocent.notification.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    private String id;
    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
