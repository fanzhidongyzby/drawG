package com.github.florian.processor;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhidong.fzd on 17/4/6.
 */
public class SimpleArrayLazyNodeGraphProcessorTest {
    GraphProcessor graphBuilder;
    Graph graph;

    @Before
    public void setUp() {
        final Vertex vertex1 = new Vertex("v1");
        final Vertex vertex2 = new Vertex("v2");
        final Edge edge1 = new Edge(vertex1, vertex2, "e1", "v1");
        final Edge edge2 = new Edge(vertex1, vertex2, "e2", "v2");

        graph = new Graph("g");
        graph.getVertices().add(vertex1);
        graph.getVertices().add(vertex2);
        graph.getEdges().add(edge1);
        graph.getEdges().add(edge2);

        graphBuilder = new SimpleArrayLazyNodeGraphProcessor();
    }

    @Test
    public void getVerticesString() {
        final String verticesString = graphBuilder.getVerticesString(graph);
        Assert.assertNotNull(verticesString);
        System.out.println("verticesString = \n" + verticesString);
    }

    @Test
    public void getEdgesString() {
        final String edgesString = graphBuilder.getEdgesString(graph);
        Assert.assertNotNull(edgesString);
        System.out.println("edgesString = \n" + edgesString);
    }

}