package com.github.zhuobinchan.easy2print.pdf.core.converter;

import com.itextpdf.io.util.UrlUtil;

import java.net.MalformedURLException;

/**
 * @author zhuobin chan on 2021-01-25 16:09
 */
public class ImagePathConverter implements PdfFieldConverter<String> {
    @Override
    public Object convertToPdfData(String s) {
        try {
            return UrlUtil.toURL(s);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
