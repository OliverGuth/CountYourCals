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

    public ArrayList<Meal> getMealList(String userName) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        if (tmpMealListFile.exists()) {
            tmpMealArrayList = mXMLReader.readMeal(tmpMealListFile, userName);
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

    public void addMeal(String userName, String mealName, ArrayList<Unit> mealUnits, Integer mealKCal, ArrayList<Food> ingredients) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(userName);
        Integer tmpIdentifier = 0;
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpIdentifier < tmpMealArrayList.get(index).getIdentifier()) {
                tmpIdentifier = tmpMealArrayList.get(index).getIdentifier();
            }
        }
        Meal tmpMeal = new Meal(mealName, tmpIdentifier, mealUnits, ingredients);
        tmpMealArrayList.add(tmpMeal);
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, userName);
    }

    public void addRelationToMeal(String userName, Integer mealIdentifier, Unit mealUnit) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(userName);
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpMealArrayList.get(index).getIdentifier() == mealIdentifier) {
                tmpMealArrayList.get(index).addUnit(mealUnit);

                index = tmpMealArrayList.size();
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, userName);
    }

    public void addFoodToMeal(String userName, Integer mealIdentifier, Integer mealQuantity, Food ingredient) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + userName + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(userName);
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpMealArrayList.get(index).getIdentifier() == mealIdentifier) {
                tmpMealArrayList.get(index).addFood(ingredient);

                index = tmpMealArrayList.size();
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, userName);
    }

//    public void editMeal() {
//
//    }

    //----------

    public ArrayList<Food> getFoodList(String userName) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + userName + "Food.xml");
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

    public void addFood(String userName, String foodName, Unit foodUnit, Integer foodKCal) {
        Food tmpFood = new Food(foodName, foodUnit, foodKCal);
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + userName + "Food.xml");
        ArrayList<Food> tmpFoodArrayList;
        tmpFoodArrayList = getFoodList(userName);
        tmpFoodArrayList.add(tmpFood);
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }

    public void addRelationToFood(String userName, String foodName, Unit foodUnit) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + userName + "Food.xml");
        ArrayList<Food> tmpFoodArrayList;
        tmpFoodArrayList = getFoodList(userName);
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
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile);
    }

    public void editDiaryEntry(String userName, Date timeStamp, String consumedName, Unit consumedUnit, Integer consumedKCal, Integer identifier) {
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + userName + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        tmpDiaryEntryArrayList = getDiaryEntryList(userName);
        for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
            if (tmpDiaryEntryArrayList.get(index).getIdentifier() == identifier) {
                tmpDiaryEntryArrayList.get(index).setConsumedName(consumedName);
                //tmpDiaryEntryArrayList.get(index).setConsumedQuantity(consumedQuantity);
                tmpDiaryEntryArrayList.get(index).setConsumedUnit(consumedUnit);
                tmpDiaryEntryArrayList.get(index).setConsumedKCal(consumedKCal);

                index = tmpDiaryEntryArrayList.size();
            }
        }
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile);
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

    public void addUser(String userName, Character gender, Integer maxKCal, String language) {
        User tmpUser = new User(userName, gender, maxKCal, language);
        File tmpUserListFile = new File(context.getFilesDir() + "/User.xml");
        ArrayList<User> tmpUserArrayList;
        tmpUserArrayList = getUserList();
        tmpUserArrayList.add(tmpUser);
        mXMLWriter.writeUser(tmpUserArrayList, tmpUserListFile);
    }

    public void editUser(String userName, Character gender, Integer maxKCal, String language) {
        File tmpUserListFile = new File(context.getFilesDir() + "/User.xml");
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

    public ArrayList<Unit> getUnitList(String userName) {
        File tmpUnitListFile = new File(context.getFilesDir() + "/" + userName + "Unit.xml");
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

    public void addUnit(String userName, String unit, String unitShort, Integer unitQuantity) {
        Unit tmpUnit = new Unit(unit, unitShort, unitQuantity);
        File tmpUnitListFile = new File(context.getFilesDir() + "/" + userName + "Unit.xml");
        ArrayList<Unit> tmpUnitArrayList;
        tmpUnitArrayList = getUnitList(userName);
        tmpUnitArrayList.add(tmpUnit);
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
