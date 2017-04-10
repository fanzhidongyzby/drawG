package com.github.florian.builder;

import java.util.List;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;
import com.github.florian.utils.StringFormatter;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class VisBasicGraphBuilder extends GraphBuilder {

    public VisBasicGraphBuilder(Graph graph) {
        super(graph);
    }

    @Override
    public String getVerticesString() {
        StringBuffer buffer = new StringBuffer();

        final List<Vertex> vertices = graph.getVertices();
        for (Vertex vertex : vertices) {
            final String key = vertex.getKey();
            buffer.append(StringFormatter.format("                {id: {}, label: '{}'},\n", key, key));
        }
        return buffer.toString();
    }

    @Override
    public String getEdgesString() {
        StringBuffer buffer = new StringBuffer();

        final List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            final String source = edge.getSource().getKey();
            final String target = edge.getTarget().getKey();
            final String key = edge.getKey();
            final Object value = edge.getValue();

            buffer.append(StringFormatter.format("                {from: {}, to: {}},\n", source, target));
        }

        return buffer.toString();
    }
}
