package webSocket;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import artRoom.entities.AdminMessage;



@ServerEndpoint("/adminsdiscussion")
@Singleton
public class AdminsDiscussionServer {

	private List<AdminMessage> messages = new ArrayList<>();
	private Set<Session> sessions = new HashSet<>();

	

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		sessions.add(session);
		System.out.println("new session added");
		//System.out.println("messages  :"+messages);
		sendMoreToSession(session.getId());
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session);
		System.out.println("session closed ");
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("error in serverdiscussions :" + throwable.getMessage());
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println(message);
		JsonReader reader = Json.createReader(new StringReader(message));
		JsonObject jsonMessage = reader.readObject();
		String s = jsonMessage.getString("action");
		
		if (s.equals("add")) {
			AdminMessage msg = new AdminMessage();
			msg.setDate(jsonMessage.getString("date"));
			msg.setMail(jsonMessage.getString("mail"));
			msg.setText(jsonMessage.getString("text"));
			messages.add(msg);
			sendMsgToAll(msg);
		}
		if (s.equals("getMore")) {
			String sessionId = jsonMessage.getString("sessionId");
			sendMoreToSession(sessionId);
		}

	}

	private void sendMsgToAll(AdminMessage adm) {
		JsonProvider jsp = JsonProvider.provider();
		JsonObjectBuilder objBuilder = jsp.createObjectBuilder();
		objBuilder.add("date", adm.getDate());
		objBuilder.add("text", adm.getText());
		objBuilder.add("mail", adm.getMail());
		JsonObject obj = objBuilder.build();
		System.out.println(obj.toString());
		for (Session session : sessions) {
			
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						session.getBasicRemote().sendText(obj.toString());
					} catch (IOException e) {
						System.out.println("sending error");
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
	}

	private void sendMoreToSession(String sessionId) {
		System.out.println("sending mooooooooooooore");
		Session se = null;
		for (Session s : sessions) {
			if (s.getId().equals(sessionId)) {
				se = s;
				break;
			}
		}
		final Session session = se;

		int max = messages.size();
		if (max > 20) {
			for (int i = max - 20; i < max; i++) {
				AdminMessage adm = messages.get(i);
				JsonProvider jsp = JsonProvider.provider();
				JsonObjectBuilder objBuilder = jsp.createObjectBuilder();
				objBuilder.add("date", adm.getDate());
				objBuilder.add("text", adm.getText());
				objBuilder.add("mail", adm.getMail());
				JsonObject obj = objBuilder.build();
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							session.getBasicRemote().sendText(obj.toString());
						} catch (IOException e) {
							System.out.println("sending error");
							e.printStackTrace();
						}
					}
				});
				t.start();			}
		} else {
			for (AdminMessage adm : messages) {
				JsonProvider jsp = JsonProvider.provider();
				JsonObjectBuilder objBuilder = jsp.createObjectBuilder();
				objBuilder.add("date", adm.getDate());
				objBuilder.add("text", adm.getText());
				objBuilder.add("mail", adm.getMail());
				JsonObject obj = objBuilder.build();
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							session.getBasicRemote().sendText(obj.toString());
						} catch (IOException e) {
							System.out.println("sending error");
							e.printStackTrace();
						}
					}
				});
				t.start();
			}
		}
	}

}
