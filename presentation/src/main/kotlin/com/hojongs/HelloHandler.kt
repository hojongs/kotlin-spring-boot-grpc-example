package com.hojongs

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class HelloHandler(val helloService: HelloService) {

    fun hello(
        request: ServerRequest
    ): Mono<ServerResponse> = helloService
        .hello()
        .flatMap { value ->
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(value)
        }
}
