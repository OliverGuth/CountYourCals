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
        mXMLReader = new XMLReader(context);
        mXMLWriter = new XMLWriter(context);
        this.context = context;
    }

    //---- Meal ----

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
        ArrayList<Meal> tmpMealArrayList = getMealList(user);
        if(tmpMealArrayList != null) tmpMealArrayList.add(meal);
        else
        {
            tmpMealArrayList = new ArrayList<Meal>();
            tmpMealArrayList.add(meal);
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, user.getName());
    }

    public void editMeal(Meal oldMeal, Meal newMeal, User user) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList = getMealList(user);
        if (tmpMealArrayList != null) {
            for (int index = 0; index < tmpMealArrayList.size(); index++) {
                if (tmpMealArrayList.get(index).getName().equals(oldMeal.getName())) {
                    tmpMealArrayList.set(index, newMeal);
                }
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpFoodListFile, user.getName());
    }

    public void deleteMeal(Meal meal, User user) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList = getMealList(user);
        if(tmpMealArrayList != null)
            for (int index = 0; index < tmpMealArrayList.size(); index++) {
                if (meal.getName().equals(tmpMealArrayList.get(index).getName()))
                {
                    tmpMealArrayList.remove(index);
                    break;
                }
            }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpFoodListFile, user.getName());
    }



    public void addFoodToMeal(String mealName, Integer mealQuantity, Food ingredient, User user) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        tmpMealArrayList = getMealList(user);
        for (int index = 0; index < tmpMealArrayList.size(); index++) {
            if (tmpMealArrayList.get(index).getName() == mealName) {
                tmpMealArrayList.get(index).addIngredient(ingredient);

                index = tmpMealArrayList.size();
            }
        }
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, user.getName());
    }

    //---- Food ----

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

    public void addFood(Food food, User user) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Food.xml");
        ArrayList<Food> tmpFoodArrayList = getFoodList(user);
        if(tmpFoodArrayList != null) tmpFoodArrayList.add(food);
        else
        {
            tmpFoodArrayList = new ArrayList<Food>();
            tmpFoodArrayList.add(food);
        }
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }

    public void editFood(Food oldFood, Food newFood, User user) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Food.xml");
        ArrayList<Food> tmpFoodArrayList = getFoodList(user);
        if (tmpFoodArrayList != null) {
            for (int index = 0; index < tmpFoodArrayList.size(); index++) {
                if (tmpFoodArrayList.get(index).getName().equals(oldFood.getName())) {
                    tmpFoodArrayList.set(index, newFood);
                }
            }
        }
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }

    public void deleteFood(Food food, User user) {
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Food.xml");
        ArrayList<Food> tmpFoodArrayList = getFoodList(user);
        if(tmpFoodArrayList != null)
            for (int index = 0; index < tmpFoodArrayList.size(); index++) {
                if (food.getName().equals(tmpFoodArrayList.get(index).getName()))
                {
                    tmpFoodArrayList.remove(index);
                    break;
                }
            }
        mXMLWriter.writeFood(tmpFoodArrayList, tmpFoodListFile);
    }


    //---- DiaryEntry ----

    public ArrayList<DiaryEntry> getDiaryEntryList(User user) {
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + user.getName() + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        if (tmpDiaryEntryListFile.exists()) {
            tmpDiaryEntryArrayList = mXMLReader.readDiaryEntry(tmpDiaryEntryListFile, user.getName());
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

    public void addDiaryEntry(DiaryEntry diaryEntry, User user) {
       /* File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + user.getName() + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        tmpDiaryEntryArrayList = getDiaryEntryList(user);
        Date tmpdate = null;
        for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
            if (tmpdate.get < tmpDiaryEntryArrayList.get(index).getTimeStamp()) {
                tmpIdentifier = tmpDiaryEntryArrayList.get(index).getTimeStamp();
            }
        }
        tmpDiaryEntryArrayList.add(diaryEntry);
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile, user.getName());
    */
    }

    public void editDiaryEntry(DiaryEntry diaryEntry, User user) {
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + user.getName() + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList;
        tmpDiaryEntryArrayList = getDiaryEntryList(user);
        for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
            if (tmpDiaryEntryArrayList.get(index).getTimeStamp() == diaryEntry.getTimeStamp()) {
                //tmpDiaryEntryArrayList.get(index).setConsumedName(diaryEntry.getConsumedName());
                //tmpDiaryEntryArrayList.get(index).setConsumedQuantity(consumedQuantity);
                tmpDiaryEntryArrayList.get(index).setConsumedUnit(diaryEntry.getConsumedUnit());
                tmpDiaryEntryArrayList.get(index).setConsumedKCal(diaryEntry.getConsumedKCal());

                index = tmpDiaryEntryArrayList.size();
            }
        }
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile, user.getName());
    }

    //---- User -----

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

    //---- Unit -----

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

    public void editUnit(Unit oldUnit, Unit newUnit, User user) {
        File tmpUnitListFile = new File(context.getFilesDir() + "/" + user.getName() + "Unit.xml");
        ArrayList<Unit> tmpUnitArrayList = getUnitList(user);
        if(tmpUnitArrayList != null) {
            for (int index = 0; index < tmpUnitArrayList.size(); index++) {
                if (tmpUnitArrayList.get(index).getUnit().equals(oldUnit.getUnit())) {
                    tmpUnitArrayList.set(index, newUnit);
                }
            }
        }
        mXMLWriter.writeUnit(tmpUnitArrayList, tmpUnitListFile);

    }

    public void deleteUnit(Unit unit, User user) {
        File tmpUnitListFile = new File(context.getFilesDir() + "/" + user.getName() + "Unit.xml");
        ArrayList<Unit> tmpUnitArrayList = getUnitList(user);
        if(tmpUnitArrayList != null)
        for (int index = 0; index < tmpUnitArrayList.size(); index++) {
            if (unit.getUnit().equals(tmpUnitArrayList.get(index).getUnit()))
            {
                tmpUnitArrayList.remove(index);
                break;
            }
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
}
