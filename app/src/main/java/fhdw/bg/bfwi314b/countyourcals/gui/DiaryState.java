package fhdw.bg.bfwi314b.countyourcals.gui;

import java.io.Serializable;

/**
 * Created by Katja Müller
 */

public enum DiaryState implements Serializable{
    //needs to implement Serializable interface in order to be saved and reloaded on instance state is changed
    DayState,
    WeekState,
    MonthState,
    AllState
}
