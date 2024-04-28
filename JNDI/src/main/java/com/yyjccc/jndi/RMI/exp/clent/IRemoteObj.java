package com.yyjccc.jndi.RMI.exp.clent;

import com.yyjccc.CommonsExp.Cc2;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IRemoteObj extends UnicastRemoteObject implements IRemote {
	public IRemoteObj() throws RemoteException {
		super();
		System.out.println("test");
	}

	@Override
	public Object start() throws RemoteException {
		try {
			System.out.println("call ");
			return Cc2.cc2();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
