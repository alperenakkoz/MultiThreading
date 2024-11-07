package org.Alperen.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void main(String[] args) {
        try(ExecutorService executorService = Executors.newFixedThreadPool(2)){
            for (int i = 0; i < 7; i++) {
                //Tasks won't be working in sequence!
                executorService.execute(new work(i));
            }
        }
    }

}

class work implements Runnable{
    private final int workId;

    work(int workId) {
        this.workId = workId;
    }

    @Override
    public void run() {
        System.out.println("Task ID " + workId + " being executed by " + Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }

}
