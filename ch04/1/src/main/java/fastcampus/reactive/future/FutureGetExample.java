package fastcampus.reactive.future;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureGetExample {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        Future<Integer> future = FutureHelper.getFuture();
        assert !future.isDone();
        assert !future.isCancelled();

        var result = future.get();
        assert result.equals(1);
        assert future.isDone();
        assert !future.isCancelled();
    }
}