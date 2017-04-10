package com.github.florian.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class FileUtil {

    public static String readResourceAsString(String resource) {
        final InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream(resource);
        String EOF = new String(new char[] { 0xff });
        if (stream != null) {
            return new Scanner(stream).useDelimiter(EOF).next();
        }

        return null;
    }

    public static boolean writeStringToFile(String content, String fileName) {
        try {
            PrintWriter printWriter = new PrintWriter(fileName);
            printWriter.write(content);
            printWriter.flush();
            printWriter.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
