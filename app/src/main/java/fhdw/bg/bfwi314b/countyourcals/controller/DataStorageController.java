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
 * Created by Fabian Schmitz
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


    //Constructor
    public DataStorageController(Context context) {
        mXMLReader = new XMLReader(context);
        mXMLWriter = new XMLWriter(context);
        this.context = context;
    }

    //---- Meal ----

    //Reads and returns the Meals of the actual Users as ArrayList<Meal>
    public ArrayList<Meal> getMealList(User user) {
        File tmpMealListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList;
        if (tmpMealListFile.exists()) {
            //If there is an existing File the content will be read and returned
            tmpMealArrayList = mXMLReader.readMeal(tmpMealListFile, user.getName());
        } else {
            //If there is no existing File it will be created
            //In this case an empty List will be returned
            try {
                tmpMealListFile.createNewFile();
            } catch (Exception exception) {
                System.err.println(exception);
            }
            tmpMealArrayList = new ArrayList<Meal>();
        }
        return tmpMealArrayList;
    }

    //Adds a Meal to the actual Users Meal-List
    public void addMeal(Meal meal, User user) {
        //First the Meal-List is read
        File tmpMealListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList = getMealList(user);
        //Then the new Meal is added
        if(tmpMealArrayList != null) tmpMealArrayList.add(meal);
        else
        {
            tmpMealArrayList = new ArrayList<Meal>();
            tmpMealArrayList.add(meal);
        }
        //Finally the List will be written to the File
        mXMLWriter.writeMeal(tmpMealArrayList, tmpMealListFile, user.getName());
    }

    //Edits a Meal of the actual Users Meal-List
    public void editMeal(Meal oldMeal, Meal newMeal, User user) {
        //First the Meal-List is read
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList = getMealList(user);
        //Then the Meal is searched and changed
        if (tmpMealArrayList != null) {
            for (int index = 0; index < tmpMealArrayList.size(); index++) {
                if (tmpMealArrayList.get(index).getName().equals(oldMeal.getName())) {
                    tmpMealArrayList.set(index, newMeal);
                }
            }
        }
        //Finally the List will be written to the File
        mXMLWriter.writeMeal(tmpMealArrayList, tmpFoodListFile, user.getName());
    }

    //Deletes a Meal of the actual Users Meal-List
    public void deleteMeal(Meal meal, User user) {
        //First the Meal-List is read
        File tmpFoodListFile = new File(context.getFilesDir() + "/" + user.getName() + "Meal.xml");
        ArrayList<Meal> tmpMealArrayList = getMealList(user);
        //Then the Meal is searched and removed
        if(tmpMealArrayList != null)
            for (int index = 0; index < tmpMealArrayList.size(); index++) {
                if (meal.getName().equals(tmpMealArrayList.get(index).getName()))
                {
                    tmpMealArrayList.remove(index);
                    break;
                }
            }
        //Finally the List will be written to the File
        mXMLWriter.writeMeal(tmpMealArrayList, tmpFoodListFile, user.getName());
    }

    //Adds a Ingredient (Food) to an existing Meal of the actual Users Meal-List
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

    //Reads and returns the Foods of the actual Users as ArrayList<Food>
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

    //Adds a Food to the actual Users Food-List
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

    //Edits a Food of the actual Users Food-List
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

    //Deletes a Food of the actual Users Food-List
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

    //Reads and returns the DiarayEntrys of the actual Users as ArrayList<DiaryEntry>
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

    //Adds a DiaryEntry to the actual Users DiaryEntry-List
    public void addDiaryEntry(DiaryEntry diaryEntry, User user) {
       File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + user.getName() + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList = getDiaryEntryList(user);
        if(tmpDiaryEntryArrayList == null)
            tmpDiaryEntryArrayList = new ArrayList<DiaryEntry>();
        tmpDiaryEntryArrayList.add(diaryEntry);
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile, user);
    }

    //Edits a DiaryEntry of the actual Users DiaryEntry-List
    public void editDiaryEntry(DiaryEntry newDiaryEntry, DiaryEntry oldDiaryEntry, User user) {
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + user.getName() + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList = getDiaryEntryList(user);
        if (tmpDiaryEntryArrayList != null) {
            for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
                if (tmpDiaryEntryArrayList.get(index).getTimeStamp().equals(oldDiaryEntry.getTimeStamp())) {
                    tmpDiaryEntryArrayList.set(index, newDiaryEntry);
                }
            }
        }
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile, user);
    }

    //Deletes a DiaryEntry of the actual Users DiaryEntry-List
    public void deleteDiaryEntry(DiaryEntry diaryEntry, User user) {
        File tmpDiaryEntryListFile = new File(context.getFilesDir() + "/" + user.getName() + "DiaryEntry.xml");
        ArrayList<DiaryEntry> tmpDiaryEntryArrayList = getDiaryEntryList(user);
        if(tmpDiaryEntryArrayList != null)
            for (int index = 0; index < tmpDiaryEntryArrayList.size(); index++) {
                if (diaryEntry.getTimeStamp().equals(tmpDiaryEntryArrayList.get(index).getTimeStamp()))
                {
                    tmpDiaryEntryArrayList.remove(index);
                    break;
                }
            }
        mXMLWriter.writeDiaryEntry(tmpDiaryEntryArrayList, tmpDiaryEntryListFile, user);
    }

    //---- User -----

    //Reads and returns all existing Users
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

    //Adds a User to the User-List
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

    //Edits an existing User
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

    //Reads and returns the Units of the actual Users as ArrayList<Unit>
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

    //Adds a Unit to the actual Users Unit-List
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

    //Edits a Unit of the actual Users Unit-List
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

    //Deletes a Unit of the actual Users Unit-List
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

    //-----------------

    public void factoryReset()
    {
        if(context.getFilesDir().isDirectory())
        {
            for (File child : context.getFilesDir().listFiles()) if(child.getName().contains(".xml")) child.delete();
        }
    }
}
