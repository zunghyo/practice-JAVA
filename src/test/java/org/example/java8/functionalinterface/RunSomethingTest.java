package org.example.java8.functionalinterface;

import org.example.java8.functionalinterface.RunSomething;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunSomethingTest {

    @Test
    @DisplayName("함수형 인터페이스 테스트")
    void test1(){

        //함수형 인터페이스 내부 클래스 구현
        RunSomething runSomething = new RunSomething() {
            @Override
            public int doIt(int number) {
                return number+10;
            }
        };
        runSomething.doIt(10);

        //람다식으로 변경
        RunSomething runSomething2 = (number) -> number + 20;
        System.out.println(runSomething2.doIt(10));;
        System.out.println(runSomething2.doIt(10));;

        //순수하지 않은 함수 -> 함수 외부에 있는 값을 사용
        int baseNumber = 30;
        RunSomething runSomething3 = (number) -> number + baseNumber;
        System.out.println(runSomething2.doIt(10));;
        System.out.println(runSomething2.doIt(10));;

    }


}
