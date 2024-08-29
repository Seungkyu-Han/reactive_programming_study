package fastcampus.reactive.future;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureHelper {

    public static Future<Integer> getFuture(){
        var executor = Executors.newSingleThreadExecutor();

        try{
            return executor.submit(() -> 1);
        }finally {
            executor.shutdown();
        }
    }

    public static Future<Integer> getFutureCompleteAfter1s(){
        var executor = Executors.newSingleThreadExecutor();

        try{
            return executor.submit(() -> {
                Thread.sleep(1);
                return 1;
            });
        }finally {
            executor.shutdown();
        }
    }
}
