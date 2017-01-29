package fhdw.bg.bfwi314b.countyourcals.controller;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.datastorage.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.datastorage.Food;
import fhdw.bg.bfwi314b.countyourcals.datastorage.Meal;
import fhdw.bg.bfwi314b.countyourcals.datastorage.User;
import fhdw.bg.bfwi314b.countyourcals.datastorage.XMLReader;
import fhdw.bg.bfwi314b.countyourcals.datastorage.XMLWriter;

/**
 * Created by Niko.
 */

public class DataStorageController {

    private XMLReader mXMLReader;
    private XMLWriter mXMLWriter;

//    private File mUserFile;
//    private File mFoodFile;
//    private File mMealFile;
//    private File mDiaryEntryFile;

    private String mUser;


    public DataStorageController() {
        mXMLReader = new XMLReader();
        mXMLWriter = new XMLWriter();
    }

    public void getMealList() {

    }

    public void addMeal(String userName, String mealName, Integer mealQuantity, String mealUnit, Integer mealKCal, ArrayList<String> ingredientsName, ArrayList<Integer> ingredientsQuantity, ArrayList<String> ingredientsUnit, ArrayList<Integer> ingredientsKCal) {
        Meal tmpMeal = new Meal(mealName, mealQuantity, mealUnit, mealKCal, ingredientsName, ingredientsQuantity, ingredientsUnit, ingredientsKCal);
        File tmpMealListFile = new File(userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        if (tmpMealListFile.exists()) {
            tmpMealArrayList = mXMLReader.readMeal(tmpMealListFile);
        } else {
            try {
                tmpMealListFile.createNewFile();
            } catch (Exception exception) {
                System.err.println(exception);
            }
            tmpMealArrayList = new ArrayList<Meal>();
        }
        tmpMealArrayList.add(tmpMeal);
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile);
    }

    public void editMeal() {

    }

    //----------

    public void getFoodList() {

    }

    public void addFood(String userName, String foodName, Integer foodQuantity, String foodUnit, Integer foodKCal) {
        Food tmpFood = new Food(foodName, foodQuantity, foodUnit, foodKCal);
        File tmpFoodListFile = new File(userName + "Food.xml");
        ArrayList<Food> tmpFoodArrayList;
        if (tmpFoodListFile.exists()) {
            tmpFoodArrayList = mXMLReader.readFood(tmpFoodListFile);
        } else {
            try {
                tmpFoodListFile.createNewFile();
            } catch (Exception exception) {
                System.err.println(exception);
            }
            tmpFoodArrayList = new ArrayList<Food>();
        }
        tmpFoodArrayList.add(tmpFood);
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }

    public void editFood() {

    }

    //----------

    public void getDiaryEntryList() {

    }

    public void addDiaryEntry(String userName, String timeStamp, String consumedName, Integer consumedQuantity, String consumedUnit, Integer consumedKCal) {
        DiaryEntry tmpDiaryEntry = new DiaryEntry(timeStamp, consumedName, consumedQuantity, consumedUnit, consumedKCal);
        File tmpDiaryEntryListFile = new File(userName + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        if (tmpDiaryEntryListFile.exists()) {
            tmpDiaryEntryArrayList = mXMLReader.readDiaryEntry(tmpDiaryEntryListFile);
        } else {
            try {
                tmpDiaryEntryListFile.createNewFile();
            } catch (Exception exception) {
                System.err.println(exception);
            }
            tmpDiaryEntryArrayList = new ArrayList<DiaryEntry>();
        }
        tmpDiaryEntryArrayList.add(tmpDiaryEntry);
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile);
    }

    public void editDiaryEntry() {

    }

    //----------

    public void getUserList() {

    }

    public void addUser(String userName, Character gender, Integer maxKCal, String language) {
        User tmpUser = new User(userName, gender, maxKCal, language);
        File tmpUserListFile = new File("User.xml");
        ArrayList<User> tmpUserArrayList;
        if (tmpUserListFile.exists()) {
            tmpUserArrayList = mXMLReader.readUser(tmpUserListFile);
        } else {
            try {
                tmpUserListFile.createNewFile();
            } catch (Exception exception) {
                System.err.println(exception);
            }
            tmpUserArrayList = new ArrayList<User>();
        }
        tmpUserArrayList.add(tmpUser);
        mXMLWriter.writeUser(tmpUserArrayList, tmpUserListFile);
    }

    public void editUser() {

    }

    //----------


}
