package com.huazai.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Dom4jTest {
    @Test
    public void test() throws Exception {
        // 1、等到解析器
        SAXReader saxReader = new SAXReader();
        // 2、指定解析哪个xml文件，得到代表整个文档的Document对象
        Document document = saxReader.read("src/dom4j_stu.xml");
//        list(document.getRootElement());
//        read(document);
//        add(document);
//        delete(document);
//        update(document);
        addByIndex(document);

    }

    public void delete(Document document) throws IOException {
        Element student = document.getRootElement().element("学生");
        student.getParent().remove(student);

        Element student1 = document.getRootElement().element("学生");
        student1.remove(student1.attribute("sex"));

        write(document);
    }

    public void update(Document document) throws IOException {
        List<Element> students = document.getRootElement().elements("学生");
        // 所有的年龄加10
        for (Element e : students) {
            Element age = e.element("年龄");
            if (age != null) {
                age.setText((Integer.valueOf(age.getText()) + 10) + "");
            }

            Element name = e.element("名字");
            if (name != null) {
                name.addAttribute("nickname", "kitty");
            }
        }

        write(document);
    }

    /**
     * 增加
     *
     * @param document
     * @throws IOException
     */
    public void add(Document document) throws IOException {

        Element student = DocumentHelper.createElement("student");
        Element name = DocumentHelper.createElement("name");
        name.addCDATA("马云爸爸");
        name.addAttribute("nickname", "马云爸爸");
        Element age = DocumentHelper.createElement("age");
        Element introduce = DocumentHelper.createElement("introduce");

        document.getRootElement().add(student);
        document.getRootElement().add(name);

        document.getRootElement().add(age);
        document.getRootElement().add(introduce);

//        document.getRootElement().addElement("test");
//        document.getRootElement().addAttribute("addAttribute","addAttribute");
        write(document);
    }

    public void addByIndex(Document document) throws IOException {
        Element student = DocumentHelper.createElement("学生");
        student.setText("庞英华");

        List<Element> elements = document.getRootElement().elements();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i).element("名字");
            if (element != null && "周星驰".equals(element.getText())) {
                elements.add(i + 1, student);
            }
        }

        write(document);
    }

    private void write(Document document) throws IOException {
        // 1、用字节流可以避免编码问题
        // XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/dom4j_stu.xml"));
        // 2、或者指定编码格式，OutputFormat还可以指定内容样式
        // OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 2.1 OutputFormat outputFormat = OutputFormat.createCompactFormat();
        // outputFormat.setEncoding("utf-8");
        // XMLWriter xmlWriter = new XMLWriter(new FileWriter("src/dom4j_stu.xml"),outputFormat);

        // 3、
        XMLWriter xmlWriter = new XMLWriter(new FileWriter("src/dom4j_stu.xml"));
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public void read(Document document) {
        // 获取根元素
        Element rootElement = document.getRootElement();
        // 取出对应元素的所有元素
        List<Element> stuList = rootElement.elements("学生");
        System.out.println(stuList.get(0).getName());
        // 取出对应元素集合的第一个元素
        Element stu = rootElement.element("学生");
        System.out.println(stu.getName());

        // 取属性
        Attribute attribute = stu.attribute(0);
        System.out.println(attribute.getName() + "：" + attribute.getValue() + " " + attribute.getStringValue());

        System.out.println(stu.attribute("aihao").getName() + "：" + stu.attribute("aihao").getValue() + " " + stu.attribute("aihao").getStringValue());

        System.out.println(stu.getTextTrim() + " " + stu.attributeValue("aihao"));

        // 不能跨层取数据，跨层取需要用到XPATH技术
        System.out.println(rootElement.elements("名字").size());
    }


    /**
     * 遍历
     *
     * @param element
     */
    public void list(Element element) {
        System.out.println(element.getName() + "：" + element.getTextTrim());
        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            list(next);
        }

    }
}
