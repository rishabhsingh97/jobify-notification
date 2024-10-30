package com.rsuniverse.jobify_notification.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsuniverse.jobify_notification.models.dtos.UserDTO;
import com.rsuniverse.jobify_notification.models.enums.KafkaTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.rsuniverse.jobify_notification.models.dtos.NotificationDTO;
import com.rsuniverse.jobify_notification.models.enums.NotificationMode;
import com.rsuniverse.jobify_notification.models.pojos.KafkaEvent;
import com.rsuniverse.jobify_notification.models.responses.BaseRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.runtime.ObjectMethods;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailNotificationService emailNotificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topicPattern = "user-.*" , groupId = "${spring.kafka.consumer.group-id}")
    public void handleUserEvent(@Payload KafkaEvent event) {
        try {
            log.info("New user event: {}", event);
            UserDTO user = objectMapper.convertValue(event.getPayload(), UserDTO.class);

            NotificationDTO notificationDTO = NotificationDTO.builder()
                    .to(user.getFullName())
                    .from("Jobify Notifications")
                    .modes(Collections.singleton(NotificationMode.EMAIL))
                    .subject("Hiii")
                    .text("test")
                    .email("rishthakur18@gmail.com")
                    .phoneNumber(null)
                    .whatsappNumber(null)
                    .build();

            if (notificationDTO.getModes().contains(NotificationMode.EMAIL)) {
                boolean emailSent = emailNotificationService.sendNotification(
                        notificationDTO.getTo(),
                        notificationDTO.getFrom(),
                        notificationDTO.getEmail(),
                        notificationDTO.getSubject(),
                        notificationDTO.getText(),
                        false
                );
                if (emailSent) {
                    BaseRes.success(null);
                }
                log.info("Email notification sent to {}", notificationDTO.getEmail());
            }
        } catch (Exception e) {
            log.error("Error handling user event: {}", e.getMessage());
        }
    }

}
