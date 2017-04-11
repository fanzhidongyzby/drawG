package com.github.florian.generator;

import java.util.ArrayList;
import java.util.List;

import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/11.
 */
public class MultiEvenlyGraphGenerator extends AbstractGraphGenerator {
    private final int                        count;
    private final List<EvenlyGraphGenerator> generators = new ArrayList<EvenlyGraphGenerator>();

    public MultiEvenlyGraphGenerator() {
        count = Config.getInt("generator.multiple.evenly.count", 5);
        for (int degree = 0; degree < count; degree += (count % 2 + 1)) {
            generators.add(new EvenlyGraphGenerator(count, degree));
        }
    }

    protected boolean doGenerate() {
        this.graphs.clear();
        for (EvenlyGraphGenerator generator : generators) {
            if (generator.generate()) {
                graphs.addAll(generator.getGraphList());
            } else {
                return false;
            }
        }
        return true;
    }
}
