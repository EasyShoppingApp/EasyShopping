package by.shoptrip;

import by.shoptrip.db.DBHelper;
import by.shoptrip.db.DBManager;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.json.JSONObject;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: joycollector
 * Date: 18.10.14
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
public class EventsHelper {

    private static ServerContainer wscontainer;
    private static DBManager dbManager;

    public static void delete(JSONObject object) throws IOException, EncodeException {
        JSONObject deletedObject = dbManager.delete(object);
        EventsHelper.sendObjectToAllOpenSockets(deletedObject);
    }

    public static void add(JSONObject object) throws IOException, EncodeException {
        JSONObject addedObject = dbManager.add(object);
        EventsHelper.sendObjectToAllOpenSockets(object);
    }

    public static void toggle(JSONObject object) throws IOException, EncodeException {
        JSONObject modifiedObject = dbManager.toggle(object);
        EventsHelper.sendObjectToAllOpenSockets(modifiedObject);
    }

    public static void initialize(ServletContextHandler context) throws DeploymentException {
        wscontainer = WebSocketServerContainerInitializer.configureContext(context);
        wscontainer.addEndpoint(EventSocket.class);
        dbManager = DBHelper.getManager();
    }

    public static void sendObjectToAllOpenSockets(JSONObject object) throws IOException, EncodeException {
        Set<Session> openSessions = wscontainer.getOpenSessions();
        for (Session openSession : openSessions) {
            openSession.getBasicRemote().sendObject(object);
        }
    }
}
