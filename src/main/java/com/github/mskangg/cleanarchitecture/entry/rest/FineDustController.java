package com.github.mskangg.cleanarchitecture.entry.rest;

import com.github.mskangg.cleanarchitecture.core.domain.FineDust;
import com.github.mskangg.cleanarchitecture.core.usecase.FineDustUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${api.path}/fine-dust")
public class FineDustController {
    private final FineDustUseCase fineDustUseCase;

    @Autowired
    public FineDustController(FineDustUseCase fineDustUseCase) {
        this.fineDustUseCase = fineDustUseCase;
    }

    @GetMapping
    public Mono<FineDust> findFineDust(@RequestParam(name = "q") String query){
        return fineDustUseCase.findFineDustByQuery(query);
    }
}
