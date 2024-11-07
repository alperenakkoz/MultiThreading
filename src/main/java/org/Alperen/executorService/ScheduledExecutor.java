package org.Alperen.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void main(String[] args) {
        //Fixed Thread Pool + Scheduling
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(new ProbeTask(), 100, 200, TimeUnit.MILLISECONDS);

        try {
            if(!service.awaitTermination(10000, TimeUnit.MILLISECONDS)){
                service.shutdownNow();
            }
        }catch (InterruptedException e){
                service.shutdownNow();
        }
    }
}

class ProbeTask implements Runnable{

    @Override
    public void run() {
        System.out.println("checking for updates " + Thread.currentThread().getName());
    }
}
