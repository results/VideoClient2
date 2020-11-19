package task.impl;

import java.io.IOException;
import java.net.DatagramPacket;

import client.Client;
import io.Logger;
import io.packet.Packet;
import io.packet.PacketHandler;
import task.Task;


public class PacketSenderTask extends Task {
	

	@Override
	public Object call() {
		if(!PacketHandler.getPacketOutQueue().isEmpty()) {
			Packet packet = PacketHandler.getPacketOutQueue().peek();//CHANGE TO POOLLL TO WORK
			if(packet != null) {
				try {
					Client.getSocket().send(new DatagramPacket(packet.getBytes(),packet.getLength(),PacketHandler.getSocketAddress(packet.getAddress(),packet.getPort())));
					packet = null;
					Logger.log("Sent : "+(++Client.pkt));
				} catch (IOException e) {
					packet = null;
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
