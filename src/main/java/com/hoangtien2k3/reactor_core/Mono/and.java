package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * Join the termination signals from this mono and another source into the returned void mono.
 *
 * `and` : là một cách để kết hợp nhiều Mono lại với nhau sao cho tất cả chúng phải hoàn thành thành công
 * */
public class and {
    public static void main(String[] args) {
        // Tạo một Mono chứa giá trị 10
        Mono<Integer> mono1 = Mono.just(10);

        // Tạo một Mono chứa giá trị 20
        Mono<Integer> mono2 = Mono.just(20);

        // Nối hai Mono và in ra giá trị của chúng
        mono1.and(mono2)
                .subscribe(System.out::println);


        // VD2:
        Mono<Void> mono11 = Mono.fromRunnable(() -> {
            System.out.println("Mono 1 is running");
        });

        Mono<Void> mono22 = Mono.fromRunnable(() -> {
            System.out.println("Mono 2 is running");
        });

        Mono<Void> combined = mono11.and(mono22);

        combined.doOnTerminate(() -> {
            System.out.println("Both Monos have completed");
        }).subscribe();


        // VD3
        Mono<String> mono111 = Mono.just("First Mono");
        Mono<String> mono222 = Mono.just("Second Mono");
        Mono<String> mono333 = Mono.just("Third Mono");

        // Sử dụng and để kết hợp các Mono
        Mono<Void> result = Mono.when(mono111, mono222, mono333);

        // Đăng ký và xử lý kết quả
        result.doOnSuccess(unused -> System.out.println("All Monos completed successfully!"))
                .doOnError(error -> System.err.println("An error occurred: " + error))
                .subscribe();
    }
}
