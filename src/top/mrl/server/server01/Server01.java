package top.mrl.server.server01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server01 {
    private ServerSocket serverSocket;
    public static void main(String[] args) {
        Server01 server01 = new Server01();
        server01.start();
        server01.receive();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("[ERR]:服务器启动失败");
        }
    }

    public void receive() {
        try {
            Socket client = serverSocket.accept();
            System.out.println("[INFO]:建立了新的连接");
            Request request = new Request(client);

            // 响应
            Response response = new Response(client);

            response.print("<html>");
            response.print("<head>");
            response.print("<title>Response</title>");
            response.print("</head>");
            response.print("<body>");
            response.print("Welcome Back " + request.getParameter("uname"));
            response.print("</body>");
            response.print("</html>");

            response.push(200);
        } catch (IOException e) {
            System.out.println("[ERR]:连接建立失败");
        }
    }

    // 响应
    public void response() {

    }

    public void stop() {

    }
}
