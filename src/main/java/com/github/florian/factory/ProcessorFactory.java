package com.github.florian.factory;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.github.florian.processor.GraphProcessor;
import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class ProcessorFactory {
    public static GraphProcessor create() {
        GraphProcessor processor = null;

        final String name = Config.getString("processor.class", "");
        try {
            Class clz = Class.forName(name);
            if (!GraphProcessor.class.isAssignableFrom(clz)) {
                throw new Exception(clz.getName() + " is invalid processor");
            }
            processor = (GraphProcessor) clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getFullStackTrace(e));
        }

        return processor;
    }
}
