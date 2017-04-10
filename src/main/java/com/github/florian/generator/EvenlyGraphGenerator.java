package com.github.florian.generator;

import com.github.florian.builder.GraphBuilder;
import com.github.florian.builder.VisBasicGraphBuilder;
import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;

/**
 * Created by zhidong.fzd on 17/4/1.
 */
public class EvenlyGraphGenerator extends GraphGenerator {

    private final int count;
    private final int degree;

    public EvenlyGraphGenerator(int count, int degree) {

        this.count = count;
        this.degree = degree;
    }

    private void genVertex(int id) {
        graph.getVertices().add(new Vertex(String.valueOf(id)));
    }

    private void genEdge(int start, int end) {
        if (start < end) {
            graph.getEdges().add(new Edge(String.valueOf(start), String.valueOf(end)));
        }
    }

    @Override
    public boolean generate() {

        if (!(count > 0 && degree >= 0 && degree < count)) {
            System.out.println("Invalid arguments !");
            return false;
        }

        for (int index = 0; index < count; index++) {
            genVertex(index);
        }

        if (degree == 0) {
            return true;
        }

        if (count % 2 == 1 && degree % 2 != 0) {
            System.out.println("Degree should be odd when vertices' count is even !");
            return false;
        }

        // 生成边
        for (int index = 0; index < count; index++) {

            // 对面的点
            int opposite = (index + count / 2) % count;

            // 点偶数，度奇数，链接对面的点
            if (count % 2 == 0 && degree % 2 == 1) {
                genEdge(index, opposite);
            }

            // 链接对面两侧的点
            for (int i = (count + 1) % 2; i <= (degree - count % 2) / 2; i++) {
                final int previous = (opposite - i + count) % count;
                final int next = (opposite + i + count % 2) % count;

                genEdge(index, previous);
                genEdge(index, next);
            }

        }

        return true;
    }

}
