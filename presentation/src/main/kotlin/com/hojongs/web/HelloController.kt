package com.hojongs.web

import com.hojongs.service.HelloService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Controller
class HelloController(
    private val helloService: HelloService
) {

    fun hello(
        request: ServerRequest
    ): Mono<ServerResponse> =
        helloService
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
