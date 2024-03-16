package com.yyjccc.webshell.Controller;

import java.io.IOException;

public class Shell_Controller{
	public Shell_Controller(){}

	public String shellMethod(){
		try {
			Runtime.getRuntime().exec("calc");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return "webshell!";
	}
}
