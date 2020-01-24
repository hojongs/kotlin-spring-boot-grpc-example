package com.hojongs

import io.mockk.spyk
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

class HelloServiceTest {
    private val helloService = spyk(HelloService())

    @Test
    fun `test hello() returns hello string`() {
        StepVerifier
            .create(
                helloService.hello()
            )
            .expectNext("hello")
            .verifyComplete()
    }
}
