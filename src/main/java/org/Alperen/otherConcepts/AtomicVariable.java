package org.Alperen.otherConcepts;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {

    private static int count = 0; //not atomic
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                //count++; //not atomic
                counter.addAndGet(1);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                //count++; //not atomic
                counter.incrementAndGet();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Count value is : " + counter);
    }
}