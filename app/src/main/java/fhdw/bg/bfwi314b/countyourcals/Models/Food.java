package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niko.
 */

public class Food {

    private String mName;
    private ArrayList<String> mUnit;
    private ArrayList<Integer> mQuantity;
    private Integer mKCal;

    public Food(String name) {
        mUnit = new ArrayList<String>();
        mQuantity = new ArrayList<Integer>();
        mKCal = 0;

        mName = name;
    }

    public Food(String name, Integer quantity, String unit, Integer kCal) {
        mUnit = new ArrayList<String>();
        mQuantity = new ArrayList<Integer>();

        mName = name;
        mUnit.add(unit);
        mQuantity.add(quantity);
        mKCal = kCal;
    }

    public Food(String name, ArrayList<Integer> quantity, ArrayList<String> unit, Integer kCal) {
        mName = name;
        mUnit = (ArrayList<String>) unit.clone();
        mQuantity = (ArrayList<Integer>) quantity.clone();
        mKCal = kCal;
    }

    public void addRelation(Integer quantity, String unit) {
        mUnit.add(unit);
        mQuantity.add(quantity);
    }

    public String toString() {
        return mName;
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

    public Integer getKCal() {
        return mKCal;
    }

}
