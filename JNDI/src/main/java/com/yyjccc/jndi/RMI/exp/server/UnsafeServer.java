package com.yyjccc.jndi.RMI.exp.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UnsafeServer {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("obj",new IRemoteObj());
		} catch (RemoteException | AlreadyBoundException e) {
			throw new RuntimeException(e);
		}

	}
}
