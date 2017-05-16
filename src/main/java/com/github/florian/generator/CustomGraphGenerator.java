package com.github.florian.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.github.florian.graph.Edge;
import org.apache.commons.lang.StringUtils;

import com.github.florian.graph.Vertex;

/**
 * Created by zhidong.fzd on 17/5/5.
 */
public class CustomGraphGenerator extends SingleGraphGenerator {

    private Map<String, HashSet<String>> vertexGroups = new HashMap<String, HashSet<String>>();
    private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
    private Map<String, Map<String, Edge>> edgeMap = new HashMap<String, Map<String, Edge>>();

    private boolean ignoreValue = false;

    private Vertex getVertex(String value) {
        if (StringUtils.isBlank(value)) {
            throw new RuntimeException("Invalid vertex");
        }

        if (!vertexMap.containsKey(value)) {
            Vertex vertex = null;
            if (ignoreValue) {
                vertex = genVertex();
            } else {
                vertex = genVertex(value);
            }
            vertexMap.put(value, vertex);
        }
        return vertexMap.get(value);
    }

    public CustomGraphGenerator setGroup(String group, String ... ids) {
        if (!vertexGroups.containsKey(group)) {
            vertexGroups.put(group, new HashSet<String>());
        }
        for (String id : ids) {
            vertexGroups.get(group).add(id);
        }
        return this;
    }

    public CustomGraphGenerator ignoreValue() {
        this.ignoreValue = true;
        return this;
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
        for (String group : vertexGroups.keySet()) {
            final HashSet<String> ids = vertexGroups.get(group);
            for (String id : ids) {
                if(vertexMap.containsKey(id)) {
                    vertexMap.get(id).setCategory(group);
                }
            }
        }
        return true;
    }
}
