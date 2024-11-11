package org.Alperen.otherConcepts;

public class VolatileExample {

    private static int number; //piggybacking on ready's volatile keyword

    //visibility without mutual exclusion
    private volatile static boolean ready; //ensures visibility

    private static class Reader extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }

            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new Reader().start();
        number = 42;
        ready = true;
    }
}
