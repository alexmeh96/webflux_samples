package com.example.webflux_app.repository;

import com.example.webflux_app.domain.Anime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {
    Mono<Anime> findById(Integer id);
}
