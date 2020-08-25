package top.mrl.server.servlet01;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

public class Request {
    private InputStream is;
    private String requestInfo;
    private String method;
    private String uri;
    private String query;
    private Map<String, List<String>> queryMap = new HashMap<>();

    private final String CRLF = "\r\n";

    public Request() {
    }

    public Request(InputStream is) {
        this();
        this.is = is;

        byte[] datas = new byte[1024*1024];
        int len = 0;
        try {
            if (is != null) {
                len = is.read(datas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestInfo = new String(datas,0,len);

        parse();
    }

    public Request(Socket client) throws IOException {
        this(client.getInputStream());
    }

    public void parse() {
        // method
        method = requestInfo.substring(0, requestInfo.indexOf("/") - 1);
        System.out.println("Method: " + method);
        // uri And query
        uri = requestInfo.substring(requestInfo.indexOf("/"), requestInfo.indexOf("HTTP/") - 1);
        if (uri.indexOf("?") >= 0) {
            String[] uriArray = uri.split("\\?");
            uri = uriArray[0];
            query = uriArray[1];
        }else {
            query = "";
        }
        System.out.println("uri: " + uri);
        // datas
        if (method.equals("POST")) {
            query += "&" + requestInfo.substring(requestInfo.lastIndexOf(CRLF) + 2);
        }
        System.out.println("query: " + query);
        convertMap();
    }

    private void convertMap() {
        String[] keyValues = query.split("&");
        for (String keyValue:keyValues) {
            String[] kv = keyValue.split("=");
            kv = Arrays.copyOf(kv,2);
            String key = kv[0];
            String value = kv[1]==null?null:decode(kv[1], "utf-8");
            if (!queryMap.containsKey(key)) {
                queryMap.put(key, new ArrayList<>());
            }
            queryMap.get(key).add(value);
        }
    }

    private String decode(String value, String enc) {
        try {
            return java.net.URLDecoder.decode(value,enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getParameters(String key) {
        List<String> values = queryMap.get(key);
        if (null == values || values.size()<1) {
            return null;
        }
        return values.toArray(new String[0]);
    }

    public String getParameter(String key) {
        String[] values = getParameters(key);
        return values==null?null:values[0];
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }
}
