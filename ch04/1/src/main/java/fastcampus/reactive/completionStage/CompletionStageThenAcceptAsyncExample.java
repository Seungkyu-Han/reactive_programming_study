package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenAcceptAsyncExample {

    public static void main(String[] args) throws InterruptedException {

        log.info("start main");

        CompletionStage<Integer> stage = Helper.finishedStage();

        stage.thenAcceptAsync(i -> {
            log.info("{} in then AcceptAsync1", i);
        }).thenAcceptAsync(i -> {
            log.info("{} in then AcceptAsync2", i);
        });

        log.info("after thenAccept");

        Thread.sleep(100);
    }
}
