package com.github.mskangg.cleanarchitecture.core.usecase;

import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import reactor.core.publisher.Mono;

public interface FineDustUseCase {
    Mono<FineDust> findFineDustByQuery(String query);
    Mono<Void> updateFineDustByQuery(String query);
}
