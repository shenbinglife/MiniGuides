package io.github.shenbinglife.argrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Class[] runners = new Class[0];

        List<String> supportedToolTypes = new ArrayList<>();
        Map<String, Class<? extends ArgsRunner>> toolMap = new HashMap<>();
        for (Class runner : runners) {
            if (!ArgsRunner.class.isAssignableFrom(runner)) {
                LOGGER.warn("ignore the runner which not extends ArgsRunner: " + runner.getCanonicalName());
                continue;
            }
            TooType annotation = (TooType) runner.getAnnotation(TooType.class);
            if (annotation == null) {
                LOGGER.warn("can not get tool type from this runner: " + runner.getCanonicalName());
                continue;
            }
            String runnerType = annotation.value();
            supportedToolTypes.add(runnerType);
            toolMap.put(runnerType, runner);
        }

        if (args == null || args.length == 0) {
            printHelp(supportedToolTypes);
            System.exit(0);
        }
        String toolType = args[0];
        String[] reservedArgs = new String[args.length - 1];
        System.arraycopy(args, 1, reservedArgs, 0, args.length - 1);

        Class<? extends ArgsRunner> runner = toolMap.get(toolType);
        if (runner == null) {
            System.err.println("unsupport tool type: " + toolType);
            System.exit(1);
        } else {
            LOGGER.info("runner tool type: " + toolType);
            ArgsRunner argsRunner = runner.getDeclaredConstructor(String[].class).newInstance((Object) reservedArgs);
            if (Utils.arrayContains(reservedArgs, "--help")) {
                argsRunner.printHelp();
            } else {
                argsRunner.run();
            }
        }
    }

    public static void printHelp(List<String> toolTypes) {
        System.out.println("useage : java -jar this.jar <tool type> <arg1> <arg2> ...");
        System.out.println("useage for tool type : java -jar this.jar <tool type> --help");
        System.out.println("current supported tool types: " + String.join(", ", toolTypes));
    }

}
