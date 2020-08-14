package top.mrl.server.servlet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class XmlTest2 {
    public static void main(String[] args) throws Exception {
        // 1. 获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2. 从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        // 3. 加载文档Document注册处理器
        // 4. 编写处理器
        PersonHandler1 handler = new PersonHandler1();
        parser.parse(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("top/mrl/server/servlet/web.xml"), handler);

        WebContext webContext = new WebContext(handler.getMappings(), handler.getEntities());
        Class cls = Class.forName(webContext.getClass("/reg"));
        Servlet servlet = (Servlet)cls.getConstructor().newInstance();
        System.out.println(servlet);
        servlet.service();
    }
}

class PersonHandler1 extends DefaultHandler {
    private List<Entity> entities;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;
    private String tag;
    private boolean isMapping;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        entities = new ArrayList<>();
        mappings = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        tag = qName;
        if (qName.equals("servlet")) {
            entity = new Entity();
            isMapping = false;
        }else if (qName.equals("servlet-mapping")) {
            mapping = new Mapping();
            isMapping = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String str = new String(ch,start,length);
        if (null!=tag) {
            if (isMapping) {
                if (tag.equals("servlet-name")) {
                    mapping.setName(str);
                }else if (tag.equals("url-pattern")) {
                    mapping.addPatterns(str);
                }
            }else {
                if (tag.equals("servlet-name")) {
                    entity.setName(str);
                }else if (tag.equals("servlet-class")) {
                    entity.setCls(str);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("servlet")) {
            entities.add(entity);
        }else if (qName.equals("servlet-mapping")) {
            mappings.add(mapping);
        }
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}
