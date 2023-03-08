package org.example.java8.methodreference;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferenceTest {

    @Test
    @DisplayName("스태틱 메소즈 참조")
    void test(){
        Greeting greeting = new Greeting();
        UnaryOperator<String> hi1 = (s) -> "hi " + s;
        hi1.apply("chas");
        UnaryOperator<String> hi2 = Greeting::hi;
        hi1.apply("chas");
    }

    @Test
    @DisplayName("특정 객체의 인스턴스 메소드 참조")
    void test0(){
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
    }

    @Test
    @DisplayName("생성자 참조")
    void test1(){
        Function<String, Greeting> boriGreeting = Greeting::new;
        Greeting hahah = boriGreeting.apply("chas");
        System.out.println(hahah.getName());

        Supplier<Greeting> chasGreeting = Greeting::new;
    }

    @Test
    @DisplayName("임의의 객체의 인스턴스 메소드 참조")
    void test2(){
        String[] names = {"aaa","chas","borii"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }

}
