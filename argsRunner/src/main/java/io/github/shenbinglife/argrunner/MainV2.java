package io.github.shenbinglife.argrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainV2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainV2.class);

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            LOGGER.error("arguments must have one argument for tool type.");
            System.exit(1);
        }
        LOGGER.info("ArgsRunner params: " + String.join(" ", args));
        String type = args[0];
        ArgsRunner runner = Utils.load(type);
        runner.init(args);
        runner.run();
        runner.destroy();

    }
}
