package com.bytebard.chatbot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bytebard.chatbot.api.ChatRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatRequestTest {

    @Test
    void build_chat_request_from_test_properties() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            assertNotNull(inputStream, "테스트용 application.properties 파일을 찾을 수 없습니다.");
            properties.load(inputStream);
        }

        String apiKey = properties.getProperty("chat.api-key");
        String model = properties.getProperty("chat.model");
        List<ChatRequest.Message> messages = Arrays.asList(
                new ChatRequest.Message("user", properties.getProperty("chat.user-message")),
                new ChatRequest.Message("system", properties.getProperty("chat.system-message"))
        );

        Chatbot.init(apiKey, model);

        ChatRequest request = new ChatRequest(model, messages);
        assertEquals("unit-test-api-key", apiKey);
        assertEquals("gpt-4o-mini", request.getModel());
        assertEquals(2, request.getMessages().size());
        assertEquals("user", request.getMessages().get(0).getRole());
        assertEquals("안녕?", request.getMessages().get(0).getContent());
        assertEquals("system", request.getMessages().get(1).getRole());
        assertEquals("시스템 : 안녕하세요", request.getMessages().get(1).getContent());

        Map<String, Object> chatResult = Chatbot.chat(request.getMessages().get(0).getContent());
        assertEquals("안녕?", chatResult.get("question"));
        assertEquals("반갑습니다.", chatResult.get("answer"));
    }
}
