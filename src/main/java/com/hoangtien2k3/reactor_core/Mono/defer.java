package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @method: static <T> Mono<T> defer(Supplier<? extends Mono<? extends T>> supplier)
 * <p>
 * => Create a Mono provider that will supply a target Mono to subscribe to for each Subscriber downstream.
 * <p>
 * => Tạo một nhà cung cấp Mono sẽ cung cấp một Mono mục tiêu để đăng ký cho mỗi Subscriber ở dưới dòng.
 * <p>
 * <p>
 * @Chức năng của defer:
 * <p>
 * + Lười (Lazy) Evaluation: defer cho phép bạn trì hoãn việc tạo đối tượng Mono cho đến khi có một subscriber.
 * <p>
 * + Supplier-based Creation: defer sử dụng một Supplier để cung cấp Mono
 * <p>
 * + Dynamic Behavior: defer cho phép bạn thay đổi hành vi của Mono dựa trên logic của Supplier
 */
public class defer {
    public static void main(String[] args) {

        // VD1:
        Mono<Integer> mono = Mono.defer(() -> {
            System.out.println("Creating Mono");
            return Mono.just(1);
        });

        // Mono chỉ được tạo khi có subscriber
        mono.subscribe(System.out::println); // Output: Creating Mono 1


        // VD2:
        // Sử dụng Supplier phức tạp hơn
        AtomicInteger counter = new AtomicInteger();
        Mono<Integer> deferredMono = Mono.defer(() -> {
            int value = counter.getAndIncrement();
            return Mono.just(value);
        });

        // Khi subscribe, Mono mới được tạo và trả về giá trị hiện tại của counter
        deferredMono.subscribe(System.out::println); // In ra: 0
        deferredMono.subscribe(System.out::println); // In ra: 1


        //VD3:
        Mono<String> result = Mono.defer(() -> {
                    if (Math.random() > 0.5) {
                        return Mono.just("Condition met");
                    } else {
                        return Mono.error(new RuntimeException("Condition not met"));
                    }
                })
                .onErrorResume(throwable -> Mono.just("Condition not met - Processing alternative"))
                .flatMap(message -> Mono.just("Processed: " + message));

        result.subscribe(System.out::println, System.err::println);
    }
}
