package com.github.zhuobinchan.easy2print.pdf.core.style;

import com.github.zhuobinchan.easy2print.pdf.core.model.PdfModel;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;

/**
 * @author zhuobin chan on 2021-01-19 18:23
 */
public class DefaultPdfFontStyle implements PdfFontStyleInterface {
    private static final Color BLACK = new DeviceRgb(42, 42, 42);

    @Override
    public PdfFont getPdfFont(PdfModel model) {
        return null;
    }

    @Override
    public float fontSize(PdfModel model) {
        return 27F;
    }

    @Override
    public Color fontColor(PdfModel model) {
        return BLACK;
    }

}
