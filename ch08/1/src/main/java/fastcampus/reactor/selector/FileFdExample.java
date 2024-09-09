package fastcampus.reactor.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.lang.reflect.Field;

@Slf4j
public class FileFdExample {

    public static void main(String[] args) {
        var file = new File(FileFdExample.class
                .getClassLoader()
                .getResource("hello.txt").getFile());

        try(var fis = new FileInputStream(file)) {
            FileDescriptor fd = fis.getFD();
            Field field = FileDescriptor.class.getDeclaredField("fd");
            field.setAccessible(true);
            long value = field.getLong(fd);
            log.info("fd = {}", value);
        }catch (Exception e) {
            log.error("error");
        }
    }
}
