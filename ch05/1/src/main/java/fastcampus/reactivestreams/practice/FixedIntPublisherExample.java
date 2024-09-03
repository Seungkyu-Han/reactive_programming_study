package fastcampus.reactivestreams.practice;

import lombok.SneakyThrows;

import java.util.concurrent.Flow;

public class FixedIntPublisherExample {
    @SneakyThrows
    public static void main(String[] args) {
        Flow.Publisher<FixedIntPublisher.Result> publisher = new FixedIntPublisher();
        Flow.Subscriber<Object> subscriber = new RequestNSubscriber<>(10);

        publisher.subscribe(subscriber);

        Thread.sleep(1000);
    }
}
