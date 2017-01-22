package fhdw.bg.bfwi314b.countyourcals.fhdw.bg.bfwi314b.countyourcals.datastorage;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Niko.
 * <p>
 * Class creates Connection to Writer and Reader
 */

public class DataStorage {

    private String mUser;
    private String mDirectory;
    private String mUserDirectory;
    private FileOutputStream mUserFileOutputStream;
    private FileOutputStream mKCalFileOutputStream;
    private FileOutputStream mFoodFileOutputStream;
    private FileOutputStream mMealFileOutputStream;

    DataStorage(String user) {
        mUser = user;
        mDirectory = "";
        mUserDirectory = "";
    }

    public void setmUser(String user) {
        mUser = user;
        mUserDirectory = "";
    }
}
