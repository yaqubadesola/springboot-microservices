package com.boldlinks.spring_profile.config;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.mail")
@Validated
public record MailProperties(

        @NotBlank(message = "Environment name is required")
        String environmentName,

        @NotBlank(message = "Mail server URL is required")
        String mailServerUrl,

        @NotBlank(message = "Support email is required")
        @Email(message = "Support email must be valid")
        String supportEmail,

        @NotNull(message = "Send real emails flag is required")
        Boolean sendRealEmails,

        @NotBlank(message = "Welcome subject is required")
        String welcomeSubject

) {
}