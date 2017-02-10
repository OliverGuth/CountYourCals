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
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko.
 */

public class XMLMealReader {

    public ArrayList<Meal> readMeal(File file/*, String userName*/) throws ParserConfigurationException, IOException, SAXException {
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
        NodeList tmpMealChildNodes;
        NodeList tmpMealRelationNodes;
        String tmpName = "";
        Integer tmpIdentifier = 0;
        Integer tmpMealQuantity = 0;
        String tmpMealUnitName = "";
        String tmpMealUnitShort = "";
        ArrayList<Unit> tmpMealUnits = new ArrayList<Unit>();
        //ArrayList<Food> tmpIngredientList = new ArrayList<Food>();
        ArrayList<Meal> mealList = new ArrayList<Meal>();

        for (int i = 0; i < mealNodeList.getLength(); i++) {
            mealNode = mealNodeList.item(i);
            tmpMealChildNodes = mealNode.getChildNodes();
            tmpName = tmpMealChildNodes.item(0).getTextContent();
            tmpIdentifier = Integer.parseInt(tmpMealChildNodes.item(1).getTextContent());
            for (int j = 2; j < tmpMealChildNodes.getLength(); j++) {
                tmpMealRelationNodes = tmpMealChildNodes.item(j).getChildNodes();
                for (int k = 0; k < tmpMealRelationNodes.getLength(); k++) {
                    tmpMealQuantity = Integer.parseInt(tmpMealRelationNodes.item(k).getChildNodes().item(0).getTextContent());
                    tmpMealUnitName = tmpMealRelationNodes.item(k).getChildNodes().item(1).getTextContent();
                    tmpMealUnitShort = tmpMealRelationNodes.item(k).getChildNodes().item(2).getTextContent();
                    tmpMealUnits.add(new Unit(tmpMealUnitName, tmpMealUnitShort, tmpMealQuantity));
                }
            }
            //tmpIngredientList = this.readMealIngredients(new File(userName + tmpIdentifier + ".xml"));
            //mealList.add(new Meal(tmpName, tmpIdentifier, tmpMealQuantity, tmpMealUnit, tmpIngredientList));
            mealList.add(new Meal(tmpName, tmpIdentifier, tmpMealUnits));
            tmpName = "";
            tmpIdentifier = 0;
            //tmpMealQuantity.clear();
            //tmpMealUnit.clear();
            tmpMealUnitName = "";
            tmpMealUnitShort = "";
            tmpMealQuantity = 0;
            tmpMealUnits.clear();
            //tmpIngredientList.clear();
        }

        return mealList;
    }

    public ArrayList<Food> readMealIngredients(File file) throws ParserConfigurationException, IOException, SAXException {
        XMLReader xmlReader = new XMLReader();
        return xmlReader.readFood(file);

        /*BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String mealIngredientsXML = "";
        while (line != null) {
            mealIngredientsXML = mealIngredientsXML + line /*+ System.getProperty("line.Seperator")*//*;
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(mealIngredientsXML));
        document = documentBuilder.parse(inputSource);

        Element xmlData = document.getDocumentElement();
        NodeList mealIngredientsNodeList = xmlData.getElementsByTagName("MealIngredient");

        Node mealIngredientNode;
        NodeList tmpMealIngredientChildNodes;
        NodeList tmpMealIngredientRelationNodes;
        String tmpName = "";
        Integer tmpIngredientKCal = 0;
        ArrayList<Integer> tmpMealIngredientQuantity = new ArrayList<Integer>();
        ArrayList<String> tmpMealIngredientUnit = new ArrayList<String>();
        ArrayList<Food> mealIngredientList = new ArrayList<Food>();

        for (int i = 0; i < mealIngredientsNodeList.getLength(); i++) {
            mealIngredientNode = mealIngredientsNodeList.item(i);
            tmpMealIngredientChildNodes = mealIngredientNode.getChildNodes();
            tmpName = tmpMealIngredientChildNodes.item(0).getTextContent();
            tmpIngredientKCal = Integer.parseInt(tmpMealIngredientChildNodes.item(1).getTextContent());
            for (int j = 2; j < tmpMealIngredientChildNodes.getLength(); j++) {
                tmpMealIngredientRelationNodes = tmpMealIngredientChildNodes.item(j).getChildNodes();
                tmpMealIngredientQuantity.add(Integer.parseInt(tmpMealIngredientRelationNodes.item(0).getTextContent()));
                tmpMealIngredientUnit.add(tmpMealIngredientRelationNodes.item(1).getTextContent());
            }
            mealIngredientList.add(new Food(tmpName, tmpMealIngredientQuantity, tmpMealIngredientUnit, tmpIngredientKCal));
            tmpName = "";
            tmpIngredientKCal = 0;
            tmpMealIngredientQuantity.clear();
            tmpMealIngredientUnit.clear();
        }

        return mealIngredientList;*/
    }
}
