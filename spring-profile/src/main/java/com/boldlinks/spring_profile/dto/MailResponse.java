package com.boldlinks.spring_profile.dto;

public record MailResponse(
        String approach,
        String activeEnvironment,
        String mailServerUrl,
        String supportEmail,
        boolean sendRealEmails,
        String toEmail,
        String subject,
        String message,
        String deliveryStatus
) {
}
