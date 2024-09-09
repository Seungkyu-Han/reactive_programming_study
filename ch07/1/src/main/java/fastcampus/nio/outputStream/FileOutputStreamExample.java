package fastcampus.nio.outputStream;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class FileOutputStreamExample {

    public static void main(String[] args) throws IOException {
        var file = new File(FileOutputStreamExample.class
                .getClassLoader()
                .getResource("test.txt")
                .getFile());

        try(var fos = new FileOutputStream(file)) {
            var context = "Hello World!";

            fos.write(context.getBytes());
            fos.flush();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
