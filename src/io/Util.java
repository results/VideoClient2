package io;

import java.util.Arrays;

public class Util {
	
	public static byte[] merge(byte[]... arrays)
	{
		int finalLength = 0;
		for (byte[] array : arrays) {
			finalLength += array.length;
		}

		byte[] dest = null;
		int destPos = 0;

		for (byte[] array : arrays)
		{
			if (dest == null) {
				dest = Arrays.copyOf(array, finalLength);
				destPos = array.length;
			} else {
				System.arraycopy(array, 0, dest, destPos, array.length);
				destPos += array.length;
			}
		}
		return dest;
	}
	
	int fromByteArray(byte[] bytes) {
	     return ((bytes[0] & 0xFF) << 24) | 
	            ((bytes[1] & 0xFF) << 16) | 
	            ((bytes[2] & 0xFF) << 8 ) | 
	            ((bytes[3] & 0xFF) << 0 );
	}
	
	public static byte[] intToByteArray(int val) {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; i++) {
		    bytes[i] = (byte)(val >>> (i * 8));
		}
		return bytes;
	}

}
