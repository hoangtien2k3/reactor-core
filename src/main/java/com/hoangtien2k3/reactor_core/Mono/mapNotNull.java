package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * method:
 *      <R> Mono<R>	mapNotNull(Function<? super T,? extends R> mapper)
 *
 *      => Transform the item emitted by this Mono by applying a synchronous function to it, which is allowed to produce a null value.
 *      => Chuyển đổi mục được phát ra bởi Mono này bằng cách áp dụng một hàm đồng bộ cho nó, cho phép tạo ra giá trị null.
 *
 *
 *     => Nếu hàm biến đổi trả về null:
 *     +  Nếu hàm biến đổi trả về null, giá trị đó sẽ bị loại bỏ khỏi dòng dữ liệu.
 *     + `Mono` hoặc `Flux` sẽ không phát ra bất kỳ giá trị null nào.
 *
 * */
public class mapNotNull {
    public static void main(String[] args) {
        // Tạo một Mono chứa giá trị 10
        Mono<Integer> mono = Mono.just(10);

        // Sử dụng mapNotNull để nhân giá trị của Mono với 2
        Mono<Integer> resultMono = mono.mapNotNull(value -> {
            if (value % 2 == 0) {
                return value * 2;
            } else {
                return null;
            }
        });

        // Đăng ký lắng nghe sự kiện khi có giá trị được phát ra từ Mono
        resultMono.subscribe(
                value -> System.out.println("Result: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        // Output: Result: 20
    }
}
