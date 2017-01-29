package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niko.
 */

public class Food {

    private String mName;
    private ArrayList<String> mUnit;
    private ArrayList<Integer> mQuantity;
    private ArrayList<Integer> mKCal;

    public Food(String name) {
        mUnit = new ArrayList<String>();
        mQuantity = new ArrayList<Integer>();
        mKCal = new ArrayList<Integer>();

        mName = name;
    }

    public Food(String name, String unit, Integer quantity, Integer kCal) {
        mUnit = new ArrayList<String>();
        mQuantity = new ArrayList<Integer>();
        mKCal = new ArrayList<Integer>();

        mName = name;
        mUnit.add(unit);
        mQuantity.add(quantity);
        mKCal.add(kCal);
    }

    public Food(String name, ArrayList<Integer> quantity, ArrayList<String> unit, ArrayList<Integer> kCal) {
        mName = name;
        mUnit = (ArrayList<String>) unit.clone();
        mQuantity = (ArrayList<Integer>) quantity.clone();
        mKCal = (ArrayList<Integer>) kCal.clone();
    }

    public void addRelation(Integer quantity, String unit, Integer kCal) {
        mUnit.add(unit);
        mQuantity.add(quantity);
        mKCal.add(kCal);
    }

    public String getName() {
        return mName;
    }

    public ArrayList<String> getUnit() {
        return mUnit;
    }

    public ArrayList<Integer> getQuantity() {
        return mQuantity;
    }

    public ArrayList<Integer> getKCal() {
        return mKCal;
    }

}
