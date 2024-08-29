package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenAcceptExample {

    public static void main(String[] args) throws InterruptedException {
        log.info("start main");

        CompletionStage<Integer> stage = Helper.finishedStage();

        stage.thenAccept(i -> {
            log.info("{} in thenAccept1", i);
        }).thenAccept(i -> {
            log.info("{} in thenAccept2", i);
        });

        log.info("end main");

        Thread.sleep(100);
    }
}
