module com.example.mengerspongefractal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mengerspongefractal to javafx.fxml;
    exports com.example.mengerspongefractal;
}