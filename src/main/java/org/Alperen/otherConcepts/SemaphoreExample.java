package org.Alperen.otherConcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 15; i++) {
                service.execute(() -> SomeService.INSTANCE.scrape());
            }
        }
    }
}

//thread-safe, straightforward, and recommended way to implement the Singleton pattern
enum SomeService {
    INSTANCE;
    //Semaphore gives permit to threads
    private final Semaphore semaphore = new Semaphore(3,true);

    public void scrape() {
        try {
            semaphore.acquire();
            invokeWork();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    private void invokeWork() {
        try {
            System.out.println("Thread: " + Thread.currentThread().getName() + "Doing important stuff...");
            System.out.println( "Remaining permits:" + semaphore.availablePermits());

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
