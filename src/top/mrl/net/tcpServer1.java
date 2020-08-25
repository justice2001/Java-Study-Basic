package top.mrl.net;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class tcpServer1 {
    public static void main(String[] args) throws IOException {
        // 打开ServerSocket
        ServerSocket ss = new ServerSocket(8888);
        // 在Socket上监听用户
        Socket client = ss.accept();
        System.out.println("Have a client connected!");
        // 输入输出
        DataInputStream dis = new DataInputStream(client.getInputStream());
        System.out.println(dis.readUTF());
        // 关闭流
        dis.close();
        client.close();
    }
}
