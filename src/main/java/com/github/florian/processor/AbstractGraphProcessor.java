package com.github.florian.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.florian.generator.MultiEvenlyGraphGenerator;
import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Point;
import com.github.florian.graph.Vertex;
import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public abstract class AbstractGraphProcessor implements GraphProcessor {
    protected static final Logger LOG           = LoggerFactory
        .getLogger(AbstractGraphProcessor.class);
    boolean                       multipleGraph = Config.getString("generator.class", "")
        .equals(MultiEvenlyGraphGenerator.class.getCanonicalName());

    int xGap = Config.getInt("processor.vis.basic.x.gap", 100);
    int yGap = Config.getInt("processor.vis.basic.y.gap", 100);


    private void processSingleGraph(Graph graph) {
        final String verticesString = getVerticesString(graph.getVertices(),
            graph.getDesc().getOrigin());
        final String edgesString = getEdgesString(graph.getEdges(), graph.getDesc().getOrigin());
        if (doProcess(verticesString, edgesString, graph.getDesc())) {
            LOG.info("graph process ok");
        } else {
            LOG.info("graph process failed");
        }
    }

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

    private void processMultipleGraph(List<Graph> graphs, Graph.Desc desc) {
        int rowCount = Config.getInt("row.count", 1);

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

    public void process(List<Graph> graphs) {
        Graph graph = new Graph();
        if (!graphs.isEmpty()) {
            graph = graphs.get(0);
        }
        if (multipleGraph) {
            processMultipleGraph(graphs, graph.getDesc());
        } else {
            processSingleGraph(graph);
        }
    }

    protected abstract String getVerticesString(List<Vertex> vertices, Point origin);

    protected abstract String getEdgesString(List<Edge> edges, Point origin);

    protected abstract boolean doProcess(String verticesString, String edgesString,
                                         Graph.Desc desc);

}
