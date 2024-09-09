package fastcampus.nio.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JavaNIOBlockingServer {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));

            while(true){
                SocketChannel clientSocket = serverSocketChannel.accept();

                while(clientSocket != null){
                    Thread.sleep(1000);
                }

                ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
                clientSocket.read(requestByteBuffer);
                requestByteBuffer.flip();
                String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
                log.info("request: {}", requestBody);

                ByteBuffer responseBody = ByteBuffer.wrap("This is Server".getBytes());
                clientSocket.write(responseBody);

            }
        }
    }
}
