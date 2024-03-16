package com.yyjccc.jackson.usage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Unserise {
	public static void main(String[] args) {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.enableDefaultTyping();
		String jsonstring = "{\"age\":6,\"name\":\"mi1k7ea\",\"sex\":[\"com.yyjccc.jackson.usage.Sex\",{\"id\":1,\"name\":\"女\"}]}";

		Person p2 = null;
		try {
			p2 = objectMapper.readValue(jsonstring, Person.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(p2);
	}

}
