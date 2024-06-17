package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @method: <p>static Mono<Long> delay(Duration duration)
 * <p>static Mono<Long> delay(Duration duration, Scheduler timer)
 * <p>
 * <p>=> Create a Mono which delays an onNext signal by a given duration on a default Scheduler and completes.
 * <p>=> Tạo một Mono mà trì hoãn tín hiệu onNext bằng một khoảng thời gian cụ thể trên một Scheduler mặc định và hoàn thành.
 * @note: + Mono.delay trì hoãn toàn bộ quá trình tạo và phát ra giá trị của Mono. Giá trị sẽ chỉ được tạo ra và phát ra sau khi thời gian trì hoãn kết thúc.
 * <p>
 * => (hiểu đơn giản là sau một khoảng thời gian, Mono mới được tạo và phát ra giá trị)
 */
public class delay {
    public static void main(String[] args) {
        // Sử dụng delay để tạo Mono với khoảng thời gian trì hoãn:
        Mono.delay(Duration.ofSeconds(2))
                .flatMap(time -> Mono.just("Delayed message: " + time))
                .subscribe(System.out::println);

        // Giữ chương trình chạy đủ lâu để thấy kết quả
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Ví dụ 2:
        Scheduler scheduler = Schedulers.boundedElastic();

        Mono<String> result = Mono.delay(Duration.ofSeconds(2), scheduler)
                .flatMap(time -> Mono.just("Delayed message: " + time))
                .subscribeOn(scheduler);

        result.subscribe(System.out::println);

        // Giữ chương trình chạy đủ lâu để thấy kết quả
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
