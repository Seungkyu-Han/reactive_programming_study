package fastcampus.reactor.reactor;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@RequiredArgsConstructor
public class Acceptor implements EventHandler {

    private final Selector selector;

    private final ServerSocketChannel serverSocketChannel;

    @SneakyThrows
    @Override
    public void handle() {
        SocketChannel clientSocket = serverSocketChannel.accept();

        new TcpEventHandler(selector, clientSocket);
    }
}
