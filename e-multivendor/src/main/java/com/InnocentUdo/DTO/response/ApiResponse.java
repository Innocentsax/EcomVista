package com.InnocentUdo.DTO.response;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
