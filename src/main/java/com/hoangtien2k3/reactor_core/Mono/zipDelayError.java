package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @method: static <T1,T2,R> Mono<R> zipDelayError(Publisher<? extends T1> source1,
 * Publisher<? extends T2> source2,
 * BiFunction<? super T1,? super T2,? extends R> combinator);
 * <p>
 * => Combine two sources via a zipper function, delaying errors from any source until both sources have terminated.
 * <p>
 * => Kết hợp hai nguồn thông qua một hàm zipper, trì hoãn lỗi từ bất kỳ nguồn nào cho đến khi cả hai nguồn đều đã kết thúc.
 * <p>
 * <p>
 * <p>
 * static <R> Mono<R> zipDelayError(Iterable<? extends Mono<?>> monos,
 * Function<? super Object[], ? extends R> combinator)
 * <p>
 * => Combine the values from this mono and another into a new combined mono using a combinator function.
 * <p>
 * => Kết hợp các giá trị từ mono này và mono khác thành một mono kết hợp mới bằng cách sử dụng một hàm tổ hợp.
 */
public class zipDelayError {
    public static void main(String[] args) {
        /**
         * static <T1,T2,R> Mono<R> zipDelayError(Publisher<? extends T1> source1,
         *                                       Publisher<? extends T2> source2,
         *                                      BiFunction<? super T1,? super T2,? extends R> combinator);
         * => Combine two sources via a zipper function, delaying errors from any source until both sources have terminated.
         * => Kết hợp hai nguồn thông qua một hàm zipper, trì hoãn lỗi từ bất kỳ nguồn nào cho đến khi cả hai nguồn đều đã kết thúc.
         * */
        Mono<String> mono1 = Mono.just("A");
        Mono<String> mono2 = Mono.error(new RuntimeException("Ops!"));

//        Function<Object[], String> combinator = objects -> {
//            StringBuilder sb = new StringBuilder();
//            for (Object obj : objects) {
//                sb.append(obj.toString()).append(" ");
//            }
//            return sb.toString().trim();
//        };

        // Hàm tổ hợp để nối các giá trị thành một chuỗi
        Function<Object[], String> combinator = objects -> Arrays.stream(objects)
                .map(Object::toString)
                .collect(Collectors.joining(" "))
                .trim();

        // Sử dụng zipDelayError để kết hợp các Mono và xử lý lỗi một cách linh hoạt
        Mono<String> zippedMono = Mono.zipDelayError(combinator, mono1, mono2);

        // Đăng ký và xử lý kết quả
        zippedMono.subscribe(
                result -> System.out.println("Result: " + result), // Result: Hello oops!
                error -> System.err.println("Error: " + error.getMessage()) // Error: Oops!
        );


        /**
         * static <R> Mono<R> zipDelayError(Iterable<? extends Mono<?>> monos,
         *                                  Function<? super Object[], ? extends R> combinator)
         * => Combine the values from this mono and another into a new combined mono using a combinator function.
         * => Kết hợp các giá trị từ mono này và mono khác thành một mono kết hợp mới bằng cách sử dụng một hàm tổ hợp.
         * */
        Mono<String> mono11 = Mono.just("A");
        Mono<String> mono22 = Mono.error(new RuntimeException("Oops!"));

        Iterable<Mono<?>> monos = List.of(mono11, mono22);

        // Hàm tổ hợp để nối các giá trị thành một chuỗi
        Function<Object[], String> combinator1 = objects -> Arrays.stream(objects)
                .map(Object::toString)
                .collect(Collectors.joining(" "))
                .trim();

        // Sử dụng zipDelayError với Iterable để kết hợp các Mono và xử lý lỗi một cách linh hoạt
        Mono<String> zippedMono1 = Mono.zipDelayError(monos, combinator1);

        // Đăng ký và xử lý kết quả
        zippedMono1.subscribe(
                result -> System.out.println("Result: " + result), // Result: A oops!
                error -> System.err.println("Error: " + error.getMessage()) // Error: Oops!
        );

        // ...
    }
}
