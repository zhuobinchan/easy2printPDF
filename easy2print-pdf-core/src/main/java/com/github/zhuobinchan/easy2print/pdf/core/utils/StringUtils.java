package com.github.zhuobinchan.easy2print.pdf.core.utils;

/**
 * @author zhuobin chan on 2021-01-19 18:10
 */
public class StringUtils {
    public static final String EMPTY = "";

    public static boolean contains(final String seq, final String searchSeq) {
        if (seq == null || searchSeq == null) {
            return false;
        }
        return seq.contains(searchSeq);
    }

    public static String replace(final String text, final String searchString, final String replacement) {
        if (isEmpty(text) || isEmpty(searchString) || isEmpty(replacement)) {
            return text;
        }

        return text.replace(searchString, replacement);
    }

    public static boolean isEmpty(String cs) {
        return cs == null || cs.length() == 0;
    }

}
