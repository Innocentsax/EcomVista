package dev.Innocent.service;

import dev.Innocent.DTO.response.AIChatResponse;

public interface ChatbotService {
    AIChatResponse getCoinDetails(String prompt) throws Exception;
    String simpleChat(String prompt);
}
