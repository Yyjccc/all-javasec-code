package com.yyjccc.Xtream.Usage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XstreamSer {

	private static void Serize(){
		Person person = new Person();
		person.setAge(12);
		person.setName("yyjccc");
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(person);
		System.out.println(xml);
	}


	private static void Unseralize(){
		FileInputStream xml;
		try {
			xml = new FileInputStream("fastjson_Exp/src/main/resources/person.xml");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		XStream xstream = new XStream(new DomDriver());
		Person p = (Person) xstream.fromXML(xml);
		System.out.println(p);
	}
	public static void main(String[] args) {
		Unseralize();
	}
}
