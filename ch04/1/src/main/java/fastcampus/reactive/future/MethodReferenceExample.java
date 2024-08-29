package fastcampus.reactive.future;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class MethodReferenceExample {

    @Getter
    @RequiredArgsConstructor
    public static class Person{
        private final String name;

        public Boolean compareTo(Person person){
            return person.name.compareTo(name) > 0;
        }
    }

    public static void print(String name){
        System.out.println(name);
    }

    public static void main(String[] args) {
        var target = new Person("f");

        Consumer<String> staticPrint = MethodReferenceExample::print;

        Stream.of("a", "b", "g", "h")
                .map(Person::new)
                .filter(target::compareTo)
                .map(Person::getName)
                .forEach(staticPrint);
    }
}
