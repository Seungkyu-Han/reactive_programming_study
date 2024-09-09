package fastcampus.nio.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class JavaIOSingleServer {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        try(ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();

            byte[] requestBytes = new byte[1024];
            inputStream.read(requestBytes);
            log.info("request: {}", new String(requestBytes));

            OutputStream outputStream = clientSocket.getOutputStream();
            String response = "This is server";
            outputStream.write(response.getBytes());
            outputStream.flush();
        }

        log.info("end main");
    }
}
