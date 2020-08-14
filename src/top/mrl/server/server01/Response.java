package top.mrl.server.server01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.cert.CRL;
import java.util.Date;

public class Response {
    private BufferedWriter bw;
    private Socket client;
    private StringBuilder headInfo;
    private StringBuilder body;
    private int len;

    private final String SPACE = " ";
    private final String CRLF = "\r\n";

    public Response() {
        headInfo = new StringBuilder();
        body = new StringBuilder();
        len = 0;
    }

    public Response(Socket client) {
        this();
        this.client = client;
    }

    private void creatHeadInfo(int code) {
        // 响应行:HTTP/1.1 200 OK
        headInfo.append("HTTP/1.1").append(SPACE);
        headInfo.append(code).append(SPACE);
        headInfo.append("OK").append(CRLF);
        // 响应头:
            /*
             Date:Mon,31Dec209904:25:57GMT
             Server:Zhengyi Server/0.0.1;charser=GBK
             Content-type:text/html
             Content-length:39725426
             */
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:Zhengyi Server/0.0.1;charser=GBK").append(CRLF);
        headInfo.append("Content-type:text/html").append(CRLF);
        headInfo.append("Content-length:").append(len).append(CRLF);
        headInfo.append(CRLF);
    }

    public Response print(String string) {
        body.append(string);
        len += string.getBytes().length;
        return this;
    }

    public Response println(String string) {
        print(string).print(CRLF);
        return this;
    }

    public Response printHtml(String title, String body) {
        return this;
    }

    public void push(int code) {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            creatHeadInfo(code);
            bw.append(headInfo);
            bw.append(body);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
