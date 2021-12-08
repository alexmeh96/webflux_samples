package com.example.demo.config;

import com.example.demo.handlers.MainHandler;
import com.example.demo.handlers.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class MessageRouter {

    @Bean
    public RouterFunction<ServerResponse> messageRoute(MessageHandler messageHandler) {
        RequestPredicate route1 = RequestPredicates
                .GET("/router/message/get")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        RequestPredicate route2 = RequestPredicates
                .POST("/router/message/add")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        RequestPredicate route3 = RequestPredicates
                .PUT("/router/message/update")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        RequestPredicate route4 = RequestPredicates
                .DELETE("/router/message/delete")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions
                .route(route1, messageHandler::getMessage)
                .andRoute(route2,  messageHandler::addMessage)
                .andRoute(route3,  messageHandler::updateMessage)
                .andRoute(route4,  messageHandler::deleteMessage);
    }
}
