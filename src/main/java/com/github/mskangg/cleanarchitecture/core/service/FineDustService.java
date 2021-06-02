package com.github.mskangg.cleanarchitecture.core.service;

import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import com.github.mskangg.cleanarchitecture.core.usecase.FindFineDustByQueryPort;
import com.github.mskangg.cleanarchitecture.core.usecase.FineDustUseCase;
import com.github.mskangg.cleanarchitecture.core.usecase.UpdateFineDustByQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FineDustService implements FineDustUseCase {
    private final FindFineDustByQueryPort simpleRedisProvider;
    private final FindFineDustByQueryPort openAPIProvider;
    private final UpdateFineDustByQueryPort updateFineDustByQueryPort;

    @Autowired
    public FineDustService(FindFineDustByQueryPort simpleRedisProvider, FindFineDustByQueryPort openAPIProvider,
                           UpdateFineDustByQueryPort updateFineDustByQueryPort) {
        this.simpleRedisProvider = simpleRedisProvider;
        this.openAPIProvider = openAPIProvider;
        this.updateFineDustByQueryPort = updateFineDustByQueryPort;
    }

    @Override
    public Mono<FineDust> findFineDustByQuery(String query) {
        return simpleRedisProvider.findFineDustByQuery(query);
    }

    @Override
    public Mono<Void> updateFineDustByQuery(String query) {
        openAPIProvider.findFineDustByQuery(query).subscribe(fineDust ->
                updateFineDustByQueryPort.updateFineDustByQuery(query, fineDust));
        return Mono.empty();
    }
}
