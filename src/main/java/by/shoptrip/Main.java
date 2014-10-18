package by.shoptrip;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.servlet.Servlet;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: joycollector
 * Date: 18.10.14
 * Time: 8:33
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/app");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{"index.html"});
        //resource_handler.setResourceBase("./resources");

        // Temp code for testing
        resource_handler.setResourceBase("C:\\Users\\joycollector\\IdeaProjects\\ShopTrip\\src\\web");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{context, resource_handler, new DefaultHandler()});

        server.setHandler(handlers);

        EventsHelper.initialize(context);

        server.start();
        server.join();
    }
}
