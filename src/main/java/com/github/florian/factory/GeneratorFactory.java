package com.github.florian.factory;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.generator.EvenlyGraphGenerator;
import com.github.florian.generator.GraphGenerator;
import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class GeneratorFactory {
    private static final Logger LOG = LoggerFactory.getLogger(GeneratorFactory.class);

    public static GraphGenerator create() {
        GraphGenerator generator = new EvenlyGraphGenerator();

        Class<?> clz = null;
        final String name = Config.getString("generator.class", "");
        try {
            clz = Class.forName(name);
            if (!GraphGenerator.class.isAssignableFrom(clz)) {
                LOG.error(clz.getName() + " is invalid generator");
            } else {
                generator = (GraphGenerator) clz.newInstance();
            }
        } catch (Exception e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }

        return generator;
    }
}
