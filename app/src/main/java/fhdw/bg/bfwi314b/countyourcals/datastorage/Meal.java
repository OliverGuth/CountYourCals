package fhdw.bg.bfwi314b.countyourcals.datastorage;

import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Niko.
 */

public class Meal {

    private String mName;
    private Integer mIdentifier;
    //private Food mMeal;
    private Integer mMealQuantity;
    private String mMealUnit;
    private Integer mMealKCal;
    private ArrayList<String> mIngredients;
    private ArrayList<Integer> mIngredientsQuantity;
    private ArrayList<String> mIngredientsUnit;
    private ArrayList<Integer> mIngredientsKCal;
    private Integer mSumOfKCal;

    public Meal(String name, Integer identifier) {
        mName = name;
        mIdentifier = identifier;
        //mMeal = new Food(mName);
        mMealQuantity = null;
        mMealUnit = null;
        mMealKCal = null;
        mIngredients = new ArrayList<String>();
        mIngredientsQuantity = new ArrayList<Integer>();
        mIngredientsUnit = new ArrayList<String>();
        mIngredientsKCal = new ArrayList<Integer>();
        mSumOfKCal = null;
    }

    public Meal(String name, Integer identifier, Integer mealQuantity, String mealUnit, Integer mealKCal, ArrayList<String> ingredients, ArrayList<Integer> ingredientsQuantity, ArrayList<String> ingredientsUnit, ArrayList<Integer> ingredientsKCal) {
        mName = name;
        mIdentifier = identifier;
        mMealQuantity = mealQuantity;
        mMealUnit = mealUnit;
        mMealKCal = mealKCal;
        mIngredients = (ArrayList<String>) ingredients.clone();
        mIngredientsUnit = (ArrayList<String>) ingredientsUnit.clone();
        mIngredientsQuantity = (ArrayList<Integer>) ingredientsQuantity.clone();
        mIngredientsKCal = (ArrayList<Integer>) ingredientsKCal.clone();
        mSumOfKCal = 0;

        for (int i = 0; i < mIngredientsKCal.size(); i++) {
            mSumOfKCal = mSumOfKCal + mIngredientsKCal.get(i);
        }
    }

    public void addFood(String foodName, Integer quantity, String unit, Integer kCal) {
        mIngredients.add(foodName);
        mIngredientsQuantity.add(quantity);
        mIngredientsUnit.add(unit);
        mIngredientsKCal.add(kCal);
        mSumOfKCal = mSumOfKCal + kCal;
    }

    public void changeRelation(Integer mealQuantity, String mealUnit, Integer mealKCal) {
        //mMeal.addRelation(quantity, unit, kCal);
        mMealQuantity = mealQuantity;
        mMealUnit = mealUnit;
        mMealKCal = mealKCal;
    }

    public String toString() {
        return mName;
    }

    public Integer getSumOfKCal() {
        return mSumOfKCal;
    }

    public String[] getRelation() {
        String[] mealRelation = {mMealQuantity.toString(), mMealUnit, mMealKCal.toString()};
        return mealRelation;
    }

    public ArrayList<String>[] getIngredientsWithRelation() {
        ArrayList<String>[] arrayListArray = new ArrayList[4];
        arrayListArray[0] = mIngredients;
        arrayListArray[2] = mIngredientsUnit;
        arrayListArray[1] = new ArrayList<String>();
        arrayListArray[3] = new ArrayList<String>();

        for (int i = 0; i < mIngredientsQuantity.size(); i++) {
            arrayListArray[2].add(mIngredientsQuantity.get(i).toString());
            arrayListArray[3].add(mIngredientsKCal.get(i).toString());
        }

        return arrayListArray;
    }

    public ArrayList<String> getIngredients() {
        return mIngredients;
    }

    public ArrayList<Integer> getIngredientsQuantity() {
        return mIngredientsQuantity;
    }

    public ArrayList<String> getIngredientsUnit() {
        return mIngredientsUnit;
    }

    public ArrayList<Integer> getIngredientsKCal() {
        return mIngredientsKCal;
    }

    public String getName() {
        return mName;
    }

    public Integer getMealQuantity() {
        return mMealQuantity;
    }

    public String getMealUnit() {
        return mMealUnit;
    }

    public Integer getMealKCal() {
        return mMealKCal;
    }

    public Integer getIdentifier() {
        return mIdentifier;
    }
}
