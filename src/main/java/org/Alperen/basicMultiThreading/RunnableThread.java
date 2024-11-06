package org.Alperen.basicMultiThreading;

public class RunnableThread {
    public static void main(String[] args) {
        Thread one = new Thread(new Thread1());
        Thread two = new Thread(() -> {
                for(int i = 0; i < 20; i++){
                    System.out.println("Thread 2 :" + i);
            }
        });

        //there is no way to know the sequence
        one.start();
        two.start();
    }
}

class Thread1 implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            System.out.println("Thread 1 :" + i);
        }
    }
}
