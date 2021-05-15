package com.github.mskangg.cleanarchitecture.core.usecase;

import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import reactor.core.publisher.Mono;

public interface UpdateFineDustByQueryPort {
    Mono<Void> updateFineDustByQuery(String query, FineDust fineDust);
}
