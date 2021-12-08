package com.example.demo;


import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
public class MainController {

    @CrossOrigin
    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        //приёмник на клиенте по умолчанию
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Flux - " + LocalTime.now().toString());
    }

    @CrossOrigin
    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<Message>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<Message> builder()
                        .id(String.valueOf(sequence))
                        //приёмник на клиенте "periodic-event"
                        .event("periodic-event")
                        .data(new Message("SSE", LocalTime.now().toString()))
                        .build());
    }
}
