package com.example.demo.handlers;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EchoHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        System.out.println(session);
        Flux<WebSocketMessage> messageFlux = session.receive().map(message -> {
            String payload = message.getPayloadAsText();
            return "RECEIVED ON SERVER :: " + payload;
        }).map(session::textMessage);

        return session.send(messageFlux);
    }
}
