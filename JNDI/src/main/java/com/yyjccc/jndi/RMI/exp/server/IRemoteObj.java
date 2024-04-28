package com.yyjccc.jndi.RMI.exp.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IRemoteObj extends UnicastRemoteObject implements IRemote {
	protected IRemoteObj() throws RemoteException {
		super();
	}


	@Override
	public Object unSafeMethod(Object o) throws RemoteException {
		System.out.println("end ");
		return o;
	}
}
