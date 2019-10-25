package com.fastcat.winsun

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.File
import java.util.concurrent.locks.Lock

/**
 * 官方定义的习惯用法
 */
class KotlinIdiomaticUse {

    //过滤 list
    fun listFilter() {
        val list = listOf<Int>(1, 23, 4, 5)
        val positives01 = list.filter { x -> x > 0 }
        //或
        val positives02 = list.filter { it > 0 }
    }

    //检测元素是否存在于集合中
    fun listCheck() {

        val emailsList = List<String>(4) { "a"; "c";"b" }

        if ("john@example.com" in emailsList) {

        }

        if ("jane@example.com" !in emailsList) {
        }


        val name = ""
        println("Name $name")

    }

    //遍历 map/pair型list
    fun forMapOrPairList() {
        //只读 map
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)

        for ((k, v) in map) {
            println("$k -> $v")
        }

        val pairList = List<Pair<String, String>>(3) { Pair("1", "2") }

        for ((k, v) in pairList) {
            println("$k -> $v")
        }

        //k、v 可以改成任意名字。
    }


    /**
     * 使用区间
     * for (i in 1..100) { …… }  // 闭区间：包含 100
     * for (i in 1 until 100) { …… } // 半开区间：不包含 100
     * for (x in 2..10 step 2) { …… }
     * for (x in 10 downTo 1) { …… }
     * if (x in 1..10) { …… }
     */


    //扩展函数
    private fun String.spaceToCamelCase() {

    }

    fun stringTest() {
        "Convert this to camelcase".spaceToCamelCase()
    }


    //单例（饿汉式）
    object Resource {
        val name = "Name"
    }


    //If not null 缩写

    fun ifNotNull() {
        val files = File("Test").listFiles()

        println(files?.size)
    }

    //If not null and else 缩写
    fun ifNotNullAndElse() {
        val files = File("Test").listFiles()

        println(files?.size ?: "empty")
    }

    //“try/catch”表达式
    fun test() {

        val result = try {
            print("1")
        } catch (e: ArithmeticException) {
            throw IllegalStateException(e)
        }

        // 使用 result
    }

    //“if”表达式
    /**
     *  返回[param]
     */
    fun foo(param: Int) {
        val result = if (param == 1) {
            "one"
        } else if (param == 2) {
            "two"
        } else {
            "three"
        }
    }

    //单表达式函数
    fun theAnswer01() = 42

    //等价于
    fun theAnswer02(): Int {
        return 42
    }


    //交换两个变量
    fun values() {
        var a = 1
        var b = 2
        a = b.also { b = a }
    }


    //对于需要泛型信息的泛型函数的适宜形式
//  public final class Gson {
//     ……
//     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
//     ……

    /**
     * inline,方法调用是有消耗的（参考方法栈的概念），而内联函数相当于直接将字节码注入目标方法中，这样就少调用一个方法
     * reified 具体化，我的理解是将范型定义具体化，算inline的一种规定用法。
     */
    inline fun <reified T : Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

    inline fun <reified D : Any> getAbc(): D? {
        return null
    }

    fun testGson() {
        val gson = Gson()
        gson.fromJson<String>(JsonObject())
        gson.fromJson<String>(JsonPrimitive("123"))
    }

    //拓展，noinline，
    inline fun <T> check(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            //otherCheck(body)//会报错，调用的时候，由于inline特性，body不能再做参数传递，
            //在body前面加noinline就可以
            return body()
        } finally {
            lock.unlock()
        }
    }

    fun <T> otherCheck(body: () -> T) {

    }

    //返回类型为 Unit 的方法的 Builder 风格用法
    //TODO 更多用法有待挖掘
    fun arrayOfMinusOnes(size: Int): IntArray {
        //let的这种拓展貌似无意义？
        IntArray(size).let { }.let { }
        return IntArray(size).apply { fill(-1) }.apply { fill(-2) }.apply { fill(-3) }
    }


}