package fhdw.bg.bfwi314b.countyourcals.Models;

/**
 * Created by Fabian Schmitz
 */

public class User implements java.io.Serializable{

    private String mName;
    private Character mGender;
    private Integer mMaxKCal;
    private String mLanguage;

    //Constructor
    public User(String name, Character gender, Integer maxKCal, String language) {
        mName = name;
        mGender = gender;
        mMaxKCal = maxKCal;
        mLanguage = language;
    }

    //Returns the String-Representation
    public String toString() {
        return mName;
    }

    //Returns the Username as String
    public String getName() {
        return mName;
    }

    //Sets the Username
    public void setName(String name) { this.mName = name; }

    //Returns the Users gender as Character
    public Character getGender() {
        return mGender;
    }

    //Returns the Users MaxKCal as Integer
    public Integer getMaxKCal() {
        return mMaxKCal;
    }

    //Sets the Users MaxKCal
    public void setMaxKCal(Integer maxKCal) {
        mMaxKCal = maxKCal;
    }

    //Returns the Users Language as Sting
    public String getLanguage() {
        return mLanguage;
    }

    //Sets the Users Language
    public void setLanguage(String language) {
        mLanguage = language;
    }
}
