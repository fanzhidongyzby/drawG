package com.github.florian.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Point;
import com.github.florian.graph.Vertex;
import com.github.florian.utils.Config;
import com.github.florian.utils.StringFormatter;
import com.github.florian.utils.TemplateProcessor;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class VisBasicGraphProcessor extends AbstractGraphProcessor {

    private void rePosition(int verticesCount, int edgeLength, Point origin) {
        if (verticesCount == 0) {
            return;
        }
        double pi = Math.PI;
        int n = verticesCount;
        int l = edgeLength;
        double r = l / 2 / Math.sin(pi / n);
        origin.setX(origin.getX() + r * 2);
        origin.setY(origin.getY() + r * 2);
    }

    private String getPosition(int verticesCount, int edgeLength, int index, Point origin) {
        double pi = Math.PI;
        int n = verticesCount;
        int l = edgeLength;
        double r = l / 2 / Math.sin(pi / n);
        double init = (n % 2 != 0) ? pi / 2 : pi / 2 - pi / n;
        double angle = init - 2 * pi / n * index;
        double y = -Math.sin(angle) * r + origin.getY();
        double x = Math.cos(angle) * r + origin.getX();
        return "x: " + x + ", y:" + y;
    }

    @Override
    protected String getVerticesString(List<Vertex> vertices, Point origin) {
        StringBuffer buffer = new StringBuffer();
        final int vertexCount = vertices.size();
        int edgeLength = Config.getInt("processor.vis.basic.edge.length", 100);

        int i = 0;
        for (Vertex vertex : vertices) {
            final String key = vertex.getKey();
            final String value = (String)vertex.getValue();
            buffer.append(StringFormatter.format("                {id: '{}', label: '{}', {}},\n",
                key, value, getPosition(vertexCount, edgeLength, i++, origin)));
        }

        // reset origin
        rePosition(vertexCount, edgeLength, origin);

        return buffer.toString();
    }

    @Override
    protected String getEdgesString(List<Edge> edges, Point origin) {
        StringBuffer buffer = new StringBuffer();

        for (Edge edge : edges) {
            final String source = edge.getSource().getKey();
            final String target = edge.getTarget().getKey();

            buffer.append(StringFormatter.format("                {from: '{}', to: '{}'},\n",
                source, target));
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
