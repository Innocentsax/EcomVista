package dev.Innocent.service;

import dev.Innocent.enums.VerificationType;
import dev.Innocent.model.User;

public interface UserService {
    User findUserByEmail(String email) throws Exception;
    User findUserProfileByJwt(String jwt) throws Exception;
    User findUserById(Long userId) throws Exception;
    User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user);
    User updatePassword(User user, String password);
}
