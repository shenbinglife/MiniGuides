package io.github.shenbinglife.argrunner;

import java.util.Arrays;

@ToolType("example")
public class ExampleRunner extends ArgsRunner {

    @Override
    public void run() throws Exception {
        System.out.println("hello world");
    }
}
