package com.github.florian.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class Graph {

    String       name;

    List<Vertex> vertices = new ArrayList<Vertex>();

    List<Edge>   edges    = new ArrayList<Edge>();

    public Graph() {
    }

    public Graph(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "Graph{" + "key='" + name + '\'' + ", vertices="
               + Arrays.toString(vertices.toArray()) + ", edges=" + Arrays.toString(edges.toArray())
               + '}';
    }
}
