package com.yyjccc.jndi.JNDI.usage;

import com.yyjccc.jndi.RMI.usage.RMIService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class rmi {
	public static void main(String[] args) throws NamingException, RemoteException {
		Context initContext=new InitialContext();
		RMIService rmiObj= (RMIService) initContext.lookup("rmi://127.0.0.1:1099/myObj");
		System.out.println(rmiObj.say());
	}
}
