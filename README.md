## REACTOR-CORE IN JAVA

Docs: https://projectreactor.io/docs/core/release/reference/

Flux:

- [https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)

Mono:

- [https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)

## Khan Niem:

## Reactor Core:

## Publisher

- Publisher là một nguồn dữ liệu mà từ đó các phần tử dữ liệu (items) được phát ra. Nó định nghĩa một phương thức duy
  nhất:

```java
public interface Publisher<T> {
    void subscribe(Subscriber<? super T> s);
}
```

## Subscriber:

- Subscriber là một người tiêu dùng dữ liệu, nó sẽ nhận các phần tử dữ liệu từ Publisher thông qua phương thức onNext().

```java
public interface Subscriber<T> {
    void onSubscribe(Subscription s);

    void onNext(T t);

    void onError(Throwable t);

    void onComplete();
}
```

Trong đó:

- [onSubscribe(Subscription s)](): Được gọi khi Subscriber đăng ký vào Publisher. Subscriber sử dụng đối tượng
  Subscription để yêu cầu hoặc hủy nhận dữ liệu.
- [onNext(T t)](): Được gọi để xử lý mỗi phần tử dữ liệu được phát ra.
- [onError(Throwable t)](): Được gọi khi xảy ra lỗi trong quá trình phát dữ liệu.
- [onComplete()](): Được gọi khi Publisher đã phát ra tất cả các phần tử và hoàn thành.

## Subscription:

- Subscription đại diện cho mối quan hệ giữa Publisher và Subscriber. Nó cung cấp hai phương thức chính:

```java
public interface Subscription {
    void request(long n);

    void cancel();
}
```

Trong đó:

- [request(long n)](): Yêu cầu Publisher phát ra n phần tử tiếp theo cho Subscriber.
- [cancel()](): Hủy đăng ký, không nhận thêm dữ liệu nữa.

## Processor:

- Processor là một thành phần trung gian, vừa là Publisher vừa là Subscriber. Nó có thể được sử dụng để xử lý dữ liệu
  giữa nguồn dữ liệu ban đầu và người tiêu thụ.

```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
```

## Ví dụ về Dòng Chảy của Dữ Liệu:

- [Publisher]() tạo ra dữ liệu.
- [Subscriber]() đăng ký (subscribe) vào Publisher.
- [Publisher]() gửi một Subscription tới Subscriber thông qua phương thức onSubscribe.
- [Subscriber]() yêu cầu dữ liệu từ Publisher bằng cách gọi request trên Subscription.
- [Publisher]() gửi dữ liệu tới Subscriber qua phương thức onNext.
- `Khi tất cả dữ liệu đã được gửi`, Publisher gọi onComplete để báo hiệu kết thúc.
- `Nếu có lỗi xảy ra`, Publisher gọi onError.