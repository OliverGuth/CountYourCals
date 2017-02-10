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

    public String toString() {
        return mUnit;
    }

    public String getUnit() {
        return mUnit;
    }

//	public void setUnit(String mUnit) {
//		this.mUnit = mUnit;
//	}

    public String getUnitShort() {
        return mUnitShorts;
    }

//	public void setUnitShorts(String mUnitShorts) {
//		this.mUnitShorts = mUnitShorts;
//	}

    public Integer getQuantity() {
        return mQuantity;
    }

}
