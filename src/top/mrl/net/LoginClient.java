package top.mrl.net;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

public class LoginClient {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名:");
        String uname = br.readLine();
        System.out.print("请输入密码:");
        String upwd = br.readLine();

        Socket socket = new Socket("localhost", 8888);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        dos.writeUTF("uname=" + uname + "&upwd=" + upwd);
        System.out.println(dis.readUTF());

        socket.close();
        dis.close();
        dos.close();
    }
}
