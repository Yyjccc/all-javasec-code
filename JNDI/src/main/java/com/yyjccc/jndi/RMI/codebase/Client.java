package com.yyjccc.jndi.RMI.codebase;


import java.io.Serializable;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {

	public void lookup() throws Exception{
		IRemote iCalc = (IRemote) Naming.lookup("rmi://192.168.1.104:1099/calc");

		List<Integer> li = new ArrayList<>();
		li.add(1);
		li.add(2);

		System.out.println(iCalc.sum(li));
	}

	public static void main(String[] args) throws Exception{
		new Client().lookup();
	}

}
