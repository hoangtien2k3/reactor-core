package com.hoangtien2k3.reactor_core.Mono;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

/**
 * @method: <R> Mono<T>	doOnEach(Consumer<? super Signal<T>> signalConsumer)
 * <p>
 * => Add behavior (side-effect) triggered when the Mono emits an item, fails with an error, or completes successfully.
 * <p>
 * => Thêm hành vi (tác động phụ) được kích hoạt khi Mono phát ra một mục, thất bại với một lỗi hoặc hoàn thành thành công.
 * @note: + doOnEach cho phép bạn thực hiện các hành động khi một tín hiệu (signal) xảy ra trên Mono. Các tín hiệu này bao gồm: giá trị được phát ra (onNext), lỗi xảy ra (onError), hoặc Mono hoàn thành (onComplete).
 */
public class doOnEach {
    public static void main(String[] args) {
        // Create a Mono
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnEach(signal -> {
                    // Perform additional tasks when the Mono emits an item, fails with an error, or completes successfully
                    if (signal.isOnNext()) {
                        System.out.println("Emitted value: " + signal.get());
                    } else if (signal.isOnError()) {
                        System.err.println("Error: " + signal.getThrowable());
                    } else {
                        System.out.println("Completed");
                    }
                });

        // Subscribe to the Mono
        mono.subscribe();
    }
}
