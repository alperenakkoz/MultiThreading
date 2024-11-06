package org.Alperen.threadSynchronisation;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerProblem {

    public static void main(String[] args) {
        Worker worker = new Worker(5,0);

        Thread producer = new Thread(()-> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(()-> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
    }


}
class Worker{

    private int sequence = 0;
    private final Integer MAX;
    private final Integer MIN;
    private final List<Integer> container;

    private final Object LOCK = new Object();

    public Worker(Integer MAX, Integer MIN) {
        this.MAX = MAX;
        this.MIN = MIN;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (LOCK){
            while(true){
                if(container.size() == MAX){
                    System.out.println("container is full");
                    LOCK.wait();
                }else {
                    System.out.println(sequence + " added to container");
                    container.add(sequence++);
                    LOCK.notify();
                }
                Thread.sleep(500); //without these it super fast

            }
        }
    }

    public void consume() throws InterruptedException{
        synchronized (LOCK){
            while(true){
                if(container.size() == MIN){
                    System.out.println("container is empty");
                    LOCK.wait();
                }else {
                    System.out.println(container.removeFirst() + " removed from container");
                    LOCK.notify();
                }
                Thread.sleep(500); //without these it super fast
            }

        }
    }
}