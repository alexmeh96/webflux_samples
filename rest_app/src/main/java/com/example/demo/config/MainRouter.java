package com.example.demo.config;

import com.example.demo.handlers.MainHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class MainRouter {
    @Bean
    public RouterFunction<ServerResponse> route(MainHandler mainHandler) {
        RequestPredicate route = RequestPredicates
                .GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN));

        return RouterFunctions
                .route(route, mainHandler::hello)
                .andRoute(
                        RequestPredicates.GET("/"),
                        mainHandler::index
                );
    }
}
