package com.github.zhuobinchan.easy2print.pdf.core.style;

import com.github.zhuobinchan.easy2print.pdf.core.model.PdfModel;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;

/**
 * @author zhuobin chan on 2021-01-19 18:30
 */
public interface PdfFontStyleInterface {

    /**
     * @param model 模型内容
     * @return 字体样式
     */
    PdfFont getPdfFont(PdfModel model);


    /**
     * @param model 模型内容
     * @return 字体大小
     */
    float fontSize(PdfModel model);

    /**
     * @param model 模型内容
     * 字体颜色
     */
    Color fontColor(PdfModel model);
}
