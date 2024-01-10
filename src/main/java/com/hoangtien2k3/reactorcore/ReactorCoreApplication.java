package com.hoangtien2k3.reactorcore;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactorCoreApplication {

	public static void main(String[] args) throws InterruptedException {

		// Synchronous
		Flux.just(5, 10, 23, 25).log()
				.flatMap(n -> Flux.just(n * 2))
				.subscribe(System.out::println);


		System.out.println("_".repeat(150));


		// Asynchronous with subscribeOn
		Flux.just(5, 10, 23, 25).log()
				.flatMap(n -> Flux.just(n * 2))
				.subscribeOn(Schedulers.boundedElastic())
				.subscribe(value -> System.out.println(Thread.currentThread().getName() + " : " + value));
		Thread.sleep(100);


		System.out.println("_".repeat(150));


		// Asynchronous with publishOn
		Flux.just(5, 10, 23, 25).log()
				.flatMap(n -> Flux.just(n * 2))
				.publishOn(Schedulers.boundedElastic())
				.subscribe(value -> System.out.println(Thread.currentThread().getName() + " : " + value));

		Thread.sleep(100);


		System.out.println("_".repeat(150));


		// Asynchronous with publishOn
		Flux.just(5, 10, 23, 25)
				.log()
				.flatMap(n -> Flux.just(n * 2))
				.subscribeOn(Schedulers.boundedElastic())
				.subscribe(
						value -> System.out.println("onNext: " + value),
						error -> System.err.println("onError: " + error),
						() -> System.out.println("onComplete")
				);


	}

}
