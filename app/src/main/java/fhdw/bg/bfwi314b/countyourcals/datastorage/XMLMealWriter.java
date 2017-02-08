package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.Meal;

/**
 * Created by Niko.
 */

public class XMLMealWriter {

    public void writeMeal(ArrayList<Meal> mealArrayList, File file/*, String userName*/) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Meal tmpMeal;
        String tmpName;
        Integer tmpIdentifier;
        ArrayList<Integer> tmpMealQuantity;
        ArrayList<String> tmpMealUnit;
        //ArrayList<Food> tmpIngredients;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Meals>");
        fileWriter.write("\n");

        for (int i = 0; i < mealArrayList.size(); i++) {
            tmpMeal = mealArrayList.get(i);
            tmpName = tmpMeal.getName();
            tmpIdentifier = tmpMeal.getIdentifier();
            tmpMealQuantity = tmpMeal.getMealQuantity();
            tmpMealUnit = tmpMeal.getMealUnit();
            //tmpIngredients = tmpMeal.getIngredients();

            fileWriter.write("<Meal>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<Identifier>" + tmpIdentifier + "</Identifier>");
            fileWriter.write("\n");
            fileWriter.write("<MealRelations>");
            fileWriter.write("\n");
            for (int j = 0; j < tmpMealUnit.size(); j++) {
                fileWriter.write("<MealQuantity>" + tmpMealQuantity.get(j) + "</MealQuantity>");
                fileWriter.write("\n");
                fileWriter.write("<MealUnit>" + tmpMealUnit.get(j) + "</MealUnit>");
                fileWriter.write("\n");
            }
            fileWriter.write("</MealRelations>");
            fileWriter.write("\n");
            fileWriter.write("</Meal>");
            fileWriter.write("\n");
            //this.writeIngredient (tmpIngredients, new File(userName + tmpIdentifier + ".xml"));
        }
        fileWriter.write("</Meals>");
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }

    public void writeIngredient(ArrayList<Food> ingredientArrayList, File file) throws IOException {
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.writeFood(ingredientArrayList, file);

        /*FileWriter fileWriter = new FileWriter(file);
        Food tmpFood;
        String tmpName;
        ArrayList<Integer> tmpIngredientQuantity;
        ArrayList<String> tmpIngredientUnit;
        Integer tmpIngredientKCal;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Ingredients>");
        fileWriter.write("\n");

        for (int i = 0; i < ingredientArrayList.size(); i++) {
            tmpFood = ingredientArrayList.get(i);
            tmpName = tmpFood.getName();
            tmpIngredientKCal = tmpFood.getKCal();
            tmpIngredientQuantity = tmpFood.getQuantity();
            tmpIngredientUnit = tmpFood.getUnit();

            fileWriter.write("</Ingredient>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<KCal>" + tmpIngredientKCal + "</KCal>");
            fileWriter.write("\n");
            fileWriter.write("<Relations>");
            fileWriter.write("\n");
            for (int j = 0; j < tmpIngredientQuantity.size(); j++) {
                fileWriter.write("<Relation>");
                fileWriter.write("\n");
                fileWriter.write("<Quantity>" + tmpIngredientQuantity.get(j) + "</Quantity>");
                fileWriter.write("\n");
                fileWriter.write("<Unit>" + tmpIngredientUnit.get(j) + "</Unit>");
                fileWriter.write("\n");
                fileWriter.write("</Relation>");
                fileWriter.write("\n");
            }
            fileWriter.write("</Relations>");
            fileWriter.write("\n");
            fileWriter.write("</Ingredient>");
            fileWriter.write("\n");
        }

        fileWriter.write("</Ingredients>");
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();*/
    }
}
