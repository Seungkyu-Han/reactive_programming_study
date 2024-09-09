package fastcampus.reactor.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TcpEventHandler implements EventHandler{

    private final Selector selector;
    private final SocketChannel clientSocketChannel;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @SneakyThrows
    public TcpEventHandler(Selector selector, SocketChannel clientSocketChannel) {
        this.selector = selector;
        this.clientSocketChannel = clientSocketChannel;
        this.clientSocketChannel.configureBlocking(false);
        clientSocketChannel.register(selector, SelectionKey.OP_READ).attach(this);
    }

    @Override
    public void handle() {
        sendResponse(this.clientSocketChannel, handleRequest(this.clientSocketChannel));
    }


    @SneakyThrows
    private static String handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        clientSocket.read(requestByteBuffer);

        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request: {}", requestBody);

        return requestBody;
    }

    @SneakyThrows
    public void sendResponse(SocketChannel clientSocket, String requestBody) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10);

                String content = "received: " + requestBody;
                ByteBuffer responeByteBuffer = ByteBuffer.wrap(content.getBytes());
                clientSocket.write(responeByteBuffer);
                clientSocket.close();
            } catch (Exception e) { }
        }, executorService);
    }
}
