package com.mblagov.concurrent;

import java.util.concurrent.*;

public class Scheduled {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(3);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        try {
            ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
                System.out.println("Hello World");
                lock.countDown();
            }, 2000, 1000, TimeUnit.MILLISECONDS);

            lock.await(10000, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } finally {
            executor.shutdown();
        }
    }
}
