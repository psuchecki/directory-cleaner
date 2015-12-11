package com.orsastudio;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ConfigurationFileParser {
    public ConfigFileInfo parseConfigFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(getClass().getResourceAsStream("/config.xml"));

        Element rootElement = document.getDocumentElement();
        NodeList nodes = rootElement.getChildNodes();

        ConfigFileInfo configFileInfo = new ConfigFileInfo();
        for(int i=0; i<nodes.getLength(); i++){
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                configFileInfo.addElement((Element) node);
            }
        }
        return configFileInfo;
    }
}
