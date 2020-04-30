package com.hojongs.service

import com.hojongs.entity.Item
import com.hojongs.reactor.MonoK
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers

@Service
class HelloService(
    private val ioScheduler: Scheduler = Schedulers.boundedElastic()
) {

    fun hello(): Mono<String> =
        Mono.just("hello")

    fun insertItem(item: Item): Mono<Item> =
        MonoK
            .onScheduler(ioScheduler) {
                item // todo save
            }
}
