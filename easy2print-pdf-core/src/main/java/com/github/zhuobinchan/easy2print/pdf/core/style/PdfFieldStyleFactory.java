package com.github.zhuobinchan.easy2print.pdf.core.style;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author zhuobin chan on 2021-01-19 18:27
 */
public class PdfFieldStyleFactory {
    private final ThreadLocal<Map<Class<? extends PdfFontStyleInterface>, PdfFontStyleInterface>> threadLocalCacheMap = ThreadLocal.withInitial(HashMap::new);

    public PdfFontStyleInterface getPdfStyleInterface(Class<? extends PdfFontStyleInterface> pdfFontStyleInterfaceClazz) {
        Map<Class<? extends PdfFontStyleInterface>, PdfFontStyleInterface> cacheMap = threadLocalCacheMap.get();
        return Optional.of(cacheMap.computeIfAbsent(pdfFontStyleInterfaceClazz, clazz -> {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        })).orElse(null);
    }

    public void removeCache() {
        threadLocalCacheMap.remove();
    }
}
