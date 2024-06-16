package com.hoangtien2k3.reactor_core.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * method:
 *         static <T> Mono<T> log()
 *         Mono<T> log(Logger logger)
 *         Mono<T> log(Logger logger, Level level, boolean showOperatorLine, SignalType... options)
 *         Mono<T> log(String category)
 *         Mono<T> log(String category, Level level, boolean showOperatorLine, SignalType... options)
 *         Mono<T> log(String category, Level level, SignalType... options)
 *
 *        => Log values, errors and completion signal.
 *        => Đăng nhập giá trị, lỗi và tín hiệu hoàn thành.
 * */
public class log {
    public static void main(String[] args) {

        // static <T> Mono<T> log()
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .log();

        mono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );


        // Mono<T> log(Logger logger)
//        final Logger logger = LoggerFactory.getLogger(log.class);
//        Mono<String> monoLoger = Mono.just("Hello, Reactor!")
//                .log(logger);
//
//        mono.subscribe(
//                value -> System.out.println("Received: " + value),
//                error -> System.err.println("Error: " + error),
//                () -> System.out.println("Completed")
//        );

    }
}
