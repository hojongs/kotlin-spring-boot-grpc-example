package com.hojongs

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class HelloHandler(
    private val helloService: HelloService
) {

    fun hello(
        request: ServerRequest
    ): Mono<ServerResponse> = helloService
        .hello()
        .flatMap { msg ->
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    mapOf(
                        "msg" to msg
                    )
                )
        }
}
