package com.huazai.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {
    private String currentTagName;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
        this.currentTagName = tagName;
    }

    @Override
    public void endElement(String uri, String localName, String tagName) throws SAXException {
        this.currentTagName = tagName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 不打印空串
        String s = new String(ch, start, length).trim();
        if (!"".equals(s)) {
            System.out.println(s);
        }

        // 只打印名字
        if ("名字".equals(this.currentTagName)) {
            System.out.println(s);
        }
    }
}
