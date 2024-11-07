package org.Alperen.executorService;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        try(ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Future<Integer> result = executorService.submit(new ReturnValueTask());

            boolean cancelled = result.isCancelled();

            if(!cancelled){
                //max wait for 6 sec
                System.out.println(result.get(6, TimeUnit.SECONDS));
            }
            boolean done = result.isDone();
            System.out.println(done);
            result.cancel(true);

            System.out.println("Main thread execution completed!");
        }
    }
}

class ReturnValueTask implements Callable<Integer> {

    @Override
    public Integer call() {
        int i;
        for ( i = 0; i < Integer.MAX_VALUE; i++) {

        }
        return i;
    }
}