package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;

import fhdw.bg.bfwi314b.countyourcals.Models.*;

/**
 * Created by Niko Reder
 */

public class XMLWriter {

    private XMLDiaryEntryWriter mXMLDiaryEntryWriter;
    private XMLFoodWriter mXMLFoodWriter;
    private XMLMealWriter mXMLMealWriter;
    private XMLUnitWriter mXMLUnitWriter;
    private XMLUserWriter mXMLUserWriter;
    private Context mContext;

    //Constructor
    public XMLWriter(Context context) {
        mXMLDiaryEntryWriter = new XMLDiaryEntryWriter();
        mXMLFoodWriter = new XMLFoodWriter();
        mXMLMealWriter = new XMLMealWriter();
        mXMLUnitWriter = new XMLUnitWriter();
        mXMLUserWriter = new XMLUserWriter();
        mContext = context;
    }

    //Writes the DiaryEntry-List (ArrayList<DiaryEntry>) of the actual User to XML-Files
    public void writeDiaryEntry(ArrayList<DiaryEntry> entryArrayList, File file, User user) {
        try {
            //The Consumed Objects will be written to Files
            for (int i = 0; i < entryArrayList.size(); i++) {
                if (!(entryArrayList.get(i).getConsumedFood() == null)) {
                    ArrayList<Food> tmpFoodList = new ArrayList<Food>();
                    tmpFoodList.add(entryArrayList.get(i).getConsumedFood());
                    //The Food is Written to a file with the Markup "DEF" -> DiaryEntry Food
                    this.writeFood(tmpFoodList, new File(mContext.getFilesDir() + "/" + user.getName() + "DEF" + entryArrayList.get(i).getTimeStamp() + ".xml"));
                }
                if (!(entryArrayList.get(i).getConsumedMeal() == null)) {
                    ArrayList<Meal> tmpMealList = new ArrayList<Meal>();
                    tmpMealList.add(entryArrayList.get(i).getConsumedMeal());
                    //The MEal is Written to a file with the Markup "DEM" -> DiaryEntry Meal
                    this.writeMeal(tmpMealList, new File(mContext.getFilesDir() + "/" + user.getName() + "DEM" + entryArrayList.get(i).getTimeStamp() + ".xml"), (user.getName() + entryArrayList.get(i).getTimeStamp() + "DEM"));
                }
            }
            //The DiaryEntrys will be wirtten to one file
            mXMLDiaryEntryWriter.writeDiaryEntry(entryArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    //Writes the Food-List (ArrayList<Food>) to an XML-File
    public void writeFood(ArrayList<Food> foodArrayList, File file) {
        try {
            mXMLFoodWriter.writeFood(foodArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    //Writes the Meal-List (ArrayList<Meal>) of the actual User to XML-Files
    public void writeMeal(ArrayList<Meal> mealArrayList, File file, String userName) {
        try {
            //The Ingredients (Food) and Units of the Meals will be wirtten to different Files
            for (int i = 0; i < mealArrayList.size(); i++) {
                mXMLFoodWriter.writeFood(mealArrayList.get(i).getIngredients(), new File(mContext.getFilesDir() + "/" + userName + mealArrayList.get(i).getName() + "Foods.xml"));
                mXMLUnitWriter.writeUnit(mealArrayList.get(i).getUnits(), new File(mContext.getFilesDir() + "/" + userName + mealArrayList.get(i).getName() + "Units.xml"));
            }
            //The Meals will be written to one file
            mXMLMealWriter.writeMeal(mealArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    //Writes the Unit-List (ArrayList<Unit>) to an XML-File
    public void writeUnit(ArrayList<Unit> unitArrayList, File file) {
        try {
            mXMLUnitWriter.writeUnit(unitArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    //Writes the User-List (ArrayList<User>) to an XML-File
    public void writeUser(ArrayList<User> userArrayList, File file) {
        try {
            mXMLUserWriter.writeUser(userArrayList, file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }
}
