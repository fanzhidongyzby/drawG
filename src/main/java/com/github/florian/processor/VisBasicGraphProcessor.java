package com.github.florian.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.florian.graph.*;
import com.github.florian.utils.Config;
import com.github.florian.utils.StringFormatter;
import com.github.florian.utils.TemplateProcessor;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class VisBasicGraphProcessor extends AbstractGraphProcessor {

    public VisBasicGraphProcessor() {
    }

    public VisBasicGraphProcessor(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    protected Size getSize(int verticesCount) {
        final double radios = getRadios(verticesCount);
        return new Size(radios * 2, radios * 2);
    }

    private double getRadios(int verticesCount) {
        if (verticesCount == 0) {
            return 0;
        }
        return edgeLength / 2 / Math.sin(Math.PI / verticesCount);
    }

    private void rePosition(int verticesCount, Point origin) {
        double r = getRadios(verticesCount);
        origin.setX(origin.getX() + r * 2);
        origin.setY(origin.getY() + r * 2);
    }

    private String getPosition(int verticesCount, int index, Point origin) {
        double radios = getRadios(verticesCount);
        double angle = Math.PI / 2 - 2 * Math.PI / verticesCount * index;
        double y = -Math.sin(angle) * radios + origin.getY();
        double x = Math.cos(angle) * radios + origin.getX();
        return "x: " + x + ", y:" + y;
    }

    @Override
    protected String getVerticesString(List<Vertex> vertices, Point origin) {
        StringBuffer buffer = new StringBuffer();
        final int vertexCount = vertices.size();

        int i = 0;
        for (Vertex vertex : vertices) {
            final String key = vertex.getKey();
            final String value = (String) vertex.getValue();
            buffer.append(StringFormatter.format("                {id: '{}', label: '{}', {}},\n",
                key, value, getPosition(vertexCount, i++, origin)));
        }

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

    protected boolean doProcess(String verticesString, String edgesString, Desc desc) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("nodes", verticesString);
        params.put("edges", edgesString);
        params.put("physics", String.valueOf(!desc.isMultiple()));
        params.put("directed", String.valueOf(desc.isDirected()));
        params.put("layered", String.valueOf(desc.isLayered()));

        TemplateProcessor.processResource("vis.basic.template.html", "graph.html", params);

        LOG.info("use browser to view graph.html");

        return true;
    }
}
