package com.yyjccc.memorytrojan.Spring.controller;

import java.io.IOException;

public class Shell_Controller{

	public Shell_Controller(){}

	public void shellMethod() throws IOException {
		Runtime.getRuntime().exec("calc");
	}
}
