package org.example.java8.optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

public class OptionalTest {

    private ArrayList<OnlineClass> springClasses;

    @BeforeEach
    void setUp() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));
    }

    @Test
    @DisplayName("NullPointerException")
    void test1(){

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        System.out.println(studyDuration);

    }

    @Test
    @DisplayName("NullPointerException 체크하는 방법 (레거시)")
    void test2(){

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Progress progress = spring_boot.getProgress();

        // null 체크를 누락하는 경우가 발생할 수 있음
        if (progress != null) {
            System.out.println(progress.getStudyDuration());
        }
        // 에러가 발생하면 리소스를 쓰게되는 단점
        if (progress == null) {
            throw new IllegalStateException();
        }
    }

    @Test
    @DisplayName("Optional 만들기")
    void test3(){

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Optional progress = spring_boot.getOptionalProgress();
    }

    @Test
    @DisplayName("Optional에 값이 있는지 없는지 확인하기")
    void test4(){

        // isPresent()
        Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        System.out.println(spring.isPresent());

        Optional<OnlineClass> jpa = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();
        System.out.println(jpa.isPresent());

        // isEmpty()
        Optional<OnlineClass> jpa2 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();
        System.out.println(jpa.isEmpty());
    }

    @Test
    @DisplayName("Optional에 있는 값 가져오기")
    void test5(){

        Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        System.out.println(spring.get().getTitle());

        //비어있는 Optional에서 꺼내는 경우 -> NoSuchElementException -> get() 사용을 권장하지 않음
        Optional<OnlineClass> mysql = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("mysql"))
                .findFirst();
        System.out.println(mysql.get().getTitle());
    }

    @Test
    @DisplayName("Optional에 값이 있는 경우에 그 값을 가지고 ~~를 하라.")
    void test6(){
        // ifPresent
        Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        spring.ifPresent(oc -> System.out.println(oc.getTitle()));

        Optional<OnlineClass> mysql = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("mysql"))
                .findFirst();
        mysql.ifPresent(oc -> System.out.println(oc.getTitle()));
    }

    @Test
    @DisplayName("Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라")
    void test7(){
        // orElse
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        OnlineClass onlineClass = optional.orElse(createNewClasses());
        System.out.println(onlineClass.getTitle());

    }

    @Test
    @DisplayName("Optional에 값이 있으면 가져오고 없는 경우에 ~~를 하라.")
    void test8(){
        // orElseGet
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        OnlineClass onlineClass = optional.orElseGet(() -> createNewClasses());
        System.out.println(onlineClass.getTitle());
    }

    @Test
    @DisplayName("Optional에 값이 있으면 가져오고 없는 경우 에러를 던져라.")
    void test9(){
        // orElseThrow
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring2z"))
                .findFirst();

        OnlineClass onlineClass = optional.orElseThrow(IllegalArgumentException::new);
        System.out.println(onlineClass.getTitle());
    }

    @Test
    @DisplayName("Optional에 들어있는 값 걸러내기")
    void test10(){
        // filter
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        Optional<OnlineClass> oc = optional.filter(OnlineClass::isClosed);

        System.out.println(oc.isPresent());
    }

    @Test
    @DisplayName("Optional에 들어있는 값 변환하기")
    void test11(){
        // map
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        Optional<Integer> integer = optional.map(OnlineClass::getId);
        System.out.println(integer.isPresent());

        //복잡한 경우 - Optional 반환하는 경우
        Optional<Optional<Progress>> progress1 = optional.map(OnlineClass::getOptionalProgress);
        Optional<Progress> progress2 = progress1.orElseThrow();

        //Optional 반환하는 경우 flatMap 사용
        Optional<Progress> progress3 = optional.flatMap(OnlineClass::getOptionalProgress);
    }

    private OnlineClass createNewClasses() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }

}
