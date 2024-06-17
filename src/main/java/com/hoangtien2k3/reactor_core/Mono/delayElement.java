package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @method: Mono<T>	delayElement(Duration delay)
 * Mono<T>	delayElement(Duration delay, Scheduler timer)
 * <p>
 * => Create a Mono which delays an onNext signal by a given duration on a default Scheduler and completes.
 * => Tạo một Mono mà trì hoãn tín hiệu onNext bằng một khoảng thời gian cụ thể trên một Scheduler mặc định và hoàn thành.
 * @note: + Mono.delayElement trì hoãn việc phát ra giá trị của Mono sau khi giá trị đã được tạo ra ngay lập tức. Giá trị sẽ được phát ra sau khi thời gian trì hoãn kết thúc.
 * <p>
 * => (hiểu đơn giản là: Mono sẽ được tạo ra ngay lập tức, nhưng nó bị trì hoàn lại việc phát ra dữ liệu sau một khoảng thời gian)
 */
public class delayElement {
    public static void main(String[] args) {
        // Sử dụng delayElement để tạo Mono với khoảng thời gian trì hoãn:
        Mono.just("Hello, Reactor!")
                .delayElement(Duration.ofSeconds(2))
                .subscribe(System.out::println);

        // Giữ chương trình chạy đủ lâu để thấy kết quả
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
