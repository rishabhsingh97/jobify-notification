package com.rsuniverse.jobify_notification.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document("notifications")
public class Notification {

    @Id
    private String to;
    private String subject;
    private String text;
}
