package fastcampus.nio.inputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ServerSocketInputStreamExample {

    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();

        var inputStream = clientSocket.getInputStream();

        try(BufferedInputStream bis = new BufferedInputStream(inputStream)) {
            byte[] buffer = new byte[1024];

            int bytesRead = bis.read(buffer);

            String inputLine = new String(buffer, 0, bytesRead);

            log.info("bytes: {}", inputLine);
        }

        clientSocket.close();
        serverSocket.close();
    }
}
