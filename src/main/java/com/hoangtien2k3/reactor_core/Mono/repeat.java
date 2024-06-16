package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Flux;

/**
 * @method: {@link Flux<T> repeat()} : Lặp lại các phần tử của Flux vô hạn
 * <p>
 * {@link Flux<T> repeat(BooleanSupplier predicate)} : Lặp lại các phần tử của Flux vô hạn cho đến khi predicate trả về false
 * <p>
 * {@link Flux<T> repeat(long numRepeat)} : Lặp lại các phần tử của Flux numRepeat lần
 * <p>
 * {@link Flux<T> repeat(long numRepeat, BooleanSupplier predicate)} : Lặp lại các phần tử của Flux numRepeat lần cho đến khi predicate trả về false
 */
public class repeat {
    public static void main(String[] args) {

        // repeat() : Lặp lại các phần tử của Flux vô hạn
        Flux<Integer> flux = Flux.just(1, 2, 3)
                .repeat();


        // repeat(long numRepeat) : Lặp lại các phần tử của Flux numRepeat lần
        Flux<Integer> flux1 = Flux.just(1, 2, 3)
                .repeat(2);


        // repeat(BooleanSupplier predicate) : Lặp lại các phần tử của Flux vô hạn cho đến khi predicate trả về false
        Flux<Integer> flux2 = Flux.just(1, 2, 3)
                .repeat(() -> true);


        // repeat(long numRepeat, BooleanSupplier predicate) : Lặp lại các phần tử của Flux numRepeat lần cho đến khi predicate trả về false
        Flux<Integer> flux3 = Flux.just(1, 2, 3)
                .repeat(2, () -> true);
    }
}
