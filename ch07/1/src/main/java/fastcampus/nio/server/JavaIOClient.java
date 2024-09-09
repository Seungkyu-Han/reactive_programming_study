package fastcampus.nio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class JavaIOClient {

    public static void main(String[] args) {

        log.info("start main");
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress("127.0.0.1", 8080));

            OutputStream outputStream = socket.getOutputStream();
            String requestBody = "This is client";
            outputStream.write(requestBody.getBytes());
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            byte[] responseBytes = new byte[1024];
            inputStream.read(responseBytes);
            log.info("response: {}", new String(responseBytes).trim());

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
