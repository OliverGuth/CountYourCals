package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko.
 */

public class XMLDiaryEntryWriter {

    public void writeDiaryEntry(ArrayList<DiaryEntry> entryArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        DiaryEntry tmpEntry;
        Date tmpTimeStamp;
        //Integer tmpQuantity;
        //String tmpUnit;
        Unit tmpUnit;
        Integer tmpKCal;
        Integer tmpIdentifier;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<DiaryEntrys>");
        fileWriter.write("\n");

        for (int i = 0; i < entryArrayList.size(); i++) {
            tmpEntry = entryArrayList.get(i);
            tmpTimeStamp = tmpEntry.getTimeStamp();
            //tmpQuantity = tmpEntry.getConsumedQuantity();
            tmpUnit = tmpEntry.getConsumedUnit();
            tmpKCal = tmpEntry.getConsumedKCal();

            fileWriter.write("<DiaryEntry>");
            fileWriter.write("\n");
            fileWriter.write("<TimeStamp>" + tmpTimeStamp.toString() + "</TimeStamp>");
            fileWriter.write("\n");
            //fileWriter.write("<Quantity>" + tmpQuantity + "</Quantity>");
            fileWriter.write("<Quantity>" + tmpUnit.getQuantity() + "</Quantity>");
            fileWriter.write("\n");
            fileWriter.write("<Unit>" + tmpUnit.getUnit() + "</Unit>");
            fileWriter.write("\n");
            fileWriter.write("<UnitShort>" + tmpUnit.getUnitShort() + "</UnitShort>");
            fileWriter.write("\n");
            fileWriter.write("<KCal>" + tmpKCal + "</KCal>");
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
