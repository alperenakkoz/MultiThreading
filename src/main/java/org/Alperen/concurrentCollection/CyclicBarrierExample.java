package org.Alperen.concurrentCollection;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    private static final int NUM_TOURISTS = 5; //thread number
    private static final int NUM_STAGES = 3;

    //allows a set of threads to all wait for each other to reach a common barrier point.
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_TOURISTS,() -> {
        System.out.println("Tour guide starts speaking..."); //barrier action
    });

    public static void main(String[] args) {
        for (int i = 0; i < NUM_TOURISTS; i++) {
            Thread touristThread = new Thread(new Tourist(i));
            touristThread.start();
        }
    }

    static class Tourist implements Runnable {
        private final int touristId;

        public Tourist(int touristId) {
            this.touristId = touristId;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUM_STAGES; i++) {
                // Perform actions at each stage
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Tourist " + touristId + " arrives at Stage " + (i + 1));

                // Wait for all tourists to arrive at the current stage
                try {
                    barrier.await(); //if all invokes await then barrier action starts working

                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
