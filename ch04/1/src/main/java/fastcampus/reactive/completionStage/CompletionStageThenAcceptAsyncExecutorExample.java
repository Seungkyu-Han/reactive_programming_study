package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;

@Slf4j
public class CompletionStageThenAcceptAsyncExecutorExample {

    public static void main(String[] args) throws InterruptedException {
        var single = Executors.newSingleThreadExecutor();
        var fixed = Executors.newFixedThreadPool(10);

        log.info("start main");
        CompletionStage<Integer> stage = Helper.completedStage();

        stage.thenAcceptAsync(i -> log.info("{} in thenAcceptAsync1", i), fixed)
                .thenAcceptAsync(i -> log.info("{} in thenAcceptAsync2", i), single);
        Thread.sleep(200);

        single.shutdown();
        fixed.shutdown();
    }
}
