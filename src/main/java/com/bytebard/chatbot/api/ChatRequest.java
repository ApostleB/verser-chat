package com.bytebard.chatbot.api;
import java.util.List;

public final class ChatRequest {
    private final String model;
    private final List<Message> messages;

    public ChatRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }
    public String getModel() { return model; }
    public List<Message> getMessages() { return messages; }

    public static final class Message {
        private final String role;
        private final String content;
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
        public String getRole() { return role; }
        public String getContent() { return content; }
    }
}
