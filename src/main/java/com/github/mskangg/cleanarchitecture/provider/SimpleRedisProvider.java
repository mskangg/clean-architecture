package com.github.mskangg.cleanarchitecture.provider;

import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import com.github.mskangg.cleanarchitecture.core.usecase.FindFineDustByQueryPort;
import com.github.mskangg.cleanarchitecture.core.usecase.UpdateFineDustByQueryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class SimpleRedisProvider implements FindFineDustByQueryPort, UpdateFineDustByQueryPort {
    private final ReactiveRedisOperations<String, FineDust> fineDustReactiveRedisOperations;
    private final String FINE_DUST_REDIS_KEY = "fineDust:";

    @Autowired
    public SimpleRedisProvider(ReactiveRedisOperations<String, FineDust> fineDustReactiveRedisOperations) {
        this.fineDustReactiveRedisOperations = fineDustReactiveRedisOperations;
    }

    @Override
    public Mono<FineDust> findFineDustByQuery(String query) {
        return fineDustReactiveRedisOperations.opsForValue().get(FINE_DUST_REDIS_KEY + query);
    }

    @Override
    public Mono<Void> updateFineDustByQuery(String query, FineDust fineDust) {
        fineDustReactiveRedisOperations.opsForValue().set(FINE_DUST_REDIS_KEY + query, fineDust)
                .subscribe(null,
                        e -> log.info("update error"),
                        () -> log.info("update success"));

        return Mono.empty();
    }
}
