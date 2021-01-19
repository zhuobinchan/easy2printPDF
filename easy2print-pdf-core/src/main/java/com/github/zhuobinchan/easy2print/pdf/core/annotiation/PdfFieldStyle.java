package com.github.zhuobinchan.easy2print.pdf.core.annotiation;

import com.github.zhuobinchan.easy2print.pdf.core.style.DefaultPdfFontStyle;
import com.github.zhuobinchan.easy2print.pdf.core.style.PdfFontStyleInterface;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuobin chan on 2021-01-19 18:26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface PdfFieldStyle {
    /**
     * @return 自定样式
     */
    Class<? extends PdfFontStyleInterface> fontStyle() default DefaultPdfFontStyle.class;

}
