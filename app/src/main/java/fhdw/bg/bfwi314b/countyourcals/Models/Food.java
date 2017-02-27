package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;

/**
 * Created by Fabian Schmitz
 */

public class Food{

    private String mName;
    private ArrayList<Unit> mUnits;
    private Integer mKCal;

    //Constructor
    public Food(String name, ArrayList<Unit> unit, Integer kCal) {
        mName = name;
        mUnits = (ArrayList<Unit>) unit.clone();
        mKCal = kCal;
    }

    //Returns the Foods Name as Sting
    public String getName() {
        return mName;
    }

    //Returns the String-Representation
    public String toString() {
        return mName;
    }

    //Returns the Foods Units as ArrayList<Unit>
    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    //Returns the Foods KCal as Integer
    public Integer getKCal() {
        return mKCal;
    }
}
