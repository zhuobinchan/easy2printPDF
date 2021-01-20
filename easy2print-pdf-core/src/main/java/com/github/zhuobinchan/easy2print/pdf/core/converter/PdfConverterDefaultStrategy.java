package com.github.zhuobinchan.easy2print.pdf.core.converter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhuobin chan on 2021-01-19 18:24
 */
public class PdfConverterDefaultStrategy implements PdfConverterStrategy {
    private final Map<Class<? extends PdfFieldConverter<?>>, PdfFieldConverter<?>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public PdfFieldConverter<?> getConverter(Class<? extends PdfFieldConverter<?>> PdfFieldConverterClazz) {
        return Optional.of(cacheMap.computeIfAbsent(PdfFieldConverterClazz, clazz -> {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        })).orElse(null);
    }
}
