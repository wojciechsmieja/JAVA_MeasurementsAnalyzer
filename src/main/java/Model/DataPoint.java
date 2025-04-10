/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;
/**
 *
 * @author Wojciech Smieja
 * @version 3.0
 */
/**
 * Represents a single measurement data point, which includes
 * the measurement date and its corresponding value.
 */
public class DataPoint {
/**
     * The date when the measurement was taken.
     * This field stores the specific time of the measurement
     * and is used for tracking the temporal context of the data.
     */
    //private Date date;
    /**
     * The value of the measurement.
     * This field stores the numeric value associated with
     * the measurement taken at the specified date.
     */
    //private double value;
    
    DataPointRecord recordData;
    
    
    public DataPoint(LocalDate enterDate, double enterValue){
        recordData = new DataPointRecord(enterDate,enterValue);
        //this.date = enter_Date;
        //this.value = enter_Value;
    }
    //getter
    public LocalDate getDate(){
        return recordData.Data();
    }
    public double getValue(){
        return recordData.Value();
    }
    
}
