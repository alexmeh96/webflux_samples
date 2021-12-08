package com.example.demo.handlers;

import com.example.demo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatHandler implements WebSocketHandler {

    private static final Map<String, WebSocketSession> userMap = new ConcurrentHashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String query = session.getHandshakeInfo().getUri().getQuery();
        Map<String, String> queryMap = getQueryMap(query);
        String user = queryMap.get("user");
        userMap.put(user, session);

        System.out.println(user);
        System.out.println(userMap);

        return session.receive().flatMap(webSocketMessage -> {
            String payload = webSocketMessage.getPayloadAsText();
            System.out.println(payload);

            Message message;
            try {
                message = objectMapper.readValue(payload, Message.class);
                System.out.println(message);
                String userTo = message.getUserTo();
                if (userMap.containsKey(userTo)) {
                    WebSocketSession targetSession = userMap.get(userTo);
                    if (null != targetSession) {
                        WebSocketMessage textMessage = targetSession.textMessage(message.getMessage());
                        return targetSession.send(Mono.just(textMessage));
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return session.send(Mono.just(session.textMessage(e.getMessage())));
            }
            return  session.send ( Mono.just ( session.textMessage ("the target user is not online")));
        }).then().doFinally(signal ->  userMap.remove (user)); // delete the connection after the user closes the connection
    }

    private Map<String, String> getQueryMap(String queryStr) {
        Map<String, String> queryMap = new HashMap<>();
        if (!StringUtils.isEmpty(queryStr)) {
            String[] queryParam = queryStr.split("&");
            Arrays.stream(queryParam).forEach(s -> {
                String[] kv = s.split("=", 2);
                String value = kv.length == 2 ? kv[1] : "";
                queryMap.put(kv[0], value);
            });
        }
        return queryMap;
    }
}
