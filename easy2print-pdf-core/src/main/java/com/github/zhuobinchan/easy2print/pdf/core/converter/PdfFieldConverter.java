package com.github.zhuobinchan.easy2print.pdf.core.converter;

/**
 * @author zhuobin chan on 2021-01-19 18:09
 */
public interface PdfFieldConverter<INPUT> {

    String convertToPdfData(INPUT input);
}

