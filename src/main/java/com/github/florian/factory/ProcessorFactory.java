package com.github.florian.factory;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.processor.GraphProcessor;
import com.github.florian.processor.VisBasicGraphProcessor;
import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class ProcessorFactory {
    private static final Logger   LOG      = LoggerFactory.getLogger(ProcessorFactory.class);

    public static GraphProcessor create() {
        GraphProcessor processor = new VisBasicGraphProcessor();

        Class<?> clz = null;
        final String name = Config.getString("processor.class", "");
        try {
            clz = Class.forName(name);
            if (!GraphProcessor.class.isAssignableFrom(clz)) {
                LOG.error(clz.getName() + " is invalid processor");
            } else {
                processor = (GraphProcessor) clz.newInstance();
            }
        } catch (Exception e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }

        return processor;
    }
}
