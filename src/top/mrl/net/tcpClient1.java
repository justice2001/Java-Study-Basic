package top.mrl.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class tcpClient1 {
    public static void main(String[] args) throws IOException {
        // 打开Socket,建立连接
        Socket socket = new Socket("localhost", 8888);
        // 输入输出流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("Hello Server");
        dos.flush();
        // 关闭流
        dos.close();
        socket.close();
    }
}
