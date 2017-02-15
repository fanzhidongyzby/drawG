package com.github.florian.graph;

/**
 * Created by zhidong.fzd on 17/2/15.
 */
public class Edge {

    Vertex source;

    Vertex target;

    String key;

    Object value;

    public Edge(Vertex source, Vertex target, String key) {
        this(source, target, key, null);
    }

    public Edge(Vertex source, Vertex target, String key, Object value) {
        this.source = source;
        this.target = target;
        this.key = key;
        this.value = value;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
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
        return "Edge{" + "source=" + source + ", target=" + target + ", key='" + key + '\''
               + ", value=" + value + '}';
    }
}
