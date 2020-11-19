package client;

public class User {
	
	static {
		tempUser = "sejteit";
		tempPass = "dogsandcats123";
	}
	
	private static int userRights = 0;
	public static String tempUser = "";
	public static String tempPass = "";
	public static int loginAttempts = 0;
	private static String username = "";
	private static String password = "";
	private static String sessionID = "78eaeuzdueda";
	private static long lastPacket = 0;
	public static String getSessionID() {
		return sessionID;
	}
	public static void setSessionID(String session) {
		session = sessionID;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		User.password = password;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		User.username = username;
	}
	public static int getUserRights() {
		return userRights;
	}
	public static void setUserRights(int userRights) {
		User.userRights = userRights;
	}
	

}
