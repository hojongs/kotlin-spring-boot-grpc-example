package com.hojongs.grpc

import com.hojongs.HelloService
import org.lognet.springboot.grpc.GRpcService
import reactor.core.publisher.Mono

@GRpcService
class HelloGrpcService(
    private val helloService: HelloService
) : ReactorHelloServiceGrpc.HelloServiceImplBase() {

    override fun sayHello(
        request: Mono<HelloRequest>?
    ): Mono<HelloResponse> = helloService.hello()
        .map (::buildHelloResponse)

    private fun buildHelloResponse(msg: String) = HelloResponse
            .newBuilder()
            .setHello(
                proto.Hello.newBuilder()
                    .setMsg(msg)
            )
            .build()
}
