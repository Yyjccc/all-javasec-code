package org.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Tool {


	public static String base64Key = "kPH+bIxk5D2deZiIxcaaaA==";
	private static byte[] getBytesFromFile(String filename) throws Exception {
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		return data;
	}

	private static byte[] pad(byte[] data, int blockSize) {
		int padding = blockSize - (data.length % blockSize);
		byte[] paddedData = new byte[data.length + padding];
		System.arraycopy(data, 0, paddedData, 0, data.length);
		for (int i = data.length; i < paddedData.length; i++) {
			paddedData[i] = (byte) padding;
		}
		return paddedData;
	}

	private static byte[] unpad(byte[] data) {
		int padding = data[data.length - 1];
		byte[] unpaddedData = new byte[data.length - padding];
		System.arraycopy(data, 0, unpaddedData, 0, unpaddedData.length);
		return unpaddedData;
	}

	private static byte[] aesEnc(byte[] data) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(base64Key);
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] iv = generateRandomBytes(16); // Generate random IV
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

		byte[] encrypted = cipher.doFinal(pad(data, 16));

		byte[] result = new byte[iv.length + encrypted.length];
		System.arraycopy(iv, 0, result, 0, iv.length);
		System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
		return Base64.getEncoder().encode(result);
	}

	private static byte[] aesDec(byte[] encryptedData, String key) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(base64Key);
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		byte[] iv = new byte[16];
		System.arraycopy(encryptedData, 0, iv, 0, iv.length);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

		byte[] decrypted = cipher.doFinal(encryptedData, iv.length, encryptedData.length - iv.length);

		return unpad(decrypted);
	}

	private static byte[] generateRandomBytes(int length) {
		byte[] randomBytes = new byte[length];
		new Random().nextBytes(randomBytes);
		return randomBytes;
	}

	public static void Base64AESEncode() throws Exception {
		byte[] data = getBytesFromFile("ser.bin");
		byte[] encryptedData = aesEnc(data);
		System.out.println(new String(encryptedData)); // Print or use the encrypted data

		// For decryption (if needed)
		// byte[] decryptedData = aesDec(encryptedData, "your_key_here");
		// System.out.println(new String(decryptedData));
	}
}
