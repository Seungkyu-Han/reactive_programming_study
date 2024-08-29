package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenAsyncExample {

    public static void main(String[] args) throws InterruptedException {
        CompletionStage<Integer> stage = Helper.completedStage();

        stage.thenApplyAsync(value -> {
            var next = value + 1;
            return next;
        }).thenApplyAsync(value -> {
            var next = "result : " + value;
            return next;
        }).thenApplyAsync(value -> {
            var next = value.equals("result: 2");
            return next;
        }).thenAcceptAsync(value -> log.info("{}", value));

        Thread.sleep(100);
    }
}
