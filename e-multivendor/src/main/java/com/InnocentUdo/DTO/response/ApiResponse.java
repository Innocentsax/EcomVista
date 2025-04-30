package com.InnocentUdo.DTO.response;

import lombok.*;


@Getter
@Setter
public class ApiResponse {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
