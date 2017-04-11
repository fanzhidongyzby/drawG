package com.github.florian.processor;

import com.github.florian.graph.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.florian.graph.Edge;
import com.github.florian.graph.Graph;
import com.github.florian.graph.Vertex;

/**
 * Created by zhidong.fzd on 17/4/6.
 */
public class VisBasicGraphProcessorTest {
    AbstractGraphProcessor processor;
    Graph graph;

    @Before
    public void setUp() {
        final Vertex vertex1 = new Vertex("1");
        final Vertex vertex2 = new Vertex("2");
        final Edge edge1 = new Edge(vertex1, vertex2, "", "1");
        final Edge edge2 = new Edge(vertex1, vertex2, "", "2");

        final Graph graph = new Graph("g");
        graph.getVertices().add(vertex1);
        graph.getVertices().add(vertex2);
        graph.getEdges().add(edge1);
        graph.getEdges().add(edge2);

        processor = new VisBasicGraphProcessor();
    }

    @Test
    public void getVerticesString() {
        final String verticesString = processor.getVerticesString(graph.getVertices(), new Point());
        Assert.assertNotNull(verticesString);
        System.out.println(verticesString);
    }

    @Test
    public void getEdgesString() {
        final String edgesString = processor.getEdgesString(graph.getEdges(), new Point());
        Assert.assertNotNull(edgesString);
        System.out.println(edgesString);
    }

}