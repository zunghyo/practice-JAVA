package org.example.java8.functionalinterface;

import java.util.function.Function;

public class FunctionPlus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
