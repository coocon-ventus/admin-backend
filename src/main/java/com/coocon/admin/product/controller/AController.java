package com.coocon.admin.product.controller;

import com.coocon.admin.common.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product/a")
@RequiredArgsConstructor
public class AController {

    private final WebClient webClient;

    @GetMapping("/total")
    public Mono<ResponseEntity<ErrorResponse>> getHttpList(@RequestParam(value = "type") String type){
        ErrorResponse results;

        return webClient.post().uri("http://localhost:8080/register/" + type)
                .bodyValue("test")
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response ->
                        Mono.error(new Throwable("test")))
                .toEntity(ErrorResponse.class);
    }


}
