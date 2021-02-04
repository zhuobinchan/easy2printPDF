package com.github.zhuobinchan.easy2print.pdf.core.model;

/**
 * @author zhuobin chan on 2021-01-19 18:06
 */
public class PdfModel {
    private String fieldName;
    private Object fieldValue;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
