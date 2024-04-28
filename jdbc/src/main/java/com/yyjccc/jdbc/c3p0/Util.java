package com.yyjccc.jdbc.c3p0;




import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringReader;

public class Util {



		public static String toHexString(Object o){
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(bao);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				oos.writeObject(o);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			byte[] byteArray = bao.toByteArray();
			return toHexAscii(byteArray);
		}

		public static byte[] getBytesFromObj(Object o){
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(bao);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				oos.writeObject(o);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			byte[] byteArray = bao.toByteArray();
			return byteArray;
		}

		public static String toHexAscii(byte[] byteArray) {
			StringBuilder hexStringBuilder = new StringBuilder();
			for (byte b : byteArray) {
				hexStringBuilder.append(String.format("%02X", b));
			}
			return hexStringBuilder.toString();
		}

		public static byte[] fromHexAscii(String s) throws NumberFormatException {
			try {
				int len = s.length();
				if ((len % 2) != 0)
					throw new NumberFormatException("Hex ascii must be exactly two digits per byte.");

				int out_len = len / 2;
				byte[] out = new byte[out_len];
				int i = 0;
				StringReader sr = new StringReader(s);
				while (i < out_len) {
					int val = (16 * fromHexDigit(sr.read())) + fromHexDigit(sr.read());
					out[i++] = (byte) val;
				}
				return out;
			} catch (IOException e) {
				throw new InternalError("IOException reading from StringReader?!?!");
			}
		}

		private static int fromHexDigit(int c) {
			if ('0' <= c && c <= '9') {
				return c - '0';
			} else if ('A' <= c && c <= 'F') {
				return c - 'A' + 10;
			} else if ('a' <= c && c <= 'f') {
				return c - 'a' + 10;
			} else {
				throw new NumberFormatException("Invalid hex digit: " + (char) c);
			}
		}

		private static String byteArrayToString(byte[] byteArray) {
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : byteArray) {
				stringBuilder.append((char) b);
			}
			return stringBuilder.toString();
		}



}