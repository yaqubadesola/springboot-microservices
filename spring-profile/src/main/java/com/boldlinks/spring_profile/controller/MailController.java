package com.boldlinks.spring_profile.controller;

import com.boldlinks.spring_profile.config.MailProperties;
import com.boldlinks.spring_profile.dto.ConfigurationValidationResponse;
import com.boldlinks.spring_profile.dto.MailResponse;
import com.boldlinks.spring_profile.dto.ProfileInfoResponse;
import com.boldlinks.spring_profile.dto.ProfileResponse;
import com.boldlinks.spring_profile.service.EmployeeMailService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    //
    private final EmployeeMailService employeeMailService;
    private final MailProperties mailProperties;
    private final Environment environment;

    public MailController(EmployeeMailService employeeMailService, MailProperties mailProperties, Environment environment) {
        this.employeeMailService = employeeMailService;
        this.mailProperties = mailProperties;
        this.environment = environment;
    }

    @GetMapping("/active-profile")
    public ProfileResponse activeProfile() {
        return new ProfileResponse(
                Arrays.asList(environment.getActiveProfiles()),
                mailProperties.environmentName(),
                mailProperties.mailServerUrl(),
                mailProperties.sendRealEmails()
        );
    }


    @GetMapping("/profile-info")
    public ProfileInfoResponse getProfileInfo() {
        return new ProfileInfoResponse(
                Arrays.asList(environment.getActiveProfiles()),
                Arrays.asList(environment.getActiveProfiles()).get(0),
                mailProperties.environmentName(),
                mailProperties.sendRealEmails(),
                Arrays.asList(environment.getActiveProfiles()).size()
        );
    }
    @GetMapping("/config")
    public MailProperties config(){
        return mailProperties;
    }

    @GetMapping("/welcome")
    public MailResponse sendWelcomeMail(@RequestParam String email,
                                        @RequestParam String name) {
        return employeeMailService.sendWelcome(email, name);
    }

    @GetMapping("/validate-config")
    public ConfigurationValidationResponse validateConfig() {
        String mailServerUrl = mailProperties.mailServerUrl();
        String supportEmail = mailProperties.supportEmail();
        String subject = mailProperties.welcomeSubject();
        if(mailServerUrl.isBlank() || supportEmail.isBlank() || subject.isBlank()){
            return new ConfigurationValidationResponse(
                    mailProperties.environmentName(),
                    false,
                    "Mail configuration is Invalid"
            );
        }
        return new ConfigurationValidationResponse(
                mailProperties.environmentName(),
                false,
                "Mail configuration is Invalid"
        );
    }
}
