package org.Alperen.concurrentCollection;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapExample {
    // even though all operations are thread-safe, retrieval operations do not entail locking,
    // and there is not any support for locking the entire table in a way that prevents all access

    //hash and determine segment -> acquire lock -> insert/search in/on segment -> release lock
    private static final Map<String, String> cache = new ConcurrentHashMap<>();

    private static String compute(String key) {
        System.out.println(key + " not present in the cache, compute");

        return "Value : " + key;
    }

    public static String getCachedValue(String key) {
        String value = cache.get(key); //thread safe

        if (value == null) {
            value = compute(key);
            cache.put(key, value); //thread safe
        }

        return value;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            new Thread(() -> {
                String key = "Key @ " + threadNum;
                for (int j = 0; j < 2; j++) { // Fetch the same key  2 times
                    String value = getCachedValue(key);
                    System.out.println("Thread " + Thread.currentThread().getName() + ": Key=" + key + ", Value=" + value);
                }
            }).start();
        }
    }
}
