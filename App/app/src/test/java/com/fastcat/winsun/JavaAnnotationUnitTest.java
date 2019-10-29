package com.fastcat.winsun;

import com.fastcat.winsun.annotation.java.JavaTestAnnotation;
import com.fastcat.winsun.annotation.java.MyAnnotation;
import com.fastcat.winsun.annotation.java.MyClassAnnotation;
import com.fastcat.winsun.annotation.java.MyFieldAnnotation;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaAnnotationUnitTest {
    private static Class cl;
    private static Method[] methods;
    private static Field[] fields;

    static {
        cl = JavaTestAnnotation.class;
        methods = cl.getMethods();
        fields = cl.getFields();
    }


    @Test
    public void testClass() {
        //获取类注解
        try {
            // cl.getAnnotations(); 获取全部类注解
            //这种方式生效目前测试结果是需要类注解，否则返回为null
            MyClassAnnotation annotation = (MyClassAnnotation) cl.getAnnotation(MyClassAnnotation.class);
            if (annotation != null) {
                System.out.println(annotation.setValue() + "++-++");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMethod() {
        //获取方法注解
        //只能访问公有方法，包含私有方法需要使用getDeclaredMethod或getDeclaredMethods
        //Method method = cl.getMethod("testAnnotation");
        if (methods != null) {
            for (Method method : methods) {
                boolean flag = method.isAnnotationPresent(MyAnnotation.class);
                if (flag) {
                    try {
                        method.invoke(cl.newInstance());

                        //getAnnotation 如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。
                        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                        System.out.println(annotation.name() + "+++" + annotation.title());

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * getDeclaredAnnotation，该方法将忽略继承的注释
         * 但是
         * 这个是没有实现的。
         * 解释：注解本身具有针对性，也就是注释是自定义的，根据实际需要可以修改，此类内容并没有固定的内容规范，所以是没办法进行注释继承的，只能继承方法，不能继承注释的。
         */
    }


    @Test
    public void testField() {
        //获取属性注解
        //可以循环fields。
        //或
        try {
            //只能访问公有属性，包含私有属性需要使用getDeclaredField或getDeclaredFields。
            Field field = cl.getField("testA");
            if (field != null) {
                Annotation annotation = field.getAnnotation(MyFieldAnnotation.class);

                if (annotation != null) {
                    System.out.println("field: " + ((MyFieldAnnotation) annotation).other());
                }

            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }





}
