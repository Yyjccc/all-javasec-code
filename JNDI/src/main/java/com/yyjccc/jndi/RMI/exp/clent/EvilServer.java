package com.yyjccc.jndi.RMI.exp.clent;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EvilServer {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			System.out.println("RMI server start");
			registry.rebind("obj",new IRemoteObj());

		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}

	}
}
