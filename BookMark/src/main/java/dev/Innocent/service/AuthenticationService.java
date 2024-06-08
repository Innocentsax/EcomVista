package dev.Innocent.service;

import dev.Innocent.DTO.request.AuthenticationRequest;
import dev.Innocent.DTO.request.RegistrationRequest;
import dev.Innocent.DTO.response.AuthenticationResponse;
import dev.Innocent.model.User;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    void register(RegistrationRequest request) throws MessagingException;
    void activateAccount(String token) throws MessagingException;
    String generateAndSaveActivationToken(User user);
    void sendValidationEmail(User user) throws MessagingException;
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateActivationCode(int length);
}
