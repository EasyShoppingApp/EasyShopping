package by.shoptrip;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joycollector
 * Date: 18.10.14
 * Time: 9:25
 * To change this template use File | Settings | File Templates.
 */
public class ShoptripHandler extends AbstractHandler {
    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        try {
            JSONObject jsonObject = new JSONObject();
            Object id = request.getParameter("id");
            jsonObject.put("test", "passed");
            httpServletResponse.setHeader("Content-Type", "application/json");
            httpServletResponse.getOutputStream().write(jsonObject.toString().getBytes("UTF-8"));
            request.setHandled(true);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
