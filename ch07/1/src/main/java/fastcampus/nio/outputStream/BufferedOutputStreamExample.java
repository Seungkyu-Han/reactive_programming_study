package fastcampus.nio.outputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class BufferedOutputStreamExample {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var file = new File(BufferedOutputStreamExample.class
                .getClassLoader()
                .getResource("test.txt")
                .getFile());

        var fos = new FileOutputStream(file);
        try (var bos = new BufferedOutputStream(fos)) {
            var content = "Hello World in buffered";

            bos.write(content.getBytes());
            bos.flush();
        }
        log.info("end main");
    }
}
