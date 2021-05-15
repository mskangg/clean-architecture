package com.github.mskangg.cleanarchitecture.core.usecase;

import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import reactor.core.publisher.Mono;

public interface FindFineDustByQueryPort {
    Mono<FineDust> findFineDustByQuery(String query);
}
