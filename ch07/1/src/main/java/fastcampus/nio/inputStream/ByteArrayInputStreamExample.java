package fastcampus.nio.inputStream;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
public class ByteArrayInputStreamExample {

    public static void main(String[] args) {
        var bytes = new byte[]{100, 101, 102, 103, 104, 105};

        try(var bais = new ByteArrayInputStream(bytes)){
            var value = 0;

            while((value = bais.read()) != -1) {
                log.info("value: {}", value);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
