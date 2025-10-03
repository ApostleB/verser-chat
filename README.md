# ChatClient

간단한 LLM 연동 실험을 위한 Java 기반 채팅 클라이언트 샘플 프로젝트입니다. `Chatbot` 유틸리티를 통해 모델을 초기화하고 메시지를 주고받는 흐름을 테스트할 수 있습니다. 아직은 모의 응답을 반환하지만, OkHttp와 Jackson 의존성을 활용해 실제 API 연동으로 확장할 수 있도록 설계돼 있습니다.

## 요구 사항
- Java 8 이상
- Gradle 7.x 이상 (프로젝트에 Gradle Wrapper가 포함되어 있어 `./gradlew` 로 실행 가능)

## 빠르게 시작하기
```bash
./gradlew clean build
```

## 사용 예시
```java
import com.bytebard.chatbot.Chatbot;

public class App {
    public static void main(String[] args) {
        Chatbot.init("your-api-key", "gpt-4o-mini");
        var response = Chatbot.chat("안녕하세요?");
        System.out.println(response.get("answer"));
    }
}
```

현재 `Chatbot.chat` 메서드는 고정된 응답(`"반갑습니다."`)을 반환합니다. `ChatRequest` 와 같은 데이터 클래스를 확장해 실제 API 호출, 비동기/스트리밍 처리 등을 구현할 수 있습니다.

## 프로젝트 구조
```
src/
└── main/java/com/bytebard/chatbot/
    ├── Chatbot.java
    └── api/
        ├── ChatClient.java
        └── ChatRequest.java
```

## 테스트 실행
```bash
./gradlew test
```

## 다음 단계 아이디어
- OkHttp와 Jackson을 이용한 실제 채팅 API 호출 로직 구현
- `ChatClient` 인터페이스에 대한 동기/비동기/스트리밍 구현체 추가
- `ChatRequest`/`ChatResponse` 객체 직렬화/역직렬화 로직 보강
- 샘플 애플리케이션 또는 스프링 부트 통합 예제 추가
