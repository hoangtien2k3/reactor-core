package com.hoangtien2k3.reactor_core.Mono;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * method:
 *      static <T> Mono<T> from(Publisher<? extends T> source)
 *
 *      => Expose the specified Publisher with the Mono API, and ensure it will emit 0 or 1 item.
 *      => Hiển thị Nhà xuất bản được chỉ định với API Mono và đảm bảo nó sẽ phát ra 0 hoặc 1 mục.
 *
 * node:
 *      + Chuyển đổi từ một loại Publisher (có thể là Mono, Flux, hoặc các loại Publisher khác) thành một Mono.
 *
 * */
public class from {
    public static void main(String[] args) {


        /**
         * Giả sử chúng ta có một Flux phát ra nhiều giá trị, và chúng ta muốn tạo một Mono từ giá trị đầu tiên của Flux.
         * */
        // Tạo một Flux phát ra nhiều giá trị
        Flux<String> flux = Flux.just("Hello", "Reactor", "World");

        // Tạo một Mono từ Flux sử dụng Mono.from
        Mono<String> mono = Mono.from(flux);

        // Subscribe vào Mono để nhận giá trị và in ra màn hình
        mono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );



        /**
         * Giả sử chúng ta có một Mono và chúng ta muốn tạo một Mono mới từ Mono đó (thực tế là không cần thiết nhưng chỉ để minh họa).
         * */
        // Tạo một Mono phát ra một giá trị
        Mono<String> originalMono = Mono.just("Hello, Reactor!");

        // Tạo một Mono mới từ Mono ban đầu sử dụng Mono.from
        Mono<String> newMono = Mono.from(originalMono);

        // Subscribe vào Mono mới để nhận giá trị và in ra màn hình
        newMono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );



        /**
         * Giả sử chúng ta có một Publisher tùy chỉnh phát ra một giá trị.
         * */
        // Tạo một Publisher tùy chỉnh
        Publisher<String> customPublisher = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        s.onNext("Hello from custom Publisher!");
                        s.onComplete();
                    }

                    @Override
                    public void cancel() {
                        // Handle cancel
                    }
                });
            }
        };

        // Tạo một Mono từ Publisher tùy chỉnh sử dụng Mono.from
        Mono<String> monoStream = Mono.from(customPublisher);

        // Subscribe vào Mono để nhận giá trị và in ra màn hình
        monoStream.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
