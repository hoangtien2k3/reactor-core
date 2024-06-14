package com.hoangtien2k3.reactor_core.Mono;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Data
@AllArgsConstructor
class User {
    private String name;
}

@Data
@AllArgsConstructor
class Account {
    private String accountId;
}

@Data
@AllArgsConstructor
class Address {
    private String city;
}

@Data
@AllArgsConstructor
class UserProfile {
    private String name;
    private String accountId;
    private String city;
}


/**
 * method:
 *      static <R> Mono<R>	zip(Function<? super Object[],? extends R> combinator, Mono<?>... monos)
 *      => Aggregate given monos into a new Mono that will be fulfilled when all of the given Monos have produced an item, aggregating their values according to the provided combinator function.
 *      => Tổng hợp các monos đã cho thành một Mono mới sẽ được thực hiện khi tất cả các Mono đã cho đã tạo ra một mục, tổng hợp giá trị của chúng theo hàm tổ hợp đã cung cấp.
 *
 *
 *
 * */
public class zip {
    public static void main(String[] args) {

        /**
         * method:
         *      static <R> Mono<R> zip(Function<? super Object[], ? extends R> combinator, Mono<?>... monos)
         */
        Mono<User> userMono = Mono.just(new User("John Doe"));
        Mono<Account> accountMono = Mono.just(new Account("123-456-789"));
        Mono<Address> addressMono = Mono.just(new Address("New York"));

        // Hàm tổ hợp kết hợp các giá trị từ các Mono thành một UserProfile
        Function<Object[], UserProfile> combinatorDemo = array -> {
            User user = (User) array[0];
            Account account = (Account) array[1];
            Address address = (Address) array[2];
            return new UserProfile(user.getName(), account.getAccountId(), address.getCity());
        };
        Mono<UserProfile> userProfileMono = Mono.zip(combinatorDemo, userMono, accountMono, addressMono);
        userProfileMono.subscribe(userProfile -> System.out.println("UserProfile: " + userProfile));




        /**
         * method:
         *      static <R> Mono<R>	zip(Iterable<? extends Mono<?>> monos, Function<? super Object[],? extends R> combinator)
         *      => Aggregate given monos into a new Mono that will be fulfilled when all of the given Monos have produced an item, aggregating their values according to the provided combinator function.
         *      => Tổng hợp các monos đã cho thành một Mono mới sẽ được thực hiện khi tất cả các Mono đã cho đã tạo ra một mục, tổng hợp giá trị của chúng theo hàm tổ hợp đã cung cấp.
         */
        Mono<User> userMono1 = Mono.just(new User("John Doe"));
        Mono<Account> accountMono1 = Mono.just(new Account("123-456-789"));
        Mono<Address> addressMono1 = Mono.just(new Address("New York"));

        List<Mono<?>> monos1 = Arrays.asList(userMono1, accountMono1, addressMono1);

        // Hàm tổ hợp kết hợp các giá trị từ các Mono thành một UserProfile
        Function<Object[], UserProfile> combinator1 = array -> {
            User user = (User) array[0];
            Account account = (Account) array[1];
            Address address = (Address) array[2];
            return new UserProfile(user.getName(), account.getAccountId(), address.getCity());
        };

        // Kết hợp các Mono bằng cách sử dụng phương thức zip
        Mono<UserProfile> userProfileMono1 = Mono.zip(monos1, combinator1);
        userProfileMono1.subscribe(userProfile -> System.out.println("UserProfile: " + userProfile));



        /**
         * method:
         *     static <T1,T2> Mono<Tuple2<T1,T2>>	zip(Mono<? extends T1> p1, Mono<? extends T2> p2)
         *     => Combine two monos into a new {@code Mono} that will be fulfilled when both of the given Monos have produced an item, aggregating their values into a {@link Tuple2}.
         *     => Kết hợp hai monos thành một Mono mới sẽ được thực hiện khi cả hai Mono đã cho đã tạo ra một mục, tổng hợp giá trị của chúng thành một Tuple2.
         * */
        Mono<String> mono11 = Mono.just("Hello");
        Mono<Integer> mono22 = Mono.just(123);

        // Kết hợp hai Mono lại với nhau và tạo ra một Mono<Tuple2<String, Integer>>
        Mono<Tuple2<String, Integer>> zippedMono = Mono.zip(mono11, mono22);

        // Đăng ký và xử lý kết quả
        zippedMono.subscribe(tuple -> {
            String str = tuple.getT1(); // Lấy giá trị từ Mono đầu tiên
            Integer num = tuple.getT2(); // Lấy giá trị từ Mono thứ hai
            System.out.println("String: " + str + ", Integer: " + num);
        });



        /**
         * method:
         *      static <T1,T2,O> Mono<O> zip(Mono<? extends T1> p1, Mono<? extends T2> p2, BiFunction<? super T1,? super T2,? extends O> combinator)
         *      => Combine two monos into a new {@code Mono} that will be fulfilled when both of the given Monos have produced an item, applying the provided combinator to the pair of items.
         *      => Kết hợp hai monos thành một Mono mới sẽ được thực hiện khi cả hai Mono đã cho đã tạo ra một mục, áp dụng hàm tổ hợp đã cung cấp cho cặp mục.
         * */
        Mono<Integer> mono1 = Mono.just(10);
        Mono<Integer> mono2 = Mono.just(5);

        // Hàm tổ hợp để nhân hai số với nhau
        BiFunction<Integer, Integer, Integer> combinator = (num1, num2) -> num1 * num2;

        Mono<Integer> resultMono = Mono.zip(mono1, mono2, combinator);
        resultMono.subscribe(result -> System.out.println("Result: " + result)); // Result: 50



        /**
         * method:
         *      static <T1,T2,T3> Mono<Tuple3<T1,T2,T3>> zip(Mono<? extends T1> p1, Mono<? extends T2> p2, Mono<? extends T3> p3)
         *      => Merge given monos into a new Mono that will be fulfilled when all of the given Monos have produced an item, aggregating their values into a Tuple3.
         *      => Kết hợp các monos đã cho thành một Mono mới sẽ được thực hiện khi tất cả các Mono đã cho đã tạo ra một mục, tổng hợp giá trị của chúng thành một Tuple3.
         * */
        Mono<String> mono1111 = Mono.just("Hello");
        Mono<Integer> mono2222 = Mono.just(123);
        Mono<Double> mono3333 = Mono.just(3.14);

        // Kết hợp ba Mono lại với nhau và tạo ra một Mono<Tuple3<String, Integer, Double>>
        Mono<Tuple3<String, Integer, Double>> zippedMono1 = Mono.zip(mono1111, mono2222, mono3333);

        // Đăng ký và xử lý kết quả
        zippedMono1.subscribe(tuple -> {
            String str = tuple.getT1(); // Lấy giá trị từ Mono đầu tiên
            Integer num = tuple.getT2(); // Lấy giá trị từ Mono thứ hai
            Double dbl = tuple.getT3(); // Lấy giá trị từ Mono thứ ba
            System.out.println("String: " + str + ", Integer: " + num + ", Double: " + dbl);
        });



        // ...
    }
}
