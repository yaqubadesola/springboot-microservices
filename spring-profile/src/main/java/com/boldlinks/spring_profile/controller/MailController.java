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

        String[] activeProfiles = environment.getActiveProfiles();

        String primaryProfile = activeProfiles.length > 0
                ? activeProfiles[0]
                : "default";

        return new ProfileInfoResponse(
                Arrays.asList(activeProfiles),
                primaryProfile,
                mailProperties.environmentName(),
                mailProperties.sendRealEmails(),
                activeProfiles.length
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

        boolean valid =
                mailServerUrl != null && !mailServerUrl.isBlank()
                        && supportEmail != null
                        && supportEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
                        && subject != null && !subject.isBlank();

        return new ConfigurationValidationResponse(
                mailProperties.environmentName(),
                valid,
                valid
                        ? "Mail configuration is valid"
                        : "Mail configuration is invalid"
        );
    }
}
