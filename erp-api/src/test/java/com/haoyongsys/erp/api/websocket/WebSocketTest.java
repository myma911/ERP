package com.haoyongsys.erp.api.websocket;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


@ClientEndpoint
public class WebSocketTest {


    private String deviceId;

    private Session session;

    public WebSocketTest () {
        
    }

    public WebSocketTest (String deviceId) {
        this.deviceId = deviceId;
    }

    protected boolean start() {
        WebSocketContainer Container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://192.168.3.192:8081/aaron911-api/webSocket/18232191096@1";
        System.out.println("Connecting to " + uri);
        try {
            session = Container.connectToServer(WebSocketTest.class, URI.create(uri));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
    	 WebSocketTest wSocketTest = new WebSocketTest("sdf");
    	  wSocketTest.start();
    	 
         Thread.sleep(10000);

    }
}