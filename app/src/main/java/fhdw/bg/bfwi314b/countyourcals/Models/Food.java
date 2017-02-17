package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;

/**
 * Created by Niko
 */

public class Food{

    private String mName;
    private ArrayList<Unit> mUnits;
    private Integer mKCal;

    public Food(String name, ArrayList<Unit> unit, Integer kCal) {
        mName = name;
        mUnits = (ArrayList<Unit>) unit.clone();
        mKCal = kCal;
    }

    public String getName() {
        return mName;
    }

    public String toString() {
        return mName;
    }

    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    public Integer getKCal() {
        return mKCal;
    }
}
