package org.example.java8.functionalinterface;

@FunctionalInterface
public interface RunSomething {

    int doIt(int num);

    static void printName(){
        System.out.println("bori");
    }

    default void printAge(){
        System.out.println("32");
    }
}
