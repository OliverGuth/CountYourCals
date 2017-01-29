package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.security.Timestamp;

/**
 * Created by Niko.
 */

public class DiaryEntry {

    private String mTimeStamp;
    private String mConsumedName;
    private Integer mConsumedQuantity;
    private String mConsumedUnit;
    private Integer mConsumedKCal;

    public DiaryEntry(String timeStamp, String consumedName, Integer consumedQuantity, String consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedName = consumedName;
        mConsumedQuantity = consumedQuantity;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public String getConsumedName() {
        return mConsumedName;
    }

    public void setConsumedName(String mConsumedName) {
        this.mConsumedName = mConsumedName;
    }

    public Integer getConsumedQuantity() {
        return mConsumedQuantity;
    }

    public void setConsumedQuantity(Integer mConsumedQuantity) {
        this.mConsumedQuantity = mConsumedQuantity;
    }

    public String getConsumedUnit() {
        return mConsumedUnit;
    }

    public void setConsumedUnit(String mConsumedUnit) {
        this.mConsumedUnit = mConsumedUnit;
    }

    public Integer getConsumedKCal() {
        return mConsumedKCal;
    }

    public void setConsumedKCal(Integer mConsumedKCal) {
        this.mConsumedKCal = mConsumedKCal;
    }
}