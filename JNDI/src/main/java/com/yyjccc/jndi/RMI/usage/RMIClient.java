package com.yyjccc.jndi.RMI.usage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {

	public static void main(String[] args) {
		try {
			RMIService myService = (RMIService) Naming.lookup("rmi://127.0.0.1:1882/myObj");
			String s = myService.say();
			System.out.println(s);
		} catch (NotBoundException | MalformedURLException | RemoteException  e) {
			throw new RuntimeException(e);
		} 
	}
}
