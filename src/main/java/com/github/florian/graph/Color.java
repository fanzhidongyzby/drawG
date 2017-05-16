package com.github.florian.graph;

import java.util.Random;

/**
 * Created by zhidong.fzd on 17/5/15.
 */
public class Color {
    private int r;
    private int g;
    private int b;

    public static Color BLACK;

    static {
        BLACK = new Color();
        BLACK.r = BLACK.g = BLACK.b = 0;
    }

    public Color() {
        Random random = new Random();
        r = (int) (random.nextDouble() * 256);
        g = (int) (random.nextDouble() * 256);
        b = (int) (random.nextDouble() * 256);
    }

    public static void main(String[] args) {
        System.out.println("new Color() = " + new Color());
        System.out.println("new Color() = " + new Color());
        System.out.println("new Color() = " + new Color());
    }

    @Override
    public String toString() {
        return "rgb(" + r + "," + g + "," + b + ")";
    }
}
