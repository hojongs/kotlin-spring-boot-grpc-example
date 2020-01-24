package com.hojongs.grpc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.kotlintest.shouldBe
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = ["grpc.port=2019"])
class HelloGrpcServiceTest {

    companion object {
        private lateinit var channel: ManagedChannel

        @BeforeAll
        @JvmStatic
        fun buildChannel() {
            channel = ManagedChannelBuilder
                .forAddress("localhost", 2019)
                .usePlaintext()
                .build()
        }

        @AfterAll
        @JvmStatic
        fun shutdownChannel() {
            channel.shutdown()
        }
    }

    @Test
    fun `test sayHello() returns a response with msg=hello field`() {
        val stub = ReactorHelloServiceGrpc
            .newReactorStub(channel)

        StepVerifier
            .create(
                stub.sayHello(
                    HelloRequest.newBuilder()
                        .apply {
                            name = "hello"
                        }
                        .build()
                )
        )
            .assertNext { it.hello.msg shouldBe "hello" }
            .verifyComplete()
    }
}
