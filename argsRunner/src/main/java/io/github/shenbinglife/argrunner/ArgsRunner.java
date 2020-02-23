package io.github.shenbinglife.argrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class ArgsRunner {
    private final Logger LOGGER;
    private String toolType;
    protected String[] totalArgs;
    protected Properties props = new Properties();

    public ArgsRunner(String[] args) {
        LOGGER = LoggerFactory.getLogger(this.getClass());
        this.totalArgs = args;
        TooType annotation = this.getClass().getAnnotation(TooType.class);
        this.toolType = annotation.value();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                String propArg = args[i].substring(2);
                String key = propArg;
                String value = null;
                int keyIndex = propArg.indexOf("=");
                if (keyIndex > 0) {
                    key = propArg.substring(0, keyIndex);
                    value = propArg.substring(keyIndex + 1);
                } else {
                    value = (i + 1) < args.length ? args[i + 1] : null;
                    if (value.startsWith("--")) {
                        LOGGER.warn("the argument not set value : " + key);
                        value = null;
                    }
                }
                props.setProperty(key, value);
            }
        }
    }

    public abstract void run() throws Exception;

    public void printHelp() {
        System.out.println(toolType + " usage: there is no useful message.");
    }


}
