package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenComposeAsyncExample {

    public static void main(String[] args) throws InterruptedException {
        CompletionStage<Integer> stage = Helper.completedStage();

        stage.thenComposeAsync(value -> {
            var next = Helper.addOne(value);
            log.info("in thenComposeAsync1: {}", next);
            return next;
        }).thenComposeAsync(value -> {
            var next = Helper.addResultPrefix(value);
            log.info("in thenComposeAsync2: {}", next);
            return next;
        }).thenAcceptAsync(value -> {log.info("{} in thenAcceptAsync", value);
        });

        Thread.sleep(1000);
    }
}
