package com.waiso.social.framework.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

	@SuppressWarnings("unused")
	public static byte[] toByteArray(InputStream input, int bufferSize) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[bufferSize];
		long count = 0;
		int n = 0;
		while(-1 != (n = input.read(buffer))){
			output.write(buffer, 0, n);
			count += n;
		}
		return output.toByteArray();
	}

	static String byteArrayToHexString(byte in[]) {
		byte ch = 0x00;
		int i = 0;
		if(in == null || in.length <= 0){
			return null;
		}

		String pseudo[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

		StringBuffer out = new StringBuffer(in.length * 2);
		while(i < in.length){
			ch = (byte) (in[i] & 0xF0); // Strip off high nibble
			ch = (byte) (ch >>> 4);
			// shift the bits down
			ch = (byte) (ch & 0x0F);
			// must do this is high order bit is on!
			out.append(pseudo[(int) ch]); // convert the nibble to a String
											// Character
			ch = (byte) (in[i] & 0x0F); // Strip off low nibble
			out.append(pseudo[(int) ch]); // convert the nibble to a String
											// Character
			i++;
		}

		String rslt = new String(out);
		return rslt;
	}
}