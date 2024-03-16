package com.yyjccc.Xtream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Poc {

	public static void main(String[] args) {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("fastjson_Exp/src/main/resources/payload.xml");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		XStream xStream = new XStream(new DomDriver());
 		xStream.fromXML(fileInputStream);
	}
}
