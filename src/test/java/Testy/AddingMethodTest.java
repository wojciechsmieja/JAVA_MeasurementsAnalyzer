package Testy;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author SuperStudent-PL
 */
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.ParameterizedTest;
import Model.MeasurementException;
import Model.Measurement;
import java.time.LocalDate;
import java.util.stream.*;



public class AddingMethodTest {
      
    static Stream<Arguments> correctDatesForParametrizedTest() {
       return Stream.of(
            Arguments.of(LocalDate.of(2020, 10, 11), 25.5, LocalDate.of(1999, 12, 11), 19.2, LocalDate.of(2001, 12, 11), 11.4)
        );
    }
    /**
     * Test checks if method {@link Measurement#addTemperatureMeasurement(String)} 
     * work for correct arguments
     * 
     * Working:
     * Test add THREE temperature measurement by invoking
     * method addTemperatureMeasurement with correct data types and values
     * 
     * Expected results:
     * Method should work without exception because lists are not empty 
     * and data types are correct
     * If exception occur, it is for unknown reasons, 
     * then call function assertEquals, which compare communicates
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param date1 - Date of first measurement
     * @param value1 - value of fisrt measurement
     * @param date2 - Date of second measurement
     * @param value2 - value of second measurement
     * @param date3 - Date of third measurement
     * @param value3 - value of third measurement
     * @throws MeasurementException for unknown reasons
     */
    @ParameterizedTest
    @MethodSource("correctDatesForParametrizedTest")
    public void AddCorrectTemperatureDatesTest(LocalDate date1, double value1,LocalDate date2, double value2,LocalDate date3, double value3){
        try{
            Measurement _measurement = new Measurement();
            _measurement.addTemperatureMeasurement(date1, value1);
            _measurement.addTemperatureMeasurement(date2, value2);
            _measurement.addTemperatureMeasurement(date3, value3);
            assertEquals(3,_measurement.getTemperatures().size(),"Now temperature size should be 3");
        }catch (MeasurementException e){
            fail("Something bad happen while adding temperatures");
        }
    }
//---------------------------------------------------------------------------------------
    /**
     * Test checks if method {@link Measurement#addTemperatureMeasurement(String)} 
     * work for correct arguments
     * 
     * Working:
     * Test add three pressure measurement by invoking
     * method addPressureMeasurement with correct data types and values
     * 
     * Expected results:
     * Method should work without exception because lists are not empty 
     * and data types are correct
     * If exception occur, it is for unknown reasons, 
     * then call function assertEquals, which compare communicates
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param date1 - Date of first measurement
     * @param value1 - value of fisrt measurement
     * @param date2 - Date of second measurement
     * @param value2 - value of second measurement
     * @param date3 - Date of third measurement
     * @param value3 - value of third measurement
     * @throws MeasurementException for unknown reasons
     */    
    @ParameterizedTest
      @MethodSource("correctDatesForParametrizedTest")
      public void AddCorrectPressureDatesTest(LocalDate date1, double value1,LocalDate date2, double value2,LocalDate date3, double value3){
          try{
              Measurement _measurement = new Measurement();
              _measurement.addPressureMeasurement(date1, value1);
              _measurement.addPressureMeasurement(date2, value2);
              _measurement.addPressureMeasurement(date3, value3);
              assertEquals(3,_measurement.getPressures().size(),"Now temperature size should be 3");
          }catch (MeasurementException e){
              fail("Something bad happen while adding pressures");
          }
      }
//---------------------------------------------------------------------------------------
    static Stream<LocalDate> incorrectDatesForParametrizedTest(){
        return Stream.of(LocalDate.of(2027,10,11),LocalDate.of(3000,12,11));
    }
    /**
     * Test checks if method {@link Measurement#addTemperatureMeasurement(String)} 
     * work for incorrect dates
     * 
     * Working:
     * Test add three temperature measurement by invoking
     * method addTemperatureMeasurement with incorrect data types and correct values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided dates 
     * do not match the valid data requirements
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param date - Date of measurement
     * @throws MeasurementException because date is a future
     */    
    @ParameterizedTest
    @MethodSource("incorrectDatesForParametrizedTest")
    public void AddIncorrectTemperatureDatesTest(LocalDate date){
        try{
            Measurement _measurement = new Measurement();
            double value = 25.5;
            _measurement.addTemperatureMeasurement(date, value);
            fail("Exception shoould be thrown when date is a future");
        }catch (MeasurementException e){
            assertEquals("Error: Cannot add measurement from future", e.getMessage());
            
        }
    }
//---------------------------------------------------------------------------------------
    /**
     * Test checks if method {@link Measurement#addPressureMeasurement(String)} 
     * work for incorrect dates
     * 
     * Working:
     * Test add three pressure measurement by invoking
     * method addPressureMeasurement with incorrect data types and correct values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided dates 
     * do not match the valid data requirements
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param date - Date of measurement
     * @throws MeasurementException because date is a future
     */    
    @ParameterizedTest
    @MethodSource("incorrectDatesForParametrizedTest")
    public void AddIncorrectPressureDatesTest(LocalDate date){
        try{
            Measurement _measurement = new Measurement();
            double value = 25.5;
            _measurement.addPressureMeasurement(date, value);
            fail("Exception shoould be thrown when date is a future");
        }catch (MeasurementException e){
            assertEquals("Error: Cannot add measurement from future", e.getMessage());            
        }
    }
//---------------------------------------------------------------------------------------
    /**
     * Test checks if method {@link Measurement#addPressureMeasurement(String)} 
     * work for incorrect dates
     * 
     * Working:
     * Test add one temperature and pressure measurement each and  by invoking
     * method addPressureMeasurement and addPressureMeasurement with incorrect data types and correct values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided dates 
     * do not match the valid data requirements
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @throws MeasurementException because date is a null
     */  
    @Test
    public void AddNullTemperatureAndPressureDates(){
        try{
            Measurement _measurement = new Measurement();
            double value = 25.5;
            _measurement.addTemperatureMeasurement(null, value);
            _measurement.addPressureMeasurement(null, value);
            fail("Exception shoould be thrown when date is a null");
        }catch (MeasurementException e){
            assertEquals("Error: Date cannot be null", e.getMessage());            
        }        
    }
//---------------------------------------------------------------------------------------
    /**
     * Test checks if method {@link Measurement#addTemperatureMeasurement(String)} 
     * work for duplicate dates and values
     * 
     * Working:
     * Test add two temperature measurement by invoking
     * method addTemperatureMeasurement with same dates and values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the second measurement 
     * cannot be enered to database because it is a duplicate
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param date1 - Date of measurement
     * @param value1 - value of measurement
     * @throws MeasurementException because measurement is a duplicate
     */  
    static Stream<Arguments> duplicateValuesForParametrizedTest() {
        return Stream.of(
            Arguments.of(LocalDate.of(2023, 10, 11), 25.5, LocalDate.of(2023, 10, 11), 25.5), // ten sam dzień, ta sama wartość
            Arguments.of(LocalDate.of(2023, 10, 12), 30.0, LocalDate.of(2023, 10, 12), 30.0)  // inny dzień, ale również duplikat
        );
    }
    @ParameterizedTest
    @MethodSource("duplicateValuesForParametrizedTest")
    public void AddDuplicateTemperatureValuesTest(LocalDate date1, double value1, LocalDate date2, double value2) {
        Measurement _measurement = new Measurement();
        try {
            _measurement.addTemperatureMeasurement(date1, value1);
            _measurement.addTemperatureMeasurement(date2, value2);
            fail("Exception should be thrown when adding duplicate measurements");
        } catch (MeasurementException e) {
            assertEquals("Error: Entered measurement is a duplicate", e.getMessage());
        }
    }

//---------------------------------------------------------------------------------------
    /**
     * Test checks if method {@link Measurement#addPressureMeasurement(String)} 
     * work for duplicate dates and values
     * 
     * Working:
     * Test add two pressure measurement by invoking
     * method addPressureMeasurement with same dates and values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the second measurement 
     * cannot be enered to database because it is a duplicate
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param date1 - Date of measurement
     * @param value1 - value of measurement
     * @throws MeasurementException because measurement is a duplicate
     */  
    @ParameterizedTest
    @MethodSource("duplicateValuesForParametrizedTest")
    public void AddDuplicatePressureValuesTest(LocalDate date1, double value1, LocalDate date2, double value2) {
        Measurement _measurement = new Measurement();
        try {
            _measurement.addPressureMeasurement(date1, value1);
            _measurement.addPressureMeasurement(date2, value2);
            fail("Exception should be thrown when adding duplicate measurements");
        } catch (MeasurementException e) {
            assertEquals("Error: Entered measurement is a duplicate", e.getMessage());
        }
    }
//---------------------------------------------------------------------------------------
    static Stream<Double> correctValuesForParametrizedTemperatureTest(){
        return Stream.of(1.0,12.2,44.7);
    } 
    /**
     * Test checks if method {@link Measurement#addTemperatureMeasurement(String)} 
     * work for same dates but varius correct values
     * 
     * Working:
     * Test add three temperature measurement by invoking
     * method addTemperatureMeasurement with same dates and variuos correct values
     * 
     * Expected results:
     * Method should work without exception
     * If there is a Exception its from unknown reasons
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param date1 - Date of measurement
     * @param value1 - value of measurement
     * @throws MeasurementException from unknown reasons
     */  
    @ParameterizedTest
    @MethodSource("correctValuesForParametrizedTemperatureTest")
    public void AddCorrectTemperatureValuesTest(double value){
        try{
            Measurement _measurement = new Measurement();
            LocalDate date = LocalDate.now();
            _measurement.addTemperatureMeasurement(date, value);
        }catch (MeasurementException e){
            fail("Something unexpected hapenned while adding correct values...not glamour");
        }
    }   
//---------------------------------------------------------------------------------------
    static Stream<Double> incorrectValuesForParametrizedTemperatureTest(){
        return Stream.of(-300.0,-1000.0);
    } 
    /**
     * Test checks if method {@link Measurement#addTemperatureMeasurement(String)} 
     * work for same dates but varius incorrect values
     * 
     * Working:
     * Test add three temperature measurement by invoking
     * method addTemperatureMeasurement with same dates and varoius incorrect values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided measurement 
     * values do not match the requirements
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param value - value of measurement
     * @throws MeasurementException because values are below -273.0
     */ 
    @ParameterizedTest
    @MethodSource("incorrectValuesForParametrizedTemperatureTest")
    public void AddIncorrectTemperatureValuesTest(double value){
        try{
            Measurement _measurement = new Measurement();
            LocalDate date = LocalDate.now();
            _measurement.addTemperatureMeasurement(date, value);
            fail("Exception should be thrown when values are smaller than -273");
        }catch (MeasurementException e){
            assertEquals("Error: Temperature cannot be below -273C",e.getMessage());
        }
    }   
//--------------------------------------------------------------------------------------
    static Stream<Double> correctValuesForParametrizedPressureTest(){
        return Stream.of(999.0,1200.2,4400.7);
    } 
    /**
     * Test checks if method {@link Measurement#addPressureMeasurement(String)} 
     * work for same dates but varius correct values
     * 
     * Working:
     * Test add three pressure measurement by invoking
     * method addPressureMeasurement with same dates and values
     * 
     * Expected results:
     * Method should work without exception
     * If there is a Exception its from unknown reasons
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param value - value of measurement
     * @throws MeasurementException from unknown reasons
     */  
    @ParameterizedTest
    @MethodSource("correctValuesForParametrizedPressureTest")
    public void AddCorrectPressureValuesTest(double value){
        try{
            Measurement _measurement = new Measurement();
            LocalDate date = LocalDate.now();
            _measurement.addPressureMeasurement(date, value);
        }catch (MeasurementException e){
            fail("Something unexpected hapenned while adding correct pressure values...not glamour");
        }
    }  
//--------------------------------------------------------------------------------------
    static Stream<Double> incorrectValuesForParametrizedPressureTest(){
        return Stream.of(-999.0,-0.1,-10000.0);
    }    
    /**
     * Test checks if method {@link Measurement#addPressureMeasurement(String)} 
     * work for same dates but varius incorrect values
     * 
     * Working:
     * Test add three temperature measurement by invoking
     * method addPressureMeasurement with same dates and varoius incorrect values
     * 
     * Expected results:
     * Method should throw a {@link MeasurementException} because the provided measurement 
     * values do not match the requirements (Cannot be below 0)
     * The exception message is compared to the expected error message using {@code assertEquals}
     * 
     * Implemented by parametrizing the test using {@code @MethodSource} with valid data types
     * @param value - value of measurement
     * @throws MeasurementException because values are below 0.0
     */ 
    @ParameterizedTest
    @MethodSource("incorrectValuesForParametrizedPressureTest")
    public void AddIncorrectPressureValuesTest(double value){
        try{
            Measurement _measurement = new Measurement();
            LocalDate date = LocalDate.now();
            _measurement.addPressureMeasurement(date, value);
            fail("Exception should be thrown when values are smaller or equal 0");
        }catch (MeasurementException e){
            assertEquals("Error: PressureValue cannot be below 0", e.getMessage());
        }
    }
//     @Test
//    public void TestAddingTemperatureSizeMethod(){
//        try{
//            Measurement _measurement = new Measurement();
//            LocalDate date = new Date();
//            double value = 25.5;
//            _measurement.addTemperatureMeasurement(date, value);
//            assertEquals(_measurement.getTemperatures().size(),1,"Now temperature size should be 1");
//        }catch (MeasurementException e){
//            fail("Something bad happened while adding temperature measurement");
//        }
//    }
}
