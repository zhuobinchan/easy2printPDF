package com.github.zhuobinchan.easy2print.pdf.core;

import com.github.zhuobinchan.easy2print.pdf.core.annotiation.PdfField;
import com.github.zhuobinchan.easy2print.pdf.core.annotiation.PdfFieldStyle;
import com.github.zhuobinchan.easy2print.pdf.core.converter.PdfConverterInstance;
import com.github.zhuobinchan.easy2print.pdf.core.model.PdfModel;
import com.github.zhuobinchan.easy2print.pdf.core.style.PdfFieldStyleInstance;
import com.github.zhuobinchan.easy2print.pdf.core.style.PdfFontStyleInterface;
import com.github.zhuobinchan.easy2print.pdf.core.utils.StringUtils;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author zhuobin chan on 2021-01-19 17:53
 */
public class PdfModelUtils {
    /**
     * 列表通配符配置
     */
    public final static String PLACEHOLDER_STRING = "{COLLECTION_INDEX}";

    /**
     * 填充pdf文件
     *
     * @param sourcePath  源的pdf文件模板
     * @param descRootDir 目标文件目录
     * @param model       数据模板
     * @return 返回目标文件路劲
     */
    public static String printPdf(String sourcePath, String descRootDir, Object model) {
        return printPdf(sourcePath, descRootDir, model, model.getClass());
    }

    /**
     * 填充pdf文件
     *
     * @param sourcePath  源的pdf文件模板
     * @param descRootDir 目标文件目录
     * @param model       数据模板
     * @param modelClazz  模板类读取
     * @return 返回目标文件路劲
     */
    public static String printPdf(String sourcePath, String descRootDir, Object model, Class<?> modelClazz) {
        try {
            List<PdfModelWithStyle> pdfModelList = toPdfModel(model, modelClazz);
            String desPath = descRootDir + "/" + UUID.randomUUID().toString() + ".pdf";
            printPdfToFile(sourcePath, desPath, pdfModelList);
            return desPath;
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            PdfFieldStyleInstance.getInstance().removeCache();
        }
    }

    /**
     * 根据模板数据抽象类 填充到pdf
     *
     * @param sourcePath   源的pdf文件模板
     * @param desPath      目标文件
     * @param pdfModelList 板数据抽象类列表
     * @throws IOException io异常
     */
    private static void printPdfToFile(String sourcePath, String desPath, List<PdfModelWithStyle> pdfModelList) throws IOException {
        try (PdfReader reader = new PdfReader(sourcePath); PdfWriter writer = new PdfWriter(desPath); PdfDocument pdfDoc = new PdfDocument(reader, writer); Document doc = new Document(pdfDoc)) {
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
            pdfModelList.forEach(model -> {
                PdfFormField field = form.getField(model.getFieldName());
                if (field != null) {
                    if (model.getFieldStyle() == null) {
                        field.setValue(model.getFieldValue());
                    } else {
                        field.setValue(model.getFieldValue(), model.getFieldStyle().getPdfFont(model), model.getFieldStyle().fontSize(model)).setColor(model.getFieldStyle().fontColor(model));
                    }
                }
            });
        }
    }

    /**
     * 转换为转换载体的内部类
     *
     * @param model      源的model文件
     * @param modelClazz 模板class
     * @return 转换载体内部类
     * @throws IllegalAccessException 字段不合法异常
     */
    public static List<PdfModelWithStyle> toPdfModel(Object model, Class<?> modelClazz) throws IllegalAccessException {
        Field[] fields = modelClazz.getDeclaredFields();
        List<PdfModelWithStyle> resultList = new ArrayList<>();
        PdfConverterInstance pdfConverterInstance = PdfConverterInstance.INSTANCE;
        for (Field field : fields) {
            PdfField pdfField = field.getAnnotation(PdfField.class);
            if (pdfField == null) {
                continue;
            }
            field.setAccessible(true);
            String fieldName = pdfField.fieldName();
            Object fieldValue = field.get(model);
            if (StringUtils.isEmpty(fieldName)) {
                fieldName = field.getName();
            }

            if (fieldValue instanceof Collection) {
                Collection collection = (Collection) fieldValue;
                if (!collection.isEmpty()) {
                    for (Object collectionItem : collection) {
                        List<PdfModelWithStyle> collectionResultList = toPdfModel(collectionItem, collectionItem.getClass());

                        int index = pdfField.collectionStartIndex();
                        for (PdfModelWithStyle collectionResultItem : collectionResultList) {
                            if (pdfField.ignoreFieldNamePrefix4Collection()) {
                                fieldName = placeholder(collectionResultItem.getFieldName(), index);
                            } else {
                                fieldName = placeholder(fieldName + "." + collectionResultItem.getFieldName(), index);
                            }
                            collectionResultItem.setFieldName(fieldName);
                            resultList.add(collectionResultItem);
                        }

                    }
                }

                continue;
            }

            PdfModelWithStyle pdfModel = new PdfModelWithStyle();
            pdfModel.setFieldName(fieldName);
            String finalFieldValue = pdfField.fieldValuePrefix() + pdfConverterInstance.converter(pdfField.converter(), fieldValue) + pdfField.fieldValueSuffix();
            pdfModel.setFieldValue(finalFieldValue);
            pdfModel.setFieldStyle(getFieldStyleByModelField(field));
            resultList.add(pdfModel);
        }
        return resultList;
    }

    /**
     * 根据字段 转换模板的样式
     *
     * @param field 当前字段
     * @return 返回该字段样式
     */
    private static PdfFontStyleInterface getFieldStyleByModelField(Field field) {
        PdfFieldStyle pdfFieldStyle = field.getAnnotation(PdfFieldStyle.class);
        if (pdfFieldStyle == null) {
            return null;
        }
        return PdfFieldStyleInstance.getInstance().getPdfStyleInterface(pdfFieldStyle.fontStyle());
    }

    /**
     * 通配符处理
     * 示例：
     * placeholder(null,1) -> null;
     * placeholder("",1) -> "";
     * placeholder("abc",1) -> "abc";
     * placeholder("abc{COLLECTION_INDEX}",1) -> "abc1";
     *
     * @param fieldName 字段名称
     * @param index     统配值
     * @return 结果名称
     */
    private static String placeholder(String fieldName, Integer index) {
        if (StringUtils.contains(fieldName, PLACEHOLDER_STRING)) {
            return StringUtils.replace(fieldName, PLACEHOLDER_STRING, String.valueOf(index));
        }
        return fieldName;
    }

    private static class PdfModelWithStyle extends PdfModel {
        private PdfFontStyleInterface fieldStyle;

        public PdfFontStyleInterface getFieldStyle() {
            return fieldStyle;
        }

        public void setFieldStyle(PdfFontStyleInterface fieldStyle) {
            this.fieldStyle = fieldStyle;
        }
    }

}
