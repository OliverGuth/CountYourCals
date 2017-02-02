package fhdw.bg.bfwi314b.countyourcals.controller;


import java.io.File;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.datastorage.*;

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

    public ArrayList<Meal> getMealList(String userName) {
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
        return tmpMealArrayList;
    }

    public void addMeal(String userName, String mealName, Integer mealQuantity, String mealUnit, Integer mealKCal, ArrayList<String> ingredientsName, ArrayList<Integer> ingredientsQuantity, ArrayList<String> ingredientsUnit, ArrayList<Integer> ingredientsKCal) {
        File tmpMealListFile = new File(userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(userName);
        Integer tmpIdentifier = 0;
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpIdentifier < tmpMealArrayList.get(index).getIdentifier()) {
                tmpIdentifier = tmpMealArrayList.get(index).getIdentifier();
            }
        }
        Meal tmpMeal = new Meal(mealName, tmpIdentifier, mealQuantity, mealUnit, mealKCal, ingredientsName, ingredientsQuantity, ingredientsUnit, ingredientsKCal);
        tmpMealArrayList.add(tmpMeal);
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile);
    }

    public void changeRelationToMeal(String userName, Integer mealIdentifier, Integer mealQuantity, String mealUnit, Integer mealKCal) {
        File tmpMealListFile = new File(userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(userName);
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpMealArrayList.get(index).getIdentifier() == mealIdentifier) {
                tmpMealArrayList.get(index).changeRelation(mealQuantity, mealUnit, mealKCal);

                index = tmpMealArrayList.size();
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile);
    }

    public void addFoodToMeal(String userName, String mealName, Integer mealQuantity, String mealUnit, Integer mealKCal, String ingredientsName, Integer ingredientsQuantity, String ingredientsUnit, Integer ingredientsKCal) {
        File tmpMealListFile = new File(userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(userName);
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpMealArrayList.get(index).getName().equals(mealName)) {
                tmpMealArrayList.get(index).addFood(ingredientsName, ingredientsQuantity, ingredientsUnit, ingredientsKCal);
                tmpMealArrayList.get(index).changeRelation(mealQuantity, mealUnit, mealKCal);

                index = tmpMealArrayList.size();
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile);
    }

//    public void editMeal() {
//
//    }

    //----------

    public ArrayList<Food> getFoodList(String userName) {
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
        return tmpFoodArrayList;
    }

    public void addFood(String userName, String foodName, Integer foodQuantity, String foodUnit, Integer foodKCal) {
        Food tmpFood = new Food(foodName, foodQuantity, foodUnit, foodKCal);
        File tmpFoodListFile = new File(userName + "Food.xml");
        ArrayList<Food> tmpFoodArrayList;
        tmpFoodArrayList = getFoodList(userName);
        tmpFoodArrayList.add(tmpFood);
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }

    public void addRelationToFood(String userName, String foodName, Integer foodQuantity, String foodUnit, Integer foodKCal) {
        File tmpFoodListFile = new File(userName + "Food.xml");
        ArrayList<Food> tmpFoodArrayList;
        tmpFoodArrayList = getFoodList(userName);
        for (int index = 0; index < tmpFoodArrayList.size(); index++) {
            if (tmpFoodArrayList.get(index).getName().equals(foodName)) {
                tmpFoodArrayList.get(index).addRelation(foodQuantity, foodUnit, foodKCal);

                index = tmpFoodArrayList.size();
            }
        }
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }

//    public void editFood(String userName, String foodName, Integer foodQuantity, String foodUnit, Integer foodKCal) {
//    	File tmpFoodListFile = new File(userName + "Food.xml");
//        ArrayList<Food> tmpFoodArrayList;
//        tmpFoodArrayList = getFoodList(userName);
//        for(int index = 0; index < tmpFoodArrayList.size(); index++){
//        	if(tmpFoodArrayList.get(index).getName().equals(userName)){
//
//        	}
//        }
//    }

    //----------

    public ArrayList<DiaryEntry> getDiaryEntryList(String userName) {
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
        return tmpDiaryEntryArrayList;
    }

    public void addDiaryEntry(String userName, String timeStamp, String consumedName, Integer consumedQuantity, String consumedUnit, Integer consumedKCal) {
        File tmpDiaryEntryListFile = new File(userName + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        tmpDiaryEntryArrayList = getDiaryEntryList(userName);
        Integer tmpIdentifier = 0;
        for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
            if (tmpIdentifier < tmpDiaryEntryArrayList.get(index).getIdentifier()) {
                tmpIdentifier = tmpDiaryEntryArrayList.get(index).getIdentifier();
            }
        }
        DiaryEntry tmpDiaryEntry = new DiaryEntry(timeStamp, consumedName, consumedQuantity, consumedUnit, consumedKCal, tmpIdentifier);
        tmpDiaryEntryArrayList.add(tmpDiaryEntry);
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile);
    }

    public void editDiaryEntry(String userName, String timeStamp, String consumedName, Integer consumedQuantity, String consumedUnit, Integer consumedKCal, Integer identifier) {
        File tmpDiaryEntryListFile = new File(userName + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        tmpDiaryEntryArrayList = getDiaryEntryList(userName);
        for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
            if (tmpDiaryEntryArrayList.get(index).getIdentifier() == identifier) {
                tmpDiaryEntryArrayList.get(index).setConsumedName(consumedName);
                tmpDiaryEntryArrayList.get(index).setConsumedQuantity(consumedQuantity);
                tmpDiaryEntryArrayList.get(index).setConsumedUnit(consumedUnit);
                tmpDiaryEntryArrayList.get(index).setConsumedKCal(consumedKCal);

                index = tmpDiaryEntryArrayList.size();
            }
        }
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile);
    }

    //----------

    public ArrayList<User> getUserList() {
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
        return tmpUserArrayList;
    }

    public void addUser(String userName, Character gender, Integer maxKCal, String language) {
        User tmpUser = new User(userName, gender, maxKCal, language);
        File tmpUserListFile = new File("User.xml");
        ArrayList<User> tmpUserArrayList;
        tmpUserArrayList = getUserList();
        tmpUserArrayList.add(tmpUser);
        mXMLWriter.writeUser(tmpUserArrayList, tmpUserListFile);
    }

    public void editUser(String userName, Character gender, Integer maxKCal, String language) {
        File tmpUserListFile = new File("User.xml");
        ArrayList<User> tmpUserArrayList;
        tmpUserArrayList = getUserList();
        for (int index = 0; index < tmpUserArrayList.size(); index++) {
            if (tmpUserArrayList.get(index).getName().equals(userName)) {
                tmpUserArrayList.get(index).setMaxKCal(maxKCal);
                tmpUserArrayList.get(index).setLanguage(language);

                index = tmpUserArrayList.size();
            }
        }
        mXMLWriter.writeUser(tmpUserArrayList, tmpUserListFile);
    }

    //----------


}
