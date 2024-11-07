package org.Alperen.concurrentCollection;

import java.util.concurrent.CountDownLatch;

/* A group of chefs needs to prepare different dishes in a restaurant kitchen.
 Each chef is responsible for preparing a specific dish,
 and the kitchen manager wants to start serving customers only when all dishes are ready.
 */
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numberOfChefs);

        // Chefs start preparing their dishes
        new Thread(new Chef("Chef A", "Doner", latch)).start();
        new Thread(new Chef("Chef B", "Pizza", latch)).start();
        new Thread(new Chef("Chef C", "Lahmacun", latch)).start();

        // Wait until latch 0
        latch.await();

        System.out.println("Let's start serving!");
    }
}

class Chef implements Runnable {
    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    public Chef(String name,
                String dish,
                CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is preparing " + dish);
            Thread.sleep(2000);
            System.out.println(name + " has finished preparing " + dish);
            latch.countDown(); // Decrease the count by 1
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}