package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 *  method:
 *          <R> Mono<R>	map(Function<? super T,? extends R> mapper)
 *
 *          => Transform the item emitted by this Mono by applying a synchronous function to it.
 *          => Chuyển đổi mục được phát ra bởi Mono này bằng cách áp dụng một hàm đồng bộ cho nó.
 *
 *
 *  =>  Nếu hàm biến đổi trả về null:
 *      + `Mono`: một null sẽ không được phát ra như một sự kiện
 *      + `Flux`: một null sẽ được phát ra như một sự kiện onComplete
 *
 * */
public class map {
    public static void main(String[] args) {
        // Tạo một Mono chứa giá trị 10
        Mono<Integer> mono = Mono.just(10);

        // Sử dụng map để nhân giá trị của Mono với 2
        Mono<Integer> resultMono = mono.map(value -> value * 2);

        // Đăng ký lắng nghe sự kiện khi có giá trị được phát ra từ Mono
        resultMono.subscribe(
                value -> System.out.println("Result: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        // Output: Result: 20
    }
}
