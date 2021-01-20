package com.github.zhuobinchan.easy2print.pdf.core.style.strategy;

/**
 * @author zhuobin chan on 2021-01-19 18:27
 */
public class PdfFieldStyleStrategyFactory {

    public static PdfFieldStyleStrategy instanceDefaultStrategy() {
        return InstanceEnum.getInstance();
    }

    public static PdfFieldStyleStrategy newDefaultStrategy() {
        return new PdfFieldStyleDefaultStrategy();
    }

    private enum InstanceEnum {
        INSTANCE;

        private final PdfFieldStyleStrategy instanceStrategy;

        InstanceEnum() {
            this.instanceStrategy = new PdfFieldStyleDefaultStrategy();
        }

        public static PdfFieldStyleStrategy getInstance() {
            return InstanceEnum.INSTANCE.instanceStrategy;
        }
    }
}
