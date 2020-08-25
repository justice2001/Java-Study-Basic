package top.mrl.server.servlet02;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PersonHandler extends DefaultHandler {
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
