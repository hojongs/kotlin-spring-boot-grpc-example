package com.hojongs

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

class HelloService {

    fun hello(): Mono<String> = "hello".toMono()
}
