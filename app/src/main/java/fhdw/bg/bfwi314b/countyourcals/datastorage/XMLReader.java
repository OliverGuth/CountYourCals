package fhdw.bg.bfwi314b.countyourcals.datastorage;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.*;

/**
 * Created by Niko.
 */

public class XMLReader {

    private XMLDiaryEntryReader mXMLDiaryEntryReader;
    private XMLFoodReader mXMLFoodReader;
    private XMLMealReader mXMLMealReader;
    private XMLUnitReader mXMLUnitReader;
    private XMLUserReader mXMLUserReader;
    private Context mContext;

    public XMLReader(Context context) {
        mContext = context;
        mXMLDiaryEntryReader = new XMLDiaryEntryReader();
        mXMLFoodReader = new XMLFoodReader();
        mXMLMealReader = new XMLMealReader();
        mXMLUnitReader = new XMLUnitReader();
        mXMLUserReader = new XMLUserReader();
    }

    public ArrayList<DiaryEntry> readDiaryEntry(File file, String userName) {
        try {
            ArrayList<DiaryEntry> tmpDiaryEntryList;
            tmpDiaryEntryList = mXMLDiaryEntryReader.readDiaryEntry(file);
            File tmpMealFile;
            File tmpFoodFile;
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
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    public ArrayList<Food> readFood(File file) {
        try {
            return mXMLFoodReader.readFood(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    public ArrayList<Meal> readMeal(File file, String userName) {
        ArrayList<Meal> mealArrayList = null;
        try {
            mealArrayList = mXMLMealReader.readMeal(file);
            boolean b = true;
            for (int i = 0; i < mealArrayList.size(); i++) {
                mealArrayList.get(i).addIngredientList(mXMLFoodReader.readFood(new File(mContext.getFilesDir() + "/" + userName + mealArrayList.get(i).getName() + "Foods.xml")));
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return mealArrayList;
    }

    public ArrayList<Unit> readUnit(File file) {
        try {
            return mXMLUnitReader.readUnit(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }

    public ArrayList<User> readUser(File file) {
        try {
            return mXMLUserReader.readUser(file);
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return null;
    }
}
