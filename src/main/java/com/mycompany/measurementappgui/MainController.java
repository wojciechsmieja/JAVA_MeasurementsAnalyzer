/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.measurementappgui;

import Model.DataPoint;
import Model.MeasurementType;
import Model.MeasurementException;
import Model.Measurement;
import java.text.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.KeyCharacterCombination;
//import javafx.scene.input.KeyCombination;
//import javafx.scene.input.Mnemonic;
/**
 * FXML Controller class
 *
 * @author Wojciech Smieja
 * @version 3.0
 */
public class MainController{
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField ValueField;
    @FXML
    private DatePicker DateField;
    //private Button AddMeasurementButton;
    @FXML
    private ChoiceBox<MeasurementType> ChoiceType;
    private ObservableList<DataPoint> data;
    private Measurement _measurement = new Measurement();
    @FXML
    private ChoiceBox<MeasurementType> ChoiceTypeForResult;
    @FXML
    private ChoiceBox<String> ChoiceAnalysis;
    @FXML
    private Label ResultOfAnalysis;
    private final Map<String, Supplier<Double>> analysisMap = new HashMap<>();
    //KeyCombination kp = new KeyCharacterCombination("P",KeyCombination.ALT_DOWN);
    
    @FXML
    public void initialize() {
       // Mnemonic mn = new Mnemonic(AddMeasurementButton,kp);
        //checkboxes
        ObservableList<MeasurementType> choices = FXCollections.observableArrayList(MeasurementType.values());
        ObservableList<MeasurementType> choicesForResult = FXCollections.observableArrayList(MeasurementType.values());
        ObservableList<String> choicesOfAnalysis = FXCollections.observableArrayList("Average", "Max", "Min", "Median");
        ChoiceType.setItems(choices);
        ChoiceTypeForResult.setItems(choicesForResult);
        ChoiceAnalysis.setItems(choicesOfAnalysis);
        ChoiceType.setValue(MeasurementType.TEMPERATURE);
        ChoiceTypeForResult.setValue(MeasurementType.TEMPERATURE);
        ChoiceAnalysis.setValue("Average");
        //mapa
        listTempMeasurements();
    }
    private void initializeMap(){
        analysisMap.put("Temperature-Average", () -> {
        try {
            return _measurement.calculateAverage("temperatures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
        analysisMap.put("Temperature-Max", () -> {
        try {
            return _measurement.findMax("temperatures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
        analysisMap.put("Temperature-Min", () -> {
        try {
            return _measurement.findMin("temperatures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
        analysisMap.put("Temperature-Median", () -> {
        try {
            return _measurement.findMedian("temperatures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
        analysisMap.put("Pressure-Average", () -> {
        try {
            return _measurement.calculateAverage("pressures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
        analysisMap.put("Pressure-Max", () -> {
        try {
            return _measurement.findMax("pressures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
        analysisMap.put("Pressure-Min", () -> {
        try {
            return _measurement.findMin("pressures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
        analysisMap.put("Pressure-Median", () -> {
        try {
            return _measurement.findMedian("pressures");
        } catch (MeasurementException e) {
            showAlert("Błąd", e.getMessage(), Alert.AlertType.WARNING);
            return 0.0; 
        }
        });
    }
    
    @FXML
    private TableView<DataPoint> temperatureTable;
    @FXML
    private TableColumn<DataPoint, Double> tempValueColumn;
    @FXML
    private TableColumn<DataPoint, Date> tempDateColumn;
    @FXML
    private TableView<DataPoint> pressureTable;
    @FXML
    private TableColumn<DataPoint, Double> presValueColumn;
    @FXML
    private TableColumn<DataPoint, Date> presDateColumn;
    @FXML
    private void listTempMeasurements(){
        data = FXCollections.observableArrayList(_measurement.getTemperatures());
        temperatureTable.setItems(data);
        tempValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        tempDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    @FXML
    private void listPresMeasurements(){
        data = FXCollections.observableArrayList(_measurement.getPressures());
        pressureTable.setItems(data);
        presValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        presDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    @FXML
    private void addNewMeasurements(ActionEvent event) throws ParseException{
        try {
            MeasurementType selectedType = ChoiceType.getValue();
            double newValue = Double.parseDouble(ValueField.getText());
            LocalDate selectedDate = DateField.getValue();
            
            if(selectedType==MeasurementType.TEMPERATURE){
                try{
                _measurement.addTemperatureMeasurement(selectedDate, newValue);
                }catch(MeasurementException e){
                    showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
                }
                listTempMeasurements();

            }else{
                try{
                    _measurement.addPressureMeasurement(selectedDate, newValue);
                }catch(MeasurementException e){
                    showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
                }
                listPresMeasurements();

            }            
        }catch(NumberFormatException nfE){
            showAlert("Invalid Input", "Please insert the proper information of a measurement!", Alert.AlertType.WARNING);
        } catch (Exception ex) {
            showAlert("Error", "Something went wrong!", Alert.AlertType.ERROR);
        }
        refreshInputs();
    }
    @FXML 
    private void ShowResult(ActionEvent event) throws MeasurementException{
        initializeMap();
        MeasurementType selectedType = ChoiceTypeForResult.getValue();
        String key = selectedType+"-"+ChoiceAnalysis.getValue();
        if(analysisMap.containsKey(key)){
            double result = analysisMap.get(key).get();
            ResultOfAnalysis.setText(""+result);
        }     
        analysisMap.clear();
    }
    @FXML
    private void deleteTempMeasurement(ActionEvent event) {
        int index = temperatureTable.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //data.remove(index);
            try{
                _measurement.removeMeasurement("temperatures", index);
            }catch (MeasurementException e){
                showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            }
            listTempMeasurements();
        }
    }
    @FXML
    private void deletePresMeasurement(ActionEvent event) {
        int index = pressureTable.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //data.remove(index);
            try{
                _measurement.removeMeasurement("pressures", index);
            }catch (MeasurementException e){
                showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            }
            listPresMeasurements();
        }
    }
    private void refreshInputs(){
        ValueField.setText("");
        DateField.setValue(null);
    }
    
}
