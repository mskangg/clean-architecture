package com.github.mskangg.cleanarchitecture.provider;

import com.github.mskangg.cleanarchitecture.config.OpenAPIProperties;
import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import com.github.mskangg.cleanarchitecture.core.usecase.FindFineDustByQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@EnableConfigurationProperties({OpenAPIProperties.class})
public class OpenAPIProvider implements FindFineDustByQueryPort {
    private final OpenAPIProperties openAPIProperties;

    @Autowired
    public OpenAPIProvider(OpenAPIProperties openAPIProperties) {
        this.openAPIProperties = openAPIProperties;
    }

    @Override
    public Mono<FineDust> findFineDustByQuery(String query) {
        Assert.notNull(query, "Query is not null");

        if (ObjectUtils.isEmpty(query)) {
            return Mono.empty();
        }

        return WebClient.create(openAPIProperties.getUrl())
                .method(HttpMethod.GET)
                .uri("?serviceKey=" + openAPIProperties.getServiceKey() +
                        "&returnType=" + openAPIProperties.getReturnType() +
                        query)
                .retrieve()
                .bodyToMono(FineDust.class);
    }
}
