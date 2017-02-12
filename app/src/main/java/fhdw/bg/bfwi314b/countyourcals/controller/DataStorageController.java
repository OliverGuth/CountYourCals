package fhdw.bg.bfwi314b.countyourcals.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import android.content.Context;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.datastorage.*;

/**
 * Created by Niko.
 */

public class DataStorageController {

    private XMLReader mXMLReader;
    private XMLWriter mXMLWriter;
    private Context context;

//    private File mUserFile;
//    private File mFoodFile;
//    private File mMealFile;
//    private File mDiaryEntryFile;

    private String mUser;


    public DataStorageController(Context context) {
        mXMLReader = new XMLReader();
        mXMLWriter = new XMLWriter();
        this.context = context;
    }

    public ArrayList<Meal> getMealList(User user) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        if (tmpMealListFile.exists()) {
            tmpMealArrayList = mXMLReader.readMeal(tmpMealListFile, user.getName());
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

    public void addMeal(Meal meal, User user) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(user);
        Integer tmpIdentifier = 0;
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpIdentifier < tmpMealArrayList.get(index).getIdentifier()) {
                tmpIdentifier = tmpMealArrayList.get(index).getIdentifier();
            }
        }
        tmpMealArrayList.add(meal);
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, user.getName());
    }

    public void addRelationToMeal(Integer mealIdentifier, Unit mealUnit, User user) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(user);
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpMealArrayList.get(index).getIdentifier() == mealIdentifier) {
                tmpMealArrayList.get(index).addUnit(mealUnit);

                index = tmpMealArrayList.size();
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, user.getName());
    }

    public void addFoodToMeal(Integer mealIdentifier, Integer mealQuantity, Food ingredient, User user) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(user);
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpMealArrayList.get(index).getIdentifier() == mealIdentifier) {
                tmpMealArrayList.get(index).addIngredient(ingredient);

                index = tmpMealArrayList.size();
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, user.getName());
    }

//    public void editMeal() {
//
//    }

    //----------

    public ArrayList<Food> getFoodList(User user) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Food.xml");
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

    public void addFood(String foodName, Unit foodUnit, Integer foodKCal, User user) {
        Food tmpFood = new Food(foodName, foodUnit, foodKCal);
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Food.xml");
        ArrayList<Food> tmpFoodArrayList;
        tmpFoodArrayList = getFoodList(user);
        tmpFoodArrayList.add(tmpFood);
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }

    public void addRelationToFood(String foodName, Unit foodUnit, User user) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Food.xml");
        ArrayList<Food> tmpFoodArrayList;
        tmpFoodArrayList = getFoodList(user);
        for (int index = 0; index < tmpFoodArrayList.size(); index++) {
            if (tmpFoodArrayList.get(index).getName().equals(foodName)) {
                tmpFoodArrayList.get(index).addUnit(foodUnit);

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
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + userName + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        if (tmpDiaryEntryListFile.exists()) {
            tmpDiaryEntryArrayList = mXMLReader.readDiaryEntry(tmpDiaryEntryListFile, userName);
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

    public void addDiaryEntry(String userName, Date timeStamp, String consumedName, Unit consumedUnit, Integer consumedKCal) {
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + userName + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        tmpDiaryEntryArrayList = getDiaryEntryList(userName);
        Integer tmpIdentifier = 0;
        for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
            if (tmpIdentifier < tmpDiaryEntryArrayList.get(index).getIdentifier()) {
                tmpIdentifier = tmpDiaryEntryArrayList.get(index).getIdentifier();
            }
        }
        DiaryEntry tmpDiaryEntry = new DiaryEntry(timeStamp, consumedName, consumedUnit, consumedKCal, tmpIdentifier);
        tmpDiaryEntryArrayList.add(tmpDiaryEntry);
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile, userName);
    }

    public void editDiaryEntry(DiaryEntry diaryEntry, User user) {
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + user.getName() + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        tmpDiaryEntryArrayList = getDiaryEntryList(user.getName());
        for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
            if (tmpDiaryEntryArrayList.get(index).getIdentifier() == diaryEntry.getIdentifier()) {
                tmpDiaryEntryArrayList.get(index).setConsumedName(diaryEntry.getConsumedName());
                //tmpDiaryEntryArrayList.get(index).setConsumedQuantity(consumedQuantity);
                tmpDiaryEntryArrayList.get(index).setConsumedUnit(diaryEntry.getConsumedUnit());
                tmpDiaryEntryArrayList.get(index).setConsumedKCal(diaryEntry.getConsumedKCal());

                index = tmpDiaryEntryArrayList.size();
            }
        }
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile, userName);
    }

    //----------

    public ArrayList<User> getUserList() {
        File tmpUserListFile = new File(context.getFilesDir() + "/User.xml");
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

    public void addUser(User user) {
        File tmpUserListFile = new File(context.getFilesDir() + "/User.xml");
        ArrayList<User> tmpUserArrayList;
        tmpUserArrayList = getUserList();
        if(tmpUserArrayList != null) tmpUserArrayList.add(user);
        else
        {
            tmpUserArrayList = new ArrayList<User>();
            tmpUserArrayList.add(user);
        }
        mXMLWriter.writeUser(tmpUserArrayList, tmpUserListFile);
    }

    public void editUser(User user) {
        File tmpUserListFile = new File(context.getFilesDir() + "/User.xml");
        ArrayList<User> tmpUserArrayList;
        tmpUserArrayList = getUserList();
        for (int index = 0; index < tmpUserArrayList.size(); index++) {
            if (tmpUserArrayList.get(index).getName().equals(user.getName())) {
                tmpUserArrayList.get(index).setMaxKCal(user.getMaxKCal());
                tmpUserArrayList.get(index).setLanguage(user.getLanguage());

                index = tmpUserArrayList.size();
            }
        }
        mXMLWriter.writeUser(tmpUserArrayList, tmpUserListFile);
    }

    //----------

    public ArrayList<Unit> getUnitList(User user) {
        File tmpUnitListFile = new File(context.getFilesDir() + "/" + user.getName() + "Unit.xml");
        ArrayList<Unit> tmpUnitArrayList;
        if (tmpUnitListFile.exists()) {
            tmpUnitArrayList = mXMLReader.readUnit(tmpUnitListFile);
        } else {
            try {
                tmpUnitListFile.createNewFile();
            } catch (Exception exception) {
                System.err.println(exception);
            }
            tmpUnitArrayList = new ArrayList<Unit>();
        }
        return tmpUnitArrayList;
    }

    public void addUnit(Unit unit, User user) {

        File tmpUnitListFile = new File(context.getFilesDir() + "/" + user.getName() + "Unit.xml");
        ArrayList<Unit> tmpUnitArrayList = getUnitList(user);
        if(tmpUnitArrayList != null) tmpUnitArrayList.add(unit);
        else
        {
            tmpUnitArrayList = new ArrayList<Unit>();
            tmpUnitArrayList.add(unit);
        }
        mXMLWriter.writeUnit(tmpUnitArrayList, tmpUnitListFile);
    }

    public void factoryReset()
    {
        if(context.getFilesDir().isDirectory())
        {
            for (File child : context.getFilesDir().listFiles()) if(child.getName().contains(".xml")) child.delete();
        }
    }
    /*public void editUnit(String userName, Character gender, Integer maxKCal, String language) {

    }*/
}
