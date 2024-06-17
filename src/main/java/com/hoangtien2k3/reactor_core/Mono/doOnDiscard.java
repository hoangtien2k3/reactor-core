package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method:
 *
 *      <R> Mono<T>	doOnDiscard(Class<R> type, Consumer<? super R> discardHook)
 *
 *      => Add behavior triggering a Consumer when the value is discarded.
 *
 *      => Thêm hành vi kích hoạt một Consumer khi giá trị bị loại bỏ.
 *
 *
 * */
public class doOnDiscard {
    public static void main(String[] args) {
        // Create a Mono
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnDiscard(String.class, value -> {
                    // Perform additional tasks when the value is discarded
                    System.out.println("Discarded value: " + value);
                });

        // Subscribe to the Mono
        mono.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.cancel()
        );
    }
}
