package fastcampus.reactor.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleObserver<T> implements Observer<T> {

    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        log.info("onSubscribe");
        this.disposable = d;
    }

    @Override
    public void onNext(@NonNull Object o) {
        log.info("item: {}", o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        log.error("onError: {}", e.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
    }
}
