package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.Date;

/**
 * Created by Niko
 */

public class DiaryEntry {

    private Date mTimeStamp;
    private Consumable mConsumedObject;
    private Unit mConsumedUnit;
    private Integer mConsumedKCal;
    private Integer mIdentifier;
    private String mConsumedType;

    public DiaryEntry(Date timeStamp, Consumable consumed, Unit consumedUnit, Integer consumedKCal, Integer identifier) {
        mTimeStamp = timeStamp;
        mConsumedObject = consumed;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
        mIdentifier = identifier;
        mConsumedType = mConsumedObject.getType();
    }

    public DiaryEntry(Date timeStamp, Unit consumedUnit, Integer consumedKCal, Integer identifier, String consumedType) {
        mTimeStamp = timeStamp;
        mConsumedObject = null;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
        mIdentifier = identifier;
        mConsumedType = consumedType;
    }

    public String toString() {
        return mConsumedObject.toString();
    }

    public Date getTimeStamp() {
        return mTimeStamp;
    }

    public String getConsumedName() {
        return mConsumedObject.getName();
    }

    public void setConsumedObject(Consumable consumedObject) {
        mConsumedObject = consumedObject;
        mConsumedType = mConsumedObject.getType();
    }

    public Integer getIdentifier() {
        return mIdentifier;
    }

    public Unit getConsumedUnit() {
        return mConsumedUnit;
    }

    public void setConsumedUnit(Unit unit) {
        mConsumedUnit = unit;
    }

    public Integer getConsumedKCal() {
        return mConsumedKCal;
    }

    public void setConsumedKCal(Integer consumedKCal) {
        mConsumedKCal = consumedKCal;
    }

    public Consumable getConsumedObject() {
        return mConsumedObject;
    }

    public String getConsumedType() {
        return mConsumedType;
    }
}
