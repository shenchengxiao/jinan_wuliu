package com.manager.common.tool;

import com.manager.pojo.citys.City;
import com.manager.pojo.citys.Province;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KUMA on 2017/1/11.
 */
public class XmlParser {
	
    public static List<Province> parseCity(InputStream is) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);
        Element root = document.getDocumentElement();
        NodeList provinceNodes = root.getChildNodes();

        List<Province> provinces = new ArrayList<>();
        if (provinceNodes != null && provinceNodes.getLength() > 0) {
            for (int i = 0; i < provinceNodes.getLength(); i++) {
                Node node = provinceNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("a")) {
                    Element provinceElement = (Element) provinceNodes.item(i);
                    Province province = new Province();
                    province.id = i+"";
                    province.text = provinceElement.getAttribute("name");
                    NodeList cityNodes = provinceElement.getChildNodes();
                    if (cityNodes != null && cityNodes.getLength() > 0) {
                        for (int j = 0; j < cityNodes.getLength(); j++) {
                            Node cityNode = cityNodes.item(j);
                            if (cityNode.getNodeType() == Node.ELEMENT_NODE && cityNode.getNodeName().equals("a")) {
                                Element cityElement = (Element) cityNodes.item(j);
                                City city = new City();
                                StringBuffer ids = new StringBuffer();
                                ids.append(i+""+j);
                                String sid = ids.toString();
                                city.id = sid;
                                city.text = cityElement.getAttribute("name");
                                /*NodeList regionNodes = cityElement.getChildNodes();
                                if (regionNodes != null && regionNodes.getLength() > 0) {
                                    for (int k = 0; k < regionNodes.getLength(); k++) {
                                        Node regionNode = regionNodes.item(k);
                                        if (regionNode.getNodeType() == Node.ELEMENT_NODE && regionNode.getNodeName().equals("a")) {
                                            Element regionElement = (Element) regionNodes.item(k);
                                            Region region = new Region();
                                            region.id = 100+k+1;
                                            region.text = regionElement.getAttribute("name");
                                            //city.children.add(region);
                                        }
                                    }
                                }*/
                                province.children.add(city);
                            }
                        }
                    }
                    provinces.add(province);
                }
            }
        }
        return provinces;
    }

    public static List<String> parse(InputStream is) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);
        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        List<String> list = new ArrayList<>();
        if (nodes != null && nodes.getLength() > 0) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("a")) {
                    Element element = (Element) nodes.item(i);
                    list.add(element.getAttribute("name"));
                }
            }
        }
        return list;
    }
}
