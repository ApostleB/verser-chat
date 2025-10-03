package com.bytebard.chatbot;

import java.util.HashMap;
import java.util.Map;

public final class Chatbot {

    private Chatbot(){}

    public static void init(String apiKey, String model){
        System.out.println("Initializing Chatbot...");
        System.out.println("API Key: " + apiKey);
        System.out.println("Model: " + model);
    }

    public static Map<String, Object> chat(String message){
        Map<String, Object> result = new HashMap<>();
        result.put("question", message);
        result.put("answer", "반갑습니다.");

        return result;
    }
}
