package client;

public class Config {
	
	public static final String SERVER_IP = "127.0.0.1";
	public static final int LOCAL_PORT = 43595;
	public static final int SERVER_PORT = 43594;
	
	public static final int MAX_PACKET_QUEUE = 5;
	public static final int TOTAL_PACKETS = 5;
	public static final int PACKET_ID_LENGTH = 1;
	public static final int SESSION_ID_LENGTH = 12;
	public static final int DATA_START = PACKET_ID_LENGTH + SESSION_ID_LENGTH;
	
	public static final int SEND_BUFFER = 512;
	public static final int RECEIVE_BUFFER = 1024;
	//public static final int 
	public static final int MAX_CONNEECTIONS = 20;
	public static final int PACKET_COUNT = 5;
	public static boolean acceptConnections = true;
}
