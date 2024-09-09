package fastcampus.nio.inputStream;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;

@Slf4j
public class FileInputStreamExample {

    public static void main(String[] args) {
        var file = new File(FileInputStreamExample.class.getClassLoader().getResource("data.txt").getFile());

        try(var fis = new FileInputStream(file)) {
            var value = 0;

            while((value = fis.read()) != -1) {
                log.info("value: {}", (char)value);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
