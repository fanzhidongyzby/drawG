package com.github.florian.example;

import com.github.florian.generator.EvenlyGraphGenerator;
import com.github.florian.generator.MultiGraphGenerator;
import com.github.florian.generator.SingleGraphGenerator;
import com.github.florian.processor.GraphProcessor;
import com.github.florian.processor.VisBasicGraphProcessor;

/**
 * Created by zhidong.fzd on 17/4/12.
 */
public class RingGraphExample implements Example {
    public void draw() {
        GraphProcessor processor = new VisBasicGraphProcessor(1);
        MultiGraphGenerator generators = new MultiGraphGenerator();
        for (int i = 3; i <= 6; i++) {
            SingleGraphGenerator generator = new EvenlyGraphGenerator(i, 2);
            generators.addGenerator(generator);
        }

        processor.process(generators);
    }
}
