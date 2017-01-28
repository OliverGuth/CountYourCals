package fhdw.bg.bfwi314b.countyourcals.datastorage;

/**
 * Created by Niko.
 */

public class User {

    private String mName;
    private Character mGender;
    private Integer mMaxKCal;

    public User(String name) {
        mName = name;
    }

    public User(String name, Character gender, Integer maxKCal) {
        mName = name;
        mGender = gender;
        mMaxKCal = maxKCal;
    }

    public String getName() {
        return mName;
    }

    public Character getGender() {
        return mGender;
    }

    public Integer getMaxKCal() {
        return mMaxKCal;
    }

    public void setMaxKCal(Integer maxKCal) {
        mMaxKCal = maxKCal;
    }
}
