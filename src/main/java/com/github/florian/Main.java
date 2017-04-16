package com.github.florian;

import com.github.florian.example.Example;
import com.github.florian.factory.ExampleFactory;

/**
 * Created by zhidong.fzd on 17/4/10.
 */
public class Main {

    public static void main(String[] args) {
        Example example = ExampleFactory.getDefaultExample();
        example.draw();
    }
}
