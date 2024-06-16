package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @method: <P> P as(Function<? super Mono<T>,P> transformer)
 * <p>
 * => Transform this Mono into a target type.
 * <p>
 * => chuyển đổi Mono này thành một kiểu mục tiêu.
 */
public class as {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, World!");

        // Chuyển đổi Mono<String> sang Mono<Integer> bằng cách sử dụng độ dài của chuỗi
        Function<Mono<String>, Mono<Integer>> stringLengthTransformer =
                stringMono -> stringMono.map(String::length);

        Mono<Integer> lengthMono = mono.as(stringLengthTransformer);

        // Đăng ký và xử lý kết quả
        lengthMono.subscribe(length -> System.out.println("Length of the string: " + length));

    }
}
