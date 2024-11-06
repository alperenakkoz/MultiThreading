package org.Alperen.basicMultiThreading;

public class ClassThread {
    public static void main(String[] args) {
        Thread one = new ThreadOne();
        Thread two = new ThreadTwo();

        //there is no way to know the sequence
        one.start();
        two.start();

    }
}

class ThreadOne extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            System.out.println("Thread one :" + i);
        }
    }
}

class ThreadTwo extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            System.out.println("Thread two :" + i);
        }
    }
}