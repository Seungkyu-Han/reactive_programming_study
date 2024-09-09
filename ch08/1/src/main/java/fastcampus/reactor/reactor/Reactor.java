package fastcampus.reactor.reactor;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Reactor implements Runnable {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    private final EventHandler acceptor;

    @SneakyThrows
    public Reactor(int port){
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));
        serverSocketChannel.configureBlocking(false);
        acceptor = new Acceptor(selector, serverSocketChannel);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT).attach(acceptor);
    }

    @Override
    public void run() {
        executorService.submit(() -> {
            while(true){
                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    dispatch(key);
                }
            }
        });
    }

    private void dispatch(SelectionKey key){
        EventHandler eventHandler = (EventHandler) key.attachment();
        if(key.isReadable() || key.isAcceptable()){
            eventHandler.handle();
        }
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
    private static void sendResponse(SocketChannel clientSocket, String requestBody) {
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