package co.a123.mediacion.events;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;
import org.json.simple.JSONValue;

import co.a123.mediacion.gcm.PacketExtension;
import co.a123.mediacion.util.Generator;

public class EventImplementation implements EventInterface{

	private XMPPConnection connection;
	
	public EventImplementation(XMPPConnection connection) {
		this.connection = connection;
	}
	
	@Override
	public void manipularAckEvent(Map<String, Object> jsonObject) {
		String messageId = jsonObject.get("message_id").toString();
		String from = jsonObject.get("from").toString();
		System.out.println("handleAckReceipt() from: " + from + ", messageId: " + messageId);
	}

	@Override
	public void manipularNackEvent(Map<String, Object> jsonObject) {
		String messageId = jsonObject.get("message_id").toString();
		String from = jsonObject.get("from").toString();
		System.out.println("handleNackReceipt() from: " + from + ", messageId: " + messageId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void manipularRegistroDispositivo(Map<String, Object> jsonObject) {
		Map<String, String> payload = (Map<String, String>) jsonObject.get("data");
		String name = payload.get("name").toString();
		String from = jsonObject.get("from").toString();
		
		payload.put("message", "Registration successful");
		String echo = createJsonMessage(from, Generator.getRandomMessageId(),payload, null, null, false);
		send(echo);
		System.out.println("Adding new user: " + name + ":" + from);
	}

	@Override
	public void manipularEliminacionDispositivo(Map<String, Object> jsonObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(String jsonRequest) {
		Packet request = new PacketExtension(jsonRequest).toPacket();
		connection.sendPacket(request);
	}

	protected String createJsonMessage(String to, String messageId, Map<String, String> payload,String collapseKey, Long timeToLive, Boolean delayWhileIdle)
    {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("to", to);
		if (collapseKey != null)
		{
		    message.put("collapse_key", collapseKey);
		}
		if (timeToLive != null)
		{
		    message.put("time_to_live", timeToLive);
		}
		if (delayWhileIdle != null && delayWhileIdle)
		{
		    message.put("delay_while_idle", true);
		}
		message.put("message_id", messageId);
		message.put("data", payload);
		return JSONValue.toJSONString(message);
    }
	
	@Override
    public  String createJsonAck(String to, String messageId)
    {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("message_type", "ack");
		message.put("to", to);
		message.put("message_id", messageId);
		return JSONValue.toJSONString(message);
    }
}
