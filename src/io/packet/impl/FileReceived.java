package io.packet.impl;

import java.util.Arrays;

public class FileReceived {
	
	public FileReceived(String name, String ext, int blocks,int size) {
		fileName = name;
		fileExtension = ext;
		totalBlocks = blocks;
		fileSize = size;
		fileData = new byte[blocks][65507];
	}
	
	public int block;
	public int totalBlocks;
	
	public byte[][] fileData;
	
	public double fileSize;
	
	public double percent;
	
	
	public String fileName;
	public String fileExtension;
	
	
	public boolean correctBlock() {
		return block == block+1;
	}
	
	public boolean fileComplete() {
		for(int i = 0; i <= totalBlocks; i++) {
			if(fileData[totalBlocks] == null) {
				return false;
			}
		}
		return	true;// all blocks full
	}
	
	public  int byteArrayToInt(byte[] b) 
	{
	    return   b[3] & 0xFF |
	            (b[2] & 0xFF) << 8 |
	            (b[1] & 0xFF) << 16 |
	            (b[0] & 0xFF) << 24;
	}
	
	public void blockReceived(byte[] data) {
		//byte[] blockID = Arrays.copyOfRange(data, 0, 4);
		int thisBlock = byteArrayToInt(Arrays.copyOfRange(data, 0, 4));
		block = thisBlock;
		fileData[block] = data;
	}

}
