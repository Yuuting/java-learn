package com.bjpowernode.java.annotation7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 表示这个注解只能出现在类上面
@Target(ElementType.TYPE)
// 该注解可以被反射机制读取到
@Retention(RetentionPolicy.RUNTIME)
public @interface MustHasIdPropertyAnnotation {

}

// 这个注解@Id用来标注类，被标注的类中必须有一个int类型的id属性，没有就报异常。
