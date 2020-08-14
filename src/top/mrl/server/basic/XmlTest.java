package top.mrl.server.basic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlTest {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // 1. 获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2. 从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        // 3. 加载文档Document注册处理器
        // 4. 编写处理器
        PersonHandler handler = new PersonHandler();
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("top/mrl/server/basic/p.xml"), handler);

        System.out.println("======================DATAS=======================");
        List<Person> persons = handler.getPersons();
        for (Person p:persons) {
            System.out.println(p.getName() + "----->" + p.getAge());
        }
    }
}

//解析器

class PersonHandler extends DefaultHandler {
    private List<Person> persons;
    private Person person;
    private String tag;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("======数据解析开始======");
        persons = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println(qName + "---解析开始");
        if (qName.equals("person")) {
            person = new Person();
        }
        tag = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String str = new String(ch, start, length).trim();
        System.out.println("内容为-->" + str);
        if (null!=tag) {
            if (tag.equals("name")) {
                person.setName(str);
            }else if (tag.equals("age")) {
                person.setAge(Integer.parseInt(str));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println(qName + "---解析结束");
        if (qName.equals("person")) {
            persons.add(person);
        }
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("======数据解析结束======");
    }

    public List<Person> getPersons() {
        return persons;
    }
}

// 存储结构

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}