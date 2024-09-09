package fastcampus.nio.outputStream;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;

@Slf4j
public class ByteArrayOutputStreamExample {

    public static void main(String[] args) {
        try(var baos = new ByteArrayOutputStream()) {
            baos.write(1);
            baos.write(2);
            baos.write(3);
            baos.write(4);
            baos.write(5);

            var bytes = baos.toByteArray();
            log.info("bytes = {}", bytes);
        }catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
