package com.github.zhuobinchan.easy2print.pdf.core.style.strategy;

import com.github.zhuobinchan.easy2print.pdf.core.style.PdfFontStyleInterface;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式
 *
 * @author zhuobin chan on 2021-01-20 14:46
 */
public class PdfFieldStyleDefaultStrategy implements PdfFieldStyleStrategy {
    private final Map<Class<? extends PdfFontStyleInterface>, PdfFontStyleInterface> cacheMap = new ConcurrentHashMap<>();

    @Override
    public PdfFontStyleInterface getPdfStyleInterface(Class<? extends PdfFontStyleInterface> pdfFontStyleInterfaceClazz) {
        return Optional.of(cacheMap.computeIfAbsent(pdfFontStyleInterfaceClazz, clazz -> {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        })).orElse(null);
    }
}
