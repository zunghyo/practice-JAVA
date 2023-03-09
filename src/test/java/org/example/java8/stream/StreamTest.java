package org.example.java8.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    private ArrayList<String> names;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>();
        names.add("chas");
        names.add("borii");
        names.add("jelly");
        names.add("sunday");
    }

    @Test
    @DisplayName("")
    void Test1(){

        for (String s:names) {
            if(s.startsWith("c")){
                System.out.println(s);
            }
        }

        names.stream().map((s) -> {
            if (s.startsWith("b"))
                System.out.println(s);
            return s.toUpperCase();
        });

        List<String> coll = names.stream().map((s) -> {
            if (s.startsWith("b"))
                System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

    }
}
