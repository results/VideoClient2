package io.packet.impl;

import client.User;
import io.Logger;
import io.packet.IPacket;
import io.packet.Packet;

public class EmptyPacket extends IPacket {

	@Override
	public void execute() {
		Logger.log("Empty Packet");
	}
}
