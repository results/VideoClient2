package io.packet.impl;

import client.Config;
import client.User;
import io.Logger;
import io.packet.IPacket;
import io.packet.Packet;

public class LoginResponsePacket extends IPacket {
	
	public static enum loginResponse {
		ERROR,
		INVALID_PASSWORD,
		NEW_ACCOUNT,
		SUCCESS;
	}

	@Override
	public void execute() {
		switch(packet.getType()) {
			case 2:
			case 3://valid
				User.setUsername(User.tempUser);
				User.setPassword(User.tempPass);
				User.loginAttempts = 0;
				Logger.log("Logged in successfully.");
			break;
			case 1://valid
				Logger.log("Invalid login");
			//	User.setUsername(User.tempUser);
				//User.setPassword(User.tempPass);
				User.loginAttempts++;
			break;
		}
	}

}
