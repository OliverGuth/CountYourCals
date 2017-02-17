package fhdw.bg.bfwi314b.countyourcals.Models;


public class Unit {

    private String mUnit;
    private String mUnitShorts;
    private Integer mQuantity;

    public Unit(String unit, String unitShort, Integer quantity) {
        mUnit = unit;
        mUnitShorts = unitShort;
        mQuantity = quantity;
    }

    @Override
    public String toString() {
        return mUnit;
    }

    public String getUnit() {
        return mUnit;
    }

    public String getUnitShort() {
        return mUnitShorts;
    }

    public Integer getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity)
    {
        mQuantity = quantity;
    }
}
