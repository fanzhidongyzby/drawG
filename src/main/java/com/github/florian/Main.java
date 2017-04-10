package com.github.florian;

import com.github.florian.factory.GeneratorFactory;
import com.github.florian.factory.ProcessorFactory;
import com.github.florian.generator.GraphGenerator;
import com.github.florian.graph.Graph;
import com.github.florian.processor.GraphProcessor;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class Main {

    public static void main(String[] args) {

        GraphGenerator generator = GeneratorFactory.create();
        GraphProcessor processor = ProcessorFactory.create();

        if (generator.generate()) {
            final Graph graph = generator.getGraph();
            processor.process(graph);
        }

    }
}
