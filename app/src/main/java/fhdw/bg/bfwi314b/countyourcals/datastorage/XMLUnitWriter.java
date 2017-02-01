package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Niko.
 */

public class XMLUnitWriter {

    public void writeUnit(ArrayList<Unit> unitArrayList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        Unit tmpUnit;
        String tmpUnitName;
        String tmpUnitShort;

        fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fileWriter.write("\n");
        fileWriter.write("<Units>");
        fileWriter.write("\n");

        for (int i = 0; i < unitArrayList.size(); i++) {
            tmpUnit = unitArrayList.get(i);
            tmpUnitName = tmpUnit.getUnit();
            tmpUnitShort = tmpUnit.getUnitShort();

            fileWriter.write("<Unit>");
            fileWriter.write("\n");
            fileWriter.write("<UnitName>" + tmpUnitName + "</UnitName>");
            fileWriter.write("\n");
            fileWriter.write("<UnitShort>" + tmpUnitShort + "</UnitShort>");
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
