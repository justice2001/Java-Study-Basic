package top.mrl.net;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer {
    public static void main(String[] args) throws IOException {
        String umame = "";
        String upwd = "";

        ServerSocket serverSocket = new ServerSocket(8888);
        Socket client = serverSocket.accept();
        System.out.println("Client connected!");

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        String[] p = dis.readUTF().split("&");
        for (String item:p) {
            String[] tmp = item.split("=");
            if (tmp[0].equals("uname")) {
                umame = tmp[1];
            }else if (tmp[0].equals("upwd")) {
                upwd = tmp[1];
            }
        }
        if (umame.equals("admin")&&upwd.equals("123456")) {
            dos.writeUTF("登录成功!");
        }else {
            dos.writeUTF("用户名和密码不匹配!");
        }
        dos.flush();

        client.close();
        dos.close();
        dis.close();
    }
}
