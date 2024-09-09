package fastcampus.nio.channel;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@Slf4j
public class SocketChannelNonBlockingConnectOnlyExample {

    public static void main(String[] args) {
        try(var socketChannel = SocketChannel.open()){
            var address = new InetSocketAddress("127.0.0.1", 8080);
            socketChannel.configureBlocking(false);
            var connected = socketChannel.connect(address);
            assert !connected;
        }catch (Exception e){

        }
    }
}
