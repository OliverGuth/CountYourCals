package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko Reder
 */

public class XMLMealWriter {

    //Writes the Meals to an XML-File
    //Therefore the Meals-Attributes are inserted into an XML-Template and written into an File.
    //Fore Each User is one node "<Meal>" into "<Meals>" generated.
    public void writeMeal(ArrayList<Meal> mealArrayList, File file/*, String userName*/) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Meal tmpMeal;
        String tmpName;
        Integer tmpKcal;
        //ArrayList<Integer> tmpMealQuantity;
        //ArrayList<String> tmpMealUnit;
        ArrayList<Unit> tmpMealUnits;
        //ArrayList<Food> tmpIngredients;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Meals>");
        fileWriter.write("\n");

        for (int i = 0; i < mealArrayList.size(); i++) {
            tmpMeal = mealArrayList.get(i);
            tmpName = tmpMeal.getName();
            //tmpMealQuantity = tmpMeal.getMealQuantity();
            //tmpMealUnit = tmpMeal.getMealUnit();
            tmpMealUnits = tmpMeal.getUnits();
            //tmpIngredients = tmpMeal.getIngredients();
            tmpKcal = tmpMeal.getKCal();

            fileWriter.write("<Meal>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<kCal>" + tmpKcal + "</kCal>");
            fileWriter.write("\n");
            fileWriter.write("<MealRelations>");
            fileWriter.write("\n");
            for (int j = 0; j < tmpMealUnits.size(); j++) {
                //fileWriter.write("<MealQuantity>" + tmpMealQuantity.get(j) + "</MealQuantity>");
                fileWriter.write("<MealQuantity>" + tmpMealUnits.get(j).getQuantity() + "</MealQuantity>");
                fileWriter.write("\n");
                fileWriter.write("<MealUnit>" + tmpMealUnits.get(j).getUnit() + "</MealUnit>");
                fileWriter.write("\n");
                fileWriter.write("<MealUnitShort>" + tmpMealUnits.get(j).getUnitShort() + "</MealUnitShort>");
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
}
