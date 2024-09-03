package fastcampus.reactor.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleCompletableObserver implements CompletableObserver {

    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        log.debug("onSubscribe");
        this.disposable = d;
    }

    @Override
    public void onComplete() {
        log.debug("onComplete");
    }

    @Override
    public void onError(@NonNull Throwable e) {
        log.error("onError: {}", e.getMessage());
    }
}
