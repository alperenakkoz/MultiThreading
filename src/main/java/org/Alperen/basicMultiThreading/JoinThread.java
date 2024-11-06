package org.Alperen.basicMultiThreading;

public class JoinThread {
    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for(int i = 0; i < 15; i++){
                System.out.println("Thread 1 :" + i);
            }
        });

        Thread two = new Thread(() -> {
            for(int i = 0; i < 20; i++){
                System.out.println("Thread 2 :" + i);
            }
        });

        one.start();
        System.out.println("Start"); //main thread takes priority
        two.start();
        try {
            one.join(); //parent thread wait for Thread one's execution
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread one completed"); //because of join this will only be called after execution of thread one
    }
}
