package fhdw.bg.bfwi314b.countyourcals.datastorage;

/**
 * Created by Niko.
 */

public class User {

    private String mName;
    private Integer mMaxKCal;

    public User(String name) {
        mName = name;
    }

    public User(String name, Integer maxKCal) {
        mName = name;
        mMaxKCal = maxKCal;
    }

    public String getName() {
        return mName;
    }

    public Integer getMaxKCal() {
        return mMaxKCal;
    }

    public void setMaxKCal(Integer maxKCal) {
        mMaxKCal = maxKCal;
    }
}
