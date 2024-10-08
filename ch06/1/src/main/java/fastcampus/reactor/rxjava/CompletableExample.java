package fastcampus.reactor.rxjava;

import io.reactivex.rxjava3.core.Completable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableExample{

    public static void main(String[] args) {
        getCompletion()
                .subscribe(new SimpleCompletableObserver());
    }

    private static Completable getCompletion(){
        return Completable.create(completableEmitter -> {
            Thread.sleep(1000);
            completableEmitter.onComplete();
        });
    }
}
