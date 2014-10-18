package by.shoptrip;

import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: joycollector
 * Date: 18.10.14
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class ShoptripWebsocketHandler extends WebSocketHandler {
    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest httpServletRequest, String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stop() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isRunning() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isStarted() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isStarting() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isStopping() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isStopped() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isFailed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addLifeCycleListener(Listener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeLifeCycleListener(Listener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
