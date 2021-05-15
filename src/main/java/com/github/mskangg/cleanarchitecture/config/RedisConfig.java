package com.github.mskangg.cleanarchitecture.config;

import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory("192.168.19.137", 6379);
    }

    @Bean
    public ReactiveRedisOperations<String, FineDust> redisOperations(ReactiveRedisConnectionFactory connectionFactory) {
        Jackson2JsonRedisSerializer<FineDust> serializer = new Jackson2JsonRedisSerializer<>(FineDust.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, FineDust> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, FineDust> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}
