package com.github.florian.processor;

import java.util.ArrayList;
import java.util.HashMap;
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
    protected static final Logger     LOG        = LoggerFactory
        .getLogger(AbstractGraphProcessor.class);

    protected int                     rowCount   = Config.getInt("row.count", 1);
    protected int                     edgeLength = Config.getInt("processor.vis.basic.edge.length",
        100);
    private int                       xGap       = Config.getInt("processor.vis.basic.x.gap", 100);
    private int                       yGap       = Config.getInt("processor.vis.basic.y.gap", 100);
    private HashMap<Integer, Integer> layout     = new HashMap<Integer, Integer>();

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

    private Desc[][] getLayout(List<Graph> graphs, Desc desc) {
        final int graphCount = graphs.size();
        final Point origin = desc.getOrigin();

        // 计算每行列数
        final ArrayList<Integer> colCounts = splitList(graphCount, rowCount);

        // 构建描述矩阵
        final int rowCount = colCounts.size();
        int colCount = graphCount / this.rowCount;
        colCount += (graphCount % this.rowCount == 0) ? 0 : 1;
        Desc[][] graphDescMatrix = new Desc[rowCount][colCount];

        int passedCount = 0;
        for (int i = 0; i < rowCount; i++) {
            graphDescMatrix[i] = new Desc[colCount];

            for (int j = 0; j < colCounts.get(i); j++) {
                graphDescMatrix[i][j] = new Desc();
                final Graph graph = graphs.get(passedCount + j);
                final Size size = getSize(graph.getVertices().size());
                graphDescMatrix[i][j].setOrigin(new Point());
                graphDescMatrix[i][j].setSize(size);
            }

            passedCount += colCounts.get(i);
        }

        // 对齐，修正y
        double yBaseLine = 0;
        double yAlign = 0;
        for (int i = 0; i < rowCount; i++) {

            // 计算最大高度的图
            Desc maxHeightGraphDesc = new Desc();
            for (int j = 0; j < colCount && graphDescMatrix[i][j] != null; j++) {
                final double height = graphDescMatrix[i][j].getSize().getHeight();
                if (height > maxHeightGraphDesc.getSize().getHeight()) {
                    maxHeightGraphDesc = graphDescMatrix[i][j];
                }
            }
            yAlign = maxHeightGraphDesc.getOrigin().getY()
                     + maxHeightGraphDesc.getSize().getHeight() / 2;

            // 修正原点y值
            for (int j = 0; j < colCount && graphDescMatrix[i][j] != null; j++) {
                final Point graphPoint = graphDescMatrix[i][j].getOrigin();
                graphPoint.setY(yBaseLine + yAlign);
            }

            yBaseLine += maxHeightGraphDesc.getSize().getHeight() + yGap;
        }

        // 对齐，修正x
        double xBaseLine = 0;
        double xAlign = 0;
        for (int i = 0; i < colCount; i++) {

            // 计算最大宽度的图
            Desc maxWidthGraphDesc = new Desc();
            for (int j = rowCount - 1; j >= 0 && graphDescMatrix[j][i] != null; j--) {
                final double width = graphDescMatrix[j][i].getSize().getWidth();
                if (width > maxWidthGraphDesc.getSize().getWidth()) {
                    maxWidthGraphDesc = graphDescMatrix[j][i];
                }
            }
            xAlign = maxWidthGraphDesc.getOrigin().getX()
                     + maxWidthGraphDesc.getSize().getWidth() / 2;

            // 修正原点x值
            for (int j = rowCount - 1; j >= 0 && graphDescMatrix[j][i] != null; j--) {
                final Point graphPoint = graphDescMatrix[j][i].getOrigin();
                graphPoint.setX(xBaseLine + xAlign);
            }

            xBaseLine += maxWidthGraphDesc.getSize().getWidth() + xGap;
        }

        return graphDescMatrix;
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
        final Desc[][] layout = getLayout(graphs, desc);
        String verticesString = "";
        String edgesString = "";
        int index = 0;
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length && layout[i][j] != null; j++, index++) {
                final Graph graph = graphs.get(index);
                final Point origin = layout[i][j].getOrigin();
                verticesString += getVerticesString(graph.getVertices(), origin);
                edgesString += getEdgesString(graph.getEdges(), origin);
            }
        }

        if (doProcess(verticesString, edgesString, desc)) {
            LOG.info("graphs process ok");
        } else {
            LOG.info("graphs process failed");
        }
    }

    private void processMultipleGraph2(List<Graph> graphs, Desc desc) {

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

    protected abstract Size getSize(int verticesCount);

    protected abstract String getVerticesString(List<Vertex> vertices, Point origin);

    protected abstract String getEdgesString(List<Edge> edges, Point origin);

    protected abstract boolean doProcess(String verticesString, String edgesString, Desc desc);

}
