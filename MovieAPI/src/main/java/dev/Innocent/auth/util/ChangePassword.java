package dev.Innocent.auth.util;

public record ChangePassword(
        String password,
        String repeatPassword) {
}