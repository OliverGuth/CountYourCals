package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;

/**
 * Created by Fabian Schmitz
 */

public class Meal{

    private String mName;
    private ArrayList<Food> mIngredients;
    private ArrayList<Unit> mUnits;
    private Integer mKCal;

    //Constructor
    public Meal(String name) {
        mName = name;
        mUnits = new ArrayList<Unit>();
        mIngredients = new ArrayList<Food>();
        mKCal = 0;
    }

    //Constructor
    public Meal(String name, Unit mealUnit, ArrayList<Food> ingredients, int KCal) {
        mName = name;
        mUnits.add(mealUnit);
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mKCal = KCal;
    }

    //Constructor
    public Meal(String name, ArrayList<Unit> mealUnits, int KCal) {
        mName = name;
        mUnits = (ArrayList<Unit>) mealUnits.clone();
        mIngredients = new ArrayList<Food>();
        mKCal = KCal;
    }

    //Constructor
    public Meal(String name, ArrayList<Food> ingredients, ArrayList<Unit> mealUnit, int KCal) {
        mName = name;
        mUnits = (ArrayList<Unit>) mealUnit.clone();
        mIngredients = (ArrayList<Food>) ingredients.clone();
        mKCal = KCal;
    }

    //Returns the Meals Name as Sting
    public String getName() {
        return mName;
    }

    //Returns the String-Representation
    public String toString() {
        return mName;
    }

    //Adds a Unit to the Meals Unit-ArrayList
    public void addUnit(Unit unit) {
        mUnits.add(unit);
    }

    //Returns the Meals Units as ArrayList<Unit>
    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    //Returns the Meals KCal as Integer
    public Integer getKCal() {
        return mKCal;
    }

    //Adds a Food to the Ingredientlist (ArrayList<Food>) to the Meal
    public void addIngredient(Food ingredient) {
        mIngredients.add(ingredient);
    }

    //Adds a ArrayList<Food> to the Ingredientlist (ArrayList<Food>) to the Meal
    public void addIngredientList(ArrayList<Food> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            mIngredients.add(ingredients.get(i));
        }
    }

    //Returns the Ingredients of the Meal as ArrayList<Foot>
    public ArrayList<Food> getIngredients() {
        return mIngredients;
    }

}
