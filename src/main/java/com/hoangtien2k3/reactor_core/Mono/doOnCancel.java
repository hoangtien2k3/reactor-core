package com.hoangtien2k3.reactor_core.Mono;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

/**
 * @method: Mono<T>	doOnCancel(Runnable onCancel)
 * <p>
 * => Add behavior triggered when the Mono is cancelled.
 * <p>
 * => Thêm hành vi được kích hoạt khi Mono bị hủy.
 * @note: + cho phép bạn đăng ký một hành động phụ trợ để thực hiện mỗi khi Mono bị hủy.
 * <p>
 * + cho phép bạn thiết lập các hành động mà bạn muốn thực hiện khi Mono bị hủy bỏ.
 * <p>
 * + Các hành động trong doOnCancel thường là các thao tác phụ (side effects) như giải phóng tài nguyên, huỷ các kết nối, hoặc thông báo cho các thành phần khác về việc hủy bỏ.
 */
public class doOnCancel {
    public static void main(String[] args) throws InterruptedException {
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnCancel(() -> System.out.println("Cancelled"));

        mono.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                Subscription::cancel
        );



        // VD2:
        Mono<String> monoDemo = Mono.just("Hello")
                .doOnCancel(() -> System.out.println("Cancellation detected!"));

        Disposable disposable = monoDemo.subscribe(
                value -> System.out.println("Value: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        Thread.sleep(100); // Chờ 100ms trước khi dispose
        disposable.dispose();

        // Output: Cancellation detected!
    }
}
