package com.github.zhuobinchan.easy2print.pdf.core.converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhuobin chan on 2021-01-19 18:24
 */
public enum PdfConverterInstance {
    INSTANCE;
    private final Map<Class<?>, PdfFieldConverter<?>> converterMap;

    PdfConverterInstance() {
        this.converterMap = new ConcurrentHashMap<>();
    }


    public static Map<Class<?>, PdfFieldConverter<?>> getInstance() {
        return PdfConverterInstance.INSTANCE.converterMap;
    }

    public String converter(Class<?> converterClass, Object value) {
        return this.putAndGetConverter(converterClass).convertToPdfData(value);
    }

    private PdfFieldConverter putAndGetConverter(Class<?> converterClass) {
        return converterMap.computeIfAbsent(converterClass, clazz -> {
            try {
                return (PdfFieldConverter<?>) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
