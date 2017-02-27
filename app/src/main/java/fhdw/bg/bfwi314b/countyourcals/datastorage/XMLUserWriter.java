package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import fhdw.bg.bfwi314b.countyourcals.Models.User;

/**
 * Created by Niko Reder
 */

public class XMLUserWriter {

    //Writes the Users to an XML-File
    //Therefore the Users-Attributes are inserted into an XML-Template and written into an File.
    //Fore Each User is one node "<User>" into "<Users>" generated.
    public void writeUser(ArrayList<User> userArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
                User tmpUser;
        String tmpName;
        Character tmpGender;
        Integer tmpMaxKCal;
        String tmpLanguage;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Users>");
        fileWriter.write("\n");

        for (int i = 0; i < userArrayList.size(); i++) {
            tmpUser = userArrayList.get(i);
            tmpName = tmpUser.getName();
            tmpGender = tmpUser.getGender();
            tmpMaxKCal = tmpUser.getMaxKCal();
            tmpLanguage = tmpUser.getLanguage();

            fileWriter.write("<User>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<Gender>" + tmpGender + "</Gender>");
            fileWriter.write("\n");
            fileWriter.write("<MaxKCal>" + tmpMaxKCal + "</MaxKCal>");
            fileWriter.write("\n");
            fileWriter.write("<Language>" + tmpLanguage + "</Language>");
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
