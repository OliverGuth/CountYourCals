package fhdw.bg.bfwi314b.countyourcals.gui;

import java.io.Serializable;

/**
 * Created by Katja Müller
 */

public enum ManagerState implements Serializable {
    //needs to implement Serializable interface in order to be saved and reloaded on instance state is changed
    FoodState,
    MealsState,
    UnitsState
}
