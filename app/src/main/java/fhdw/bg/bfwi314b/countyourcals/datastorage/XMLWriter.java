package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Niko.
 */

public class XMLWriter {


    public void writeFood(ArrayList<Food> foodArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Food tmpFood;
        String tmpName;
        ArrayList<Integer> tmpQuantity;
        ArrayList<String> tmpUnit;
        ArrayList<Integer> tmpKCal;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write(System.getProperty("line.Seperator"));

        for (int i = 0; i < foodArrayList.size(); i++) {
            tmpFood = foodArrayList.get(i);
            tmpName = tmpFood.getName();
            tmpUnit = tmpFood.getUnit();
            tmpQuantity = tmpFood.getQuantity();
            tmpKCal = tmpFood.getKCal();

            fileWriter.write("<Food>");
            fileWriter.write(System.getProperty("line.Seperator"));
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write(System.getProperty("line.Seperator"));
            for (int j = 0; j < tmpUnit.size(); j++) {
                fileWriter.write("<Relation>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("<Quantity>" + tmpQuantity.get(j) + "</Quantity>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("<Unit>" + tmpUnit.get(j) + "</Unit>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("<kCal>" + tmpKCal + "</kCal>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("</Relation>");
                fileWriter.write(System.getProperty("line.Seperator"));
            }
            fileWriter.write("</Food>");
            fileWriter.write(System.getProperty("line.Seperator"));
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void writeMeal(ArrayList<Meal> mealArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Meal tmpMeal;
        String tmpName;
        Integer tmpMealQuantity;
        String tmpMealUnit;
        Integer tmpMealKCal;
        ArrayList<String> tmpIngredientsName;
        ArrayList<Integer> tmpIngredientsQuantity;
        ArrayList<String> tmpIngredientsUnit;
        ArrayList<Integer> tmpIngredientsKCal;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write(System.getProperty("line.Seperator"));

        for (int i = 0; i < mealArrayList.size(); i++) {
            tmpMeal = mealArrayList.get(i);
            tmpName = tmpMeal.getName();
            tmpMealQuantity = tmpMeal.getMealQuantity();
            tmpMealUnit = tmpMeal.getMealUnit();
            tmpMealKCal = tmpMeal.getMealKCal();
            tmpIngredientsName = tmpMeal.getIngredients();
            tmpIngredientsQuantity = tmpMeal.getIngredientsQuantity();
            tmpIngredientsUnit = tmpMeal.getIngredientsUnit();
            tmpIngredientsKCal = tmpMeal.getIngredientsKCal();

            fileWriter.write("<Meal>");
            fileWriter.write(System.getProperty("line.Seperator"));
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write(System.getProperty("line.Seperator"));
            fileWriter.write("<MealQuantity>" + tmpMealQuantity + "</Name>");
            fileWriter.write(System.getProperty("line.Seperator"));
            fileWriter.write("<MealUnit>" + tmpMealUnit + "</Name>");
            fileWriter.write(System.getProperty("line.Seperator"));
            fileWriter.write("<MealKCal>" + tmpMealKCal + "</Name>");
            fileWriter.write(System.getProperty("line.Seperator"));
            for (int j = 0; j < tmpIngredientsName.size(); j++) {
                fileWriter.write("<Ingerdient>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("<IngerdientName>" + tmpIngredientsName.get(j) + "</IngerdientName>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("<IngerdientQuantity>" + tmpIngredientsQuantity.get(j) + "</Quantity>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("<IngerdientUnit>" + tmpIngredientsUnit.get(j) + "</Unit>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("<IngerdientkCal>" + tmpIngredientsKCal + "</kCal>");
                fileWriter.write(System.getProperty("line.Seperator"));
                fileWriter.write("</Ingerdient>");
                fileWriter.write(System.getProperty("line.Seperator"));
            }
            fileWriter.write("</Meal>");
            fileWriter.write(System.getProperty("line.Seperator"));
        }
        fileWriter.flush();
        fileWriter.close();
    }


    public void writeUser(ArrayList<User> userArrayList, File file) throws IOException {
        
    }
}
