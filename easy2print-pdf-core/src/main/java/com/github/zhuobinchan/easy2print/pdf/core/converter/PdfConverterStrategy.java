package com.github.zhuobinchan.easy2print.pdf.core.converter;

/**
 * @author zhuobin chan on 2021-01-20 15:07
 */
public interface PdfConverterStrategy {

    PdfFieldConverter<?> getConverter(Class<? extends PdfFieldConverter<?>> PdfFieldConverterClazz);
}
