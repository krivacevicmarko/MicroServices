package org.example.security.service.implementation;

import java.util.UUID;

public class VerificationLinkGenerator {

    public String generateVerificationLink(Integer userId) {
        // Generate a unique token using UUID
        String token = UUID.randomUUID().toString();

        // Example verification link format: https://example.com/verify?token=<generated_token>&userId=<user_id>
        return "https://example.com/verify?token=" + token + "&userId=" + userId;
    }

}
