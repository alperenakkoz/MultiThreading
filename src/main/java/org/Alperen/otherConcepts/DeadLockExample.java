package org.Alperen.otherConcepts;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample{
    private final Lock lockA = new ReentrantLock(true);
    private final Lock lockB = new ReentrantLock(true);

    public void workerOne() {
        lockA.lock();
        System.out.println(Thread.currentThread().getName() +" acquired lockA");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //lockA.unlock(); //no deadlock

        lockB.lock(); //will wait for lockB but workerTwo also waiting for lockA
        System.out.println(Thread.currentThread().getName() +" acquired lockB");
        lockB.unlock();
    }

    public void workerTwo() {
        lockB.lock();
        System.out.println(Thread.currentThread().getName() +" acquired lockB");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //lockB.unlock(); //no deadlock

        lockA.lock(); //will wait for lockA but workerOne waiting for lockB
        System.out.println(Thread.currentThread().getName() +" acquired lockA");
        lockA.unlock();
    }

    public static void main(String[] args) {
        DeadLockExample deadlock = new DeadLockExample();
        new Thread(deadlock::workerOne, "Worker One").start();
        new Thread(deadlock::workerTwo, "Worker Two").start();

        new Thread(() -> {
            //ThreadMXBean supports monitoring and management of platform threads in the Java virtual machine
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while (true) {
                long[] threadIds = mxBean.findDeadlockedThreads();
                if (threadIds != null) {
                    ThreadInfo[] threadInfo = mxBean.getThreadInfo(threadIds);
                    System.out.println("Dead Lock detected!");
                    for (ThreadInfo info : threadInfo) {
                        System.out.println("Thread " +info.getThreadName() + " with ID " + info.getThreadId()  + " in Dead Lock");
                        System.out.println("Thread state : " + info.getThreadState());
                    }
                    break;
                }
                try {
                    Thread.sleep(5000); //check for every 5 sec.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}