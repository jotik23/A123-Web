package co.a123.mediacion.gcm;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.xmlpull.v1.XmlPullParser;

import co.a123.mediacion.util.Constant;


/**
 * Smack client implementation for GCM Cloud Connection Server.
 * 
 */
public class GoogleConnectionManager {

	private static GoogleConnectionManager instance = null;
	private GCMConnection connection = GCMConnection.getInstance();
	final String userName = new StringBuilder(Constant.GCM_SENDER_ID).append("@").append(Constant.GCM_SERVER).toString();
	final String password = Constant.GCM_SERVER_KEY;

	
	private GoogleConnectionManager(){

		ProviderManager.getInstance().addExtensionProvider(Constant.GCM_ELEMENT_NAME, Constant.GCM_NAMESPACE,
			new PacketExtensionProvider()
			{

			    @Override
			    public PacketExtension parseExtension(XmlPullParser parser) throws Exception
			    {
				String json = parser.nextText();
				PacketExtension packet = new co.a123.mediacion.gcm.PacketExtension(json);
				return packet;
			    }
			});
	}
	
	public  boolean isConected(){
		return connection.isConected();
	}
	
	public boolean connect(){
		
		boolean isConnect = false;
		
		try
		{
			connection.connect(userName, password);;
		    isConnect = true;
		}
		catch (XMPPException e)
		{
			isConnect = false;
		    e.printStackTrace();
		}
		
		return isConnect;
	}
	
	public boolean disconnect(){
		return connection.disconnect();
	}
	
	public static GoogleConnectionManager getInstance(){
		if(instance == null){
			instance = new GoogleConnectionManager();
		}
		return instance;
	}
}
