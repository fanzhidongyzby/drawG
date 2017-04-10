package com.github.florian.generator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public abstract class GraphGenerator {
    protected static final Logger          LOG       = LoggerFactory
        .getLogger(GraphGenerator.class);
    protected Graph                        graph     = new Graph();
    private Map<String, Vertex>            vertexMap = new HashMap<String, Vertex>();
    private Map<Vertex, Map<Vertex, Edge>> edgeMap   = new HashMap<Vertex, Map<Vertex, Edge>>();

    public Graph getGraph() {
        return graph;
    }

    protected Vertex genVertex() {
        return genVertex(graph.getVertices().size()+1);
    }

    protected Vertex genVertex(int id) {
        return genVertex(String.valueOf(id));
    }

    protected Vertex genVertex(String id) {
        if (!vertexMap.containsKey(id)) {
            final Vertex vertex = new Vertex(id);
            graph.getVertices().add(vertex);
            vertexMap.put(id, vertex);
            return vertex;
        } else {
            return vertexMap.get(id);
        }
    }

    protected Edge genEdge(String start, String end) {
        final Vertex source = genVertex(start);
        final Vertex target = genVertex(end);
        if (!edgeMap.containsKey(source)) {
            edgeMap.put(source, new HashMap<Vertex, Edge>());
        }
        if (!edgeMap.get(source).containsKey(target)) {
            final Edge edge = new Edge(source, target);
            graph.getEdges().add(edge);
            edgeMap.get(source).put(target, edge);
        }
        return edgeMap.get(source).get(target);
    }

    protected Edge genEdge(int start, int end) {
        return genEdge(String.valueOf(start), String.valueOf(end));
    }

    protected abstract boolean doGenerate();

    /**
     * 生成数据，统计时间，保存source id
     */
    public boolean generate() {
        StopWatch watch = new StopWatch();
        watch.start();

        final boolean ok = doGenerate();

        watch.stop();

        if (!ok) {
            LOG.error("generate graph failed");
        } else {
            LOG.info(graph.getVertices().size() + " vertices and " + getGraph().getEdges().size() + " edges generated, use "
                    + watch.getTime() + " ms");
        }

        return  ok;
    }
}
