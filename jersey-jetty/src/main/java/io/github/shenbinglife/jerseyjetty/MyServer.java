package io.github.shenbinglife.jerseyjetty;

import io.github.shenbinglife.jerseyjetty.controller.MyMessage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class MyServer {

    private Server server;
    private int port = 8080;

    public MyServer() {
    }

    public MyServer(int port) {
        this.port = port;
    }

    void init() {
        server = new Server(port);
        ServletContextHandler ctx =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ctx.setBaseResource(Resource.newClassPathResource("/web"));
        ctx.setContextPath("");
        ctx.setAttribute("server", server);
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages",
                MyMessage.class.getPackage().toString());


        ServletContextTemplateResolver templateResolver =
                new ServletContextTemplateResolver(ctx.getServletContext());

        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        ctx.setAttribute("engine", templateEngine);
    }

    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException("error start jetty server", e);
        }
    }
}
