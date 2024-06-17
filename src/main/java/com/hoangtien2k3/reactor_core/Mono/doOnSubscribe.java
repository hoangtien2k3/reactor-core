package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method: <R> Mono<T>	doOnDiscard(Class<R> type, Consumer<? super R> discardHook)
 * <p>
 * => Add behavior triggering a Consumer when the value is discarded.
 * <p>
 * => Thêm hành vi kích hoạt một Consumer khi giá trị bị loại bỏ.
 * @note: + doOnSubscribe trong Reactor cho phép bạn thực hiện các hành động phụ khi một subscriber đăng ký vào Mono.
 */
public class doOnSubscribe {
    public static void main(String[] args) {
        // Create a Mono
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnSubscribe(subscription -> {
                    // Perform additional tasks when the Mono is subscribed
                    System.out.println("Subscribed");
                });

        // Subscribe to the Mono
        mono.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.request(1)
        );
    }
}
