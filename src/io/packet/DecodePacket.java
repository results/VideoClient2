package io.packet;

import java.net.DatagramPacket;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.JsonUtil;
import io.Logger;

public class DecodePacket {
	
		static HashMap<String,Object> decodedPacket;
		
		public static void decode(DatagramPacket received, boolean queue) {
			try {
				byte[] byt = received.getData();
				try {
					 decodedPacket = JsonUtil.JsonToMap(new String(byt,0,byt.length).trim());
				} catch(Exception e) {
					Packet file = new Packet(decodedPacket);
					file.getDataMap().put("Packet", 4);
					file.rawData = received.getData();//for files
					Logger.log("Decoded file! as "+file.toString());
					if(queue) {//add packet to queue
						///PacketHandler.getPacketInQueue().offer();
						//return;
					}
					decodedPacket = null;
					received = null;
				}
				decodedPacket.putIfAbsent("Address", received.getAddress().toString());
				decodedPacket.putIfAbsent("Port", received.getPort());
				Packet p = new Packet(decodedPacket);
				p.rawData = received.getData();//for files
				Logger.log("Decoded packet! as "+p.toString());
				if(queue) {//add packet to queue
					PacketHandler.getPacketInQueue().offer(p);
					Logger.log("Offered");
				}
				decodedPacket = null;
				received = null;
				byt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}
