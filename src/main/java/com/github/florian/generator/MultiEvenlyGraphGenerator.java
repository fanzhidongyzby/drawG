package com.github.florian.generator;

import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/11.
 */
public class MultiEvenlyGraphGenerator extends MultiGraphGenerator {
    private final int count;

    public MultiEvenlyGraphGenerator() {
        count = Config.getInt("generator.evenly.count", 5);
        for (int degree = 0; degree < count; degree += (count % 2 + 1)) {
            addGenerator(new EvenlyGraphGenerator(count, degree));
        }
    }
}
