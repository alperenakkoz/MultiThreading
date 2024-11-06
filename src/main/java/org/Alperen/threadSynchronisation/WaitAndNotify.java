package org.Alperen.threadSynchronisation;

public class WaitAndNotify {
    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        Thread one = new Thread(()->{
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two = new Thread(()->{
            try {
                two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        one.start();
        two.start();
    }

    private static void one() throws InterruptedException{
        synchronized (LOCK){
            System.out.println("Entered method one"); //first
            LOCK.wait();
            System.out.println("Back again. Method one"); //fourth
        }
    }

    private static void two() throws InterruptedException{
        synchronized (LOCK){
            System.out.println("Entered method two"); //second
            LOCK.notify(); //execute last in this block
            System.out.println("After notify still two"); //third
        }
    }
}
