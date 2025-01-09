package com.CRUD_Agriculture.CRUD_Agriculture.model.request;

import java.util.List;

public class GroqRequest {
    private String model; // Model cần sử dụng, ví dụ "default-model"
    private List<Message> messages; // Danh sách tin nhắn (ngữ cảnh)

    public GroqRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    // Getter và Setter
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // Class Message
    public static class Message {
        private String role; // Vai trò: "user" hoặc "assistant"
        private String content; // Nội dung tin nhắn

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        // Getter và Setter
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
