package fhdw.bg.bfwi314b.countyourcals.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Fabian Schmitz
 */

public class DiaryEntry {

    private Date mTimeStamp;
    private Food mConsumedFood;
    private Meal mConsumedMeal;
    private Unit mConsumedUnit;
    private Integer mConsumedKCal;

    //Constructor
    public DiaryEntry(Date timeStamp, Food consumedFood, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = consumedFood;
        mConsumedMeal = null;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    //Constructor
    public DiaryEntry(Date timeStamp, Meal consumedMeal, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = null;
        mConsumedMeal = consumedMeal;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    //Constructor
    public DiaryEntry(Date timeStamp, Food consumedFood, Meal consumedMeal, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = consumedFood;
        mConsumedMeal = consumedMeal;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    //Constructor
    public DiaryEntry(Date timeStamp, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = null;
        mConsumedMeal = null;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    //Returns the String-Representation
    public String toString() {
        if (mConsumedFood == null && mConsumedMeal == null) {
            return null;
        } else if (mConsumedFood == null) {
            return mConsumedMeal.toString();
        } else if (mConsumedMeal == null) {
            return mConsumedFood.toString();
        } else if (!(mConsumedFood == null && mConsumedMeal == null)) {
            return mConsumedFood.toString() + mConsumedMeal.toString();
        } else {
            return null;
        }
    }

    //Returns the DiaryEntrys TimeStamp as Date
    public Date getTimeStamp() {
        return mTimeStamp;
    }

    //Returns the Name of the Consumed Object as String (with attached TimeStamp)
    //If there is a Food and a Meal these Method returns both Names without a TimeStamp
    public String getConsumedName() {
        if (mConsumedFood == null && mConsumedMeal == null) {
            return null;
        } else if (mConsumedFood == null) {
            return mConsumedMeal.getName() + " (" + new SimpleDateFormat("dd.MM.yyyy").format(mTimeStamp) + ")";
        } else if (mConsumedMeal == null) {
            return mConsumedFood.getName() + " (" + new SimpleDateFormat("dd.MM.yyyy").format(mTimeStamp) + ")";
        } else if (!(mConsumedFood == null && mConsumedMeal == null)) {
            return mConsumedFood.getName() + mConsumedMeal.getName();
        } else {
            return null;
        }
    }

    //Returns the DiaryEntrys Unit
    public Unit getConsumedUnit() {
        return mConsumedUnit;
    }

    //Sets the DiaryEntrys Unit
    public void setConsumedUnit(Unit unit) {
        mConsumedUnit = unit;
    }

    //Returns the Consumed KCal of the DiaryEntry
    public Integer getConsumedKCal() {
        return mConsumedKCal;
    }

    //Sets the Consumed KCal of the DiaryEntry
    public void setConsumedKCal(Integer consumedKCal) {
        mConsumedKCal = consumedKCal;
    }

    //Returns the Consumed Food of the DiaryEntry
    public Food getConsumedFood() {
        return mConsumedFood;
    }

    //Returns the Consumed Meal of the DiaryEntry
    public Meal getConsumedMeal() {
        return mConsumedMeal;
    }

    //Sets the Consumed Food of the DiaryEntry
    public void setConsumedFood(Food consumedFood) {
        mConsumedFood = consumedFood;
    }

    //Sets the Consumed Meal of the DiaryEntry
    public void setConsumedMeal(Meal consumedMeal) {
        mConsumedMeal = consumedMeal;
    }
}
