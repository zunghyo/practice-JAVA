package org.example.java8.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaTest {
    private int baseNumber = 10;

    // 로컬 클래스
    class LocalClass {
        void printBaseNumber(Integer baseNumber){
            System.out.println(baseNumber);
        }
    }

    // 익명 클래스
    Consumer<Integer> integerConsumer = new Consumer<Integer>(){
        @Override
        public void accept(Integer baseNumber) {
            System.out.println(baseNumber);
        }
    };

    // 람다
    IntConsumer printInt = (baseNumber) -> {
        System.out.println(baseNumber);
    };

    // effective final
    private void scopeLambda(){
        // int baseNumber = 10;

        // 람다
        IntConsumer printInt = (baseNumber) -> {
            System.out.println(baseNumber);
        };
    }

    @Test
    void test(){
        new LocalClass().printBaseNumber(11);
        integerConsumer.accept(11);
        printInt.accept(11);
    }

}
