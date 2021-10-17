package com.homework;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 */
public class AsyncRun2 {

    private static final CountDownLatch LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        SumThread sumThread = new SumThread();
        sumThread.start();
        LATCH.await();

        int result = sumThread.getResult();
        System.out.println("异步计算结果：" + result);
        System.out.println("计算耗时： " + (System.currentTimeMillis() - start) + " ms");
    }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    static class SumThread extends Thread {
        private Integer result;

        public Integer getResult() {
            return result;
        }

        @Override
        public void run() {
            result = sum();
            LATCH.countDown();
        }
    }
}