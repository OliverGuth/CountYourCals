package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;

/**
 * Created by Niko
 */

public class Food implements Consumable {

    private String mName;
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

    public String getName() {
        return mName;
    }

    public String toString() {
        return mName;
    }

    public void addUnit(Unit unit) {
        mUnits.add(unit);
    }

    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    public Integer getKCal() {
        return mKCal;
    }

    public String getType() {
        return "Food";
    }
}
