package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @method:
 * <p>static <T> Mono<T> empty()
 *
 * <p>=> Create a Mono that completes without emitting any item.
 * <p>=> Tạo một Mono hoàn thành mà không phát ra bất kỳ mục nào.
 */
public class empty {
    public static void main(String[] args) {
        Mono<Optional<String>> mono = getMonoValue(false);

        mono.subscribe(
                optionalValue -> optionalValue.ifPresentOrElse(
                        value -> System.out.println("Received: " + value),
                        () -> System.out.println("No value present")
                ),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }

    public static Mono<Optional<String>> getMonoValue(boolean shouldReturnValue) {
        if (shouldReturnValue) {
            return Mono.just(Optional.of("Hello, World!"));
        } else {
            return Mono.just(Optional.empty()); // Trả về Optional.empty() thay vì null
        }
    }
}
