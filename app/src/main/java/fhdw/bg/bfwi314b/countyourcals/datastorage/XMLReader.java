package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Niko.
 */

public class XMLReader {

    private XMLDiaryEntryReader xmlDiaryEntryReader;
    private XMLFoodReader xmlFoodReader;
    private XMLMealReader xmlMealReader;
    private XMLUnitReader xmlUnitReader;
    private XMLUserReader xmlUserReader;

    public XMLReader() {
        xmlDiaryEntryReader = new XMLDiaryEntryReader();
        xmlFoodReader = new XMLFoodReader();
        xmlMealReader = new XMLMealReader();
        xmlUnitReader = new XMLUnitReader();
        xmlUserReader = new XMLUserReader();
    }

    public ArrayList<DiaryEntry> readDiaryEntry(File file) {
        try {
            return xmlDiaryEntryReader.readDiaryEntry(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    public ArrayList<Food> readFood(File file) {
        try {
            return xmlFoodReader.readFood(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    public ArrayList<Meal> readMeal(File file, String userName) {
        try {
            ArrayList<Meal> mealArrayList;
            mealArrayList = xmlMealReader.readMeal(file);
            for (int i = 0; i < mealArrayList.size(); i++) {
                mealArrayList.get(i).addFoodList(xmlFoodReader.readFood(new File(userName + mealArrayList.get(i).getIdentifier() + ".xml")));
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    public ArrayList<Unit> readUnit(File file) {
        try {
            return xmlUnitReader.readUnit(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    public ArrayList<User> readUser(File file) {
        try {
            return xmlUserReader.readUser(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }
}
