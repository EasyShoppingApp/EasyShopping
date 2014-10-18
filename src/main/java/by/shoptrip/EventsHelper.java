package by.shoptrip;

import by.shoptrip.db.DBHelper;
import by.shoptrip.db.DBManager;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
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

    public static void delete(JSONObject object) throws IOException, EncodeException, JSONException {
        JSONObject deletedObject = dbManager.delete(object);
        JSONObject response = new JSONObject();
        response.put("action", "delete");
        response.put("object", deletedObject);
        EventsHelper.sendObjectToAllOpenSockets(response);
    }

    public static void add(JSONObject object) throws IOException, EncodeException, JSONException {
        JSONObject addedObject = dbManager.add(object);
        JSONObject response = new JSONObject();
        response.put("action", "add");
        response.put("object", addedObject);
        EventsHelper.sendObjectToAllOpenSockets(response);
    }

    public static void toggle(JSONObject object) throws IOException, EncodeException, JSONException {
        JSONObject modifiedObject = dbManager.toggle(object);
        JSONObject response = new JSONObject();
        response.put("action", "toggle");
        response.put("object", modifiedObject);
        EventsHelper.sendObjectToAllOpenSockets(response);
    }


    public static void list(Session session) throws IOException, EncodeException, JSONException {
        List<JSONObject> lists = dbManager.getLists();
        JSONArray jsonArray = new JSONArray(lists);
        JSONObject response = new JSONObject();
        response.put("action", "list");
        response.put("object", jsonArray);
        session.getBasicRemote().sendText(response.toString());
    }

    public static void initialize(ServletContextHandler context) throws DeploymentException {
        wscontainer = WebSocketServerContainerInitializer.configureContext(context);
        wscontainer.addEndpoint(EventSocket.class);
        dbManager = DBHelper.getManager();
    }

    public static void sendObjectToAllOpenSockets(Object object) throws IOException, EncodeException {
        Set<Session> openSessions = wscontainer.getOpenSessions();
        for (Session openSession : openSessions) {
            openSession.getBasicRemote().sendText(object.toString());
        }
    }
}
