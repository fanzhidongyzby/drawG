package com.github.florian.builder;

import com.github.florian.graph.Graph;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public abstract class GraphBuilder {

    protected Graph graph;

    public GraphBuilder(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public abstract String getVerticesString();

    public abstract String getEdgesString();

}
