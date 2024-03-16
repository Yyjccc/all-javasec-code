package com.yyjccc.enity;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Test {
    private Map mymap;


    public Test(){
        System.out.println("调用了构造方法");
    }
    static {
        System.out.println("调用了静态代码块");
    }
    public void setCmd(String cmd) throws IOException {
        System.out.println("调用了set方法");
        Runtime.getRuntime().exec(cmd);
    }


    public String getCmd(){
        System.out.println("调用了get方法");
        return "get";
    }

    public Map getMymap() {
        return mymap;
    }




}
