package top.mrl.server.servlet01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public static void main(String[] args) {
        Server server01 = new Server();
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

            Servlet servlet = null;
            if (request.getUri().equals("/login")) {
                servlet = new LoginServlet();
            }else if (request.getUri().equals("/reg")) {
                servlet = new RegisterServlet();
            }

            assert servlet != null;
            servlet.service(request,response);

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
