package com.yyjccc.webshell.Controller;


import com.yyjccc.webshell.other.CustomObjectInputStream;
import com.yyjccc.webshell.other.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.util.Base64;


@Controller
public class ApiController {
	public ApiController() {
	}

	@RequestMapping(
			value = {"/api/test"},
			method = {RequestMethod.GET}
	)
	public String test(Data data, ModelMap map) throws Exception {
		byte[] base64decodedBytes = Base64.getDecoder().decode(data.getData());
		ByteArrayInputStream bais = new ByteArrayInputStream(base64decodedBytes);
		CustomObjectInputStream ois = new CustomObjectInputStream(bais);
		ois.readObject();
		ois.close();
		return "api";
	}
}

