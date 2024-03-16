package com.yyjccc.jackson.usage;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;
import java.io.IOException;

public class Generator {

	public static void main(String[] args) {
		JsonFactory jsonFactory = new JsonFactory();
		try {
			JsonGenerator generator = jsonFactory.createGenerator(new File("output.json"), JsonEncoding.UTF8);
			generator.writeStartObject();
			generator.writeStringField("name","qzy");
			generator.writeNumberField("age",18);
			generator.writeEndObject();
			generator.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
