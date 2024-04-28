package com.yyjccc.jndi.RMI.usage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

	public String say() throws RemoteException;
}
