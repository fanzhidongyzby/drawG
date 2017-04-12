package com.github.florian.graph;

/**
 * Created by zhidong.fzd on 17/4/12.
 */
public class Desc {

    private boolean directed;

    private boolean layered;

    private Point   origin = new Point();

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
