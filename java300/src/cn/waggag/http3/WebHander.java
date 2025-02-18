package cn.waggag.http3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHander extends DefaultHandler{
	private List<Entity> entityList;
	
	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public List<Mapping> getMappingList() {
		return mappingList;
	}

	public void setMappingList(List<Mapping> mappingList) {
		this.mappingList = mappingList;
	}

	private List<Mapping> mappingList;
	
	private Entity entity;
	private Mapping mapping;
	
	private String beginTag;
	private boolean isMap;

	@Override
	public void startDocument() throws SAXException {
		//文档解析开始
		entityList = new ArrayList<Entity>();
		mappingList = new ArrayList<Mapping>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName != null) {
			beginTag = qName;
			if(qName.equals("servlet")) {
				isMap = false;
				entity = new Entity();
			}else if(qName.equals("servlet-mapping")){
				isMap = true;
				mapping = new Mapping();
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//处理的内容
		if(beginTag != null) {
			String str = new String(ch, start, length);
			if(isMap) {
				if(beginTag.equals("servlet-name")) {
					mapping.setName(str);
					
				}else if(beginTag.equalsIgnoreCase("url-pattern")){
					mapping.getUrlPattern().add(str);
				}
			}else {
				if(beginTag.equals("servlet-name")) {
					entity.setName(str);
					
				}else if(beginTag.equalsIgnoreCase("url-pattern")){
					entity.setClz(str);
				}
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//结束元素
		if(qName != null) {
			if(qName.equals("servlet")) {
				entityList.add(entity);
			}else if(qName.equals("servlet-mapping")){
				mappingList.add(mapping);
			}
		}
		beginTag = null;
	}

	@Override
	public void endDocument() throws SAXException {
		//文档解析结束
		
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//获取解析器
		SAXParser sax = factory.newSAXParser();
		//指定xml+处理器
		WebHander webhander = new WebHander();
		sax.parse(Thread.currentThread().getContextClassLoader().
				getResourceAsStream("cn/waggag/http3/web.xml"), webhander);
		
		System.out.println(webhander.getEntityList());
	
	}
	
}
