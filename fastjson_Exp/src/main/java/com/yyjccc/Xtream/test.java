package com.yyjccc.Xtream;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

class Person//JavaBean实体类
{
	private String name;
	private int age;
	public Person(String name,int age)
	{
		this.name=name;
		this.age=age;
	}
	@Override
	public String toString()
	{
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
public class test
{
	public static void main(String[] args)
	{
		Person bean=new Person("张三",19);
		XStream xstream = new XStream();
		//XML序列化
		String xml = xstream.toXML(bean);
		System.out.println(xml);
		//XML反序列化
		bean=(Person)xstream.fromXML(xml);
		System.out.println(bean);

		xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.setMode(XStream.NO_REFERENCES);
		//Json序列化
		String json=xstream.toXML(bean);
		System.out.println(json);
		//Json反序列
		bean=(Person)xstream.fromXML(json);
		System.out.println(bean);
	}
}

