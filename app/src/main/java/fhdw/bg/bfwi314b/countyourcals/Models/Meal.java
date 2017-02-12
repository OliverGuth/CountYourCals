package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;

/**
 * Created by Niko
 */

public class Meal implements Consumable {

    private String mName;
    private Integer mIdentifier;
    private ArrayList<Unit> mUnits;
    private ArrayList<Food> mIngredients;
    private Integer mKCal;

    public Meal(String name, Integer identifier) {
        mName = name;
        mIdentifier = identifier;
        mUnits = new ArrayList<Unit>();
        mIngredients = new ArrayList<Food>();
        mKCal = 0;
    }

    public Meal(String name, Integer identifier, Unit mealUnit, ArrayList<Food> ingredients) {
        mName = name;
        mIdentifier = identifier;
        mUnits.add(mealUnit);
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mKCal = 0;
        for (int i = 0; i < mIngredients.size(); i++) {
            mKCal = mKCal + mIngredients.get(i).getKCal();
        }
    }

    public Meal(String name, Integer identifier, ArrayList<Unit> mealUnits) {
        mName = name;
        mIdentifier = identifier;
        mUnits = (ArrayList<Unit>) mealUnits.clone();
        mIngredients = new ArrayList<Food>();
        mKCal = 0;
        for (int i = 0; i < mIngredients.size(); i++) {
            mKCal = mKCal + mIngredients.get(i).getKCal();
        }
    }

    public Meal(String name, Integer identifier, ArrayList<Unit> mealUnit, ArrayList<Food> ingredients) {
        mName = name;
        mIdentifier = identifier;
        mUnits = (ArrayList<Unit>) mealUnit.clone();
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mKCal = 0;
        for (int i = 0; i < mIngredients.size(); i++) {
            mKCal = mKCal + mIngredients.get(i).getKCal();
        }
    }

    public String getName() {
        return mName;
    }

    public String toString() {
        return mName;
    }

    public void addUnit(Unit unit) {
        mUnits.add(unit);
    }

    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    public Integer getKCal() {
        return mKCal;
    }

    public String getType() {
        return "Meal";
    }

    public void addIngredient(Food ingredient) {
        mIngredients.add(ingredient);
    }

    public void addIngredientList(ArrayList<Food> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            mIngredients.add(ingredients.get(i));
            mKCal = mKCal + ingredients.get(i).getKCal();
        }
    }

    public ArrayList<Food> getIngredients() {
        return mIngredients;
    }

    public Integer getIdentifier() {
        return mIdentifier;
    }
}
