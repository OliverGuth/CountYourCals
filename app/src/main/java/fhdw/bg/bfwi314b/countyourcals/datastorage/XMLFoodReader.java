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

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko Reder
 */

public class XMLFoodReader {

    //Reads the Foods  from  an XML-File and returns these as ArrayList<Food>
    //Therefore the XML-Opject is mapped into a tree and all attributes are read
    //From these Attributes the Foods are creadted (as Food)
    public ArrayList<Food> readFood(File file) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String foodXML = "";
        while (line != null) {
            foodXML = foodXML + line /*+ System.getProperty("line.Seperator")*/;
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(foodXML));
        document = documentBuilder.parse(inputSource);

        Element xmlData = document.getDocumentElement();
        /*NodeList rootNode = xmlData.getElementsByTagName("Foods");
        NodeList foodNodeList = rootNode.item(0).getChildNodes();*/
        NodeList foodNodeList = xmlData.getElementsByTagName("Food");

        Node foodNode;
        NodeList foodChildNodes;
        NodeList relationNode;
        String tmpName = "";
        String tmpUnitName = "";
        String tmpUnitShort = "";
        Integer tmpQuantity = 0;
        ArrayList<Unit> tmpUnits = new ArrayList<Unit>();
        Integer tmpKCal = null;
        ArrayList<Food> foodList = new ArrayList<Food>();

        for (int i = 0; i < foodNodeList.getLength(); i++) {
            foodNode = foodNodeList.item(i);
            foodChildNodes = foodNode.getChildNodes();
            tmpName = foodChildNodes.item(0).getTextContent();
            tmpKCal = Integer.parseInt(foodChildNodes.item(1).getTextContent());
            for (int j = 2; j < foodChildNodes.getLength(); j++) {
                relationNode = foodChildNodes.item(j).getChildNodes();
                tmpQuantity = Integer.parseInt(relationNode.item(0).getTextContent());
                tmpUnitName = relationNode.item(1).getTextContent();
                tmpUnitShort = relationNode.item(2).getTextContent();
                tmpUnits.add(new Unit(tmpUnitName, tmpUnitShort, tmpQuantity));
            }
            foodList.add(new Food(tmpName, tmpUnits, tmpKCal));
            //tmpQuantity.clear();
            tmpUnits.clear();
            tmpKCal = null;
            tmpName = "";
        }

        return foodList;
    }
}
