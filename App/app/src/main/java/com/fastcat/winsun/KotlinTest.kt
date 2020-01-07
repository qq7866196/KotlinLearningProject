package com.fastcat.winsun

/**
 *
 * 标签处返回
 * xxx@{
 *  return @xxx
 * }
 *
 * 可以使用使用隐式标签更方便
 * 该标签与接受该 lambda 的函数同名
 *
 */
fun foo() {
    run loop@{
        //仅返回在当前方法体内
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
            print(it)
        }
    }
    print(" done with nested loop")
}

//隐式标签
fun foo3() {
    run {
        //仅返回在当前方法体内
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@run // run 代表返回到run方法处，即循环不会继续执行，使用forEach则效果等同continue
            print(it)
        }
    }
    print(" done with nested loop")
}

//等价
fun foo2() {
    println()
    run {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) {
                print(" done with nested loop")
                return
            }
            print(it)
        }
    }
}

fun main() {
    //foo()
    //foo3()

    val solution = Solution();
    val b = solution.isIsomorphic("paperr","titler")
    print(b.toString())
}

class Solution {

    fun isIsomorphic(s: String, t: String): Boolean {
        val chatS = s.toCharArray()
        val chatT = t.toCharArray()
        for (index in chatS.indices) {
            if (s.indexOf(chatS[index]) != t.indexOf(chatT[index])) {
                return false
            }
        }
        return true
    }

 /*   public boolean isIsomorphic(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if(s.indexOf(ch1[i]) != t.indexOf(ch2[i])){
                return false;
            }
        }
        return true;
    }*/

}
