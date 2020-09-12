package io.github.shenbinglife.argrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class ArgsRunner implements Runner {
    protected final Logger LOGGER;
    protected String toolType;
    protected String[] originArgs;
    protected Properties props = new Properties();

    public ArgsRunner() {
        LOGGER = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public void init(String[] args) {
        this.originArgs = args;
        ToolType annotation = this.getClass().getAnnotation(ToolType.class);
        this.toolType = annotation.value();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                String propArg = args[i].substring(2);
                String key = propArg;
                String value = null;
                int keyIndex = propArg.indexOf("=");
                if (keyIndex >= 0) {
                    key = propArg.substring(0, keyIndex);
                    value = propArg.substring(keyIndex + 1);
                } else {
                    value = (i + 1) < args.length ? args[i + 1] : null;
                    if (value.startsWith("--")) {
                        LOGGER.debug("ArgsRunner set null for key : " + key);
                        value = null;
                    }
                }
                props.setProperty(key, value);
            }
        }
    }


    public abstract void run() throws Exception;

    @Override
    public void destroy() {
    }

    public void printHelp() {
        LOGGER.info(toolType + " usage: there is no useful message.");
    }


}
