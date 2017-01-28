package fhdw.bg.bfwi314b.countyourcals.controller;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import fhdw.bg.bfwi314b.countyourcals.datastorage.User;
import fhdw.bg.bfwi314b.countyourcals.datastorage.XMLReader;
import fhdw.bg.bfwi314b.countyourcals.datastorage.XMLWriter;

/**
 * Created by Niko.
 */

public class DataStorageController {

    private XMLReader mXMLReader;
    private XMLWriter mXMLWriter;

    private File mUserFile;
    private File mFoodFile;
    private File mMealFile;
    private File mDiaryEntryFile;

    private String mUser;


    public DataStorageController() {
        mXMLReader = new XMLReader();
        mXMLWriter = new XMLWriter();
    }

    //public ArrayList<User> getUserList() {
        //File file = Environment.getDataDirectory()
        //mUserFile =
        //return
    //}

}
