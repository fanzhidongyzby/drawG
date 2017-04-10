package com.github.florian.utils;

import java.util.Map;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class TemplateProcessor {

    public static String process(String content, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            final String match = "\\$\\{" + key + "}";
            content = content.replaceAll(match, value);
        }
        return content;
    }

    public static boolean processResource(String resource, String outputFile,
                                          Map<String, String> params) {
        String string = FileUtil.readResourceAsString(resource);
        string = process(string, params);
        return FileUtil.writeStringToFile(string, outputFile);
    }
}
