package com.boldlinks.spring_profile.dto;

public record ConfigurationValidationResponse(
        String activeEnvironment,
        boolean configurationValid,
        String message
) {
}
