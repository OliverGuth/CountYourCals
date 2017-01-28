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

/**
 * Created by Niko.
 */

public class XMLFoodReader {

    public ArrayList<Food> readFood(File file) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String foodXML = "";
        while (line != null) {
            foodXML = foodXML + line + "\n";
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
        NodeList roodNode = xmlData.getElementsByTagName("Foods");
        NodeList foodNodeList = roodNode.item(0).getChildNodes();

        Node foodNode;
        NodeList foodChildNodes;
        NodeList relationNode;
        String tmpName = "";
        ArrayList<String> tmpUnit = new ArrayList<String>();
        ArrayList<Integer> tmpQuantity = new ArrayList<Integer>();
        ArrayList<Integer> tmpKCal = new ArrayList<Integer>();
        ArrayList<Food> foodList = new ArrayList<Food>();

        for (int i = 0; i < foodNodeList.getLength(); i++) {
            foodNode = foodNodeList.item(i);
            foodChildNodes = foodNode.getChildNodes();
            tmpName = foodChildNodes.item(0).getNodeValue();
            for (int j = 1; j < foodChildNodes.getLength(); j++) {
                relationNode = foodChildNodes.item(j).getChildNodes();
                tmpQuantity.add(Integer.parseInt(relationNode.item(0).getNodeValue()));
                tmpUnit.add(relationNode.item(1).getNodeValue());
                tmpKCal.add(Integer.parseInt(relationNode.item(2).getNodeValue()));
            }
            foodList.add(new Food(tmpName, tmpQuantity, tmpUnit, tmpKCal));
            tmpQuantity.clear();
            tmpUnit.clear();
            tmpKCal.clear();
            tmpName = "";
        }

        return foodList;
    }
}
