package com.hojongs.grpc

import io.grpc.ManagedChannelBuilder
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier
import kotlin.random.Random
import com.hojongs.grpc.Item as ProtoItem

@SpringBootTest
class HelloGrpcServiceTest {

    companion object {
        private val stub = ReactorHelloServiceGrpc.newReactorStub(
            ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build()
        )
    }

    //    @RepeatedTest(1)
    fun `test sayHello() returns a response with msg=hello field`() {
        val hello = "hello"
        val request =
            HelloRequest.newBuilder()
                .setName(hello)
                .build()

        StepVerifier
            .create(
                stub.sayHello(request)
                    .doOnNext { println("hello response $it") }
            )
            .assertNext { it.hello.msg shouldBe hello }
            .verifyComplete()
    }

    @Test
    fun test_insertItem() {
        val id = Random.nextInt(0, 10000)
        val name = "item"
        val request = ProtoItem.newBuilder()
            .setId(id)
            .setName(name)
            .build()
        val protoItem = stub.insertItem(request).block()

        println("protoItem $protoItem")
        Thread.sleep(100)
    }
}
