package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method:
 * <p>
 * Mono<T>	doOnNext(Consumer<? super T> onNext)
 * </p>
 *
 * <p>
 * => Add behavior triggered when the Mono emits a data successfully.
 * <p>
 * <p>
 * => Thêm hành vi được kích hoạt khi Mono phát ra dữ liệu thành công.
 * </p>
 * <p>
 * @note:
 * <p> + Đây là một hàm nhận một giá trị phát ra của Mono và thực hiện một hành động nào đó với giá trị đó.
 * <p> + Hàm này không thay đổi giá trị hoặc làm gián đoạn chuỗi Reactive.
 */
public class doOnNext {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnNext(value -> updateState(value));

        mono.subscribe(System.out::println);
    }

    private static void updateState(String value) {
        // Giả sử chúng ta có một trạng thái nào đó cần được cập nhật
        System.out.println("State updated with value: " + value);
    }
}
