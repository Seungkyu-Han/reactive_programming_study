package fastcampus.reactive.future;

import lombok.SneakyThrows;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureGetTimeoutExample {

    @SneakyThrows
    public static void main(String[] args) {
        Future<Integer> future = FutureHelper.getFutureCompleteAfter1s();
        var result = future.get(1500, TimeUnit.MILLISECONDS);

        assert result.equals(1);

        Future<Integer> futureToTimeOut = FutureHelper.getFutureCompleteAfter1s();
        Exception exception = null;

        try{
            futureToTimeOut.get(500, TimeUnit.MILLISECONDS);
        }catch (Exception e) {
            exception = e;
        }
        assert exception != null;
    }
}
