package com.github.florian.generator;

import java.util.List;

import com.github.florian.graph.Graph;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public interface GraphGenerator {

    List<Graph> getGraphList();

    boolean generate();
}
