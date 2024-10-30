package com.rsuniverse.jobify_notification.models.dtos;

import java.util.Set;

import com.rsuniverse.jobify_notification.models.enums.NotificationMode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDTO {
    private String name;
    private String to;
    private String from;     
    private String email;    
    private String phoneNumber;  
    private String whatsappNumber;
    private String subject;     
    private String text;        
    private Set<NotificationMode> modes;
}