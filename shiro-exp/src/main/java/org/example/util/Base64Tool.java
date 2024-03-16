package org.example.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class Base64Tool {

	public static void Base64EncodeClass(String ClassPath) throws IOException {
		// data

		File file1 = new File(ClassPath);
		FileInputStream fileInputStream1 = new FileInputStream(file1);
		byte[] bytes1 = new byte[(int) file1.length()];
		fileInputStream1.read(bytes1);
		System.out.println(URLEncoder.encode(Base64.encodeBase64String(bytes1)));

	}
}
