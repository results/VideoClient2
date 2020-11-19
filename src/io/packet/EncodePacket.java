package io.packet;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import client.Config;
import io.Logger;

public class EncodePacket {
	
	
	static Gson gson = new GsonBuilder().create();
	
	public static void encode(Packet packet, boolean queue) {
		if(packet != null) {
			if(!(packet.getDataMap() == null)) {
			try {
				String out = gson.toJson(packet.getDataMap());
				packet.getDataMap().putIfAbsent("Address", Config.SERVER_IP);
				packet.getDataMap().putIfAbsent("Port", Config.SERVER_PORT);
				packet.setJson(out);
				if(queue) {
					PacketHandler.getPacketOutQueue().offer(packet);
				}
				out = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
	}
	
	public static Packet encode(HashMap<String, Object> data, boolean queue) {
		if(data != null) {
			if(!data.isEmpty()) {
			try {
				Packet packet = new Packet(data);
				String out = gson.toJson(data);
				packet.getDataMap().putIfAbsent("Address", Config.SERVER_IP);
				packet.getDataMap().putIfAbsent("Port", Config.SERVER_PORT);
				packet.setJson(out);
				if(queue) {
					PacketHandler.getPacketOutQueue().offer(packet);
				}
				out = null;
				data = null;
				return packet;
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		return null;
	}
	

}
