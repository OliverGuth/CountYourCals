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
        Integer tmpMealKcal = 0;
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
            tmpMealKcal = Integer.parseInt(tmpMealChildNodes.item(1).getTextContent());
            for (int j = 2; j < tmpMealChildNodes.getLength(); j++) {
                tmpMealRelationNodes = tmpMealChildNodes.item(j).getChildNodes();
                tmpMealQuantity = Integer.parseInt(tmpMealRelationNodes.item(0).getChildNodes().item(0).getTextContent());
                tmpMealUnitName = tmpMealRelationNodes.item(1).getChildNodes().item(0).getTextContent();
                tmpMealUnitShort = tmpMealRelationNodes.item(2).getChildNodes().item(0).getTextContent();
                tmpMealUnits.add(new Unit(tmpMealUnitName, tmpMealUnitShort, tmpMealQuantity));
            }
            //tmpIngredientList = this.readMealIngredients(new File(userName + tmpIdentifier + ".xml"));
            //mealList.add(new Meal(tmpName, tmpIdentifier, tmpMealQuantity, tmpMealUnit, tmpIngredientList));
            mealList.add(new Meal(tmpName, tmpMealUnits, tmpMealKcal));
            tmpName = "";
            tmpMealKcal = 0;
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
}
