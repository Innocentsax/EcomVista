package dev.Innocent.enums;

public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("activate_account")

    ;
    private final String name;
    EmailTemplateName(String name) {
        this.name = name;
    }
}
