package com.github.florian.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;
import com.github.florian.utils.StringFormatter;
import com.github.florian.utils.TemplateProcessor;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class VisBasicGraphProcessor extends GraphProcessor {

    @Override
    protected String getVerticesString(Graph graph) {
        StringBuffer buffer = new StringBuffer();

        final List<Vertex> vertices = graph.getVertices();
        for (Vertex vertex : vertices) {
            final String key = vertex.getKey();
            buffer.append(
                StringFormatter.format("                {id: '{}', label: '{}'},\n", key, key));
        }
        return buffer.toString();
    }

    @Override
    protected String getEdgesString(Graph graph) {
        StringBuffer buffer = new StringBuffer();

        final List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            final String source = edge.getSource().getKey();
            final String target = edge.getTarget().getKey();

            buffer.append(
                StringFormatter.format("                {from: '{}', to: '{}'},\n", source, target));
        }

        return buffer.toString();
    }

    protected boolean doProcess(String verticesString, String edgesString) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("nodes", verticesString);
        params.put("edges", edgesString);

        TemplateProcessor.processResource("vis.basic.template.html", "graph.html", params);

        LOG.info("use browser to view graph.html");

        return true;
    }
}
