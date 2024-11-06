package org.Alperen.threadSynchronisation;

public class Synchronisation {
    private static int count = 0;

    public static void main(String[] args) {
        //load -> increment -> set the value
        //without sync it could give wrong results
        Thread one = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                increment();
            }
        });

        Thread two = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                increment();
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
        System.out.println(count);
    }

    //method level
    private synchronized static void increment(){ //only one thread can call this at a given time
        count++;
    }
}
