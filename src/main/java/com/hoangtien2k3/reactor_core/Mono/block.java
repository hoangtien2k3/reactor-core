package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @method: T block()
 * <p>
 * => Subscribe to this Mono and block indefinitely until a next signal is received.
 * <p>
 * => Đăng ký Mono này và chặn vô thời hạn cho đến khi nhận được tín hiệu tiếp theo.
 * @method: T block(Duration timeout)
 * <p>
 * => Subscribe to this Mono and block until a next signal is received or a timeout expires.
 * <p>
 * => Đăng ký Mono này và chặn cho đến khi nhận được tín hiệu tiếp theo hoặc hết thời gian chờ.
 */
public class block {
    public static void main(String[] args) {
        // Tạo một Mono chứa giá trị 10
        Mono<Integer> mono = Mono.just(10);

        // Sử dụng block để chặn vô thời hạn cho đến khi nhận được tín hiệu tiếp theo
        Integer result = mono.block();

        // In ra giá trị của Mono
        System.out.println(result);

        // VD2:
        // Tạo một Mono với độ trễ giả lập
        Mono<String> delayedMono = Mono.just("Delayed Hello")
                .delayElement(Duration.ofSeconds(2));

        // Sử dụng block() để chờ và lấy giá trị từ Mono
        System.out.println("Waiting for the result...");
        String result1 = delayedMono.block();

        // In ra giá trị kết quả
        System.out.println("Result: " + result1);
    }
}
