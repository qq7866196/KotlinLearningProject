package com.fastcat.winsun

import kotlin.concurrent.thread


/**
 * 单例
 * 传统线程执行
 * 协程执行
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
    println("main thread:"+Thread.currentThread().id)

    //继承Thread类
    object :Thread() {
        override fun run() {
            println("in thread:"+Thread.currentThread().id)
        }
    }.start()

    //下面几种类似的写法最终使用都是Runnable类初始化Thread对象
    //lambda表达式
    Thread{
        println("lambda in thread:"+Thread.currentThread().id)
    }.start()

    Thread(Runnable {
        println("in thread2:"+Thread.currentThread().id)
    }).start()

    Thread{
        run {
            println("in thread3:"+Thread.currentThread().id)
        }
    }.start()

    //kotlin 封装的方式一
    thread {
        println("kotlin in thread:"+Thread.currentThread().id)
    }

    //多种参数配置
    thread(true){

    }


    //其他利用线程池或其实现的库，AsyncTask

}

fun  main(){
    javaThreadRun()
    //TODO
    //TODO 如何证明静态内部类在不使用时是没有实例化对象的，同样饿汉式的静态对象在启动后就会被创建？


}
