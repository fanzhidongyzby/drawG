package com.github.florian.example;

import com.github.florian.factory.ProcessorFactory;
import com.github.florian.generator.CustomGraphGenerator;
import com.github.florian.generator.GraphGenerator;
import com.github.florian.processor.GraphProcessor;

/**
 * Created by zhidong.fzd on 17/4/12.
 */
public class CustomGraphExample implements Example {
    public void draw() {
        GraphProcessor processor = ProcessorFactory.getDefaultProcessor();

        CustomGraphGenerator generator = new CustomGraphGenerator().addEdge("1", "2").addEdge("1", "3")
            .addEdge("1", "4").addEdge("2", "3").addEdge("2", "4").addEdge("3", "4")
            .addEdge("4", "5").addEdge("4", "6").addEdge("5", "6").addEdge("5", "7")
            .addEdge("6", "7").addEdge("1", "2").addEdge("1", "3").addEdge("2", "3")
            .addEdge("1", "4").addEdge("2", "4").addEdge("3", "4").addEdge("1", "5")
            .addEdge("1", "6").addEdge("1", "7").addEdge("5", "7").addEdge("6", "7")
            .addEdge("1", "8").addEdge("2", "8").addEdge("3", "8").addEdge("4", "8")
            .addEdge("1", "9").addEdge("3", "9").addEdge("3", "10").addEdge("1", "11")
            .addEdge("5", "11").addEdge("6", "11").addEdge("1", "12").addEdge("1", "13")
            .addEdge("4", "13").addEdge("1", "14").addEdge("2", "14").addEdge("3", "14")
            .addEdge("4", "14").addEdge("6", "17").addEdge("7", "17").addEdge("1", "18")
            .addEdge("2", "18").addEdge("1", "20").addEdge("2", "20").addEdge("1", "22")
            .addEdge("2", "22").addEdge("24", "26").addEdge("25", "26").addEdge("3", "28")
            .addEdge("24", "28").addEdge("25", "28").addEdge("3", "29").addEdge("24", "30")
            .addEdge("27", "30").addEdge("2", "31").addEdge("9", "31").addEdge("1", "32")
            .addEdge("25", "32").addEdge("26", "32").addEdge("29", "32").addEdge("3", "33")
            .addEdge("9", "33").addEdge("15", "33").addEdge("16", "33").addEdge("19", "33")
            .addEdge("21", "33").addEdge("23", "33").addEdge("24", "33").addEdge("30", "33")
            .addEdge("31", "33").addEdge("32", "33").addEdge("9", "34").addEdge("10", "34")
            .addEdge("14", "34").addEdge("15", "34").addEdge("16", "34").addEdge("19", "34")
            .addEdge("20", "34").addEdge("21", "34").addEdge("23", "34").addEdge("24", "34")
            .addEdge("27", "34").addEdge("28", "34").addEdge("29", "34").addEdge("30", "34")
            .addEdge("31", "34").addEdge("32", "34").addEdge("33", "34");

        processor.process(generator);
    }
}
