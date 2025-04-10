/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.util.ArrayList;
//import collections for a sort() function 
import java.util.Collections;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
/**
 * @author Wojciech Smieja
 * @version 3.0
 */

public class Measurement {
    /**
 * List of all temperature measurements collected by the application.
 * Each measurement is stored as a {@link DataPoint} object containing
 */
    private ArrayList<DataPoint> temperatures = new ArrayList<>();
    /**
 * List of all pressure measurements collected by the application.
 * Each measurement is stored as a {@link DataPoint} object containing
 */
    private ArrayList<DataPoint> pressures = new ArrayList<>();
    /**
 * Temporarily stores filtered temperature measurements based on a specified date range.
 * This list is updated each time the filter method is called and only includes
 */
    //private ArrayList<DataPoint> filteredTemperatures = new ArrayList<>();
    /**
 * Temporarily stores filtered pressure measurements based on a specified date range.
 * This list is updated each time the filter method is called and only includes
 */
    //private ArrayList<DataPoint> filteredPressures = new ArrayList<>();
    
    //constructor
    public Measurement() {   
        this.temperatures = new ArrayList<>();
        this.pressures = new ArrayList<>();
        //this.filteredTemperatures = new ArrayList<>();
        //this.filteredPressures = new ArrayList<>();
    }
    //getters
    public ArrayList<DataPoint> getTemperatures(){
        return temperatures;
    }
    public ArrayList<DataPoint> getPressures(){
        return pressures;
    }
    /**
    * Remove temperature or pressure measurements to the list.
    * Function remove specified object in the list transferred as number in order
    * @param type determines the type of data user want to remove; 
    *                 should be temperatures or pressures  
    * @param index the type of int - number in order determines which object should be removed; 
    *                 should be any int but not less than 0
    *                 
    * @throws  if index is out of bound .
    */    
    public void removeMeasurement(String type, int index)throws MeasurementException{
        if("temperatures".equals(type)){
            if(index<0 || index>=temperatures.size()){
                throw new MeasurementException("Index out of bound for "+type+" list");
            }
            temperatures.remove(index);
        }else if("pressures".equals(type)){
            if(index<0 || index>=pressures.size()){
                throw new MeasurementException("Index out of bound for "+type+" list");
            }
            pressures.remove(index);
        }else{
            throw new MeasurementException("Invalid list type");
        }
        
    }
    /**
    * Add temperature or pressure measurements to the list.
    * Function checks if there is already entered value and date of a measurement to avoid duplicate.
    * enterDate is converting to the Date object if it was not transferred as Date object
    * @param enter_date the type of data - Date of measurement; 
    *                 should be any date  
    * @param enter_value the type of double - value of the measurement; 
    *                 should be any double
     * @throws Model.MeasurementException
    *                 
    * @throws  if the date is null or not instance of Date class .
    */
    public void addTemperatureMeasurement(LocalDate enter_date, double enter_value)throws MeasurementException{
        LocalDate today = LocalDate.now();
        if(enter_date==null){
            throw new MeasurementException("Error: Date cannot be null");
        }
        if (!(enter_date instanceof LocalDate)) {
            throw new MeasurementException("Error: Date have to be a object of LocalDate");
        }
        if(enter_date.isAfter(today)){
            throw new MeasurementException("Error: Cannot add measurement from future");
        }        
        if(enter_value<-273){
            throw new MeasurementException("Error: Temperature cannot be below -273C");
        }
        for(DataPoint dp: temperatures){
            if(enter_date.equals(dp.getDate()) && enter_value==dp.getValue()){
                throw new MeasurementException("Error: Entered measurement is a duplicate");
            }
        }
        temperatures.add(new DataPoint(enter_date, enter_value));
    }
    public void addPressureMeasurement(LocalDate enter_date, double enter_value)throws MeasurementException{
        LocalDate today = LocalDate.now();
        if(enter_date==null){
            throw new MeasurementException("Error: Date cannot be null");
        }
        if (!(enter_date instanceof LocalDate)) {
            throw new MeasurementException("Error: Date have to be LocalDate object");
        }
        if(enter_date.isAfter(today)){
            throw new MeasurementException("Error: Cannot add measurement from future");
        }
        if(enter_value<0){
            throw new MeasurementException("Error: PressureValue cannot be below 0");
        }
        for(DataPoint dp: pressures){
            if(enter_date.equals(dp.getDate()) && enter_value==dp.getValue()){
                throw new MeasurementException("Error: Entered measurement is a duplicate");
            }
        }         
        //Date date = (Date) enter_date;
        pressures.add(new DataPoint (enter_date, enter_value));
    }
 /**
 * Calculate average value in the list of measurements for a specified data type.
 *
 * @param dataType the type of data to calculate average values; 
 *                 should be "temperatures" for temperature data 
 *                 or any other value for pressure data.
 * @return the average value found in the specified data type list.
 *         If dataType is "temperatures," it searches in the temperature list;
 *         otherwise, it searches in the pressure list.
     * @throws Model.MeasurementException
 * @throws  if the specified list is empty.
 */
    public double calculateAverage(String dataType)throws MeasurementException{
        if("temperatures".equals(dataType)){
           if(temperatures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
           return temperatures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .average()
                   .orElseThrow(()->new MeasurementException ("Cannot calculate "+dataType+" average"));
        }else if("pressures".equals(dataType)){
            if(pressures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
            return pressures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .average()
                   .orElseThrow(()->new MeasurementException ("Cannot calculate "+dataType+" average"));            
        }else{
            throw new MeasurementException("Invalid data type: " + dataType);
        }
    }
//    public double calculateAverageTemp()throws MeaserementException{
//        
//    }
    /**
 * Finds the maximum value in the list of measurements for a specified data type.
 *
 * @param dataType the type of data to find the maximum for; 
 *                 should be "temperatures" for temperature data 
 *                 or any other value for pressure data.
 * @return the maximum value found in the specified data type list.
 *         If dataType is "temperatures," it searches in the temperature list;
 *         otherwise, it searches in the pressure list.
     * @throws Model.MeasurementException
 * @throws  if the specified list is empty.
 */
    public double findMax(String dataType)throws MeasurementException{
        if("temperatures".equals(dataType)){
           if(temperatures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
           return temperatures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .max()
                   .orElseThrow(()->new MeasurementException ("Cannot calculate maximum "+dataType+" value"));
        }else if("pressures".equals(dataType)){
            if(pressures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
            return pressures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .max()
                   .orElseThrow(()->new MeasurementException ("Cannot calculate maximum "+dataType+" value"));            
        }else{
            throw new MeasurementException("Invalid data type: " + dataType);
        }
    }
        /**
 * Finds the minimum value in the list of measurements for a specified data type.
 *
 * @param dataType the type of data to find the minimum for; 
 *                 should be "temperatures" for temperature data 
 *                 or any other value for pressure data.
 * @return the minimum value found in the specified data type list.
 *         If dataType is "temperatures," it searches in the temperature list;
 *         otherwise, it searches in the pressure list.
     * @throws Model.MeasurementException
 * @throws  if the specified list is empty.
 */
    public double findMin(String dataType)throws MeasurementException{
        if("temperatures".equals(dataType)){
           if(temperatures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
           return temperatures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .min()
                   .orElseThrow(()->new MeasurementException ("Cannot calculate minimum "+dataType+" value"));
        }else if("pressures".equals(dataType)){
            if(pressures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
            return pressures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .min()
                   .orElseThrow(()->new MeasurementException ("Cannot calculate minimum "+dataType+" value"));            
        }else{
            throw new MeasurementException("Invalid data type: " + dataType);
        }
    }
        /**
 * Finds the median value in the list of measurements for a specified data type.
 *
 * @param dataType the type of data to find the median value; 
 *                 should be "temperatures" for temperature data 
 *                 or any other value for pressure data.
 * @return the median value found in the specified data type list.
 *         If dataType is "temperatures," it searches in the temperature list;
 *         otherwise, it searches in the pressure list.
     * @throws Model.MeasurementException
 * @throws  if the specified list is empty.
 */
    public double findMedian(String dataType)throws MeasurementException{
        List<Double> sortedValues;
        if("temperatures".equals(dataType)){
           if(temperatures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
           sortedValues = temperatures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .sorted()
                   .boxed()
                   .toList();
        }else if("pressures".equals(dataType)){
            if(pressures.isEmpty()){
                throw new MeasurementException(dataType+" list is empty");
            }
           sortedValues = pressures.stream()
                   .mapToDouble(dp->dp.getValue())
                   .sorted()
                   .boxed()
                   .toList();          
        }else{
            throw new MeasurementException("Invalid data type: " + dataType);
        }
        int size = sortedValues.size();
        if (size == 1) {
            return sortedValues.get(0); 
        } else if (size % 2 == 0) {
            return (sortedValues.get(size / 2 - 1) + sortedValues.get(size / 2)) / 2.0;
        } else if(size%2!=0){
            return sortedValues.get(size / 2);
        }else{
            throw new MeasurementException("Cannot calculate median "+dataType+" value");
        }
    }
 /**
 * Filters temperature and pressure measurements within the specified date range.
 * If either startDate or endDate is null, no filtering is applied, and all measurements are saved to original scope
 * @param startDate the type of data to begin the data's scope; 
 *                 should be any date for filter arrays 
 * @param endDate the type of data to end the data's scope; 
 *                 should be any date for filter arrays 
 *                 
 * @throws  if the specified list is empty.
 */
//    public void filterMeasurementByDate(Date startDate, Date endDate) {
//        filteredTemperatures.clear();
//        filteredPressures.clear();
//        
//        // Jeśli jedna z dat jest null, zwróć wszystkie pomiary
//        if (startDate == null || endDate == null) {
//            filteredTemperatures.clear();
//            filteredTemperatures.addAll(temperatures);
//            filteredPressures.clear();
//            filteredPressures.addAll(pressures);
//            return;
//        }
//            // Sprawdzamy, czy lista temperatures nie jest pusta
//        if (!temperatures.isEmpty()) {
//            System.out.println(temperatures.get(0).getDate());
//        } else {
//            System.out.println("Temperatures list is empty.");
//        }
//        System.out.println(startDate);
//        System.out.println(endDate);
//        for (DataPoint dp : temperatures) {
//            Date data = dp.getDate();
//            // Używamy compareTo do porównania dat
//
//            if (data.compareTo(startDate) >= 0 && data.compareTo(endDate) <= 0) {
//                filteredTemperatures.add(dp);
//            }
//        }
//
//        for (DataPoint dp : pressures) {
//            Date data = dp.getDate();
//            // Używamy compareTo do porównania dat
//            if (data.compareTo(startDate) >= 0 && data.compareTo(endDate) <= 0) {
//                filteredPressures.add(dp);
//            }
//        }
//
//        // Sprawdzenie, czy listy filtrujące są puste
//        if (filteredTemperatures.isEmpty()) {
//            System.out.println("No temperature measurements found in the specified date range.");
//        }
//        if (filteredPressures.isEmpty()) {
//            System.out.println("No pressure measurements found in the specified date range.");
//        }
//    }
    
}
