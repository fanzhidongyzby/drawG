package com.github.florian.builder;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;
import com.sun.tools.javac.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
class SimpleArrayLazyNodeGraphBuilderTest {

    GraphBuilder graphBuilder;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        final Vertex vertex1 = new Vertex("v1");
        final Vertex vertex2 = new Vertex("v2");
        final Edge edge1 = new Edge(vertex1, vertex2, "e1", "v1");
        final Edge edge2 = new Edge(vertex1, vertex2, "e2", "v2");

        final Graph graph = new Graph("g");
        graph.getVertices().add(vertex1);
        graph.getVertices().add(vertex2);
        graph.getEdges().add(edge1);
        graph.getEdges().add(edge2);

        graphBuilder = new SimpleArrayLazyNodeGraphBuilder(graph);
    }

    @org.junit.jupiter.api.Test
    void getVerticesString() {
        final String verticesString = graphBuilder.getVerticesString();
        Assert.checkNonNull(verticesString);
        System.out.println("verticesString = \n" + verticesString);
    }

    @org.junit.jupiter.api.Test
    void getEdgesString() {
        final String edgesString = graphBuilder.getEdgesString();
        Assert.checkNonNull(edgesString);
        System.out.println("edgesString = \n" + edgesString);
    }

}