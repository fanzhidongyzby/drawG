package com.github.florian.factory;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.generator.GraphGenerator;
import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class GeneratorFactory {
    public static GraphGenerator getDefaultGenerator() {
        GraphGenerator generator = null;

        final String name = Config.getString("generator.class", "");
        try {
            Class clz = Class.forName(name);
            if (!GraphGenerator.class.isAssignableFrom(clz)) {
                throw new Exception(clz.getName() + " is invalid generator");
            }
            generator = (GraphGenerator) clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getFullStackTrace(e));
        }

        return generator;
    }
}
