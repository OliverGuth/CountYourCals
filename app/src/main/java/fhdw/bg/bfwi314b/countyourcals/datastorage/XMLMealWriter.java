package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Niko.
 */

public class XMLMealWriter {

    public void writeMeal(ArrayList<Meal> mealArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Meal tmpMeal;
        String tmpName;
        Integer tmpIdentifier;
        Integer tmpMealQuantity;
        String tmpMealUnit;
        Integer tmpMealKCal;
        ArrayList<String> tmpIngredientsName;
        ArrayList<Integer> tmpIngredientsQuantity;
        ArrayList<String> tmpIngredientsUnit;
        ArrayList<Integer> tmpIngredientsKCal;

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
            tmpMealKCal = tmpMeal.getMealKCal();
            tmpIngredientsName = tmpMeal.getIngredients();
            tmpIngredientsQuantity = tmpMeal.getIngredientsQuantity();
            tmpIngredientsUnit = tmpMeal.getIngredientsUnit();
            tmpIngredientsKCal = tmpMeal.getIngredientsKCal();

            fileWriter.write("<Meal>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<Identifier>" + tmpIdentifier + "</Identifier>");
            fileWriter.write("\n");
            fileWriter.write("<MealQuantity>" + tmpMealQuantity + "</MealQuantity>");
            fileWriter.write("\n");
            fileWriter.write("<MealUnit>" + tmpMealUnit + "</MealUnit>");
            fileWriter.write("\n");
            fileWriter.write("<MealKCal>" + tmpMealKCal + "</MealKCal>");
            fileWriter.write("\n");
            for (int j = 0; j < tmpIngredientsName.size(); j++) {
                fileWriter.write("<Ingerdient>");
                fileWriter.write("\n");
                fileWriter.write("<IngerdientName>" + tmpIngredientsName.get(j) + "</IngerdientName>");
                fileWriter.write("\n");
                fileWriter.write("<IngerdientQuantity>" + tmpIngredientsQuantity.get(j) + "</IngerdientQuantity>");
                fileWriter.write("\n");
                fileWriter.write("<IngerdientUnit>" + tmpIngredientsUnit.get(j) + "</IngerdientUnit>");
                fileWriter.write("\n");
                fileWriter.write("<IngerdientkCal>" + tmpIngredientsKCal + "</IngerdientkCal>");
                fileWriter.write("\n");
                fileWriter.write("</Ingerdient>");
                fileWriter.write("\n");
            }
            fileWriter.write("</Meal>");
            fileWriter.write("\n");
        }
        fileWriter.write("</Meals>");
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }
}
