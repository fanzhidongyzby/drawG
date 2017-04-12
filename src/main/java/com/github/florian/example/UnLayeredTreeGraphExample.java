package com.github.florian.example;

import com.github.florian.factory.ProcessorFactory;
import com.github.florian.generator.EvenlyGraphGenerator;
import com.github.florian.generator.MultiGraphGenerator;
import com.github.florian.generator.SparseGraphGenerator;
import com.github.florian.generator.TreeGraphGenerator;
import com.github.florian.processor.GraphProcessor;

/**
 * Created by zhidong.fzd on 17/4/12.
 */
public class UnLayeredTreeGraphExample implements Example {
    public void draw() {
        GraphProcessor processor = ProcessorFactory.getDefaultProcessor();

        TreeGraphGenerator generator = new TreeGraphGenerator(3, 100, 2);
        generator.getDesc().setLayered(false);

        processor.process(generator);
    }
}
