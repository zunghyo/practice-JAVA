package org.example.java8.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OnlineClassTest {

    private ArrayList<OnlineClass> springClasses;
    private ArrayList<OnlineClass> javaClasses;
    private List<List<OnlineClass>> events;

    @BeforeEach
    void setUp() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        events = new ArrayList<>();
        events.add(springClasses);
        events.add(javaClasses);
    }
    @Test
    @DisplayName("spring 으로 시작하는 수업")
    void test1(){
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));
    }
    @Test
    @DisplayName("close 되지 않은 수업")
    void test2(){
        springClasses.stream()
                .filter(oc -> !oc.isClosed())
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("====================================");

        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));
    }

    @Test
    @DisplayName("수업 이름만 모아서 스트림 만들기")
    void test3(){
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("두 수업 목록에 들어있는 모든 수업 아이디 출력")
    void test4(){
        events.stream()
                .flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getId()));
    }

    @Test
    @DisplayName("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만")
    void test5(){
        Stream.iterate(10, i -> i + 1 )
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("자바 수업 중에 Test가 들어있는 수업이 있는지 확인")
    void test6(){
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);
    }

    @Test
    @DisplayName("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기")
    void test7(){
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        spring.forEach(System.out::println);
    }

}