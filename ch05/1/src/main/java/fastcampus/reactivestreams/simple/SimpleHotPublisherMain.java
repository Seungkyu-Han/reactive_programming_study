package fastcampus.reactivestreams.simple;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleHotPublisherMain {

    @SneakyThrows
    public static void main(String[] args) {
        var publisher = new SimpleHotPublisher();

        var subscriber1 = new SimpleNamedSubscriber<>("subscriber1");
        publisher.subscribe(subscriber1);

        Thread.sleep(5000);
        subscriber1.cancel();

        var subscriber2 = new SimpleNamedSubscriber<>("subscriber2");
        var subscriber3 = new SimpleNamedSubscriber<>("subscriber3");
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);

        Thread.sleep(5000);
        subscriber2.cancel();
        subscriber3.cancel();

        var subscriber4 = new SimpleNamedSubscriber<>("subscriber4");
        publisher.subscribe(subscriber4);

        Thread.sleep(5000);
        subscriber4.cancel();

        publisher.shutdown();
    }
}
