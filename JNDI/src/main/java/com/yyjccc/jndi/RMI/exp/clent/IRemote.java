package com.yyjccc.jndi.RMI.exp.clent;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemote extends Remote {
	Object start()throws RemoteException;
}
