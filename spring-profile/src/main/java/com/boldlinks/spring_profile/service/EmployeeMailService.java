package com.boldlinks.spring_profile.service;

import com.boldlinks.spring_profile.config.MailProperties;
import com.boldlinks.spring_profile.dto.MailResponse;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMailService {

    private final MailProperties mailProperties;

    public EmployeeMailService(MailProperties mailProperties){
        this.mailProperties = mailProperties;
    }

    public MailResponse sendWelcome(String employeeEmail, String employeeName){
        MailResponse mailResponse = new MailResponse(
                "Profile-based configuration using @ConfigurationProperties",
                mailProperties.environmentName(),
                mailProperties.mailServerUrl(),
                mailProperties.supportEmail(),
                mailProperties.sendRealEmails(),
                employeeEmail,
                mailProperties.welcomeSubject(),
                "Hello " + employeeName + ", welcome mail prepared using "
                        + mailProperties.environmentName() + " profile values.",
                mailProperties.sendRealEmails()? "READY_FOR_DELIVERY" : "SIMULATED"
        );

        System.out.println(mailResponse);
        return mailResponse;
    }
}
