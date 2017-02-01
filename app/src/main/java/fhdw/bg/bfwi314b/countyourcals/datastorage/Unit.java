package fhdw.bg.bfwi314b.countyourcals.datastorage;


public class Unit {

    private String mUnit;
    private String mUnitShorts;

    public Unit(String unit, String unitShort) {
        mUnit = unit;
        mUnitShorts = unitShort;
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


}
