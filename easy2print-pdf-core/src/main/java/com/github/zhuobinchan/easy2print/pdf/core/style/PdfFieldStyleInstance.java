package com.github.zhuobinchan.easy2print.pdf.core.style;

/**
 * @author zhuobin chan on 2021-01-19 18:29
 */
public enum PdfFieldStyleInstance {
    INSTANCE;

    private final PdfFieldStyleFactory pdfFieldStyleFactory;

    PdfFieldStyleInstance() {
        this.pdfFieldStyleFactory = new PdfFieldStyleFactory();
    }

    public static PdfFieldStyleFactory getInstance() {
        return PdfFieldStyleInstance.INSTANCE.pdfFieldStyleFactory;
    }
}
