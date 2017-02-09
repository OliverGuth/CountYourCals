package fhdw.bg.bfwi314b.countyourcals.datastorage;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;
import fhdw.bg.bfwi314b.countyourcals.Models.User;

/**
 * Created by Niko.
 */

public class XMLWriter {

    private XMLDiaryEntryWriter xmlDiaryEntryWriter;
    private XMLFoodWriter xmlFoodWriter;
    private XMLMealWriter xmlMealWriter;
    private XMLUnitWriter xmlUnitWriter;
    private XMLUserWriter xmlUserWriter;

    public XMLWriter() {
        xmlDiaryEntryWriter = new XMLDiaryEntryWriter();
        xmlFoodWriter = new XMLFoodWriter();
        xmlMealWriter = new XMLMealWriter();
        xmlUnitWriter = new XMLUnitWriter();
        xmlUserWriter = new XMLUserWriter();
    }

    public void writeDiaryEntry(ArrayList<DiaryEntry> entryArrayList, File file) {
        try {
            xmlDiaryEntryWriter.writeDiaryEntry(entryArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    public void writeFood(ArrayList<Food> foodArrayList, File file) {
        try {
            xmlFoodWriter.writeFood(foodArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    public void writeMeal(ArrayList<Meal> mealArrayList, File file, String userName) {
        try {
            for (int i = 0; i < mealArrayList.size(); i++) {
                xmlFoodWriter.writeFood(mealArrayList.get(i).getIngredients(), new File(userName + mealArrayList.get(i).getIdentifier() + ".xml"));
            }
            xmlMealWriter.writeMeal(mealArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    public void writeUnit(ArrayList<Unit> unitArrayList, File file) {
        try {
            xmlUnitWriter.writeUnit(unitArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    public void writeUser(ArrayList<User> userArrayList, File file) {
        try {
            xmlUserWriter.writeUser(userArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }
}
