package fhdw.bg.bfwi314b.countyourcals.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Niko
 */

public class DiaryEntry {

    private Date mTimeStamp;
    private Food mConsumedFood;
    private Meal mConsumedMeal;
    private Unit mConsumedUnit;
    private Integer mConsumedKCal;

    public DiaryEntry(Date timeStamp, Food consumedFood, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = consumedFood;
        mConsumedMeal = null;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    public DiaryEntry(Date timeStamp, Meal consumedMeal, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = null;
        mConsumedMeal = consumedMeal;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    public DiaryEntry(Date timeStamp, Food consumedFood, Meal consumedMeal, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = consumedFood;
        mConsumedMeal = consumedMeal;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    public DiaryEntry(Date timeStamp, Unit consumedUnit, Integer consumedKCal) {
        mTimeStamp = timeStamp;
        mConsumedFood = null;
        mConsumedMeal = null;
        mConsumedUnit = consumedUnit;
        mConsumedKCal = consumedKCal;
    }

    @Override
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

    public Date getTimeStamp() {
        return mTimeStamp;
    }

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

    public Food getConsumedFood() {
        return mConsumedFood;
    }

    public Meal getConsumedMeal() {
        return mConsumedMeal;
    }

    public void setConsumedFood(Food consumedFood) {
        mConsumedFood = consumedFood;
    }

    public void setConsumedMeal(Meal consumedMeal) {
        mConsumedMeal = consumedMeal;
    }
}
