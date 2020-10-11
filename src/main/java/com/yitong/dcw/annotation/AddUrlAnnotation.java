package com.yitong.dcw.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname AddUrl
 * @Description TODO
 * @Date 2020/10/9 8:59
 * @Created by 邓磊
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface  AddUrlAnnotation {
    String BaseUrl() default "http://www.hc.gov.cn/";
}
