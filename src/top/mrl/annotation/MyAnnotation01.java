package top.mrl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD, ElementType.TYPE})  // 注解位置
@Retention(RetentionPolicy.RUNTIME)  // 保留策略 SOURCE:源文件里  CLASS:在class文件  RUNTIME:运行时有效,可配合反射
public @interface MyAnnotation01 {

    String value() default ""; // 值, 默认为  ""

}
