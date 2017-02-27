package fhdw.bg.bfwi314b.countyourcals.Models;

/**
 * Created by Fabian Schmitz
 */

public class Unit {

    private String mUnit;
    private String mUnitShorts;
    private Integer mQuantity;

    //Constructor
    public Unit(String unit, String unitShort, Integer quantity) {
        mUnit = unit;
        mUnitShorts = unitShort;
        mQuantity = quantity;
    }

    //Returns the String-Representation
    public String toString() {
        return mUnit;
    }

    //Returns the Units Name as String
    public String getUnit() {
        return mUnit;
    }

    //Returns the Units Sortname as String
    public String getUnitShort() {
        return mUnitShorts;
    }

    //Returns the Units Quantity as Integer
    public Integer getQuantity() {
        return mQuantity;
    }

    //Sets the Units Quantity
    public void setQuantity(int quantity)
    {
        mQuantity = quantity;
    }
}
