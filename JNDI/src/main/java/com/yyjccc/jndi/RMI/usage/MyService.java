package com.yyjccc.jndi.RMI.usage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyService extends UnicastRemoteObject implements RMIService {

	private int index=0;


	protected MyService() throws RemoteException {
		super();
	}

	@Override
	public String say()  {
		System.out.println("call say method "+(++index));
		return "hello";
	}


	public void write(){
		System.out.println("write");
	}
}
