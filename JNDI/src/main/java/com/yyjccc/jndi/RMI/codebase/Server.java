package com.yyjccc.jndi.RMI.codebase;



import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Server {

	public class RMICalc extends UnicastRemoteObject implements IRemote {
		protected RMICalc() throws RemoteException{
			super();
		}


		@Override
		public Integer sum(List<Integer> lists) throws RemoteException {
			Integer result=0;
			for (Integer list : lists){
				result+=list;
			}
			return result;
		}
	}

	private void register() throws Exception{

		//启动RMISecurityManager
		if (System.getSecurityManager() == null) {
			System.out.println("setup SecurityManager");
			System.setSecurityManager(new SecurityManager());
		}

		RMICalc rmiCalc=new RMICalc();
		LocateRegistry.createRegistry(1099);
		Naming.bind("rmi://127.0.0.1:1099/calc",rmiCalc);
		System.out.println("Registry运行中......");
	}

	public static void main(String[] args) throws Exception {
		new Server().register();
	}
}