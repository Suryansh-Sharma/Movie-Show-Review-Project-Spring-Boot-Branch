package com.suryansh.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationEmailDto {
    private String userName;
    private String sender;
    private String subject;
    private String recipient;
    private String body;
}
