package org.Alperen.concurrentCollection;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        /*
         A synchronization point at which threads can pair and swap elements within pairs.
         Exchangers may be useful in applications such as genetic algorithms and pipeline designs.
        */
        Exchanger<Integer> exchanger = new Exchanger<>();

        // Create two threads
        Thread thread1 = new Thread(new FirstThread(exchanger));
        Thread thread2 = new Thread(new SecondThread(exchanger));

        // Start the threads
        thread1.start();
        thread2.start();
    }
}

class FirstThread implements Runnable {
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            int dataToSend = 10;
            System.out.println("First thread is sending data " + dataToSend);

            // Send data to the other thread and receive data in return
            int receivedData = exchanger.exchange(dataToSend);

            System.out.println("First thread received: " + receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class SecondThread implements Runnable {
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            int dataToSend = 20;
            System.out.println("Second thread is sending data " + dataToSend);

            // Send data to the other thread and receive data in return
            int receivedData = exchanger.exchange(dataToSend);

            System.out.println("Second thread received: " + receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
