package com.homework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 */
public class AsyncRun3 {

    private static final CyclicBarrier BARRIER = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        long start = System.currentTimeMillis();
        SumThread sumThread = new SumThread();
        sumThread.start();
        BARRIER.await();

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
            try {
                BARRIER.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}