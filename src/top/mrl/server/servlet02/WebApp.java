package top.mrl.server.servlet02;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class  WebApp {
    private static  WebContext webContext;
    static {
        try {
            // 1. 获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 2. 从解析工厂获取解析器
            SAXParser parser = factory.newSAXParser();
            // 3. 加载文档Document注册处理器
            // 4. 编写处理器
            PersonHandler handler = new PersonHandler();
            parser.parse(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("top/mrl/server/servlet02/web.xml"), handler);

            webContext = new WebContext(handler.getMappings(), handler.getEntities());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Servlet getServletFromUri(String uri) {
        Class cls = null;
        try {
            cls = Class.forName(webContext.getClass(uri));

            Servlet servlet = (Servlet) cls.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {
            System.out.println("[Response]: 404 NOT FOUND");
        }
        return null;
    }
}
