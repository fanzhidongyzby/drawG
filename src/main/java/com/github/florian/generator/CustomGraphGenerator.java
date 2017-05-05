package com.github.florian.generator;

import java.util.HashMap;
import java.util.Map;

import com.github.florian.graph.Edge;
import org.apache.commons.lang.StringUtils;

import com.github.florian.graph.Vertex;

/**
 * Created by zhidong.fzd on 17/5/5.
 */
public class CustomGraphGenerator extends SingleGraphGenerator {

    private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
    private Map<String, Map<String, Edge>> edgeMap = new HashMap<String, Map<String, Edge>>();

    public CustomGraphGenerator() {
        desc.setDirected(true);
    }

    private Vertex getVertex(String value) {
        if (StringUtils.isBlank(value)) {
            throw new RuntimeException("Invalid vertex");
        }

        if (!vertexMap.containsKey(value)) {
            final Vertex vertex = genVertex(value);
            vertexMap.put(value, vertex);
        }
        return vertexMap.get(value);
    }

    public CustomGraphGenerator addEdge(long source, long target) {
        if (source < 0 || target < 0) {
            throw new RuntimeException("Invalid edge vertex");
        }
        return addEdge(String.valueOf(source), String.valueOf(target));
    }

    public CustomGraphGenerator addEdge(String source, String target) {
        if (StringUtils.isBlank(source) || StringUtils.isBlank(target)) {
            throw new RuntimeException("Invalid edge vertex");
        }

        if (!edgeMap.containsKey(source)) {
            edgeMap.put(source, new HashMap<String, Edge>());
        } else if (edgeMap.get(source).containsKey(target)) {
            return this;
        }

        final Vertex v1 = getVertex(source);
        final Vertex v2 = getVertex(target);

        final Edge edge = genEdge(v1, v2);
        edgeMap.get(source).put(target, edge);

        return this;
    }

    @Override
    protected boolean doGenerate() {
        return true;
    }
}
