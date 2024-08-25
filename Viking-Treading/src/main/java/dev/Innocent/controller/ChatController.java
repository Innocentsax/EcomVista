package dev.Innocent.controller;

import dev.Innocent.DTO.request.PromptRequest;
import dev.Innocent.DTO.response.AIChatResponse;
import dev.Innocent.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatbotService chatbotService;

    @PostMapping
    public ResponseEntity<AIChatResponse> getCoinDetails(@RequestBody PromptRequest promptRequest) throws Exception {
        AIChatResponse aiChatResponse = chatbotService.getCoinDetails(promptRequest.getPrompt());
        return new ResponseEntity<>(aiChatResponse, HttpStatus.OK);
    }

    @PostMapping("/simple")
    public ResponseEntity<String> getSimpleResponse(@RequestBody PromptRequest promptRequest) {
        String response = chatbotService.simpleChat(promptRequest.getPrompt());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
