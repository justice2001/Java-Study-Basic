package top.mrl.chatRoom;

import javax.xml.crypto.Data;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 聊天室 服务端
 * 群聊/私聊
 */

public class Server {
    private static CopyOnWriteArrayList<ClientThread> allClient = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket client = serverSocket.accept();
            ClientThread c = new ClientThread(client);
            allClient.add(c);
            new Thread(c).start();
        }
    }

    static class ClientThread implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private String userName;
        private boolean isRunning = true;

        public ClientThread(Socket client) {
            this.client = client;
            try {
                this.dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                release();
            }
            try {
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                release();
            }
            this.userName = receive();
            sendAll(this.userName + "  加入了聊天室", true);
            send("欢迎您的加入");
        }

        private void send(String msg) {
            try {
                this.dos.writeUTF(msg);
                this.dos.flush();
            } catch (IOException e) {
                release();
            }
        }

        private void sendAll(String msg, boolean isSys) {
            boolean isPrivate = msg.startsWith("@");
            if (isPrivate) {
                int idx = msg.indexOf(":");
                String targetName = msg.substring(1, idx);
                msg = msg.substring(idx + 1);
                for (ClientThread ct:allClient) {
                    if (ct.userName.equals(targetName)) {
                        ct.send(userName + " 悄悄的对您说:" + msg);
                    }
                }
            }else {
                for (ClientThread user:allClient) {
                    if (!(user ==this)) {
                        if (isSys) {
                            user.send("System:" + msg);
                        }else {
                            user.send(this.userName + ":" + msg);
                        }
                    }
                }
            }
        }

        private String receive() {
            String str = "";
            try {
                str = dis.readUTF();
            } catch (IOException e) {
                release();
            }
            return str;
        }

        private void release() {
            this.isRunning = false;
            sendAll(this.userName + "  退出了聊天室", true);
            Utils.close(dis,dos,client);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                sendAll(msg, false);
            }
        }
    }
}


class Utils implements Cloneable {
    static void close(Closeable... objs) {
        for (Closeable obj:objs) {
            try {
                obj.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
