package org.Alperen.basicMultiThreading;


//NEW -> RUNNABLE ->  WAITING / TIMED_WAITING / BLOCKED -> TERMINATED
//I skip the blocked state because there are many examples in synchronisation and locks
//https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.State.html
public class ThreadLifecycle {

    private static final Object LOCK = new Object();

    protected static Thread task = new Thread(new Task());

    public static void main(String[] args) {

        // 1. NEW state (before starting the thread)
        System.out.println("State after creating thread (NEW): " + task.getState());

        // 2. RUNNABLE state (after starting the thread)
        task.start();
        System.out.println("State after starting thread (RUNNABLE): " + task.getState());


        //3. TIMED_WAITING state because of a Thread.sleep inside the Thread task
        try {
            Thread.sleep(1000); //give time to task scheduler to run the thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("State after sleep invoked (TIMED_WAITING): " + task.getState());

        try {
            task.join(); //wait for it to finish the task
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // 5. TERMINATED state (after the thread has completed its execution)
        System.out.println("State after thread completes (TERMINATED): " + task.getState());
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            Thread t = new Thread(new Waiter());
            t.start();
            t.join(); //wait for waiter
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class Waiter implements Runnable{
    @Override
    public void run() {
        //4. WAITING state because of join
        System.out.println("After calling join inside the task (WAITING): "+ ThreadLifecycle.task.getState());
    }
}

