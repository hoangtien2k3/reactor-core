package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * method:
 *      static <T> Mono<T> error(Throwable error)
 *
 *      => Create a Mono that terminates with the specified error immediately after being subscribed to.
 *      => Tạo một Mono kết thúc với lỗi được chỉ định ngay sau khi được đăng ký.
 *
 *
 *      static <T> Mono<T> error(Supplier<? extends Throwable> errorSupplier)
 *
 *      => Create a Mono that terminates with an error immediately after being subscribed to.
 *      => Tạo một Mono kết thúc với lỗi ngay sau khi được đăng ký.
 *
 *
 * */
public class error {
    public static void main(String[] args) {

        // static <T> Mono<T> error(Throwable error)
        Mono.error(new RuntimeException("Lỗi!"))
                .subscribe(
                        value -> System.out.println("Giá trị: " + value),
                        error -> System.err.println("Lỗi: " + error)
                );



        // static <T> Mono<T> error(Supplier<? extends Throwable> errorSupplier)
        Supplier<Throwable> errorSupplier = () -> new RuntimeException("Generated error");

        Mono<String> mono = Mono.error(errorSupplier);

        mono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage()),
                () -> System.out.println("Completed")
        );

    }
}
