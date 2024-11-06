package org.Alperen.basicMultiThreading;

public class DaemonAndUserThread {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new DaemonHelper());
        Thread userThread = new Thread(new UserThreadHelper());
        daemonThread.setDaemon(true); //make it daemon

        daemonThread.start(); // JVM won't be waiting for it's full execution
        userThread.start(); //program will be terminated when all user threads are done
    }
}

class DaemonHelper implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 500; i++){
            try {
                Thread.sleep(500); // 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon running...");
        }
    }
}

class UserThreadHelper implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User Thread done with executing!");
    }
}