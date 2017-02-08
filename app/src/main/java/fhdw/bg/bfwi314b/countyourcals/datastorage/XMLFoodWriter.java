package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;

/**
 * Created by Niko.
 */

public class XMLFoodWriter {

    public void writeFood(ArrayList<Food> foodArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Food tmpFood;
        String tmpName;
        ArrayList<Integer> tmpQuantity;
        ArrayList<String> tmpUnit;
        Integer tmpKCal;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Foods>");
        fileWriter.write("\n");

        for (int i = 0; i < foodArrayList.size(); i++) {
            tmpFood = foodArrayList.get(i);
            tmpName = tmpFood.getName();
            tmpUnit = tmpFood.getUnit();
            tmpQuantity = tmpFood.getQuantity();
            tmpKCal = tmpFood.getKCal();

            fileWriter.write("<Food>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<kCal>" + tmpKCal + "</kCal>");
            fileWriter.write("\n");
            for (int j = 0; j < tmpUnit.size(); j++) {
                fileWriter.write("<Relation>");
                fileWriter.write("\n");
                fileWriter.write("<Quantity>" + tmpQuantity.get(j) + "</Quantity>");
                fileWriter.write("\n");
                fileWriter.write("<Unit>" + tmpUnit.get(j) + "</Unit>");
                fileWriter.write("\n");
                fileWriter.write("</Relation>");
                fileWriter.write("\n");
            }
            fileWriter.write("</Food>");
            fileWriter.write("\n");
        }
        fileWriter.write("</Foods>");
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }

}
