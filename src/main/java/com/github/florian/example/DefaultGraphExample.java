package com.github.florian.example;

import com.github.florian.factory.GeneratorFactory;
import com.github.florian.factory.ProcessorFactory;
import com.github.florian.generator.GraphGenerator;
import com.github.florian.processor.GraphProcessor;

/**
 * Created by zhidong.fzd on 17/4/12.
 */
public class DefaultGraphExample implements Example {
    public void draw() {
        GraphProcessor processor = ProcessorFactory.getDefaultProcessor();

        GraphGenerator generator = GeneratorFactory.getDefaultGenerator();

        processor.process(generator);
    }
}
