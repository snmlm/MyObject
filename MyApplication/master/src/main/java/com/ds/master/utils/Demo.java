package com.ds.master.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/10/14.
 */
public class Demo {
    public void run(){
        SAXParserFactory spy = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = spy.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            MyHandler myHandler = new MyHandler();
            xmlReader.setContentHandler(myHandler);
            xmlReader.parse("C:/Users/Administrator/Desktop/Demo.xml");
            List<Person> list = myHandler.getList();
            for (Person person : list) {
                System.out.println("id："+person.id +"  name："+person.name+"  age："+person.age);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

class MyHandler extends DefaultHandler {

    private List<Person> list = new ArrayList<>();
    private Person person;
    private String tag;

    /**
     * 标签开始
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tag = qName;
        if("person".equals(tag)){
            person = new Person();
            person.id = attributes.getIndex("id");
        }
    }

    /**
     * 标签结束
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("person".equals(tag)){
            list.add(person);
            person = null;
        }
        tag = null;
    }

    /**
     * 标签后的数据
     * 每次标签方法结束后都会调用获取标签后的数据
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (tag){
            case "name":
                person.name = new String(ch,start,length);
                break;
            case "age":
                person.age = new String(ch,start,length);
                break;
        }
    }

    public List<Person> getList() {
        return list ;
    }
}

class Person{
    public int id;
    public String name;
    public String age;
}
}
