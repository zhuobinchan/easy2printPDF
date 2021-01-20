package com.github.zhuobinchan.easy2print.pdf.core.converter;

/**
 * @author zhuobin chan on 2021-01-19 18:24
 */
public class PdfConverterStrategyFactory {


    public static PdfConverterStrategy instanceDefaultStrategy() {
        return InstanceEnum.getInstance();
    }

    public static PdfConverterStrategy newDefaultStrategy() {
        return new PdfConverterDefaultStrategy();
    }

    private enum InstanceEnum {
        INSTANCE;

        private final PdfConverterStrategy instanceStrategy;

        InstanceEnum() {
            this.instanceStrategy = new PdfConverterDefaultStrategy();
        }

        protected static PdfConverterStrategy getInstance() {
            return InstanceEnum.INSTANCE.instanceStrategy;
        }
    }
}
