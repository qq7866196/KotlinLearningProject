package com.example.myapplication

import kotlinx.coroutines.*

/**
 * 协程测试1
 *
 * 结论：
 * (1)GlobalScope，全局协程像守护线程 并不能进行进程保活
 * 阻塞式函数声明：
 * 非阻塞式使用： delay(500L)，协程内使用
 *
 * 挂起函数：runBlocking{},样例见[XCTest02]
 * 在挂起函数中挂起：使用coroutineScope 作用域，在其中的代码都将挂起。
 * 挂起函数之后的代码，将阻塞（不会引起ANR），不执行，待挂起函数执行完后执行。但是挂起的时候，是允许其他协程工作的。
 *
 * 后台作业：参照val job =  GlobalScope.launch{},job.join()方式。
 * 延时操作：阻塞是采用挂起操作，非阻塞采用后台作业方式。
 *
 *
 *
 */

class XCTest01 {

}

fun main() = runBlocking {
    // this: CoroutineScope

    /*val job = GlobalScope.launch {
        */
    /**
     * 后台工作协程
     *//*
        repeat(10) { i ->
            delay(1000L)
            println("work!!!!!!")
        }
    }*/

    /*  GlobalScope.launch {
          */
    /**
     * 进程保活测试,守护线程
     *//*
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }*/

    launch {
        delay(2000L)
        println("Task from runBlocking")
    }

   /* repeat(100_000) {
        // 启动大量的协程，非阻塞式的，会先执行底部的代码。
        launch {
            //理论上是每个协程，同一时刻都会输出一个点。
            doPoint()
        }
    }
*/
    coroutineScope {
        // 创建一个协程作用域

        /**
         * 挂起函数，但是挂起的时候，是允许其他协程工作的。
         */
        repeat(100_0000) {
            // 启动大量的协程，挂起，即阻塞式的，会在后面代码前完成但前作用域的内容。
            launch {
                //理论上是每个协程，同一时刻都会输出一个点。
                doPoint()
            }
        }


        /*       launch {
                   delay(5000L)
                   println("Task from nested launch")
               }

               delay(1000L)
               println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出*/
    }

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出

    //job.join()
}

suspend fun doPoint() {
    delay(1000L)
    print(".")
}
