package top.mrl.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginMultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("Wait Connect....");
        while (true) {
            Socket client = serverSocket.accept();
            new Thread(new LoginThread(client)).start();
        }
    }

    static class LoginThread implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private String uname;
        private String upwd;

        public LoginThread(Socket client) {
            System.out.println("Create a connection!");
            this.client = client;
            try {
                this.dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String[] p = new Receive(dis).receive().split("&");
            for (String item:p) {
                String[] tmp = item.split("=");
                if (tmp[0].equals("uname")) {
                    this.uname = tmp[1];
                }else if (tmp[0].equals("upwd")) {
                    this.upwd = tmp[1];
                }
            }
            if (uname.equals("admin")&&upwd.equals("123456")) {
                new Send(dos).send("登录成功!");
            }else {
                new Send(dos).send("登录失败!");
            }
            release();
        }

        public void release() {
            try {
                if (null != dis)
                    this.dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != this.dos)
                    this.dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != this.client)
                    this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static class Send {
            private String msg;
            private DataOutputStream dos;
            public Send(DataOutputStream dos) {
                this.dos = dos;
            }

            public void send(String msg) {
                try {
                    dos.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        static class Receive {
            private DataInputStream dis;
            public Receive(DataInputStream dis) {
                this.dis = dis;
            }

            public String receive() {
                try {
                    return dis.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "";
            }
        }
    }
}
