package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenAcceptAsyncRunningExample {

    public static void main(String[] args) throws InterruptedException {
        log.info("start main");

        CompletionStage<Integer> stage = Helper.runningStage();

        stage.thenAcceptAsync(i -> log.info("{} in thenAcceptAsync1", i))
                .thenAcceptAsync(i -> log.info("{} in thenAcceptAsync2", i));

        Thread.sleep(2000);
    }
}
