package org.example.java8.defaultmethod;

public interface Bar {

    default void printNameUpperCase(){
        System.out.println("this is bar");
    }
}
