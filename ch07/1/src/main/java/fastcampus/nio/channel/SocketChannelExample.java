package fastcampus.nio.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Slf4j
public class SocketChannelExample {

    public static void main(String[] args) {
        log.info("start main");

        try(var socketChannel = SocketChannel.open()){
            var address = new InetSocketAddress("127.0.0.1", 8080);
            var connected = socketChannel.connect(address);

            log.info("connected = {}", connected);

            String request = "I am client";
            ByteBuffer requestBuffer = ByteBuffer.wrap(request.getBytes());
            socketChannel.write(requestBuffer);
            requestBuffer.clear();

            ByteBuffer responseBuffer = ByteBuffer.allocateDirect(1024);
            while(socketChannel.read(responseBuffer) > 0){
                responseBuffer.flip();
                log.info("responseBuffer = {}", responseBuffer);
                responseBuffer.clear();
            }


        }catch (IOException e){

        }
    }
}
