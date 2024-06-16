package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @method:
 *
 * <p>
 * static <T> Mono<T> justOrEmpty(Optional<? extends T> data)
 * <p>
 * static <T> Mono<T>	justOrEmpty(T data)
 * <p>
 * => Create a new Mono that emits the specified item if it is not null, otherwise only emits onComplete.
 * <p>
 * => Tạo một Mono mới phát ra mục được chỉ định nếu nó không phải là null, nếu không chỉ phát ra onComplete.
 * <p>
 * @Node:
 * <p>
 * + Dùng để tạo ra một Mono chứa một giá trị hoặc một Mono.empty() nếu giá trị là null.
 * <p>
 * + Không ném ra ngoại lệ khi giá trị là null.
 */
public class justOrEmpty {
    public static void main(String[] args) {
        // Tạo một giá trị null
        String nullValue = null;

        // Sử dụng Mono.justOrEmpty để tạo Mono từ giá trị null
        Mono<String> mono = Mono.justOrEmpty(nullValue);

        // Subscribe vào Mono để kiểm tra kết quả
        mono.subscribe(
                value -> System.out.println("Received: " + value), // Xử lý giá trị (sẽ không được gọi)
                error -> System.err.println("Error: " + error),    // Xử lý lỗi
                () -> System.out.println("Completed")              // Hoàn thành
        );


        // VD2:
        // Tạo một Optional chứa giá trị không null
        Optional<String> nonNullOptional = Optional.of("Hello, Reactor!");

        // Sử dụng Mono.justOrEmpty để tạo Mono từ Optional
        Mono<String> moOptionalo = Mono.justOrEmpty(nonNullOptional);

        // Subscribe vào Mono để nhận giá trị và in ra màn hình
        moOptionalo.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
