package com.fastcat.winsun.annotation.java;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface MyAnnotation {
    String name();
    String title();
}


/**
 * @Documented 指示将此注解包含在 javadoc 中
 *
 * @Inherited 指示允许子类继承父类中的注解，默认是不允许。
 * 添加了这个注解后，子类就默认都继承了，也就是子类也将包含这个注解
 *
 */
