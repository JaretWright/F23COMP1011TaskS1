module com.example.f23comp1011tasks1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.f23comp1011tasks1 to javafx.fxml;
    exports com.example.f23comp1011tasks1;
}