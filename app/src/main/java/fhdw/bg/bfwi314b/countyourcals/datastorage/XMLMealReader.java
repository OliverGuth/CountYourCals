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
            mealXML = mealXML + line + "\n";
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
        NodeList mealNodeList = xmlData.getElementsByTagName("Meal");

        Node mealNode;
        NodeList mealChildNodes;
        NodeList ingredientNode;
        String tmpName = "";
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
            tmpName = mealChildNodes.item(0).getNodeValue();
            tmpQuantity = Integer.parseInt(mealChildNodes.item(1).getNodeValue());
            tmpUnit = mealChildNodes.item(2).getNodeValue();
            tmpKCal = Integer.parseInt(mealChildNodes.item(3).getNodeValue());
            //mealList.add(new Meal(tmpName, tmpQuantity, tmpUnit, tmpKCal))

            for (int j = 4; j < mealChildNodes.getLength(); j++) {
                ingredientNode = mealChildNodes.item(j).getChildNodes();
                tmpFoodName.add(ingredientNode.item(0).getNodeValue());
                tmpFoodQuantity.add(Integer.parseInt(ingredientNode.item(1).getNodeValue()));
                tmpFoodUnit.add(ingredientNode.item(2).getNodeValue());
                tmpFoodKCal.add(Integer.parseInt(ingredientNode.item(3).getNodeValue()));
            }
            mealList.add(new Meal(tmpName, tmpQuantity, tmpUnit, tmpKCal, tmpFoodName, tmpFoodQuantity, tmpFoodUnit, tmpFoodKCal));
            tmpFoodName.clear();
            tmpFoodQuantity.clear();
            tmpFoodUnit.clear();
            tmpFoodKCal.clear();
            tmpName = "";
            tmpQuantity = 0;
            tmpUnit = "";
            tmpKCal = 0;
        }

        return mealList;
    }
}
