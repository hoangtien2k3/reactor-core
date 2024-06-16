package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @method: Flux<T>	flux()
 * <p>
 * => Convert this Mono to a Flux
 * <p>
 * => Chuyển đổi Mono này thành một Flux
 */
public class flux {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactor!");

        // Chuyển đổi Mono<String> thành Flux<String>
        Flux<String> flux = mono.flux();

        // Đăng ký và xử lý kết quả
        flux.subscribe(System.out::println);
    }
}
