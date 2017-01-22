package fhdw.bg.bfwi314b.countyourcals.fhdw.bg.bfwi314b.countyourcals.datastorage;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by Niko
 * Wirtes Data to Files
 */

public class Writer {

    private boolean isExternalStorageWritable() {
        String externalStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(externalStorageState)) {
            return true;
        }
        return false;
    }


    public void GenerateFile(String directory, String filename) {

    }

    public boolean AddLine(String directory, String filename, String line) {
        if (isExternalStorageWritable()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(directory + filename));
                bufferedWriter.newLine();
                bufferedWriter.write(line);
                bufferedWriter.flush();
                bufferedWriter.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean AddMultipleLines(String directory, String filename, String[] lines) {
        if (isExternalStorageWritable()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(directory + filename));
                for (int index = 0; index < lines.length; index = index + 1) {
                    bufferedWriter.newLine();
                    bufferedWriter.write(lines[index]);
                }
                bufferedWriter.flush();
                bufferedWriter.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public void ChangeLine(String directory, String filename, int lineNumber, String newLineContent) {

    }
}
