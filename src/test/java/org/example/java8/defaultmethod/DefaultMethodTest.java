package org.example.java8.defaultmethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultMethodTest {

    private ArrayList<String> name;

    @BeforeEach
    void setUp() {
        name = new ArrayList<>();
        name.add("chas");
        name.add("borii");
        name.add("jelly");

    }

    @Test
    @DisplayName("인터페이스 기본 메소드와 스태틱 메소드 테스트")
    void test() {
        DefaultFoo defaultFoo = new DefaultFoo("chasoon");
        defaultFoo.printName();
        defaultFoo.printNameUpperCase();

        Foo.printAnything();
    }

    @Test
    @DisplayName("API의 기본 메소드와 스태틱 메소드 테스트 - Iterable")
    void test1() {
        name.forEach(System.out::println);

        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println)) ;
        System.out.println("====================");
        while (spliterator1.tryAdvance(System.out::println)) ;
    }

    @Test
    @DisplayName("API의 기본 메소드와 스태틱 메소드 테스트 - Collection")
    void test2() {
        Set<String> c = name.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("B"))
                .collect(Collectors.toSet());
        System.out.println(c);

        name.removeIf(s -> s.startsWith("b"));
        name.forEach(System.out::println);

    }

    @Test
    @DisplayName("API의 기본 메소드와 스태틱 메소드 테스트 - Comparator")
    void test3() {
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());
        name.forEach(System.out::println);
    }

}
