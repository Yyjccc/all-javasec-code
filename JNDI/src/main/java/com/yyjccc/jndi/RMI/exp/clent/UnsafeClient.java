package com.yyjccc.jndi.RMI.exp.clent;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UnsafeClient {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
			//IRemote obj = (IRemote) registry.lookup("obj");
			registry.list();
			System.out.println();
			//Object s = obj.start();
			//System.out.println(s);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
}
