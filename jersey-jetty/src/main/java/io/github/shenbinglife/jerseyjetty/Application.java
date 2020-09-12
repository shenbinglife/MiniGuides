package io.github.shenbinglife.jerseyjetty;

public class Application {

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        myServer.init();
        myServer.start();
    }
}
