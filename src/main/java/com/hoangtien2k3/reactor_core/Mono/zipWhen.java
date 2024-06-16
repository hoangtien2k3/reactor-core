package com.hoangtien2k3.reactor_core.Mono;


import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.function.Function;

/**
 * @method: <T2> Mono<Tuple2<T,T2>>	zipWhen(Function<T,Mono<? extends T2>> rightGenerator)
 * <p>
 * => Wait for the result from this mono, use it to create a second mono via the provided rightGenerator function and combine both results into a Tuple2.
 * <p>
 * => Chờ kết quả từ mono này, sử dụng nó để tạo một mono thứ hai thông qua hàm rightGenerator được cung cấp và kết hợp cả hai kết quả thành một Tuple2.
 */
public class zipWhen {
    private static final Mono<Integer> sourceMono = Mono.just(123456789);
    private static final Function<Integer, Mono<? extends String>> rightGenerator = intValue -> Mono.just("Value: " + intValue);

    public static void main(String[] args) {
        Mono<Tuple2<Integer, String>> zippedMono = sourceMono.zipWhen(rightGenerator);

        zippedMono.subscribe(
                tuple -> System.out.println("Result: " + tuple.getT1() + ", " + tuple.getT2()),
                System.err::println
        );
    }
}
