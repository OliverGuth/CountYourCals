package fhdw.bg.bfwi314b.countyourcals.gui;

import java.io.Serializable;

/**
 * Created by Sina Sander
 */

public enum StatisticsState implements Serializable {
    //needs to implement Serializable interface in order to be saved and reloaded on instance state is changed
    OneWeekState,
    TwoWeeksState,
    FourWeeksState
}
