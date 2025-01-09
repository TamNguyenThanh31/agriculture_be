package com.CRUD_Agriculture.CRUD_Agriculture.controllers;


import com.CRUD_Agriculture.CRUD_Agriculture.services.GroqChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class GroqChatController {

    private final GroqChatService groqChatService;

    @Autowired
    public GroqChatController(GroqChatService groqChatService) {
        this.groqChatService = groqChatService;
    }

    @PostMapping
    public Map<String, String> getResponse(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        String response = groqChatService.getGroqResponse(userMessage);

        // Trả về JSON
        return Map.of("response", response);
    }
}
