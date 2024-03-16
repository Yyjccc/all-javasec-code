package com.yyjccc.memorytrojan.decode;

import java.io.*;
import java.util.Random;

public class Base64Reverse {

	private  String  content;

	private String className;

	private byte[] clazz;

	private final String inputFile="./WebshellFile/input.txt";

	private final String prex="F:\\code\\java\\all-javasec-code\\MemoryTrojan\\src\\main\\webapp\\";

	public Base64Reverse()  {
		try {
			this.content=read(inputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}



	public Base64Reverse(String content){
		this(content, null);
		this.className=getClassName();
	}

	public Base64Reverse(String content,String className){
		this.content=content;
		this.className=className;
	}

	public  void decode()  {
		try {
		Class clazz = Class.forName("sun.misc.BASE64Decoder");
		this.clazz= (byte[]) clazz.getMethod("decodeBuffer", String.class).invoke(clazz.newInstance(), content);
		File file = new File(prex + "WebshellFile\\" + className + ".class");
		file.createNewFile();
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(this.clazz);
		}catch (Exception e){
			System.out.println("解码出错");
			e.printStackTrace();
		}
	}

	private String getClassName(){
		String name;
		if(this.clazz!=null){
			try {
				name= new MyClassLoader().defineClass(clazz).getSimpleName();
				return name;
			}catch (Exception e){
				e.printStackTrace();
				return new Random().nextInt()+"";
			}
		}else {
			return new Random().nextInt()+"";
		}
	}

	private String read(String file) throws IOException {
		FileInputStream fis = new FileInputStream(new File(file));
		byte[] buffer = new byte[10];
		StringBuilder sb = new StringBuilder();
		while (fis.read(buffer) != -1) {
			sb.append(new String(buffer));
			buffer = new byte[10];
		}
		fis.close();
		return sb.toString();
	}


}
