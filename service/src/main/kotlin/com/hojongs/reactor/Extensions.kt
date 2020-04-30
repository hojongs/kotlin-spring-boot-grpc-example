package com.hojongs.reactor

import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

// Mono-kotlin
object MonoK {
    fun <T> onScheduler(scheduler: Scheduler, f: () -> T?): Mono<T> =
        Mono.create<T> { sink -> sink.success(f()) }
            .subscribeOn(scheduler)
}
