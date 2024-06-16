package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method: Mono<T>	doOnRequest(LongConsumer consumer)
 * <p>
 * => Add behavior triggering a LongConsumer when the Mono receives any request.
 * <p>
 * => Thêm hành vi kích hoạt LongConsumer khi Mono nhận bất kỳ yêu cầu nào.
 * @note: + cho phép bạn đăng ký một hành động phụ trợ để thực hiện mỗi khi có một yêu cầu (request) được thực hiện từ phía subscriber đối với Mono
 */
public class doOnRequest {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnRequest(request -> {
                    if (request <= 0) {
                        throw new IllegalArgumentException("Request amount must be positive");
                    }
                    System.out.println("Valid request amount: " + request);
                });

        mono.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.request(1)
        );
    }
}
