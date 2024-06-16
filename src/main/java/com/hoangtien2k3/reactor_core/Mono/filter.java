package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * @method:
 * <p>Mono<T>	filter(Predicate<? super T> tester)
 * <p>=> If this Mono is valued, test the result and replay it if predicate returns true.
 * <p>=> Nếu Mono này được đánh giá, hãy kiểm tra kết quả và phát lại nó nếu predicate trả về true.
 * <p>
 * <p>
 * @filter:
 * <p>- Sử dụng filter khi điều kiện lọc của bạn có thể được kiểm tra ngay lập tức mà không cần thực hiện các tác vụ không đồng bộ.
 * <p>Các điều kiện này thường đơn giản và không đòi hỏi thời gian xử lý lâu.
 */
public class filter {
    public static void main(String[] args) {
        // filter(Predicate<? super T> predicate)
        // Predicate: boolean test(T t)
        // filter: loc cac phan

        // Tạo một Mono chứa số nguyên 4
        Mono<Integer> mono = Mono.just(4);

        // Áp dụng bộ lọc để chỉ giữ lại số chẵn
        Mono<Integer> filteredMono = mono.filter(number -> number % 2 == 0);

        // Đăng ký và in giá trị nếu có
        filteredMono.subscribe(
                value -> System.out.println("Giá trị đã lọc: " + value),
                error -> System.err.println("Lỗi: " + error),
                () -> System.out.println("Hoàn thành")
        );

    }
}
