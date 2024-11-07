package org.Alperen.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        try(ExecutorService executorService = Executors.newCachedThreadPool()){
            //If all threads busy, creates new one. 60 sec idle time = kill
            for (int i = 0; i < 100; i++) {
                executorService.execute(new TaskOne(i));
            }
        }
    }
}

class TaskOne implements Runnable{
    private final int taskId;

    TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task: " + taskId + " being executed by " + Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
