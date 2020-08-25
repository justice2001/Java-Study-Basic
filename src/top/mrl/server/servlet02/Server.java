package top.mrl.server.servlet02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private boolean isRunning;
    public static void main(String[] args) {
        Server server01 = new Server();
        server01.start();
        server01.receive();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            isRunning = true;
            System.out.println("[INFO]:服务器已启动");
        } catch (IOException e) {
            System.out.println("[ERR]:服务器启动失败");
        }
    }

    public void receive() {
        while (isRunning) {
            try {
                Socket client = serverSocket.accept();
                new Thread(new Dispatcher(client)).start();
            } catch (IOException e) {
                System.out.println("[ERR]:连接建立失败");
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
