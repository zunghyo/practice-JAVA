package org.example.java8.defaultmethod;

public interface Foo {

    void printName();

    String getName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자를 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    static void printAnything(){
        System.out.println("hi? this is anything");
    }
}
