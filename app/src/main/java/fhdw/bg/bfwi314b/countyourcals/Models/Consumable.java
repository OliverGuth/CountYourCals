package fhdw.bg.bfwi314b.countyourcals.Models;

import java.util.ArrayList;

/**
 * Created by Niko
 */

public interface Consumable {

    String getName();

    String toString();

    void addUnit(Unit unit);

    ArrayList<Unit> getUnits();

    Integer getKCal();

    String getType();

}
