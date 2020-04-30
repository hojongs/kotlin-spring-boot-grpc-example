package com.hojongs.grpc

import com.hojongs.entity.Item
import com.hojongs.service.HelloService
import org.lognet.springboot.grpc.GRpcService
import reactor.core.publisher.Mono
import com.hojongs.grpc.Item as ProtoItem

@GRpcService
class HelloGrpcService(
    private val helloService: HelloService
) : ReactorHelloServiceGrpc.HelloServiceImplBase() {

    override fun sayHello(
        request: Mono<HelloRequest>
    ): Mono<HelloResponse> = request
        .flatMap { helloService.hello() }
        .map { buildHelloResponse(it) }

    override fun insertItem(request: Mono<ProtoItem>): Mono<ProtoItem> = request
        .flatMap { helloService.insertItem(Item(id = it.id, name = it.name)) }
        .map {
            ProtoItem.newBuilder()
                .setId(it.id)
                .setName(it.name)
                .build()
        }

    private fun buildHelloResponse(msg: String) =
        HelloResponse
            .newBuilder()
            .setHello(
                proto.Hello.newBuilder()
                    .setMsg(msg)
            )
            .build()
}
