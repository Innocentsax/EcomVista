package dev.Innocent.service.impl;

import dev.Innocent.DTO.request.AuthenticationRequest;
import dev.Innocent.DTO.request.RegistrationRequest;
import dev.Innocent.DTO.response.AuthenticationResponse;
import dev.Innocent.model.User;
import dev.Innocent.repository.RoleRepository;
import dev.Innocent.repository.TokenRepository;
import dev.Innocent.repository.UserRepository;
import dev.Innocent.security.JwtService;
import dev.Innocent.service.AuthenticationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;

    @Override
    public void register(RegistrationRequest request) throws MessagingException {

    }

    @Override
    public void activateAccount(String token) throws MessagingException {

    }

    @Override
    public String generateAndSaveActivationToken(User user) {
        return "";
    }

    @Override
    public void sendValidationEmail(User user) throws MessagingException {

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }

    @Override
    public String generateActivationCode(int length) {
        return "";
    }
}
