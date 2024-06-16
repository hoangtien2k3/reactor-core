package com.hoangtien2k3.reactor_core.Mono;

import reactor.core.publisher.Mono;

/**
 * method:
 *      static <T> Mono<T>	fromRunnable(Runnable runnable)
 *
 *      => Create a Mono that completes empty once the provided Runnable has been executed.
 *      => Tạo một Mono hoàn thành trống sau khi Runnable được cung cấp đã được thực thi.
 *
 *  note:
 *      + sử dụng để tạo một Mono từ một Runnable.
 *      + Đây là một cách để biểu diễn các hành động không trả về giá trị nhưng có thể làm việc bất đồng bộ, và bắt kết quả
 *
 *      + Thực hiện hành động bất đồng bộ: fromRunnable cho phép bạn thực hiện một Runnable mà không cần chờ đợi đồng bộ.
 *      + Không có kết quả trả về: Runnable không trả về bất kỳ giá trị nào, nên Mono tạo ra từ fromRunnable sẽ không có giá trị dữ liệu được phát ra.
 *      + Kết thúc với onComplete(): Sau khi Runnable hoàn thành hành động của nó, Mono sẽ phát ra onComplete() để thông báo là đã hoàn thành.
 *
 * */
public class fromRunnable {
    public static void main(String[] args) {
        // Định nghĩa một Runnable để thực hiện công việc
        // Thực hiện công việc bất đồng bộ, ví dụ: lưu dữ liệu vào cơ sở dữ liệu
        Runnable task = fromRunnable::saveDataToDatabase;

        // Tạo Mono từ Runnable
        Mono<Void> mono = Mono.fromRunnable(task);

        // Đăng ký Subscriber để nhận thông báo khi hoàn tất công việc
        mono.subscribe(
                result -> System.out.println("Công việc đã hoàn tất."),
                error -> System.err.println("Đã xảy ra lỗi: " + error.getMessage())
        );
    }

    // Phương thức giả định thực hiện công việc bất đồng bộ
    private static void saveDataToDatabase() {
        // Giả lập thực hiện lưu dữ liệu mất thời gian
        System.out.println("Đang lưu dữ liệu vào cơ sở dữ liệu...");
        try {
            Thread.sleep(2000); // Giả định thời gian lưu dữ liệu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Đã lưu dữ liệu vào cơ sở dữ liệu.");
    }
}
