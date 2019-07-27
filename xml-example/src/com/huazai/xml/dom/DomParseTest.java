package com.huazai.xml.dom;


import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class DomParseTest {
    @Test
    public void test() throws Exception {
        // 1、创建一个DocumentBuilderFactory工厂类
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // 2、通过工厂类创建一个DocumentBuilder对象
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        // 3、指定解析哪个xml文件
        Document document = documentBuilder.parse("src/dom_stu.xml");
        traverseDomTree(document);
        System.out.println();
        read(document);
        System.out.println();
//        add(document);
        System.out.println();
//        delete(document);
        update(document);
    }

    public void update(Document document) throws TransformerException {
        Element element = (Element) document.getElementsByTagName("学生").item(0);
        Element name = (Element) element.getElementsByTagName("名字").item(0);
        name.setTextContent("宋江");
        name.setAttribute("昵称", "黑松江");

        write(document, "src/dom_stu.xml");
    }

    public void delete(Document document) throws TransformerException {
        Element element = (Element) document.getElementsByTagName("学生").item(0);
        element.removeAttribute("sex");
        element.removeChild(element.getLastChild());

        write(document, "src/dom_stu.xml");
    }

    public void add(Document document) throws Exception {
        // 创建element
        Element element = document.createElement("学生");
        // 设置属性
        element.setAttribute("sex", "man");
        // 增加孩子
        Element name = document.createElement("姓名");
        name.setTextContent("刘德华");
        name.setAttribute("昵称", "华仔");
        Element age = document.createElement("年龄");
        age.setTextContent("65");
        Element introduction = document.createElement("介绍");
        introduction.setTextContent("坏孩子");
        element.appendChild(name);
        element.appendChild(age);
        element.appendChild(introduction);
        document.getDocumentElement().appendChild(element);

        write(document, "src/dom_stu.xml");
    }

    /**
     * 操作xml文档
     *
     * @param node
     * @param path
     * @throws TransformerException
     */
    private void write(Node node, String path) throws TransformerException {
        // 通过Transform对象写入xml文件
        // 获取工程类对象
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(node), new StreamResult(path));
    }

    public void read(Document document) {
        NodeList nodeList = document.getElementsByTagName("学生");
        System.out.println("学生个数：" + nodeList.getLength());
        Node item = nodeList.item(0);
        System.out.println(item.getTextContent());
        System.out.println(item.getAttributes());

        Element element = (Element) item;
        System.out.println("getTextContent：" + element.getTextContent());
        System.out.println("getTagName：" + element.getTagName());
        System.out.println("getNodeName：" + element.getNodeName());
        System.out.println("getAttribute：" + element.getAttribute("sex"));
        System.out.println("第一个学生的名字：" + element.getElementsByTagName("名字").item(0).getTextContent());
    }

    /**
     * 遍历dom树
     */
    private void traverseDomTree(Node node) {
        if (node != null) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                // 判断节点类型
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(item);
                }
                traverseDomTree(item);
            }
        }
    }

}
