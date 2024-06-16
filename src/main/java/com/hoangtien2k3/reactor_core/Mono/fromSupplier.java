package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.function.Supplier;

/**
 * method:
 *    static <T> Mono<T> fromSupplier(Supplier<? extends T> supplier)
 *
 *    => Create a Mono, producing its value using the provided Supplier.
 *    => Tạo một Mono, sản xuất giá trị của nó bằng cách sử dụng Supplier được cung cấp.
 *
 *
 * note:
 *    + để tạo một Mono từ một Supplier. Đây là một cách để lấy giá trị từ Supplier và bắt kết quả của nó vào một Mono.
 *
 *    + Chỉ đơn giản trả về một giá trị và không ném ra ngoại lệ.
 *    + Bạn không cần phải xử lý lỗi bởi vì Supplier không thực thi một công việc có thể gây lỗi.
 */
public class fromSupplier {
    public static void main(String[] args) {
        // Tạo một Supplier để sinh ra một số ngẫu nhiên từ 1 đến 100
        Supplier<Integer> randomSupplier = () -> {
            Random random = new Random();
            return random.nextInt(100) + 1;
        };

        // Tạo một Mono từ Supplier
        Mono<Integer> randomMono = Mono.fromSupplier(randomSupplier);

        // Subscribe để nhận giá trị từ Mono
        randomMono.subscribe(
                value -> System.out.println("Giá trị ngẫu nhiên là: " + value),
                error -> System.err.println("Đã xảy ra lỗi: " + error),
                () -> System.out.println("Hoàn thành")
        );
    }
}
