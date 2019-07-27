package com.huazai.xml.xpath;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * 更多xpath表达式查看xml文档
 */
public class XPathTest {
    @Test
    public void test() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/xpath.xml");
        System.out.println(document);
        List list = document.selectNodes("/A/B");
        System.out.println(list.size());
    }
}
