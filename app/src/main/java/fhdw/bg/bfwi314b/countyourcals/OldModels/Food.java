package fhdw.bg.bfwi314b.countyourcals.OldModels;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko.
 */

public class Food {

    private String mName;
    //private ArrayList<String> mUnit;
    //private ArrayList<Integer> mQuantity;
    private ArrayList<Unit> mUnits;
    private Integer mKCal;

    public Food(String name) {
        //mUnit = new ArrayList<String>();
        //mQuantity = new ArrayList<Integer>();
        mUnits = new ArrayList<Unit>();
        mKCal = 0;

        mName = name;
    }

    public Food(String name, Unit unit, Integer kCal) {
        //mUnit = new ArrayList<String>();
        //mQuantity = new ArrayList<Integer>();
        mUnits = new ArrayList<Unit>();

        mName = name;
        //mUnit.add(unit);
        //mQuantity.add(quantity);
        mUnits.add(unit);
        mKCal = kCal;
    }

    public Food(String name, ArrayList<Unit> unit, Integer kCal) {
        mName = name;
        //mUnit = (ArrayList<String>) unit.clone();
        //mQuantity = (ArrayList<Integer>) quantity.clone();
        mUnits = (ArrayList<Unit>) unit.clone();
        mKCal = kCal;
    }

    public void addUnit(Unit unit) {
        //mUnit.add(unit);
        //mQuantity.add(quantity);
        mUnits.add(unit);
    }

    public String toString() {
        return mName;
    }

    public String getName() {
        return mName;
    }

    /*public ArrayList<String> getUnit() {
        return mUnit;
    }

    public ArrayList<Integer> getQuantity() {
        return mQuantity;
    }*/

    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    public Integer getKCal() {
        return mKCal;
    }

}
