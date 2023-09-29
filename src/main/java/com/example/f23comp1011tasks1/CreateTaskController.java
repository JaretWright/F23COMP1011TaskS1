package com.example.f23comp1011tasks1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTaskController implements Initializable {

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
        if (fieldsArePopulated())
        {
            //create a Task object
            String title = titleTextField.getText();
            String description = descriptionTextArea.getText();
            LocalDate dueDate = dueDatePicker.getValue();
            int severity = severitySpinner.getValue();
            User user = userComboBox.getValue();

            try {
                Task task = new Task(title, description, dueDate, severity, user);
                msgLabel.setText("Task created");
                //save the task to the DB

            } catch (IllegalArgumentException e)
            {
                msgLabel.setText(e.getMessage());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            msgLabel.setText("Please ensure ALL fields have a value");
    }

    private boolean fieldsArePopulated()
    {
        return !titleTextField.getText().isEmpty() &&
                !descriptionTextArea.getText().isEmpty() &&
                userComboBox.getValue() !=null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLabel.setText("");

        //load the combobox with users from the DB
        userComboBox.getItems().addAll(DBUtility.getUsersFromDB());

        //configure the due date to default to tomorrow
        dueDatePicker.setValue(LocalDate.now().plusDays(1));

        //configure the spinner to only allow the numbers 1, 2, 3
        //i: minimum value
        //i1: maximum value
        //i2: default value
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory
                                                            .IntegerSpinnerValueFactory(1, 3, 2);
        severitySpinner.setValueFactory(spinnerValueFactory);
    }
}
