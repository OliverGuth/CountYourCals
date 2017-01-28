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
    private XMLUserReader xmlUserReader;

    public XMLReader() {
        xmlDiaryEntryReader = new XMLDiaryEntryReader();
        xmlFoodReader = new XMLFoodReader();
        xmlMealReader = new XMLMealReader();
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

    public ArrayList<Meal> readMeal(File file) {
        try {
            return xmlMealReader.readMeal(file);
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
