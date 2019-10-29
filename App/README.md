

# KotlinLearningProject
学习kotlin官方文档项目
刚开始会有点凌乱，随着学习进度，会慢慢进行结构调整，归纳整理的'

临时增加一些关于junit的使用，会区分java和android

[TOC]

## 习惯用法（KotlinIdiomaticUse）


## 编码规范
### 源文件组织
鼓励多个声明（类、顶级函数或者属性）放在同一个 Kotlin 源文件中， 只要这些声明在语义上彼此紧密关联并且文件保持合理大小 （不超过几百行）。

特别是在为类定义与类的所有客户都相关的扩展函数时， 请将它们放在与类自身定义相同的地方。而在定义仅对指定客户有意义的扩展函数时，
请将它们放在紧挨该客户代码之后。不要只是为了保存 “Foo 的所有扩展函数”而创建文件（？？）

### 注释（KotlinIdiomaticUse.foo）
留意下合并到文档中的这种注释写法

### 不可变性
优先使用不可变（而不是可变）数据。初始化后未修改的局部变量与属性，总是将其声明为 val 而不是 var 。

总是使用不可变集合接口（Collection, List, Set, Map）来声明无需改变的集合。使用工厂函数创建集合实例时，尽可能选用返回不可变集合类型的函数：

```kotlin

// 不良：使用可变集合类型作为无需改变的值
fun validateValue(actualValue: String, allowedValues: HashSet<String>) { …… }

// 良好：使用不可变集合类型
fun validateValue(actualValue: String, allowedValues: Set<String>) { …… }

// 不良：arrayListOf() 返回 ArrayList<T>，这是一个可变集合类型
val allowedValues = arrayListOf("a", "b", "c")

// 良好：listOf() 返回 List<T>
val allowedValues = listOf("a", "b", "c")

```

### 类型别名
如果有一个在代码库中多次用到的函数类型或者带有类型参数的类型，那么最好为它定义一个类型别名:???
```kotlin
typealias MouseClickHandler = (Any, MouseEvent) -> Unit
typealias PersonIndex = Map<String, Person>
```

### 使用扩展函数
放手去用扩展函数。每当你有一个主要用于某个对象的函数时，
可以考虑使其成为一个以该对象为接收者的扩展函数。**为了尽量减少 API 污染，尽可能地限制扩展函数的可见性**。
根据需要，使用局部扩展函数、成员扩展函数或者具有私有可视性的顶层扩展函数。


### 使用中缀函数
官方：
一个函数只有用于两个角色类似的对象时才将其声明为中缀函数。良好示例如：and、 to、zip。 不良示例如：add。
如果一个方法会改动其接收者，那么不要声明为中缀形式。

解读：
中缀函数：如果一个函数只有一个参数，且用 infix 修饰，那么这个函数就是中缀函数

中缀函数的使用条件
* 必须是成员函数或者扩展函数；
* 必须只有一个参数；
* 参数不能是可变参数或默认参数


### 工厂函数
如果为一个类声明一个工厂函数，那么不要让它与类自身同名。优先使用独特的名称， 该名称能表明为何该工厂函数的行为与众不同。只有当确实没有特殊的语义时， 才可以使用与该类相同的名称。

例如：

```kotlin
class Point(val x: Double, val y: Double) {
    companion object {
        fun fromPolar(angle: Double, radius: Double) = Point(...)
    }
}
```

如果一个对象有多个重载的构造函数，它们并非调用不同的超类构造函数，并且不能简化为具有默认参数值的单个构造函数，那么优先用工厂函数取代这些重载的构造函数。


### 平台类型(???)

官方：
返回平台类型表达式的公有函数/方法必须显式声明其 Kotlin 类型：

fun apiCall(): String = MyJavaApi.getProperty("name")
任何使用平台类型表达式初始化的属性（包级别或类级别）必须显式声明其 Kotlin 类型：

class Person {
    val name: String = MyJavaApi.getProperty("name")
}
使用平台类型表达式初始化的局部值可以有也可以没有类型声明：

fun main() {
    val name = MyJavaApi.getProperty("name")
    println(name)
}

解读：



## 协程
线程安全与单例模式（SingleInstance）


## 集合
### filter（KotlinIdiomaticUse）
### map

# 个人实践归纳
## 参数使用 (ParameterUseTest)
### 固定个数参数
### 不固定参数
### 可选参数




