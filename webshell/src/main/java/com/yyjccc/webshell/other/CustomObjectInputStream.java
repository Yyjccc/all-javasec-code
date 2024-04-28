package com.yyjccc.webshell.other;


import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class CustomObjectInputStream extends ObjectInputStream {
	public CustomObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	protected Class resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
		String className = desc.getName();
		String[] denyClasses = new String[]{"java.lang.reflect.Proxy", "javax.management.BadAttributeValueExpException", "sun.rmi.server.UnicastRef", "sun.rmi.transport.LiveRef", "sun.rmi.transport.tcp.TCPEndpoint", "java.rmi.server.RemoteObject", "java.rmi.server.RemoteRef", "java.rmi.server.ObjID", "java.rmi.RemoteObjectInvocationHandler", "java.rmi.server.UnicastRemoteObject", "java.rmi.registry.Registry"};
		String[] var4 = denyClasses;
		int var5 = denyClasses.length;

		for(int var6 = 0; var6 < var5; ++var6) {
			String denyClass = var4[var6];
			if (className.startsWith(denyClass)) {
				throw new InvalidClassException("Unauthorized deserialization attempt", className);
			}
		}

		return super.resolveClass(desc);
	}
}
