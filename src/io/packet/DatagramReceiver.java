package io.packet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import client.Client;
import client.Config;
import io.Logger;


/**
 * Since datagramsocket.receive is blocking it needs to be on its own thread
 * @author sejte
 *
 */
public class DatagramReceiver implements Runnable {
	
	public DatagramReceiver(DatagramSocket socket) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Thread.currentThread().setName("DatagramPacket Receiver");
		Logger.log("Started : "+Thread.currentThread().getName());
		scheduler.scheduleAtFixedRate(this, 600, 600, TimeUnit.MILLISECONDS);
	}

	/**
	 * Will block, after UDP received will wait 600ms before checking again
	 */
	@Override
	public void run() {
		byte[] buffer = new byte[Config.RECEIVE_BUFFER];	 
		DatagramPacket received = new DatagramPacket(buffer, buffer.length);
		try {
			if(Client.getSocket() != null) {
				Client.getSocket().receive(received);
				if(received != null) {
					if(received.getLength() > 0) {
						DecodePacket.decode(received,true);
					}
				}
			}
			buffer = null;
			received = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
