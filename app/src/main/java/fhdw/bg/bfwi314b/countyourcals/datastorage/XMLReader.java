package fhdw.bg.bfwi314b.countyourcals.datastorage;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.*;

/**
 * Created by Niko Reder
 */

public class XMLReader {

    private XMLDiaryEntryReader mXMLDiaryEntryReader;
    private XMLFoodReader mXMLFoodReader;
    private XMLMealReader mXMLMealReader;
    private XMLUnitReader mXMLUnitReader;
    private XMLUserReader mXMLUserReader;
    private Context mContext;

    //Constructor
    public XMLReader(Context context) {
        mContext = context;
        mXMLDiaryEntryReader = new XMLDiaryEntryReader();
        mXMLFoodReader = new XMLFoodReader();
        mXMLMealReader = new XMLMealReader();
        mXMLUnitReader = new XMLUnitReader();
        mXMLUserReader = new XMLUserReader();
    }

    //Reads the DiaryEntry-List (ArrayList<DiaryEntry>) of the actual User from XML-Files
    public ArrayList<DiaryEntry> readDiaryEntry(File file, String userName) {
        try {
            ArrayList<DiaryEntry> tmpDiaryEntryList;
            tmpDiaryEntryList = mXMLDiaryEntryReader.readDiaryEntry(file);
            File tmpMealFile;
            File tmpFoodFile;
            //Meal and Food of the DiaryEntrys are written to several files and needed to be read first
            for (int i = 0; i < tmpDiaryEntryList.size(); i++) {
                tmpFoodFile = new File(mContext.getFilesDir() + "/" + userName + "DEF" + tmpDiaryEntryList.get(i).getTimeStamp() + ".xml");
                tmpMealFile = new File(mContext.getFilesDir() + "/" + userName + "DEM" + tmpDiaryEntryList.get(i).getTimeStamp() + ".xml");
                if (tmpFoodFile.exists()) {
                    ArrayList<Food> tmpFoodList = this.readFood(tmpFoodFile);
                    tmpDiaryEntryList.get(i).setConsumedFood(tmpFoodList.get(0));
                }
                if (tmpMealFile.exists()) {
                    ArrayList<Meal> tmpMealList = this.readMeal(tmpMealFile, (userName + tmpDiaryEntryList.get(i).getTimeStamp() + "DEM"));
                    tmpDiaryEntryList.get(i).setConsumedMeal(tmpMealList.get(0));
                }
            }
            return tmpDiaryEntryList;
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    //Reads the Food-List (ArrayList<Food>) from an XML-File
    public ArrayList<Food> readFood(File file) {
        try {
            return mXMLFoodReader.readFood(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    //Reads the Meal-List (ArrayList<Meal>) of the actual User from XML-Files
    public ArrayList<Meal> readMeal(File file, String userName) {
        ArrayList<Meal> mealArrayList = null;
        try {
            mealArrayList = mXMLMealReader.readMeal(file);
            boolean b = true;
            //Ingredient (Food) and Unit of the Meals are written to several files and needed to be read also
            for (int i = 0; i < mealArrayList.size(); i++) {
                mealArrayList.get(i).getUnits().clear();
                mealArrayList.get(i).addIngredientList(mXMLFoodReader.readFood(new File(mContext.getFilesDir() + "/" + userName + mealArrayList.get(i).getName() + "Foods.xml")));
                mealArrayList.get(i).getUnits().addAll(mXMLUnitReader.readUnit(new File(mContext.getFilesDir() + "/" + userName + mealArrayList.get(i).getName() + "Units.xml")));
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return mealArrayList;
    }

    //Reads the Unit-List (ArrayList<Unit>) from an XML-File
    public ArrayList<Unit> readUnit(File file) {
        try {
            return mXMLUnitReader.readUnit(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    //Reads the User-List (ArrayList<User>) from an XML-File
    public ArrayList<User> readUser(File file) {
        try {
            return mXMLUserReader.readUser(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }
}
