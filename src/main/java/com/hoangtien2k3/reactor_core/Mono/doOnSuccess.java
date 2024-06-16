package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method: Mono<T>	doOnSuccess(Consumer<? super T> onSuccess)
 * <p>
 * => Add behavior triggered as soon as the Mono can be considered to have completed successfully.
 * <p>
 * => Thêm hành vi được kích hoạt ngay khi Mono có thể được coi là đã hoàn thành thành công.
 */
public class doOnSuccess {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnSuccess(value -> {
                    // Thực hiện các tác vụ phụ trợ sau khi thành công
                    System.out.println("Success with value: " + value);
                    sendNotification(value);
                });

        mono.subscribe(System.out::println);
    }

    private static void sendNotification(String value) {
        // Gửi thông báo
        System.out.println("Notification sent for value: " + value);
    }
}
