package org.betterJavaApplication.lambda;


import org.betterJavaApplication.lambda.entities.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

interface A {
    void show();
}

public class LambdaTest {

    public static void main(String[] args) {
        Printable test = () -> System.out.println("Test");
        printThing(test);

        A obj = () -> System.out.println("Hi");
        obj.show();


        List<String> testList = new ArrayList<>();
        testList.add("Cat");
        testList.add("Dog");
        testList.add("Turtle");

        testList.forEach(entity -> {
            if (entity.equals("Turtle")) {
                System.out.println("Ouch! That turtle will out live you!");
            }
        });

        List<Integer> ints = Stream.of(1,2,4,3,5).toList();
        ints.forEach((i)-> System.out.println((i - 1)+ " " + i));

        Consumer<Integer> consumer = System.out::println;
        consumer.accept(10);


    }

    public static void printThing(Printable thing) {
        thing.print();
    }
}
