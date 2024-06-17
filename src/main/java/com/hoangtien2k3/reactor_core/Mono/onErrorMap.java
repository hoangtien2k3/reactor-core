package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method: <E extends Throwable> Mono<T> onErrorMap(Class<E> type, Function<? super E,? extends Throwable> mapper)
 * <p>
 * => Transform an error emitted by this Mono by synchronously applying a function to it if the error matches the given type.
 * <p>
 * => Chuyển đổi một lỗi được phát ra bởi Mono này bằng cách áp dụng một hàm đồng bộ vào nó nếu lỗi khớp với loại đã cho.
 * <p>
 * <p>
 * Mono<T>	onErrorMap(Function<? super Throwable,? extends Throwable> mapper)
 * <p>
 * => Transform any error emitted by this Mono by synchronously applying a function to it.
 * <p>
 * => Chuyển đổi bất kỳ lỗi nào được phát ra bởi Mono này bằng cách áp dụng một hàm đồng bộ vào nó.
 * <p>
 * <p>
 * Mono<T>	onErrorMap(Predicate<? super Throwable> predicate, Function<? super Throwable,? extends Throwable> mapper)
 * <p>
 * => Transform an error emitted by this Mono by synchronously applying a function to it if the error matches the given predicate.
 * <p>
 * => Chuyển đổi một lỗi được phát ra bởi Mono này bằng cách áp dụng một hàm đồng bộ vào nó nếu lỗi khớp với predicate đã cho.
 * @Note: + Mono phát ra một lỗi và bạn muốn chuyển đổi lỗi này thành một loại lỗi khác có ý nghĩa hơn
 */
public class onErrorMap {
    public static void main(String[] args) {

        // Mono phát ra một lỗi, và bạn muốn chuyển đổi(map) lỗi này -> thành một lỗi khác có ý nghĩa hơn

        // <E extends Throwable> Mono<T> onErrorMap(Class<E> type, Function<? super E,? extends Throwable> mapper)
        // Mono gặp phải ngoại lệ ArithmeticException, chúng ta sẽ chuyển đổi nó thành một ngoại lệ mới.
        Mono.error(new ArithmeticException("Divide by zero"))
                .onErrorMap(ArithmeticException.class, ex -> new RuntimeException("ArithmeticException occurred"))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error.getMessage())
                );



        // Mono<T>	onErrorMap(Function<? super Throwable,? extends Throwable> mapper)
        // Nếu Mono gặp phải bất kỳ loại ngoại lệ nào, chúng ta sẽ chuyển đổi nó thành một ngoại lệ mới.
        Mono.error(new IllegalArgumentException("Invalid argument"))
                .onErrorMap(ex -> new RuntimeException("Error occurred"))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error.getMessage())
                );

        

        // Mono<T>	onErrorMap(Predicate<? super Throwable> predicate, Function<? super Throwable,? extends Throwable> mapper)
        // Nếu Mono gặp phải ngoại lệ IllegalArgumentException, chúng ta sẽ chuyển đổi nó thành một ngoại lệ mới.
        Mono.error(new IllegalArgumentException("Invalid argument"))
                .onErrorMap(e -> e instanceof IllegalArgumentException, e -> new RuntimeException("IllegalArgumentException occurred"))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error.getMessage())
                );
    }
}
