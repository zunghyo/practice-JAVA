package org.example.java8.functionalinterface;

import java.util.function.Consumer;

public class ConsumerPrint implements Consumer {
    @Override
    public void accept(Object o) {
        System.out.println(o);
    }
}
