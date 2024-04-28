package com.yyjccc.jndi.RMI.usage;

import com.yyjccc.jndi.RMI.usage.MyService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("myObj",new MyService());
			System.out.println("rmi server start");
			//Naming.bind("myObj",new MyService());
		} catch (RemoteException | AlreadyBoundException  e) {
			throw new RuntimeException(e);
		}
	}
}
