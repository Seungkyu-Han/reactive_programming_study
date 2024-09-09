package fastcampus.nio.outputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ServerSocketOutputStreamExample {

    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();

        byte[] buffer = new byte[1024];
        clientSocket.getInputStream().read(buffer);

        var outputStream = clientSocket.getOutputStream();
        var bos = new BufferedOutputStream(outputStream);
        var bytes = "Hello World!".getBytes();
        bos.write(bytes);
        bos.flush();

        clientSocket.close();

        serverSocket.close();
    }
}
