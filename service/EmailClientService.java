package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailClientService {

    private final RestTemplate restTemplate;

    @Autowired
    public EmailClientService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void sendEmail(String to, String subject, String body) {
        String url = "http://localhost:8082/api/email/send";
        EmailDTO email = new EmailDTO(to, subject, body);
        restTemplate.postForEntity(url, email, String.class);
    }
}

