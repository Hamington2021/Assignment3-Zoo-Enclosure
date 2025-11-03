module com.example.zoostructure {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zoostructure to javafx.fxml;
    exports com.example.zoostructure;
    exports com.example.zoostructure.Controllers;
    opens com.example.zoostructure.Controllers to javafx.fxml;
    exports com.example.zoostructure.Model;
    opens com.example.zoostructure.Model to javafx.fxml;
}