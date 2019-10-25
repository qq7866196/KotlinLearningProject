package com.fastcat.winsun;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/***
 * java 启动线程的几种方式
 * 1.继承Thread
 * 2.实现Runnable接口，实际上还是依靠Thread类启动
 * 3.实现Callable，同上
 *
 */

public class ThreadTestJava {

    /**
     * run 可以多次执行，start不能。
     */
    public static void main(String[] agrs) {

        println("in main thread :" + Thread.currentThread().getId());

        new Thread(new Runnable() {
            @Override
            public void run() {
                println("in thread :" + Thread.currentThread().getId());
            }
        }).start();

        new Thread() {
            @Override
            public void run() {
                println("in thread :" + Thread.currentThread().getId());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                //代码执行了，但并未开启新的线程
                println("in thread :" + Thread.currentThread().getId());
            }
        }.run();

        CallableThread callableThread = new CallableThread();
        FutureTask futureTask = new FutureTask(callableThread);
        new Thread(futureTask).start();

    }


    public static void println(String msg) {
        System.out.println(msg);
    }


    public static class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            println("callable in thread :" + Thread.currentThread().getId());
            return null;
        }
    }


}
