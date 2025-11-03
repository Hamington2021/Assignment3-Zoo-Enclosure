module com.example.zoostructure {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zoostructure to javafx.fxml;
    exports com.example.zoostructure;
}