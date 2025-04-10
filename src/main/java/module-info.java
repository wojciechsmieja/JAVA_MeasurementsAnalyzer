module com.mycompany.measurementappgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    //requires lombok;

    opens Model to javafx.base;
    exports Model;
    opens com.mycompany.measurementappgui to javafx.fxml;
    exports com.mycompany.measurementappgui;
}
