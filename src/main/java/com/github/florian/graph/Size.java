package com.github.florian.graph;

/**
 * Created by zhidong.fzd on 17/4/11.
 */
public class Size {
    double width;
    double height;

    public Size() {
    }

    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "<" + width + ", " + height + '>';
    }
}
