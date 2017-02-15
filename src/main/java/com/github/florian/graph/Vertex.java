package com.github.florian.graph;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class Vertex {

    String key;

    Object value;

    public Vertex(String key) {
        this(key, null);
    }

    public Vertex(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vertex{" + "key='" + key + '\'' + ", value=" + value + '}';
    }
}
