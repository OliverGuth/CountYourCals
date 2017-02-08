package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Niko.
 */

public class XMLDiaryEntryWriter {

    public void writeDiaryEntry(ArrayList<DiaryEntry> entryArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        DiaryEntry tmpEntry;
        Date tmpTimeStamp;
        String tmpName;
        Integer tmpQuantity;
        String tmpUnit;
        Integer tmpKCal;
        Integer tmpIdentifier;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<DiaryEntrys>");
        fileWriter.write("\n");

        for (int i = 0; i < entryArrayList.size(); i++) {
            tmpEntry = entryArrayList.get(i);
            tmpTimeStamp = tmpEntry.getTimeStamp();
            tmpName = tmpEntry.getConsumedName();
            tmpQuantity = tmpEntry.getConsumedQuantity();
            tmpUnit = tmpEntry.getConsumedUnit();
            tmpKCal = tmpEntry.getConsumedKCal();
            tmpIdentifier = tmpEntry.getIdentifier();

            fileWriter.write("<DiaryEntry>");
            fileWriter.write("\n");
            fileWriter.write("<TimeStamp>" + tmpTimeStamp.toString() + "</TimeStamp>");
            fileWriter.write("\n");
            fileWriter.write("<Name>" + tmpName + "</Name>");
            fileWriter.write("\n");
            fileWriter.write("<Quantity>" + tmpQuantity + "</Quantity>");
            fileWriter.write("\n");
            fileWriter.write("<Unit>" + tmpUnit + "</Unit>");
            fileWriter.write("\n");
            fileWriter.write("<KCal>" + tmpKCal + "</KCal>");
            fileWriter.write("\n");
            fileWriter.write("<Identifier>" + tmpIdentifier + "</Identifier>");
            fileWriter.write("\n");
            fileWriter.write("</DiaryEntry>");
            fileWriter.write("\n");
        }
        fileWriter.write("</DiaryEntrys>");
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }
}
