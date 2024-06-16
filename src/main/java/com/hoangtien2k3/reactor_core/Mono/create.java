package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * method:
 *      public static <T> Mono<T> create(Consumer<MonoSink<T>> callback)
 *
 *      => Creates a deferred emitter that can be used with callback-based APIs to signal at most one value, a complete or an error signal.
 *      => Tạo một bộ phát hoãn mà có thể được sử dụng với các API dựa trên gọi lại để báo hiệu tối đa một giá trị, một tín hiệu hoàn chỉnh hoặc lỗi.
 *
 *
 * note:
 *      + Phương thức tạo Mono mà cho phép bạn tạo một Mono tùy chỉnh bằng cách cung cấp một Consumer của MonoSink.
 *      + Đây là một cách mạnh mẽ để tạo Mono trong trường hợp bạn cần kiểm soát hoàn toàn quá trình phát tán (emitting) giá trị, bao gồm xử lý lỗi và hoàn thành.
 *
 * */
public class create {
    public static void main(String[] args) {
        Mono<String> mono = Mono.create(sink -> {
            // Đưa ra một giá trị
            sink.success("Hello, world!");

            // Ném ra một ngoại lệ
            // sink.error(new RuntimeException("Lỗi xảy ra"));

            // Hoàn thành Mono
            // sink.success();

            // Đây là một ví dụ về việc hoàn thành Mono sau một khoảng thời gian
            /*new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    sink.success("Hoàn thành sau 1 giây");
                } catch (InterruptedException e) {
                    sink.error(e);
                }
            }).start();*/
        });

        mono.subscribe(
                value -> System.out.println("Nhận được giá trị: " + value),
                error -> System.err.println("Đã xảy ra lỗi: " + error.getMessage()),
                () -> System.out.println("Hoàn thành")
        );


        /**
         * Giai thich:
         *
         * Trong Consumer<MonoSink<String>> callback, chúng ta có thể gọi sink.success(value) để phát ra một giá trị,
         * sink.error(exception) để báo lỗi, và sink.success() để hoàn thành Mono.
         * */
    }
}
