package fhdw.bg.bfwi314b.countyourcals.datastorage;

import java.security.Timestamp;
import java.util.Date;

/**
 * Created by Niko.
 */

public class DiaryEntry {

    private Date mTimeStamp;
    private String mConsumedName;
    private Integer mConsumedQuantity;
    private String mConsumedUnit;
    private Integer mConsumedKCal;
    private Integer mIdentifier;

    public DiaryEntry(Date timeStamp, String consumedName, Integer consumedQuantity, String consumedUnit, Integer consumedKCal, Integer identifier) {
        mTimeStamp = timeStamp;
        mConsumedName = consumedName;
        mConsumedQuantity = consumedQuantity;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
        mIdentifier = identifier;
    }

    public String toString() {
        return mConsumedName;
    }

    public Date getTimeStamp() {
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

    public Integer getIdentifier() {
        return mIdentifier;
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
