package io.packet;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;

import io.Logger;

public abstract class IPacket {
	

	public Packet packet;
	
	public HashMap<String,Object> responsePacket = new HashMap<String,Object>();
	
	public InetSocketAddress socket;
	
	public void execute() {
		
	}
	
	public void respond() {
		
	}
	
	public void setVars(Packet packet) {
		this.packet = packet;
	}
	
	public void handlePacket(Packet packet) {
		this.setVars(packet);
		this.execute();
		this.respond();
		if(!this.responsePacket.isEmpty()) {//packet response has data, encode and queue
			//if(socket != null && c == null) {
			//	responsePacket.putIfAbsent("Address", socket.getHostString());
			//	responsePacket.putIfAbsent("Port", socket.getPort());
			//}
			if(responsePacket.containsKey("Address") && responsePacket.containsKey("Port")) {
				EncodePacket.encode(responsePacket, true);
			} else {
				Logger.log("Failed to encode packet. Missing socket information: "+responsePacket.toString());
			}
		}
		
	}
	
}

