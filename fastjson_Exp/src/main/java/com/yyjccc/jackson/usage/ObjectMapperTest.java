package com.yyjccc.jackson.usage;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class ObjectMapperTest {

	public static com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
	public static void main(String[] args) {
		//unserialize();
		serialize();
	}

	public static void unserialize() {
		String json="{\"name\":\"John\", \"age\":30}";
		Person person = null;
		try {
			person = objectMapper.readValue(json, Person.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(person);
	}


	public static void serialize(){
		Person yyjccc = new Person("yyjccc", 18);
		try {
			String s = objectMapper.writeValueAsString(yyjccc);
			System.out.println(s);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
