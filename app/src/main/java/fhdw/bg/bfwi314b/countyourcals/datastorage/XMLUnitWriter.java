package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko Reder
 */

public class XMLUnitWriter {

    //Writes the Units to an XML-File
    //Therefore the Unit-Attributes are inserted into an XML-Template and written into an File.
    //Fore Each User is one node "<Unit>" into "<Units>" generated.
    public void writeUnit(ArrayList<Unit> unitArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Unit tmpUnit;
        String tmpUnitName;
        String tmpUnitShort;
        Integer tmpUnitQuantity;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Units>");
        fileWriter.write("\n");

        for (int i = 0; i < unitArrayList.size(); i++) {
            tmpUnit = unitArrayList.get(i);
            tmpUnitName = tmpUnit.getUnit();
            tmpUnitShort = tmpUnit.getUnitShort();
            tmpUnitQuantity = tmpUnit.getQuantity();

            fileWriter.write("<Unit>");
            fileWriter.write("\n");
            fileWriter.write("<UnitName>" + tmpUnitName + "</UnitName>");
            fileWriter.write("\n");
            fileWriter.write("<UnitShort>" + tmpUnitShort + "</UnitShort>");
            fileWriter.write("\n");
            fileWriter.write("<UnitShort>" + tmpUnitQuantity + "</UnitShort>");
            fileWriter.write("\n");
            fileWriter.write("</Unit>");
            fileWriter.write("\n");
        }
        fileWriter.write("</Units>");
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }

}
