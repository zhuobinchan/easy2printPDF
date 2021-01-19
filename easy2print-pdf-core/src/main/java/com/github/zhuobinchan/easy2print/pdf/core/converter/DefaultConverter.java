package com.github.zhuobinchan.easy2print.pdf.core.converter;

import com.github.zhuobinchan.easy2print.pdf.core.utils.StringUtils;

/**
 * @author zhuobin chan on 2021-01-19 18:10
 */
public class DefaultConverter implements PdfFieldConverter<Object> {
    @Override
    public String convertToPdfData(Object o) {
        if (o == null) {
            return StringUtils.EMPTY;
        }
        return o.toString();
    }
}
