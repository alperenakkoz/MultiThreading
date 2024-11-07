package org.Alperen.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntensiveTask {
    public static void main(String[] args) {
        //not physical cores! Virtual cores(thread number)
        int cores = Runtime.getRuntime().availableProcessors();
        try (ExecutorService service = Executors.newFixedThreadPool(cores)) {
            System.out.println("Created thread pool with " + cores + " threads");

            for (int i = 0; i < 20; i++) {
                service.execute(new CpuTask());
            }
        }
    }
}

class CpuTask implements Runnable{
    @Override
    public void run() {
        System.out.println("CPU intensive task being done by " + Thread.currentThread().getName());

    }



}
