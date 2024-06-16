package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * method:
 *      Mono<T> filterWhen(Function<? super T,? extends Publisher<Boolean>> asyncPredicate)
 *      => If this Mono is valued, test the value asynchronously using a generated Publisher<Boolean> test.
 *      => Đánh giá mỗi giá trị được phát ra bởi Mono này một cách không đồng bộ bằng cách sử dụng một bài kiểm tra Publisher<Boolean> được tạo ra.
 *
 * filterWhen:
 *      - Sử dụng filterWhen khi điều kiện lọc của bạn đòi hỏi thực hiện các tác vụ không đồng bộ như gọi API,
 *        truy vấn cơ sở dữ liệu, hoặc các tác vụ cần thời gian xử lý.
 *
 *
 * */
public class filterWhen {
    public static void main(String[] args) {
        Mono<Integer> flux = Mono.just(4);

        Mono<Integer> filteredFlux = flux.filterWhen(number ->
                Mono.fromCallable(() -> {
                    // Giả sử đây là một tác vụ không đồng bộ, như gọi API hoặc truy vấn cơ sở dữ liệu.
                    Thread.sleep(100); // Mô phỏng độ trễ
                    return number % 2 == 0;
                })
        );

        filteredFlux.subscribe(
                value -> System.out.println("Giá trị đã lọc: " + value),
                error -> System.err.println("Lỗi: " + error),
                () -> System.out.println("Hoàn thành")
        );
    }
}
