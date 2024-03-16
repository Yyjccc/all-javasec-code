package com.yyjccc.jackson.usage;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

public class JSONParse {
	public static void main(String[] args) {
		String json = "{\"name\":\"fakes0u1\",\"age\":123}";
		JsonFactory jsonFactory = new JsonFactory();
		try {
			JsonParser parser = jsonFactory.createParser(json);
			System.out.println(parser);
			while(!parser.isClosed()){
				JsonToken jsonToken = parser.nextToken();
				System.out.println(jsonToken+" : "+parser.getCurrentName());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}
}
