package com.github.florian;

import com.github.florian.builder.GraphBuilder;
import com.github.florian.builder.VisBasicGraphBuilder;
import com.github.florian.generator.EvenlyGraphGenerator;
import com.github.florian.generator.GraphGenerator;
import com.github.florian.graph.Graph;
import com.github.florian.utils.TemplateProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class Main {

    public static void main(String[] args) {

        GraphGenerator generator = new EvenlyGraphGenerator(200, 4);
        if (generator.generate()) {
            final Graph graph = generator.getGraph();

            GraphBuilder builder = new VisBasicGraphBuilder(graph);

            Map<String, String> params = new HashMap<String, String>();
            params.put("nodes", builder.getVerticesString());
            params.put("edges", builder.getEdgesString());

            TemplateProcessor.processResource("vis.basic.template.html", "graph.html", params);
        }

    }
}
