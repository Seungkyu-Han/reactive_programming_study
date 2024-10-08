package fastcampus.reactor.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;

import java.util.List;

@Slf4j
public class FlowableExample {

    public static void main(String[] args) {
        log.info("start main");

        getItems()
                .subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));

        log.info("finish main");
    }

    private static Flowable<Integer> getItems(){
        return Flowable.fromIterable(List.of(1, 2, 3, 4, 5));
    }
}
