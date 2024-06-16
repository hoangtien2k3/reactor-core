package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * method:
 *      static <T> Mono<T> defer(Supplier<? extends Mono<? extends T>> supplier)
 *
 *      => Create a Mono provider that will supply a target Mono to subscribe to for each Subscriber downstream.
 *      => Tạo một nhà cung cấp Mono sẽ cung cấp một Mono mục tiêu để đăng ký cho mỗi Subscriber ở dưới dòng.
 *
 *
 *  Chức năng của defer:
 *      + Lười (Lazy) Evaluation: defer cho phép bạn trì hoãn việc tạo đối tượng Mono cho đến khi có một subscriber.
 *      + Supplier-based Creation: defer sử dụng một Supplier để cung cấp Mono
 *      + Dynamic Behavior: defer cho phép bạn thay đổi hành vi của Mono dựa trên logic của Supplier
 *
 * */
public class defer {
    public static void main(String[] args) {
        // Tạo một Mono từ một Supplier
        Mono<Integer> mono = Mono.defer(() -> Mono.just(1));

        // Sử dụng Supplier phức tạp hơn
        AtomicInteger counter = new AtomicInteger();
        Mono<Integer> deferredMono = Mono.defer(() -> {
            int value = counter.getAndIncrement();
            return Mono.just(value);
        });

        // Khi subscribe, Mono mới được tạo và trả về giá trị hiện tại của counter
        deferredMono.subscribe(System.out::println); // In ra: 0
        deferredMono.subscribe(System.out::println); // In ra: 1
    }
}
