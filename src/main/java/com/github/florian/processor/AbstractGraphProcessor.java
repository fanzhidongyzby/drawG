package com.github.florian.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.generator.GraphGenerator;
import com.github.florian.generator.MultiGraphGenerator;
import com.github.florian.generator.SingleGraphGenerator;
import com.github.florian.graph.*;
import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public abstract class AbstractGraphProcessor implements GraphProcessor {
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractGraphProcessor.class);

    private ArrayList<Integer> splitList(int size, int row) {
        ArrayList<Integer> rows = new ArrayList<Integer>();
        if (size == 0 || row == 0) {
            return rows;
        }

        int col = size / row;
        int mod = size % row;
        for (int i = 0; i < row; i++) {
            int len = col + ((i >= row - mod) ? 1 : 0);
            if (len != 0) {
                rows.add(len);
            }
        }

        return rows;
    }

    private void processSingleGraph(Graph graph, Desc desc) {
        final String verticesString = getVerticesString(graph.getVertices(),
            graph.getDesc().getOrigin());
        final String edgesString = getEdgesString(graph.getEdges(), graph.getDesc().getOrigin());
        if (doProcess(verticesString, edgesString, desc)) {
            LOG.info("graph process ok");
        } else {
            LOG.info("graph process failed");
        }
    }

    private void processMultipleGraph(List<Graph> graphs, Desc desc) {
        int rowCount = Config.getInt("row.count", 1);
        int xGap = Config.getInt("processor.vis.basic.x.gap", 100);
        int yGap = Config.getInt("processor.vis.basic.y.gap", 100);

        final ArrayList<Integer> rows = splitList(graphs.size(), rowCount);

        String verticesString = "";
        String edgesString = "";
        final Point origin = desc.getOrigin();
        int passedCount = 0;
        for (int size : rows) {
            double originY = origin.getY();
            double max = origin.getY();
            for (int j = 0; j < size; j++) {
                int index = passedCount + j;

                final Graph graph = graphs.get(index);
                verticesString += getVerticesString(graph.getVertices(), origin);
                edgesString += getEdgesString(graph.getEdges(), origin);

                max = Math.max(max, origin.getY());
                origin.setX(origin.getX() + xGap);
                origin.setY(originY);
            }
            passedCount += size;

            origin.setX(0);
            origin.setY(max + yGap);
        }
        if (doProcess(verticesString, edgesString, desc)) {
            LOG.info("graphs process ok");
        } else {
            LOG.info("graphs process failed");
        }
    }

    public void process(GraphGenerator generator) {
        final boolean multiple = MultiGraphGenerator.class.isAssignableFrom(generator.getClass());

        if (multiple) {
            final MultiGraphGenerator multiGraphGenerator = (MultiGraphGenerator) generator;
            final List<Graph> graphs = multiGraphGenerator.generate();
            processMultipleGraph(graphs, multiGraphGenerator.getDesc());
        } else {
            final SingleGraphGenerator singleGraphGenerator = (SingleGraphGenerator) generator;
            final Graph graph = singleGraphGenerator.generate();
            processSingleGraph(graph, singleGraphGenerator.getDesc());
        }
    }

    protected abstract String getVerticesString(List<Vertex> vertices, Point origin);

    protected abstract String getEdgesString(List<Edge> edges, Point origin);

    protected abstract boolean doProcess(String verticesString, String edgesString, Desc desc);

}
