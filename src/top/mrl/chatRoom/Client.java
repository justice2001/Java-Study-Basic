package top.mrl.chatRoom;

import java.io.*;
import java.net.Socket;

/**
 * 聊天室 客户端
 * 群聊/私聊
 */

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8888);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入您的用户名:");
        String userName = console.readLine();

        new Thread(new Receive(client)).start();
        new Thread(new Send(client, userName)).start();
    }
}


class Receive implements Runnable {
    private Socket client;
    private DataInputStream dis;

    public Receive(Socket client) {
        this.client = client;
        try {
            this.dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = this.dis.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


class Send implements Runnable{
    private Socket client;
    private DataOutputStream dos;
    private String userName;
    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public Send(Socket client,String userName) {
        this.client = client;
        try {
            this.dos = new DataOutputStream(client.getOutputStream());
            this.userName = userName;
            this.dos.writeUTF(userName);
            this.dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = console.readLine();
                this.dos.writeUTF(msg);
                this.dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
