package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompletionStageExceptionallyExample {

    public static void main(String[] args) throws InterruptedException {
        Helper.completedStage()
                .thenApplyAsync(i -> {
                    log.info("in thenApplyAsync");
                    return i / 0;
                })
                .exceptionally(e -> {
                    log.info("{} in exceptionally", e.getMessage());
                    return 0;
                }).thenAccept(i -> {
                    log.info("{} in thenAcceptAsync", i);
                });

        Thread.sleep(1000);
    }
}
