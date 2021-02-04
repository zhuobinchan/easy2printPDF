package com.github.zhuobinchan.easy2print.pdf.core.annotiation;


import com.github.zhuobinchan.easy2print.pdf.core.converter.DefaultConverter;
import com.github.zhuobinchan.easy2print.pdf.core.converter.PdfFieldConverter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * pdf 模板字段
 *
 * @author zhuobin chan on 2021-01-12 13:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface PdfField {

    /**
     * @return 字段名称
     */
    String fieldName() default "";

    /**
     * @return 数组前缀是否忽略
     */
    boolean ignoreFieldNamePrefix4Collection() default false;

    /**
     * @return 数组通配符起始位
     */
    int collectionStartIndex() default 0;

    /**
     * @return 结果转换类
     */
    Class<? extends PdfFieldConverter> converter() default DefaultConverter.class;

}

