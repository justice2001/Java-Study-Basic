package top.mrl.server.servlet02;

import java.io.IOException;
import java.net.Socket;

/**
 * 分发器
 */

public class Dispatcher implements Runnable {
    private Socket client;
    private Response response;
    private Request request;

    public Dispatcher (Socket client) {
        try {
            this.client = client;
            request = new Request(client);
            // 响应
            response = new Response(client);
        }catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void run() {
        try {
            Servlet servlet = (Servlet) WebApp.getServletFromUri(request.getUri());
            if (null!=servlet) {
                servlet.service(request,response);
                response.push(200);
            }else {
                response.push(404);
            }
        }catch (Exception e) {
            response.push(500);
        }
        release();
    }

    private void release() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
