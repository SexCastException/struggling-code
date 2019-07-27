package com.huazai.xml.sax;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParseTest {

    @Test
    public void testParse() throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse("src/sax_stu.xml", new MyHandler());
    }

    @Test
    public void test() {
        //  -Xmx1024m
        byte[] bytes = new byte[1024 * 1024 * 500];
        bytes[0] = 0;
        System.out.println(bytes);
    }
}
