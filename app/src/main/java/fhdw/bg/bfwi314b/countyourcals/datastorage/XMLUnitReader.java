package fhdw.bg.bfwi314b.countyourcals.datastorage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko.
 */

public class XMLUnitReader {

    public ArrayList<Unit> readUnit(File file) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String unitXML = "";
        while (line != null) {
            unitXML = unitXML + line /*+ System.getProperty("line.Seperator")*/;
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(unitXML));
        document = documentBuilder.parse(inputSource);

        Element xmlData = document.getDocumentElement();
        /*NodeList rootNode = xmlData.getElementsByTagName("Users");
        NodeList unitNodeList = rootNode.item(0).getChildNodes();*/
        NodeList unitNodeList = xmlData.getElementsByTagName("Unit");

        Node unitNode;
        NodeList unitChildNodes;
        String tmpUnit = "";
        String tmpUnitShort = "";
        Integer tmpUnitQuantity = 0;

        ArrayList<Unit> unitList = new ArrayList<Unit>();

        for (int i = 0; i < unitNodeList.getLength(); i++) {
            unitNode = unitNodeList.item(i);
            unitChildNodes = unitNode.getChildNodes();
            tmpUnit = unitChildNodes.item(0).getTextContent();
            tmpUnitShort = unitChildNodes.item(1).getTextContent();
            tmpUnitQuantity = Integer.parseInt(unitChildNodes.item(2).getTextContent());
            unitList.add(new Unit(tmpUnit, tmpUnitShort, tmpUnitQuantity));
            tmpUnit = "";
            tmpUnitShort = "";
        }

        return unitList;
    }

}
