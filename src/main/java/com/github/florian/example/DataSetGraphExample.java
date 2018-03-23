package com.github.florian.example;

import com.github.florian.factory.ProcessorFactory;
import com.github.florian.generator.CustomGraphGenerator;
import com.github.florian.processor.GraphProcessor;
import com.github.florian.utils.Config;
import com.github.florian.utils.FileUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhidong.fzd on 17/4/12.
 */
public class DataSetGraphExample implements Example {
    private String fileName = Config.getString("example.dataset.file", "dataset.txt");

    private void processDataSet(CustomGraphGenerator generator) {
        String content = FileUtil.readFileAsString(fileName);
        final String[] groups = content.split(";");
        for (String group : groups) {
            final String[] ids = group.split(",");
            for (int i = 0; i < ids.length; i++) {
                String id = ids[i];
                if (id.contains(":")) {
                    String category = StringUtils.substringBefore(id, ":");
                    ids[i] = StringUtils.substringAfter(id, ":");
                    generator.setGroup(category, ids[i]);
                }
            }
            for (int i = 1; i < ids.length; i++) {
                String end = ids[i];
                generator.addEdge(ids[0], end);
            }
        }
    }

    public void draw() {
        GraphProcessor processor = ProcessorFactory.getDefaultProcessor();

        CustomGraphGenerator generator = new CustomGraphGenerator();

        processDataSet(generator);

        processor.process(generator);
    }
}
