package com.example.f23comp1011tasks1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CreateTaskController {

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Label msgLabel;

    @FXML
    private Spinner<Integer> severitySpinner;

    @FXML
    private TextField titleTextField;

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    void submitTask(ActionEvent event) {

    }

}
