package io.github.shenbinglife.jerseyjetty.controller;


import org.eclipse.jetty.server.Server;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("msg")
public class MyMessage {

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String getMessage(@Context ServletContext context) {
        Server server = (Server) context.getAttribute("server");
        System.out.println(server.getURI());

        return "My message\n";
    }

    @GET
    @Path("index")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String getIndex(@Context ServletContext context, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        TemplateEngine engine = (TemplateEngine) context.getAttribute("engine");
        WebContext ctx =
                new WebContext(request, response, context, request.getLocale());

        ctx.setVariable("user", new User("jerry", 11));
        return engine.process("index", ctx);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getMessagejson(@Context ServletContext context) {
        Server server = (Server) context.getAttribute("server");
        System.out.println(server.getURI());

        return new User("tom", 9);
    }
}