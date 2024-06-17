package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @method: <E extends Throwable> Mono<T> onErrorResume(Class<E> type, Function<? super E,? extends Mono<? extends T>> fallback)
 * <p>
 * => Subscribe to a fallback publisher when an error matching the given type occurs, using a function to choose the fallback depending on the error.
 * <p>
 * => Đăng ký một bộ phát dự phòng khi xảy ra lỗi khớp với loại đã cho, sử dụng một hàm để chọn bộ phát dự phòng tùy thuộc vào lỗi.
 * <p>
 * <p>
 * Mono<T>	onErrorResume(Function<? super Throwable,? extends Mono<? extends T>> fallback)
 * <p>
 * => Subscribe to a fallback publisher when any error occurs, using a function to choose the fallback depending on the error.
 * <p>
 * => Đăng ký một bộ phát dự phòng khi xảy ra bất kỳ lỗi nào, sử dụng một hàm để chọn bộ phát dự phòng tùy thuộc vào lỗi.
 * <p>
 * <p>
 * Mono<T>	onErrorResume(Predicate<? super Throwable> predicate, Function<? super Throwable,? extends Mono<? extends T>> fallback)
 * <p>
 * => Subscribe to a fallback publisher when an error matching a given predicate occurs.
 * <p>
 * => Đăng ký một bộ phát dự phòng khi xảy ra lỗi khớp với một predicate đã cho.
 * @Note: + Cho phép bạn xử lý các lỗi xảy ra trong luồng Mono và cung cấp một Mono thay thế để tiếp tục xử lý.
 */
public class onErrorResume {
    public static void main(String[] args) {
        // <E extends Throwable> Mono<T> onErrorResume(Class<E> type, Function<? super E,? extends Mono<? extends T>> fallback)
        // Mono gặp phải ngoại lệ IOException, chúng ta sẽ trả về một Mono khác với giá trị "Recovered from IOException".
        Mono<Object> someMono = Mono.error(new IOException("File not found"))
                .onErrorResume(IOException.class, ex -> {
                    System.out.println("Handling IOException: " + ex.getMessage());
                    return Mono.just("Recovered from IOException");
                });

        someMono.subscribe(
                value -> System.out.println("Received value: " + value),
                error -> System.err.println("Error: " + error.getMessage())
        );


        // Mono<T>	onErrorResume(Function<? super Throwable,? extends Mono<? extends T>> fallback)
        // Nếu Mono gặp phải bất kỳ loại ngoại lệ nào, chúng ta sẽ trả về một Mono khác với giá trị "Recovered from any Throwable".
        Mono<Object> someMono1 = Mono.error(new RuntimeException("Something went wrong"))
                .onErrorResume(ex -> {
                    System.out.println("Handling Throwable: " + ex.getMessage());
                    return Mono.just("Recovered from any Throwable");
                });

        someMono1.subscribe(
                value -> System.out.println("Received value: " + value),
                error -> System.err.println("Error: " + error.getMessage())
        );


        // Mono<T>	onErrorResume(Predicate<? super Throwable> predicate, Function<? super Throwable,? extends Mono<? extends T>> fallback)
        // Nếu Mono gặp phải ngoại lệ ArithmeticException, chúng ta sẽ trả về một Mono khác với giá trị "Recovered from ArithmeticException".
        Mono<Integer> someMono2 = Mono.just(10)
                .flatMap(num -> Mono.just(num / 0))
                .onErrorResume(ex -> ex instanceof ArithmeticException, ex -> {
                    System.out.println("Handling ArithmeticException: " + ex.getMessage());
                    return Mono.just(0); // Recovered value
                });

        someMono2.subscribe(
                value -> System.out.println("Received value: " + value),
                error -> System.err.println("Error: " + error.getMessage())
        );
    }
}
