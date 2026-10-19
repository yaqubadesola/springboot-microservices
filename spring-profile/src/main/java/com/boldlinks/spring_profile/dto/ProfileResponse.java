package com.boldlinks.spring_profile.dto;

import java.util.List;

public record ProfileResponse(
        List<String> activeProfiles,
        String loadedMailEnvironment,
        String emailServerUrl,
        boolean sendRealEmails
) {
}
