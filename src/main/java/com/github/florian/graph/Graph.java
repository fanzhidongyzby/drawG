package com.github.florian.graph;

import com.github.florian.Main;

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
    Desc         desc     = new Desc();

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

    public Desc getDesc() {
        return desc;
    }

    public void setDesc(Desc desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Graph{" + "key='" + name + '\'' + ", vertices="
               + Arrays.toString(vertices.toArray()) + ", edges=" + Arrays.toString(edges.toArray())
               + '}';
    }

    public class Desc {

        boolean directed;

        boolean layered;

        Point origin = new Point();

        public boolean isDirected() {
            return directed;
        }

        public void setDirected(boolean directed) {
            this.directed = directed;
        }

        public boolean isLayered() {
            return layered;
        }

        public void setLayered(boolean layered) {
            this.layered = layered;
        }

        public Point getOrigin() {
            return origin;
        }

        public void setOrigin(Point origin) {
            this.origin = origin;
        }
    }
}
