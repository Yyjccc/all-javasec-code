package com.yyjccc.yaml;


import org.yaml.snakeyaml.Yaml;

public class test {

	public static void unserialize(){
		String str1 = "!!com.yyjccc.yaml.User {age: 25, name: DawnT0wn}";
		Yaml yaml = new Yaml();
		System.out.println("unserialize start");
		yaml.load(str1);

		//		String str2 = "age: 25\n" +
//				"name: DawnT0wn";
		//yaml.loadAs(str2, User.class);
	}

	public static void main(String[] args) {
		unserialize();
	}
	public static void serialize(){
		User user = new User();
		user.setName("DawnT0wn");
		user.setAge(25);
		Yaml yaml = new Yaml();
		String str = yaml.dump(user);
		System.out.println(str);
	}

}
