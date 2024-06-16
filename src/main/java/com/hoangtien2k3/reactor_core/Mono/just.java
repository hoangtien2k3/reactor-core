package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @mẹthod:
 * <p>
 * static <T> Mono<T> just(T data)
 * <p>
 * => Create a new Mono that emits the specified item.
 * <p>
 * => Tạo một Mono mới phát ra mục được chỉ định.
 * <p>
 * <p>
 * @Node:
 * <p>
 * + Nếu bạn truyền một giá trị null cho Mono.just, nó sẽ ném ra NullPointerException.
 * <p>
 * + Thích hợp khi bạn chắc chắn rằng giá trị không phải là null.
 * <p>
 * <p>
 * @Sử dụng:
 * <p>
 * + Mono.just() được sử dụng để tạo một Mono từ một giá trị cụ thể đã biết trước.
 * <p>
 * + Nó là đơn giản và thường được sử dụng khi bạn muốn chuyển đổi một giá trị đã có sẵn (hoặc tính toán mà không cần bất đồng bộ) thành một Mono.
 * <p>
 * + Mono.just() sẽ bắn ra một onComplete() ngay sau khi phát ra giá trị đầu tiên.
 */
public class just {
    public static void main(String[] args) {
        // Tạo một Mono chứa giá trị "Hello, Reactor!"
        Mono<String> helloMono = Mono.just("Hello, Reactor!");

        // Subscribe vào Mono để nhận giá trị và in ra màn hình
        helloMono.subscribe(
                value -> System.out.println("Received: " + value), // Xử lý giá trị
                error -> System.err.println("Error: " + error),    // Xử lý lỗi
                () -> System.out.println("Completed")              // Hoàn thành
        );
    }
}
