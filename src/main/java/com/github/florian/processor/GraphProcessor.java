package com.github.florian.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.graph.Graph;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public abstract class GraphProcessor {

    protected static final Logger LOG = LoggerFactory.getLogger(GraphProcessor.class);

    protected abstract String getVerticesString(Graph graph);

    protected abstract String getEdgesString(Graph graph);

    protected abstract boolean doProcess(String verticesString, String edgesString);

    public void process(Graph graph) {
        final String verticesString = getVerticesString(graph);
        final String edgesString = getEdgesString(graph);
        if (doProcess(verticesString, edgesString)) {
            LOG.info("graph process ok");
        } else {
            LOG.info("graph process failed");
        }
    }

}
