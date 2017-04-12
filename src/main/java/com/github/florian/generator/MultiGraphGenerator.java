package com.github.florian.generator;

import java.util.ArrayList;
import java.util.List;

import com.github.florian.graph.Graph;

/**
 * Created by zhidong.fzd on 17/4/11.
 */
public class MultiGraphGenerator extends AbstractGraphGenerator {

    private List<SingleGraphGenerator> generators = new ArrayList<SingleGraphGenerator>();

    public void addGenerator(SingleGraphGenerator generator) {
        if (generator != null) {
            generators.add(generator);
        }
    }

    public List<Graph> generate() {
        List<Graph> graphs = new ArrayList<Graph>();
        for (SingleGraphGenerator generator : generators) {
            final Graph graph = generator.generate();
            if (graph != null) {
                graphs.add(graph);
            }
        }
        return graphs;
    }
}
