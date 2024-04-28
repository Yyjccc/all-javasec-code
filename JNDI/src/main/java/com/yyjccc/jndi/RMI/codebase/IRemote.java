package com.yyjccc.jndi.RMI.codebase;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IRemote extends Remote {

	//这使用List类做参数是方便我们传递恶意对象
	public Integer sum(List<Integer> lists) throws RemoteException;
}
