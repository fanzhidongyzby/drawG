package com.github.florian.processor;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.graph.Graph;

import java.util.List;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public abstract class GraphProcessor {

    protected static final Logger LOG = LoggerFactory.getLogger(GraphProcessor.class);

    protected abstract String getVerticesString(List<Vertex> vertices);

    protected abstract String getEdgesString(List<Edge> edges);

    protected abstract boolean doProcess(String verticesString, String edgesString, Graph.Desc desc);

    public void process(Graph graph) {
        final String verticesString = getVerticesString(graph.getVertices());
        final String edgesString = getEdgesString(graph.getEdges());
        if (doProcess(verticesString, edgesString, graph.getDesc())) {
            LOG.info("graph process ok");
        } else {
            LOG.info("graph process failed");
        }
    }

}
