package com.github.florian.utils;

import org.slf4j.helpers.MessageFormatter;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class StringFormatter {

    /**
     * 快速格式化
     * @param fmt
     * @param values
     * @return
     */
    public static String format(String fmt, Object... values) {
        return MessageFormatter.arrayFormat(fmt, values).getMessage();
    }

}
