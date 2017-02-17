package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;

/**
 * Created by Niko
 */

public class Meal{

    private String mName;
    private ArrayList<Food> mIngredients;
    private ArrayList<Unit> mUnits;
    private Integer mKCal;

    public Meal(String name) {
        mName = name;
        mUnits = new ArrayList<Unit>();
        mIngredients = new ArrayList<Food>();
        mKCal = 0;
    }

    public Meal(String name, Unit mealUnit, ArrayList<Food> ingredients, int KCal) {
        mName = name;
        mUnits.add(mealUnit);
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mKCal = KCal;
    }

    public Meal(String name, ArrayList<Unit> mealUnits, int KCal) {
        mName = name;
        mUnits = (ArrayList<Unit>) mealUnits.clone();
        mIngredients = new ArrayList<Food>();
        mKCal = KCal;
    }

    public Meal(String name, ArrayList<Food> ingredients, ArrayList<Unit> mealUnit, int KCal) {
        mName = name;
        mUnits = (ArrayList<Unit>) mealUnit.clone();
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mKCal = KCal;
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

    public void addIngredient(Food ingredient) {
        mIngredients.add(ingredient);
    }

    public void addIngredientList(ArrayList<Food> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            mIngredients.add(ingredients.get(i));
        }
    }

    public ArrayList<Food> getIngredients() {
        return mIngredients;
    }

}
