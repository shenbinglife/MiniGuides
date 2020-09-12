package io.github.shenbinglife.argrunner;

public interface Runner {
    void init(String[] args);

    void run() throws Throwable;

    void destroy();


}
