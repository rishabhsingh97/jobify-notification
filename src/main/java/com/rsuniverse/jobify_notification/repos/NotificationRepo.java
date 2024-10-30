package com.rsuniverse.jobify_notification.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rsuniverse.jobify_notification.models.entities.Notification;

@Repository
public interface NotificationRepo extends  MongoRepository<Notification, String>{
}
