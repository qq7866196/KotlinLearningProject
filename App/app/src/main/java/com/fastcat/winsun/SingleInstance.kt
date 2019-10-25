package com.fastcat.winsun

import kotlin.concurrent.thread


/**
 * 单例
 * 传统线程执行
 * 协程执行
 *
 *
 *
 * 结论：
 * 1.类在不使用的情况下，即使是静态字段，也是不进行初始化的
 * 2.
 *
 */

/**
 * 1.饿汉式
 * 线程安全
 * 有点：直观
 * 缺点：类载入即创建对象
 *
 * java:
 *
 * public class SingletonDemo {
 *  private static SingletonDemo instance=new SingletonDemo();
 *  private SingletonDemo(){
 *
 *  }
 *  public static SingletonDemo getInstance(){
 *  return instance;
 *  }
 * }
 *
 */

object SingleInstance01 {
    var ace = 0
}

fun testUse() {
    SingleInstance01.ace = 12
}


class SingleInstance {
    var out_side = 11

    //2.懒汉式

    /**
     * 3.线程安全的懒汉式
     * 同步代价较高，不建议使用
     * java:
     * public class SingletonDemo {
     *      private static SingletonDemo instance;
     *      private SingletonDemo(){}
     *
     *      public static synchronized SingletonDemo getInstance(){
     *          //使用同步锁
     *          if(instance==null){
     *              instance=new SingletonDemo();
     *          }
     *          return instance;
     *      }
     * }
     *
     */

    //Kotlin实现
    class SingletonDemo02 private constructor() {
        companion object {
            private var instance: SingletonDemo02? = null
                get() {
                    if (field == null) {
                        field = SingletonDemo02()
                    }
                    return field
                }

            @Synchronized
            fun get(): SingletonDemo02 {
                return instance!!
            }
        }

    }

    //4.双检查式，线程安全
    companion object {
        val instance: Instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Instance()
        }

        var param01 = 0
    }

    class Instance


    /**
     * 5.静态内部类，线程安全
     *
     * //Java实现
     *
     * public class SingletonDemo {
     *  private static class SingletonHolder{
     *      private static SingletonDemo instance=new SingletonDemo();
     *  }
     *
     *  private SingletonDemo(){
     *      System.out.println("Singleton has loaded");
     *  }
     *
     *  public static SingletonDemo getInstance(){
     *      return SingletonHolder.instance;
     *  }
     *
     * }
     *
     */

    //kotlin实现
    class SingletonDemo private constructor() {
        companion object {
            val instance = SingletonHolder.holder
        }

        private object SingletonHolder {
            val holder = SingletonDemo()
        }

    }


}


fun javaThreadRun() {
    println("main thread:" + Thread.currentThread().id)

    //继承Thread类
    object : Thread() {
        override fun run() {
            println("in thread:" + Thread.currentThread().id)
        }
    }.start()

    //下面几种类似的写法最终使用都是Runnable类初始化Thread对象
    //lambda表达式
    Thread {
        println("lambda in thread:" + Thread.currentThread().id)
    }.start()

    Thread(Runnable {
        println("in thread2:" + Thread.currentThread().id)
    }).start()

    Thread {
        run {
            println("in thread3:" + Thread.currentThread().id)
        }
    }.start()

    //kotlin 封装的方式一
    thread {
        println("kotlin in thread:" + Thread.currentThread().id)
    }

    //多种参数配置
    thread(true) {

    }


    //其他利用线程池或其实现的库，AsyncTask

}

fun main() {
    javaThreadRun()
    //通过profiler内存dump可以看出，SingleInstance01调用后(如在MainActivity的onCreate方法中调用)，就会保留对象，其他代码并未执行
    //同一个类里，初始化一个静态字段时，其他静态字段也会完成初始化
    //同一个类里，创建类对象，并赋值全局字段。静态字段会被初始化，从kotlin转换的代码可以看出
    //同一个类里，非内部类，只要不调用，不管其内部是否有静态字段，都不会初始化
    //静态内部类在不使用时是没有实例化对象的,


}
