package fastcampus.reactive.completionStage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenApplyExample {

    public static void main(String[] args) {
        CompletionStage<Integer> stage = Helper.completionStageAfter1s();

        stage.thenApply(i -> {
            log.info("in thenApply");
            return i + 1;
        }).thenAccept(System.out::println);
    }
}
