/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Wojciech Smieja
 * @version 3.0
 */
 /**
 * Enum class is used for displaying values in Choiceboxes and is used in functions.
 * Every value has value accesible for an user
 */
public enum MeasurementType {

    TEMPERATURE("Temperature"),
    PRESSURE("Pressure");

    private final String displayName;

    MeasurementType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
