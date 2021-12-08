package com.example.demo.handlers;

import com.example.demo.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandler {

    private final List<Message> messageList = new ArrayList<>(
            List.of(
                    new Message(1L, "Hello, reactive!"),
                    new Message(2L, "More then one"),
                    new Message(3L, "Third post"),
                    new Message(4L, "Fourth post"),
                    new Message(5L, "Fifth post")
            )
    );

    public Mono<ServerResponse> getMessage(ServerRequest request) {

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(messageList), Message.class);
    }

    public Mono<ServerResponse> addMessage(ServerRequest request) {
        request
                .bodyToMono(Message.class)
                .subscribe(messageList::add);

        return ServerResponse
                .ok().body(Mono.just("Success"), String.class);

    }

    public Mono<ServerResponse> updateMessage(ServerRequest request) {

        return ServerResponse
                .ok().body(Mono.just("Success"), String.class);

    }

    public Mono<ServerResponse> deleteMessage(ServerRequest request) {

        return ServerResponse
                .ok().body(Mono.just("Success"), String.class);

    }
}
