package com.github.florian.generator;

import com.github.florian.graph.Graph;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public abstract class GraphGenerator {

    protected Graph graph = new Graph();

    public Graph getGraph() {
        return graph;
    }

    public abstract boolean generate();
}
