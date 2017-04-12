package com.github.florian;

import com.github.florian.factory.GeneratorFactory;
import com.github.florian.factory.ProcessorFactory;
import com.github.florian.generator.*;
import com.github.florian.processor.GraphProcessor;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class Main {

    private static void drawMultiGraph() {
        GraphProcessor processor = ProcessorFactory.getDefaultProcessor();

        MultiGraphGenerator multiGraphGenerator = new MultiGraphGenerator();
        multiGraphGenerator.addGenerator(new EvenlyGraphGenerator(10, 6));
        multiGraphGenerator.addGenerator(new TreeGraphGenerator(2, 3, 4));
        multiGraphGenerator.addGenerator(new SparseGraphGenerator(3));
        multiGraphGenerator.addGenerator(new EvenlyGraphGenerator(7, 4));

        processor.process(multiGraphGenerator);
    }

    private static void drawSingleGraph() {
        GraphProcessor processor = ProcessorFactory.getDefaultProcessor();

        GraphGenerator generator = GeneratorFactory.getDefaultGenerator();

        processor.process(generator);
    }

    public static void main(String[] args) {

//        drawSingleGraph();
        drawMultiGraph();

    }
}
