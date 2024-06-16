package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method:
 * <p><E extends Throwable> Mono<T> doOnError(Class<E> exceptionType, Consumer<? super E> onError)
 * <p>Mono<T>	doOnError(Consumer<? super Throwable> onError)
 * <p>Mono<T>	doOnError(Predicate<? super Throwable> predicate, Consumer<? super Throwable> onError)
 * <p>
 * <p>=> Add behavior triggered when the Mono completes with an error matching the given exception type.
 * <p>=> Thêm hành vi được kích hoạt khi Mono hoàn thành với một lỗi khớp với loại ngoại lệ cụ thể được cung cấp.
 * <p>
 * <p>
 * @note:
 * <p>+ cho phép bạn đăng ký một hành động để thực thi khi chuỗi Reactive gặp lỗi. Nó không phát ra lỗi,
 * mà thay vào đó cung cấp một cơ chế để bạn thực hiện các hành động phụ trợ (side effects) khi lỗi xảy ra
 */
public class doOnError {
    public static void main(String[] args) {

        // <E extends Throwable> Mono<T> doOnError(Class<E> exceptionType, Consumer<? super E> onError)
        Mono<Object> mono = Mono.error(new IllegalArgumentException("Invalid argument"))
                .doOnError(IllegalArgumentException.class, e -> System.out.println("Handled IllegalArgumentException: " + e.getMessage()))
                .doOnError(e -> System.out.println("Handled Throwable: " + e.getMessage()));

        mono.subscribe(System.out::println, System.err::println);


        // Mono<T> doOnError(Consumer<? super Throwable> onError)
        Mono.error(new RuntimeException("Lỗi!"))
                .doOnError(e -> System.out.println("Handled Throwable: " + e.getMessage()))
                .subscribe(
                        value -> System.out.println("Giá trị: " + value),
                        error -> System.err.println("Lỗi: " + error)
                );


        // Mono<T> doOnError(Predicate<? super Throwable> predicate, Consumer<? super Throwable> onError)
        Mono.error(new RuntimeException("Lỗi!"))
                .doOnError(e -> e instanceof RuntimeException, e -> System.out.println("Handled RuntimeException: " + e.getMessage()))
                .subscribe(
                        value -> System.out.println("Giá trị: " + value),
                        error -> System.err.println("Lỗi: " + error)
                );

    }
}
