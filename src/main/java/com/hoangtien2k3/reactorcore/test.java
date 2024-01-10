package com.hoangtien2k3.reactorcore;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class test {
    public static void main(String[] args) throws InterruptedException {
        Flux.just(1, 2, 3, 4, 5)
                .log()
                .map(i -> i * 2)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        value -> System.out.println(Thread.currentThread().getName() + " : " + value),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("onComplete")
                );

        // Đợi một chút để cho phép các hoạt động bất đồng bộ kết thúc.
        Thread.sleep(2000);
    }
}
