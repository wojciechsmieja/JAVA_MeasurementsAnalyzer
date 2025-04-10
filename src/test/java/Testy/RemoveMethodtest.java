package Testy;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wojci
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.ParameterizedTest;
import Model.MeasurementException;
import Model.Measurement;
import java.time.LocalDate;
import java.util.stream.*;
public class RemoveMethodtest {

    static Stream<Arguments> correctValuesForRemoveTest(){
        return Stream.of(Arguments.of("temperatures",0,"pressures",0));
    }
    /**
     * Test checks if method {@link Measurement#removeMeasurement(String, int)} 
     * work for correct arguments
     * 
     * Working:
     * Test add one measurement each to temperature list and pressure list 
     * then invokes method removeMeasurement with correct data types
     * 
     * Expected results:
     * Method should work without exception because lists are not empty 
     * and data types are correct
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param data1 - Type of data for which list method should work
     * @param index1 - Intiger value provides which index from list should be ereased
     * @param data2 - Type of data for which list method should work
     * @param index2 - Intiger value provides which index from list should be ereased
     * @throws MeasurementException for unknown reasons
     */
    @ParameterizedTest
    @MethodSource("correctValuesForRemoveTest")   
    public void CorrectValuesRemoveTest(String data1, int index1, String data2, int index2){
        Measurement _measurement = new Measurement();
        try{
            _measurement.addTemperatureMeasurement(LocalDate.now(), 1.1);
            _measurement.addPressureMeasurement(LocalDate.now(), 999.1);
            _measurement.removeMeasurement(data1, index1);
            _measurement.removeMeasurement(data2,index2);
        }catch (MeasurementException e){
            fail("Something bad happened while removing measurement");
        }
    }
//------------------------------------------------------------------------------------------------
    static Stream<Arguments> incorrectTypesForRemoveTest(){
        return Stream.of(Arguments.of("temperature",0,"pressure",0));
    }  
    /**
     * Test checks if method {@link Measurement#removeMeasurement(String)} 
     * work for incorrect arguments
     * 
     * Working:
     * Test add one measurement each to temperature list and pressure list 
     * then invokes method removeMeasurement with incorrect data types
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided arguments 
     * do not match the valid data types ("temperatures" or "pressures")
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with invalid data types
     * @param data1 - Type of data for which list method should work
     * @param index1 - Intiger value provides which index from list should be ereased
     * @param data2 - Type of data for which list method should work
     * @param index2 - Intiger value provides which index from list should be ereased
     * @throws MeasurementException because provided arguments are incorrect
     */  
    @ParameterizedTest
    @MethodSource("incorrectTypesForRemoveTest")   
    public void IncorrectTypesRemoveTest(String data1, int index1, String data2, int index2){
        Measurement _measurement = new Measurement();
        try{
            _measurement.addTemperatureMeasurement(LocalDate.now(), 1.1);
            _measurement.addPressureMeasurement(LocalDate.now(), 999.1);
            _measurement.removeMeasurement(data1, index1);
            _measurement.removeMeasurement(data2,index2);
            fail("Should throw exception because types are incorrect");
        }catch (MeasurementException e){
            assertEquals("Invalid list type",e.getMessage());
        }
    }
//---------------------------------------------------------------------------------------------
    static Stream<Arguments> incorrectIndexForTemperatureRemoveTest(){
        return Stream.of(Arguments.of("temperatures",12,"temperatures",22));
    }  
    /**
     * Test checks if method {@link Measurement#removeMeasurement(String)} 
     * work for incorrect arguments
     * 
     * Working:
     * Test add two measurements to temperature list  
     * then invokes method removeMeasurement with incorrect index values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided 
     * index values are out of bound - there's no enough entered measurements in list
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with invalid data types
     * @param data1 - Type of data for which list method should work
     * @param index1 - Intiger value provides which index from list should be ereased
     * @param data2 - Type of data for which list method should work
     * @param index2 - Intiger value provides which index from list should be ereased
     * @throws MeasurementException because provided index values are out of bound
     */  
    @ParameterizedTest
    @MethodSource("incorrectIndexForTemperatureRemoveTest") 
    public void IncorrectIndexTemperatureRemoveTest(String data1, int index1, String data2, int index2){
        Measurement _measurement = new Measurement();
        try{
            _measurement.addTemperatureMeasurement(LocalDate.now(), 1.1);
            _measurement.addTemperatureMeasurement(LocalDate.now(), 999.1);
            _measurement.removeMeasurement(data1, index1);
            _measurement.removeMeasurement(data2,index2);
            fail("Should throw exception because index are out of border");
        }catch (MeasurementException e){
            assertEquals("Index out of bound for "+data1+" list",e.getMessage());
            assertEquals("Index out of bound for "+data2+" list",e.getMessage());
        }
    }
//---------------------------------------------------------------------------------------------
    static Stream<Arguments> incorrectIndexForPressureRemoveTest(){
        return Stream.of(Arguments.of("pressures",12,"pressures",22));
    }  
    /**
     * Test checks if method {@link Measurement#removeMeasurement(String)} 
     * work for incorrect arguments
     * 
     * Working:
     * Test add two measurements to pressure list  
     * then invokes method removeMeasurement with incorrect index values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided 
     * index values are out of bound - there's no enough entered measurements in list
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with invalid data types
     * @param data1 - Type of data for which list method should work
     * @param index1 - Intiger value provides which index from list should be ereased
     * @param data2 - Type of data for which list method should work
     * @param index2 - Intiger value provides which index from list should be ereased
     * @throws MeasurementException because provided index values are out of bound
     */  
    @ParameterizedTest
    @MethodSource("incorrectIndexForPressureRemoveTest") 
    public void IncorrectIndexPressureRemoveTest(String data1, int index1, String data2, int index2){
        Measurement _measurement = new Measurement();
        try{
            _measurement.addTemperatureMeasurement(LocalDate.now(), 1.1);
            _measurement.addTemperatureMeasurement(LocalDate.now(), 999.1);
            _measurement.removeMeasurement(data1, index1);
            _measurement.removeMeasurement(data2,index2);
            fail("Should throw exception because index are out of border");
        }catch (MeasurementException e){
            assertEquals("Index out of bound for "+data1+" list",e.getMessage());
            assertEquals("Index out of bound for "+data2+" list",e.getMessage());
        }
    }
}
