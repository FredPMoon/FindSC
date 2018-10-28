package servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WebSocketEndpoint {

    @OnOpen
    public void onOpen() {
    }

    @OnClose
    public void onClose() {
    }

    @OnMessage
    public void onMessage(String packet, Session session) {
        String[] packetData = packet.split(" ");
        String packetType = packetData[0];
        String packetPayload = packetData[1];
        int itemId = Integer.parseInt(packetPayload);
        if (packetType.equals("itemid")) {
            List<Session> sessions = map.getOrDefault(itemId, new ArrayList<Session>());
            sessions.add(session);
            map.put(itemId, sessions);
        } else if (packetType.equals("refresh")) {
            broadcast(itemId, session);
        }
    }

    private static Map<Integer, List<Session>> map = new HashMap<Integer, List<Session>>();

    public static void broadcast(int itemId, Session exclude) {
        List<Session> sessions = map.get(itemId);
        if (sessions != null) {
            for (Session session : sessions) {
                if (session != exclude) {
                    try {
                        session.getBasicRemote().sendText("refresh");
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

}
