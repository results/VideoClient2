package task.impl;

import client.User;
import io.Logger;
import io.packet.Packet;
import io.packet.PacketHandler;
import task.Task;

public class PacketReceiverTask extends Task{

	@Override
	public Object call() {
		if(!PacketHandler.getPacketInQueue().isEmpty()) {
			Packet packet = PacketHandler.getPacketInQueue().poll();//CHANGE TO POLL TO WORK
			if(packet != null) {
					if(PacketHandler.validPacket(packet)) {
						try {		
							PacketHandler.getPacketType()[packet.getType()].handlePacket(packet);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						Logger.log("Unhandled packet received.Id "+packet.getType()+" : len "+packet.getLength()+" UID"+packet.getUID());
					}			
				packet = null;
			}
		}
		return null;
	}
}
