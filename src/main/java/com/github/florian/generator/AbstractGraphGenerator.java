package com.github.florian.generator;

import com.github.florian.graph.Desc;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public abstract class AbstractGraphGenerator implements GraphGenerator {

    protected Desc desc = new Desc();

    public Desc getDesc() {
        return desc;
    }
}
