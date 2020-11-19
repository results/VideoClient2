package io.packet.impl;

import client.Client;
import io.Logger;
import io.packet.IPacket;

public class FileReceive extends IPacket {
	
	@Override
	public void execute() {
		Logger.log("Received file");
		Logger.log(packet.toString());
		if(Client.myFile != null) {
			Client.myFile.blockReceived(packet.getBytes());
		} else {
		Client.myFile = new FileReceived(packet.getString("FileName"), packet.getString("Format"), packet.getInt("FileSize"), packet.getInt("FileBlocks"));
		//responsePacket.put("Packet",4);
		//responsePacket.put("FileName", "image");
		//responsePacket.put("FileSize", fileSize);
		//responsePacket.put("FileBlocks", getBlocksForFile());
		//responsePacket.put("Format", ".jpg");
		//Logger.log(packet.toString());
		}
	}
}
