package com.github.florian.processor;

import java.util.List;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;
import com.github.florian.utils.StringFormatter;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class SimpleArrayLazyNodeGraphProcessor extends GraphProcessor {

    @Override
    protected String getVerticesString(Graph graph) {
        StringBuffer buffer = new StringBuffer();

        final List<Vertex> vertices = graph.getVertices();
        for (Vertex vertex : vertices) {
            final String key = vertex.getKey();
            buffer.append(StringFormatter.format("  '{}', \n", key));
        }
        return StringFormatter.format("[\n{}]\n", buffer.toString());
    }

    @Override
    protected String getEdgesString(Graph graph) {
        StringBuffer buffer = new StringBuffer();

        final List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            final String source = edge.getSource().getKey();
            final String target = edge.getTarget().getKey();
            final String key = edge.getKey();
            final Object value = edge.getValue();

            buffer.append(StringFormatter.format("  {from:'{}', to:'{}', name:'{}'},\n", source,
                target, value == null ? key : StringFormatter.format("{}={}", key, value)));
        }

        return StringFormatter.format("[\n{}]\n", buffer.toString());
    }

    protected boolean doProcess(String verticesString, String edgesString) {
        LOG.info("graph vertices:");
        LOG.info("\n" + verticesString);
        LOG.info("graph edges:");
        LOG.info("\n" + edgesString);
        LOG.info("visit http://live.yworks.com/yfiles-for-html/2.0/databinding/graphbuilder/index.html and paste the info to view graph");

        return true;
    }
}
