package co.a123.mediacion.util;

public class Constant {

	public static final String ACTION_ENCENDER = "co.a123.mediacion.encender";
	public static final String ACTION_APAGAR = "co.a123.mediacion.apagar";
	
	public static final String GCM_SERVER = "gcm.googleapis.com";
    public static final int GCM_PORT = 5235;
    public static final String GCM_SENDER_ID = "99409704415";//SenderID
    public static final String GCM_SERVER_KEY = "AIzaSyAP-yx-93Mu7P6JHKd7D4GhBR5n4htiGxA";
    public static final long GCM_TIME_TO_LIVE = 60L * 60L * 24L * 7L * 4L; // 4 Weeks

    public static final String GCM_ELEMENT_NAME = "gcm";
    public static final String GCM_NAMESPACE = "google:mobile:data";
}
