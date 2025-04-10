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
public class CalculatingAverageMethodTest {
    
    static Stream<String> correctTypesForAverage(){
        return Stream.of("temperatures", "pressures");
    }
    /**
     * Test checks if method {@link Measurement#findAverage(String)} work for correct arguments
     * 
     * Working:
     * Test add one measurement each to temperature list and pressure list 
     * then invokes method calculateAvegare with correct data types
     * 
     * Expected results:
     * Method should work without exception because lists are not empty 
     * and data types are correct
     * If exception occur, it is for unknown reasons 
     * then call function assertEquals, which compare communicates
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param type - Type of data for which the average is to be calculated
     * @throws MeasurementException for unknown reasons
     */
    @ParameterizedTest
    @MethodSource("correctTypesForAverage")
    public void CalculateAverageWithCorrectTypesNotEmptyTest(String type){
        Measurement _measurement = new Measurement();
        try{
            _measurement.addTemperatureMeasurement(LocalDate.now(), 0.0);
            _measurement.addPressureMeasurement(LocalDate.now(), 999);
            _measurement.calculateAverage(type);
        }catch(MeasurementException e){
            assertEquals("Cannot calculate "+type+" average", e.getMessage());
            fail("Something unexpected happened while calculating average values");
        }
    }
//---------------------------------------------------------------------------------
    static Stream<String> incorrectTypesForAverage(){
        return Stream.of("temperature", "nothing");
    }
    /**
    * Test checks if method {@link Measurement#findAverage(String)} 
    * work for incorrect arguments
    * 
    * Working:
    * Test add one measurement each to temperature list and pressure list 
    * then invokes method calculateAverage with incorrect data types
    * 
    * Expected results:
    * Method should throw a {@link MeasurementException} because the provided arguments 
    * do not match the valid data types ("temperatures" or "pressures")
    * The exception message is compared to the expected error message using {@code assertEquals}
    * 
    * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
    * @param type - Type of data for which the average is to be calculated
    * @throws MeasurementException because provided arguments are incorrect
    */
    @ParameterizedTest
    @MethodSource("incorrectTypesForAverage")
    public void CalculateAverageWithIncorrectTypesNotEmptyTest(String type){
        Measurement _measurement = new Measurement();
        try{
            _measurement.addTemperatureMeasurement(LocalDate.now(), 0.0);
            _measurement.addPressureMeasurement(LocalDate.now(), 999);
            _measurement.calculateAverage(type);
            fail("Exception should be thrown because method arguments - types are incorrect");
        }catch(MeasurementException e){
            assertEquals("Invalid data type: " + type,e.getMessage());
        }
    }
//----------------------------------------------------------------------------------------------
    /**
     * Test checks if method {@link Measurement#findAverage(String)} 
     * work without any entered measurements
     * 
     * Working:
     * Test do not add any of the measurements 
     * then invokes method calculateAverage with correct data types
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the mesurements lists are empty
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param type - Type of data for which the average is to be calculated
     * @throws MeasurementException because lists are empty
     */       
    @ParameterizedTest
    @MethodSource("correctTypesForAverage")
    public void CalculateAverageWithCorrectTypesButEmptyTest(String type){
        Measurement _measurement = new Measurement();
        try{
            _measurement.calculateAverage(type);
            fail("Exception should be thrown because lists are empty");            
        }catch(MeasurementException e){
            assertEquals(type+" list is empty",e.getMessage());
        }
    }
//------------------------------------------------------------------------------------------------
    static Stream<Arguments> correctValuesForAverageMethod() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(2020, 10, 11), 28.0,
                LocalDate.of(1999, 12, 11), 20.0,
                LocalDate.of(2001, 11, 11), 30.0
            )
        );
    }   
    /**
     * Test checks if method {@link Measurement#findAverage(String)} 
     * return correct value
     * 
     * Working:
     * Test add three measurements to temperature list  
     * then invokes method calculateAverage with correct data types
     * 
     * Expected results:
     * Test use {@code assertEquals} to compare expected value and returned value by method 
     * If Returned value is incorrect show communicate which contains expected value
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param type - Type of data for which the average is to be calculated
     */ 
    @ParameterizedTest
    @MethodSource("correctValuesForAverageMethod")
    public void CalculateAverageAfterAddingMeasurementsTemperatureTest(LocalDate date1, double value1, LocalDate date2, double value2,LocalDate date3, double value3) {
        try {
            Measurement _measurement = new Measurement();
            _measurement.addTemperatureMeasurement(date1,value1);
            _measurement.addTemperatureMeasurement(date2,value2);
            _measurement.addTemperatureMeasurement(date3,value3);
            double avgTemp = _measurement.calculateAverage("temperatures");
            assertEquals(26.0, avgTemp, "The minimum temperature should be 26.0");
        } catch (MeasurementException e) {
            fail("Unexpected exception during integration test");
        }
    }
//------------------------------------------------------------------------------------------------------
    /**
     * Test checks if method {@link Measurement#findAverage(String)} 
     * return correct value
     * 
     * Working:
     * Test add three measurements to pressure list  
     * then invokes method calculateAverage with correct data types
     * 
     * Expected results:
     * Test use {@code assertEquals} to compare expected value and returned value by method 
     * If Returned value is incorrect show communicate which contains expected value
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param type - Type of data for which the average is to be calculated
     */ 
    @ParameterizedTest
    @MethodSource("correctValuesForAverageMethod")
    public void CalculateAverageAfterAddingMeasurementsPressureTest(LocalDate date1, double value1, LocalDate date2, double value2,LocalDate date3, double value3) {
        try {
            Measurement _measurement = new Measurement();
            _measurement.addPressureMeasurement(date1,value1);
            _measurement.addPressureMeasurement(date2,value2);
            _measurement.addPressureMeasurement(date3,value3);
            double avgTemp = _measurement.calculateAverage("pressures");
            assertEquals(26.0, avgTemp, "The minimum pressure should be 26.0");
        } catch (MeasurementException e) {
            fail("Unexpected exception during integration test");
        }
    }

}
