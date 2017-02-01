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

public class XMLMealReader {

    public ArrayList<Meal> readMeal(File file) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String mealXML = "";
        while (line != null) {
            mealXML = mealXML + line /*+ System.getProperty("line.Seperator")*/;
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(mealXML));
        document = documentBuilder.parse(inputSource);

        Element xmlData = document.getDocumentElement();
        /*NodeList rootNode = xmlData.getElementsByTagName("Meals");
        NodeList mealNodeList = rootNode.item(0).getChildNodes();*/
        NodeList mealNodeList = xmlData.getElementsByTagName("Meal");

        Node mealNode;
        NodeList mealChildNodes;
        NodeList ingredientNode;
        String tmpName = "";
        Integer tmpIdentifier = 0;
        Integer tmpQuantity = 0;
        String tmpUnit = "";
        Integer tmpKCal = 0;
        ArrayList<String> tmpFoodName = new ArrayList<String>();
        ArrayList<Integer> tmpFoodQuantity = new ArrayList<Integer>();
        ArrayList<String> tmpFoodUnit = new ArrayList<String>();
        ArrayList<Integer> tmpFoodKCal = new ArrayList<Integer>();
        ArrayList<Meal> mealList = new ArrayList<Meal>();

        for (int i = 0; i < mealNodeList.getLength(); i++) {
            mealNode = mealNodeList.item(i);
            mealChildNodes = mealNode.getChildNodes();
            tmpName = mealChildNodes.item(0).getTextContent();
            tmpIdentifier = Integer.parseInt(mealChildNodes.item(1).getTextContent());
            tmpQuantity = Integer.parseInt(mealChildNodes.item(2).getTextContent());
            tmpUnit = mealChildNodes.item(3).getTextContent();
            tmpKCal = Integer.parseInt(mealChildNodes.item(4).getTextContent());
            //mealList.add(new Meal(tmpName, tmpQuantity, tmpUnit, tmpKCal))

            for (int j = 4; j < mealChildNodes.getLength(); j++) {
                ingredientNode = mealChildNodes.item(j).getChildNodes();
                tmpFoodName.add(ingredientNode.item(0).getTextContent());
                tmpFoodQuantity.add(Integer.parseInt(ingredientNode.item(1).getTextContent()));
                tmpFoodUnit.add(ingredientNode.item(2).getTextContent());
                tmpFoodKCal.add(Integer.parseInt(ingredientNode.item(3).getTextContent()));
            }
            mealList.add(new Meal(tmpName, tmpIdentifier, tmpQuantity, tmpUnit, tmpKCal, tmpFoodName, tmpFoodQuantity, tmpFoodUnit, tmpFoodKCal));
            tmpFoodName.clear();
            tmpFoodQuantity.clear();
            tmpFoodUnit.clear();
            tmpFoodKCal.clear();
            tmpName = "";
            tmpIdentifier = 0;
            tmpQuantity = 0;
            tmpUnit = "";
            tmpKCal = 0;
        }

        return mealList;
    }
}
