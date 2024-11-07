package org.Alperen.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    /*
    A reentrant mutual exclusion Lock with the same basic behavior and semantics as the
    implicit monitor lock accessed using synchronized methods and statements, but with extended capabilities
     */

    //if you give "true" in constructor it gives priority to longest waited thread
    private final ReentrantLock lock = new ReentrantLock();
    private int sharedData = 0;

    public void methodA() {
        lock.lock();
        try {
            sharedData++;
            System.out.println("Method A: sharedData = " + sharedData);
            methodB();

        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        lock.lock();
        try {
            sharedData--;
            System.out.println("Method B: sharedData = " + sharedData);

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        // Create and start multiple threads
        for (int i = 0; i < 5; i++) {
            new Thread(example::methodA).start();
        }
    }
}
