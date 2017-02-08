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
    private ArrayList<Integer> mMealQuantity;
    private ArrayList<String> mMealUnit;
    //private Integer mMealKCal;
    private ArrayList<Food> mIngredients;
    private Integer mSumOfKCal;

    public Meal(String name, Integer identifier) {
        mName = name;
        mIdentifier = identifier;
        mMealQuantity = new ArrayList<Integer>();
        mMealUnit = new ArrayList<String>();
        //mMealKCal = null;
        mIngredients = new ArrayList<Food>();
        mSumOfKCal = null;
    }

    public Meal(String name, Integer identifier, Integer mealQuantity, String mealUnit, /*Integer mealKCal,*/ ArrayList<Food> ingredients) {
        mName = name;
        mIdentifier = identifier;
        mMealQuantity = new ArrayList<Integer>();
        mMealQuantity.add(mealQuantity);
        mMealUnit = new ArrayList<String>();
        mMealUnit.add(mealUnit);
        //mMealKCal = mealKCal;
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mSumOfKCal = 0;

        for (int i = 0; i < mIngredients.size(); i++) {
            mSumOfKCal = mSumOfKCal + mIngredients.get(i).getKCal();
        }
    }

    public Meal(String name, Integer identifier, ArrayList<Integer> mealQuantity, ArrayList<String> mealUnit) {
        mName = name;
        mIdentifier = identifier;
        mMealQuantity = (ArrayList<Integer>) mealQuantity.clone();
        mMealUnit = (ArrayList<String>) mealUnit.clone();
        //mMealKCal = mealKCal;
        mIngredients = new ArrayList<Food>();
        mSumOfKCal = 0;

        for (int i = 0; i < mIngredients.size(); i++) {
            mSumOfKCal = mSumOfKCal + mIngredients.get(i).getKCal();
        }
    }

    public Meal(String name, Integer identifier, ArrayList<Integer> mealQuantity, ArrayList<String> mealUnit, /*Integer mealKCal,*/ ArrayList<Food> ingredients) {
        mName = name;
        mIdentifier = identifier;
        mMealQuantity = (ArrayList<Integer>) mealQuantity.clone();
        mMealUnit = (ArrayList<String>) mealUnit.clone();
        //mMealKCal = mealKCal;
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mSumOfKCal = 0;

        for (int i = 0; i < mIngredients.size(); i++) {
            mSumOfKCal = mSumOfKCal + mIngredients.get(i).getKCal();
        }
    }

    public void addFood(Food ingredient) {
        mIngredients.add(ingredient);
        mSumOfKCal = mSumOfKCal + ingredient.getKCal();
    }

    public void addFoodList(ArrayList<Food> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            mIngredients.add(ingredients.get(i));
            mSumOfKCal = mSumOfKCal + ingredients.get(i).getKCal();
        }
    }

    public void addRelation(Integer quantity, String unit) {
        mMealUnit.add(unit);
        mMealQuantity.add(quantity);
    }

    public String toString() {
        return mName;
    }

    public Integer getSumOfKCal() {
        return mSumOfKCal;
    }

    public ArrayList<Food> getIngredients() {
        return mIngredients;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<Integer> getMealQuantity() {
        return mMealQuantity;
    }

    public ArrayList<String> getMealUnit() {
        return mMealUnit;
    }

    public Integer getIdentifier() {
        return mIdentifier;
    }
}
