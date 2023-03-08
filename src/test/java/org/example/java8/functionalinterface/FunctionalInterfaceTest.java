package org.example.java8.functionalinterface;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

@DisplayName("자바에서 제공하는 함수형 인터페이스")
class FunctionalInterfaceTest {

    @Test
    @DisplayName("Function Test")
    void test1(){
        FunctionPlus10 functionPlus10 = new FunctionPlus10();
        System.out.println(functionPlus10.apply(100));
    }

    @Test
    @DisplayName("Function Lambda Test")
    void test2(){
        Function<Integer, Integer> plus20 = (i) -> i + 20;
        System.out.println(plus20.apply(100));
    }

    @Test
    @DisplayName("Function compose Test")
    void test3(){
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        System.out.println(plus10.compose(multiply2).apply(2));
        System.out.println(plus10.andThen(multiply2).apply(2));
    }

    @Test
    @DisplayName("Consumer Test")
    void test4(){
        ConsumerPrint consumerPrint = new ConsumerPrint();
        consumerPrint.accept("HEllO ?");
    }

}