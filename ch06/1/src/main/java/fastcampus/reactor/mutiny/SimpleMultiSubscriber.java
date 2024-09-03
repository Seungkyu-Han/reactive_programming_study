package fastcampus.reactor.mutiny;

import io.smallrye.mutiny.subscription.MultiSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

@Slf4j
@RequiredArgsConstructor
public class SimpleMultiSubscriber<T> implements MultiSubscriber<T> {

    private final Integer count;

    @Override
    public void onItem(T t) {
        log.info("item: {}", t);
    }

    @Override
    public void onFailure(Throwable throwable) {
        log.error("failure: {}", throwable.getMessage());
    }

    @Override
    public void onCompletion() {
        log.info("completed");
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(count);
        log.info("subscribe");
    }
}
