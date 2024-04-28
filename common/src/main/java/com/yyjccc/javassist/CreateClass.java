package com.yyjccc.javassist;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateClass {

	public static CtMethod getOutputProperties;

	public static CtClass templateImpl;

	public static ClassPool pool;

	static {
		try {
			pool=new ClassPool(true);
			templateImpl=pool.get("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
			getOutputProperties = templateImpl.getDeclaredMethod("getOutputProperties");
		} catch (NotFoundException e) {
			throw new RuntimeException(e);
		}
	}


	public static void ctClass() throws NotFoundException {
		ClassPool pool1=ClassPool.getDefault();
		CtClass myClass=pool.makeClass("com.yyjccc.TestClass");
		CtClass[] interfaces = templateImpl.getInterfaces();
		CtField[] declaredFields = templateImpl.getDeclaredFields();
		System.out.println(templateImpl.getName());
		System.out.println(Arrays.toString(templateImpl.getInterfaces()));
	}

	public static void ctMethod() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
		CtClass ctClass5 = pool.get(TemplatesImpl.class.getName());
		CtMethod ctMethod = ctClass5.getDeclaredMethod("getOutputProperties");
		// 方法名
		String methodName = ctMethod.getName();
		// 返回类型
		CtClass returnType = ctMethod.getReturnType();
		// 方法参数, 通过此种方式得到方法参数列表
		// 格式: com.kawa.TestService.getOrder(java.lang.String,java.util.List)
		String longName = ctMethod.getLongName();
		// 方法签名 格式: (Ljava/lang/String;Ljava/util/List;Lcom/test/Order;)Ljava/lang/Integer;
		String signature = ctMethod.getSignature();

		// 获取方法参数名称, 可以通过这种方式得到方法真实参数名称
		List<String> argKeys = new ArrayList<>();
		MethodInfo methodInfo = ctMethod.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		CtClass[] parameterTypes = ctMethod.getParameterTypes();

	}




	public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
		ctClass();
		ctMethod();
	}
}
