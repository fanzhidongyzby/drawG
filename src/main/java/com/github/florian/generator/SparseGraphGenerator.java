package com.github.florian.generator;

import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/1.
 */
public class SparseGraphGenerator extends TreeGraphGenerator {

    private int edgeID = 0;

    private int factor;

    public SparseGraphGenerator() {
        this(Config.getInt("generator.sparse.factor", 2));
    }

    public SparseGraphGenerator(int factor) {
        super();
        super.inDegree = 1;
        this.factor = factor > 1 ? factor : 2;
    }

    public SparseGraphGenerator(int depth, int firstOutDegree, int otherOutDegree, int factor) {
        super(depth, firstOutDegree, otherOutDegree, 1);
        this.factor = factor > 1 ? factor : 2;
    }

    protected void genEdge(String start, String end) {
        edgeID++;
        if (edgeID % factor == 0) {
            super.genEdge(start, end);
        }
    }

}
