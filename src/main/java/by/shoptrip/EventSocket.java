package by.shoptrip;

/**
 * Created with IntelliJ IDEA.
 * User: joycollector
 * Date: 18.10.14
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */

import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ClientEndpoint
@ServerEndpoint(value="/events/")
public class EventSocket
{
    @OnOpen
    public void onWebSocketConnect(Session sess) {
        System.out.println("Socket Connected: " + sess);
    }

    @OnMessage
    public void onWebSocketText(String message) throws JSONException, IOException, EncodeException {
        JSONObject jsonObject = new JSONObject(message);
        String action = jsonObject.getString("action");
        JSONObject object = jsonObject.getJSONObject("object");
        switch (action) {
            case "delete":
                EventsHelper.delete(object);
                break;
            case "add":
                EventsHelper.add(object);
                break;
            case "toggle":
                EventsHelper.delete(object);
                break;
        }
        System.out.println("Received TEXT message: " + message);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("Socket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace(System.err);
    }
}
