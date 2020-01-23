package com.hojongs

import io.mockk.spyk
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

class HelloServiceTest {
    private val helloService = spyk(HelloService())

    @Test
    fun `test hello()`() {
        StepVerifier.create(
            helloService.hello()
        )
            .expectNext(
                mapOf(
                    "msg" to "hello"
                )
            )
            .verifyComplete()
    }
}
