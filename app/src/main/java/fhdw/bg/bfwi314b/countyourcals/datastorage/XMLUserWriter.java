package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Niko.
 */

public class XMLUserWriter {

    public void writeUser(ArrayList<User> userArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        User tmpUser;
        String tmpName;
        Character tmpGender;
        Integer tmpMaxKCal;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Users>");
        fileWriter.write("\n");

        for (int i = 0; i < userArrayList.size(); i++) {
            tmpUser = userArrayList.get(i);
            tmpName = tmpUser.getName();
            tmpGender = tmpUser.getGender();
            tmpMaxKCal = tmpUser.getMaxKCal();

            fileWriter.write("<User>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<Gender>" + tmpGender + "</Gender>");
            fileWriter.write("\n");
            fileWriter.write("<MaxKCal>" + tmpMaxKCal + "</MaxKCal>");
            fileWriter.write("\n");
            fileWriter.write("</User>");
            fileWriter.write("\n");
        }
        fileWriter.write("</Users>");
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }
}
