package com.yyjccc.jndi.RMI.exp.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemote extends Remote {

	 Object unSafeMethod(Object o) throws RemoteException;
}
