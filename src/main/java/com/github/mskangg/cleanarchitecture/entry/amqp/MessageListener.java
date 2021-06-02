package com.github.mskangg.cleanarchitecture.entry.amqp;

import com.github.mskangg.cleanarchitecture.core.usecase.FineDustUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MessageListener {
    private final FineDustUseCase fineDustUseCase;

    @Autowired
    public MessageListener(FineDustUseCase fineDustUseCase) {
        this.fineDustUseCase = fineDustUseCase;
    }

    @RabbitListener(queues = "FineDustQ")
    public void updateContentSync(MessageContent messageContent) {
        Assert.notNull(messageContent.getQuery(), "Query is not null");
        fineDustUseCase.updateFineDustByQuery(messageContent.getQuery());
    }
}
