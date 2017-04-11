package com.github.florian.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;
import com.github.florian.utils.StringFormatter;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public abstract class AbstractGraphGenerator implements GraphGenerator {
    protected static final Logger          LOG      = LoggerFactory
        .getLogger(AbstractGraphGenerator.class);
    private static int                     vertexID = 0;
    protected List<Graph>                  graphs   = new ArrayList<Graph>();
    protected Graph                        graph    = new Graph();
    private Map<Vertex, Map<Vertex, Edge>> edgeMap  = new HashMap<Vertex, Map<Vertex, Edge>>();

    public AbstractGraphGenerator() {
        graphs.add(graph);
    }

    public List<Graph> getGraphList() {
        return graphs;
    }

    protected Vertex genVertex() {
        final int id = vertexID++;
        final Vertex vertex = new Vertex(String.valueOf(id), String.valueOf(id));
        graph.getVertices().add(vertex);

        return vertex;
    }

    protected Vertex genVertex(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new RuntimeException("invalid vertex value");
        }
        final int id = vertexID++;
        final Vertex vertex = new Vertex(String.valueOf(id), value);
        graph.getVertices().add(vertex);

        return vertex;
    }

    protected Edge genEdge(Vertex source, Vertex target) {
        if (source == null || target == null) {
            throw new RuntimeException("invalid vertex when create edge");
        }

        if (!edgeMap.containsKey(source)) {
            edgeMap.put(source, new HashMap<Vertex, Edge>());
        }
        if (edgeMap.get(source).containsKey(target)) {
            throw new RuntimeException(StringFormatter.format("edge from {} to {} exists",
                source.getKey(), target.getKey()));
        }

        final Edge edge = new Edge(source, target);
        graph.getEdges().add(edge);
        edgeMap.get(source).put(target, edge);

        return edge;
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
            int verticesCount = 0;
            int edgesCount = 0;
            for (Graph graph : graphs) {
                verticesCount += graph.getVertices().size();
                edgesCount += graph.getEdges().size();
            }
            LOG.info(verticesCount + " vertices and " + edgesCount + " edges generated, use "
                     + watch.getTime() + " ms");
        }

        return ok;
    }
}
