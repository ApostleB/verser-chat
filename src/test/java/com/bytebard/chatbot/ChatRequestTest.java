package com.bytebard.chatbot;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bytebard.chatbot.Chatbot;
import com.bytebard.chatbot.api.ChatRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatRequestTest {

    @Test
    void build_simple_request() {
        Map<String, Object> result = Chatbot.chat("안녕하세요");
        System.out.println("채팅 결과 : " + result.toString());

//        ChatRequest req = new ChatRequest("gpt-4o-mini", Arrays.asList(
//                new ChatRequest.Message("user", "안녕?"),
//                new ChatRequest.Message("system", "시스템 : 안녕하세요")
//        ));

//        assertEquals("gpt-4o-mini", req.getModel());
//        assertEquals(2, req.getMessages().size());
//        assertEquals("user", req.getMessages().get(0).getRole());
//        assertEquals("시스템 : 안녕하세요", req.getMessages().get(1).getContent());
//
//        System.out.println("✅ ChatRequest 생성/조회 테스트 OK");
//        System.out.println("현재 적용 모델 확인 : " + req.getModel());
//        System.out.println("메시지 테스트 : " + req.getMessages().get(0).getRole() + " : " + req.getMessages().get(0).getContent());
//        System.out.println("메시지 테스트 : " + req.getMessages().get(1).getRole() + " : " + req.getMessages().get(1).getContent());
    }
}
