package fastcampus.reactivestreams.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Future;

public class SimpleHotPublisher implements Flow.Publisher<Integer> {
    private final ExecutorService publisherExecutor = Executors.newSingleThreadExecutor();
    private final Future<Void> task;
    private final List<Integer> numbers = new ArrayList<>();
    private List<SimpleHotSubscription> subscriptions = new ArrayList<>();

    public SimpleHotPublisher() {
        numbers.add(1);
        task = publisherExecutor.submit(() -> {
            for (int i = 2; Thread.interrupted(); i++) {
                numbers.add(i);
                subscriptions.forEach(SimpleHotSubscription::wakeup);
                Thread.sleep(100);
            }
            return null;
        });
    }

    public void shutdown(){
        this.task.cancel(true);
        publisherExecutor.shutdown();
    }


    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        var subscription = new SimpleHotSubscription(subscriber);
        subscriber.onSubscribe(subscription);
        subscriptions.add(subscription);
    }

    private class SimpleHotSubscription implements Flow.Subscription {

        private int offset;
        private int requiredOffset;
        private final Flow.Subscriber<? super Integer> subscriber;
        private final ExecutorService subscriptionExecutorService = Executors.newSingleThreadExecutor();

        public SimpleHotSubscription(Flow.Subscriber<? super Integer> subscriber) {
            this.offset = numbers.size() - 1;
            this.requiredOffset = numbers.size() - 1;
            this.subscriber = subscriber;
        }


        @Override
        public void request(long n) {
            requiredOffset += n;

            subscriptionExecutorService.submit(() -> {
                while(offset < requiredOffset && offset < numbers.size()) {
                    var item = numbers.get(offset);
                    subscriber.onNext(item);
                    offset ++;
                }
            });
        }

        @Override
        public void cancel() {
            this.subscriber.onComplete();
            if(subscriptions.contains(this)) subscriptions.remove(this);
            subscriptionExecutorService.shutdown();
        }

        public void wakeup() {
            subscriptionExecutorService.submit(() -> {
                while(offset < requiredOffset && offset < numbers.size()) {
                    var item = numbers.get(offset);
                    subscriber.onNext(item);
                    offset ++;
                }
            });
        }
    }
}
