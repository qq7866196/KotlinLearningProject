package com.fastcat.winsun

class ParameterUseTest {
    /* ### 固定个数参数
     ### 不固定参数
     ### 可选参数*/
    companion object {

        fun test01(p1: String, p2: Int) {
            println("$p1+$p2")
        }

        //注意和java不同的是，可变参数不需要一定在最后一位
        fun test02(p1: String, p2: Int, vararg ps: String, p3: String, p4: Int) {
            println("$p1+$p2+$ps+$p3+$p4")

            for (str in ps) {
                print(str)
            }

            println()
        }

        fun test03(p1: String = "", p2: Int = 0, p3: String = "") {
            println("$p1+$p2+$p3")
        }

    }


}

fun main() {
    ParameterUseTest.test01("haha", 1)
    //这种写法，就必须指定参数名
    ParameterUseTest.test02("haha", 2, "e", "f", "g", p3 = "abc", p4 = 1)
    ParameterUseTest.test03("haha", 3)

    //可选参数用法
    ParameterUseTest.test03("haha")
    ParameterUseTest.test03(p2 = 3)
    ParameterUseTest.test03(p2 = 3, p3 = "wa")

}