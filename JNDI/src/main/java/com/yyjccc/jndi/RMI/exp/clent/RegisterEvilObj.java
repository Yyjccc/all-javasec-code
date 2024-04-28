package com.yyjccc.jndi.RMI.exp.clent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterEvilObj extends UnicastRemoteObject {
	protected RegisterEvilObj() throws RemoteException {
		super();
	}
}
