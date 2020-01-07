package com.example.myapplication

import kotlinx.coroutines.*

/**
 * 协程测试2
 * 结论：
 *
 *
 *
 */

class XCTest02 {

}

fun main() {
    println("start")

    runBlocking {
        delay(5000L)
        println("runBlocking")
    }

    println("end")
}

