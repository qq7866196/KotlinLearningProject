package com.fastcat.winsun.annotation.java;

@MyClassAnnotation(setValue = "why")
public class JavaTestAnnotation {

    @MyFieldAnnotation(other = "What")
    public String testA;


    @MyAnnotation(name = "123", title = "231")
    public void testAnnotation() {
        System.out.println("hello world!");
    }


}
