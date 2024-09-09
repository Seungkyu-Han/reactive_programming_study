package fastcampus.nio.inputStream;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
public class ByteArrayInputStreamAllExample {

    public static void main(String[] args) {
        var bytes = new byte[]{100, 101, 102, 103, 104};

        try(var bias = new ByteArrayInputStream(bytes)) {
            var values = bias.readAllBytes();
            log.info("values: {}", values);
        }catch (IOException io){
            log.error(io.getMessage(), io);
        }
    }
}
