package com.tidc.part.time.monarch.validation;

import com.tidc.part.time.monarch.validation.enumerate.ValidNumberSizeType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: 李家宝
 * @Date: 2018/10/29 19:29
 */
@Deprecated
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
public @interface NumberSize {
    String message() default "number size error";
    int max();
    int min();
    ValidNumberSizeType type() default ValidNumberSizeType.INTERVAL;
}
