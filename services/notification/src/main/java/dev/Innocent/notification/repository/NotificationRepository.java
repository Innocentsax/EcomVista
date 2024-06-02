package dev.Innocent.notification.repository;

import dev.Innocent.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
