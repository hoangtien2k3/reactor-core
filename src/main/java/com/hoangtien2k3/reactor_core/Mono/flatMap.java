package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @method: <R> Mono<R>	flatMap(Function<? super T,? extends Mono<? extends R>> transformer)
 * <p>
 * => Transform the item emitted by this Mono asynchronously, returning the value emitted by another Mono.
 * <p>
 * => Chuyển đổi mục được phát ra bởi Mono này một cách không đồng bộ, trả về giá trị được phát ra bởi một Mono khác.
 */
public class flatMap {
    public static void main(String[] args) {
        // Tạo một Mono chứa userId
        Mono<String> userIdMono = Mono.just("user123");

        // Sử dụng flatMap để biến đổi userId và gọi một dịch vụ không đồng bộ để lấy thông tin người dùng
        Mono<String> userOrdersMono = userIdMono
                .flatMap(userId -> getUserDetails(userId).flatMap(flatMap::getUserOrders));

        // Subscribe vào Mono để nhận danh sách đơn hàng của người dùng
        userOrdersMono.subscribe(
                orders -> System.out.println("User orders: " + orders), // Xử lý danh sách đơn hàng
                error -> System.err.println("Error: " + error),         // Xử lý lỗi
                () -> System.out.println("Completed")                  // Hoàn thành
        );
    }

    // Giả lập một dịch vụ không đồng bộ để lấy thông tin người dùng dựa trên userId
    public static Mono<String> getUserDetails(String userId) {
        // Trong thực tế, đây có thể là một gọi API hoặc truy vấn cơ sở dữ liệu không đồng bộ
        return Mono.just("User details for " + userId)
                .delayElement(Duration.ofSeconds(1)); // Giả lập trễ 1 giây
    }

    // Giả lập một dịch vụ không đồng bộ để lấy danh sách đơn hàng của người dùng
    public static Mono<String> getUserOrders(String userDetails) {
        // Trong thực tế, đây có thể là một gọi API hoặc truy vấn cơ sở dữ liệu không đồng bộ
        return Mono.just("Orders for " + userDetails)
                .delayElement(Duration.ofSeconds(1)); // Giả lập trễ 1 giây
    }
}
