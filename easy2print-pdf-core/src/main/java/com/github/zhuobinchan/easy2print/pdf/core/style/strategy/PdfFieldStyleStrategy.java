package com.github.zhuobinchan.easy2print.pdf.core.style.strategy;

import com.github.zhuobinchan.easy2print.pdf.core.style.PdfFontStyleInterface;

/**
 * @author zhuobin chan on 2021-01-20 14:44
 */
public interface PdfFieldStyleStrategy {
    PdfFontStyleInterface getPdfStyleInterface(Class<? extends PdfFontStyleInterface> pdfFontStyleInterfaceClazz);
}
