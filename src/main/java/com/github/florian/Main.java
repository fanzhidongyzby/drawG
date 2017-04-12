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
        multiGraphGenerator.addGenerator(new EvenlyGraphGenerator(22, 3));
        multiGraphGenerator.addGenerator(new TreeGraphGenerator(3,3,2));
        multiGraphGenerator.addGenerator(new SparseGraphGenerator(3,3,2,1));
        multiGraphGenerator.addGenerator(new EvenlyGraphGenerator(22, 20));

        processor.process(multiGraphGenerator);
    }

    private static void drawSingleGraph() {
        GraphProcessor processor = ProcessorFactory.getDefaultProcessor();

        GraphGenerator generator = GeneratorFactory.getDefaultGenerator();

        processor.process(generator);
    }

    public static void main(String[] args) {

        drawSingleGraph();
//        drawMultiGraph();

    }
}
