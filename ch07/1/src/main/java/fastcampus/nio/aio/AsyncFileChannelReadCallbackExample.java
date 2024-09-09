package fastcampus.nio.aio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AsyncFileChannelReadCallbackExample {

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File(AsyncFileChannelReadCallbackExample.class
                .getClassLoader()
                .getResource("hello.txt")
                .getFile());

        var channel =AsynchronousFileChannel.open(file.toPath());
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, 0, null, new CompletionHandler<Integer, ByteBuffer>() {
            @SneakyThrows
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                buffer.flip();
                var resultString = StandardCharsets.UTF_8.decode(buffer).toString();
                log.info("result: {}", resultString);
                channel.close();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

        while(channel.isOpen()){
            log.info("Reading");
        }
    }
}
