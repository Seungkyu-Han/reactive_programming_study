package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenAcceptRunningExample {

    public static void main(String[] args) throws InterruptedException {
        log.info("start main");

        CompletionStage<Integer> stage = Helper.runningStage();

        stage.thenAccept(i -> log.info("{} in thenAccept1", i))
                .thenAccept(i -> log.info("{} in thenAccept2", i));

        Thread.sleep(2000);
    }
}
