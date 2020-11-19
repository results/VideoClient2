package client;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.JsonUtil;
import io.Logger;
import io.packet.DatagramReceiver;
import io.packet.EncodePacket;
import io.packet.Packet;
import io.packet.PacketHandler;
import io.packet.impl.FileReceived;
import task.TaskHandler;
import task.impl.PacketReceiverTask;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import task.impl.PacketSenderTask;

public class Client {
	
	static Console console;
	
    public static Console getConsole() {
        return console;
    }
    
    /***
     * Builds the frame.
     */
    public static void initFrame() {
    	try {
			UIManager.setLookAndFeel(new FlatDarkLaf() );
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        console = new Console();
        JFrame f = new JFrame("Client logs");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000,350);
        f.getContentPane().add(console, BorderLayout.CENTER);
        f.setVisible(true);

    }
	
	/**
	 * Returns the task scheduler
	 * @return taskScheduler
	 */

	private static DatagramSocket socket;
	private static InetAddress address;
	
	public static DatagramSocket getSocket() {
		return socket;
	}
	
	public static InetAddress getAddress() {
		return address;
	}
	
	static HashMap<String, Object> packetDatas;
	
	public static void packetTogJson() {
		packetDatas = new HashMap<>();
		//packetDatas.put("Packet", "3");
		//packetDatas.put("session", "mommamia");
		Gson gson = new GsonBuilder().create();
		String out = gson.toJson(packetDatas);
		Logger.log(out);
		Logger.log(out.getBytes());
		byte[] byt = out.getBytes();
		 JsonObject jsonObject = new Gson().fromJson(new String(byt,0,byt.length), JsonObject.class);
		// Logger.log("read Packet "+ jsonObject.get("Packet"));
		 JsonArray array = new JsonArray();
		 array.add(jsonObject);
		 Logger.log(jsonObject.toString());
	}
	
	public static int pkt = 0;
	
	//bind port and start server
	private static void bind() {
		try {
			//packetToJson();
			address = InetAddress.getByName(Config.SERVER_IP);
			socket = new DatagramSocket(Config.LOCAL_PORT);
			Logger.log("Client: Sucessfully bound to port "+Config.LOCAL_PORT);
		} catch (SocketException | UnknownHostException e) {
			Logger.log("Failed to bind to port "+Config.LOCAL_PORT);
			e.printStackTrace();
		}
	}
	
	public static SocketAddress getSocketAddress(String IP, int port) {
		SocketAddress address = new InetSocketAddress(IP, port);
		return address;
	}
	
	public void process() {
		
	}
	
	public static FileReceived myFile;


	public static void main(String[] args) throws IOException {
		 	initFrame();
			bind();
			new DatagramReceiver(getSocket());
			new TaskHandler();
			TaskHandler.addTask(new PacketReceiverTask());
			TaskHandler.addTask(new PacketSenderTask());
			packetDatas = new HashMap<>();
			packetDatas.clear();
			packetDatas.put("Packet", "4");
			packetDatas.put("Username", "mommamia");
			packetDatas.put("Password", "mia");
			Gson gson = new GsonBuilder().create();
			String out = gson.toJson(packetDatas);
			byte[] byt = out.getBytes();
			//Logger.log(out);
			//JsonObject jsonObject = new Gson().fromJson(new String(byt,0,byt.length), JsonObject.class);
			EncodePacket.encode(JsonUtil.JsonToMap(out),true);
	//		PacketHandler.addPacketToOutQueue(new Packet(new DatagramPacket(out.getBytes(),out.getBytes().length,getSocketAddress("127.0.0.1",43594))));
			
	}
}
