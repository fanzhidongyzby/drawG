package com.github.florian.factory;

import com.github.florian.example.Example;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class ExampleFactory {
    public static Example getDefaultExample() {
        Example example = null;

        final String name = Config.getString("example.class", "");
        try {
            Class clz = Class.forName(name);
            if (!Example.class.isAssignableFrom(clz)) {
                throw new Exception(clz.getName() + " is invalid example");
            }
            example = (Example) clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getFullStackTrace(e));
        }

        return example;
    }
}
