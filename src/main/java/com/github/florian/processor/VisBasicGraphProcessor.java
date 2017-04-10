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
    protected String getVerticesString(List<Vertex> vertices) {
        StringBuffer buffer = new StringBuffer();

        for (Vertex vertex : vertices) {
            final String key = vertex.getKey();
            buffer.append(
                StringFormatter.format("                {id: '{}', label: '{}'},\n", key, key));
        }
        return buffer.toString();
    }

    @Override
    protected String getEdgesString(List<Edge> edges) {
        StringBuffer buffer = new StringBuffer();

        for (Edge edge : edges) {
            final String source = edge.getSource().getKey();
            final String target = edge.getTarget().getKey();

            buffer.append(
                StringFormatter.format("                {from: '{}', to: '{}'},\n", source, target));
        }

        return buffer.toString();
    }

    protected boolean doProcess(String verticesString, String edgesString, Graph.Desc desc) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("nodes", verticesString);
        params.put("edges", edgesString);
        params.put("directed", String.valueOf(desc.isDirected()));
        params.put("layered", String.valueOf(desc.isLayered()));

        TemplateProcessor.processResource("vis.basic.template.html", "graph.html", params);

        LOG.info("use browser to view graph.html");

        return true;
    }
}
