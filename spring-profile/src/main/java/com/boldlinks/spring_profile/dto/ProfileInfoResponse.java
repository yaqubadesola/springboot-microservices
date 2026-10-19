package com.boldlinks.spring_profile.dto;

import java.util.List;
public record ProfileInfoResponse(
        List<String> activeProfiles,
        String primaryProfile,
        String environmentName,
        boolean realEmailsEnabled,
        int profileCount
) {
}
