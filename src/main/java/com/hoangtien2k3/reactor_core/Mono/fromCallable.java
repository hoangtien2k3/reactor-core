package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

/**
 * @method:
 * <p>
 * static <T> Mono<T> fromCallable(Callable<? extends T> supplier)
 * <p>
 * => Create a Mono producing its value using the provided Callable.
 * <p>
 * => Tạo một Mono sản xuất giá trị của nó bằng cách sử dụng Callable được cung cấp.
 * <p>
 * @note:
 * <p>
 * + Sử dụng đê tạo một `Mono` từ một `Callable`. (hay chuyen doi tu mot Callable thanh mot Mono)
 * <p>
 * + Thực hiện bất đồng bộ: fromCallable cho phép bạn thực hiện các thao tác có thể ném ra ngoại lệ và bắt kết quả từ một Callable mà không cần phải chờ đợi đồng bộ.
 * <p>
 * + Khả năng ném ra ngoại lệ: fromCallable tự động bắt và phát lại bất kỳ ngoại lệ nào xảy ra từ Callable làm kết quả của Mono.
 * <p>
 * + Xử lý ngoại lệ: fromCallable tự động bắt và phát lại bất kỳ ngoại lệ nào xảy ra từ Callable làm kết quả của Mono.
 * <p>
 * <p>
 * @Su dung:
 * <p>
 * + Mono.fromCallable() được sử dụng khi bạn muốn thực hiện một nhiệm vụ có thể mất thời gian (như tính toán bất đồng bộ) và chuyển kết quả từ một Callable thành một Mono
 * <p>
 * + Mono.fromCallable() chỉ bắn ra một onComplete() sau khi Callable hoàn thành thành công hoặc một onError() nếu Callable ném ra một ngoại lệ.
 */
public class fromCallable {
    public static void main(String[] args) {
        // Tạo một Mono từ một Callable
        Mono<String> mono = Mono.fromCallable(() -> {
            // Thực hiện một số thao tác có thể ném ra ngoại lệ
            return "Hello, Reactor!";
        }).onErrorResume(e -> {
            // Xử lý ngoại lệ, có thể trả về một giá trị mặc định hoặc một Mono khác
            return Mono.just("Error occurred!");
        });

        // Đăng ký lắng nghe sự kiện khi có giá trị được phát ra từ Mono
        mono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );


        // VD2:
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            return 123;
        };
        Mono<Integer> mono2 = Mono.fromCallable(callable);
        mono2.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
