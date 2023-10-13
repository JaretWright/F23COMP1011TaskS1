package com.example.f23comp1011tasks1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TableViewController {

    @FXML
    private Label avgDaysOverDueLabel;

    @FXML
    private CheckBox createdCheckBox;

    @FXML
    private CheckBox doneCheckBox;

    @FXML
    private CheckBox inProgressCheckBox;

    @FXML
    private Label tasksShowingLabel;

    @FXML
    private TextField filterTextField;

    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;

    @FXML
    private TableColumn<Task, Status> statusColumn;

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, Integer> taskIdColumn;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, User> userColumn;

    private ArrayList<Task> allTasks;

    @FXML
    void initialize()
    {
        allTasks = DBUtility.getTasksFromDB();

        //configure the tableview columns to know where to find their data
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));//calls Task.getDueDate()
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        tableView.getItems().addAll(allTasks);
        updateLabels();

        //configure the TextField to have a listener
        //this is an anymous inner class
        filterTextField.textProperty().addListener((observableValue, oldValue, searchTerm) -> {
            filterApplied(searchTerm);
        }
        );

        //Configure the CheckBoxes to be selected on startup and have listeners attached
        createdCheckBox.setSelected(true);
        inProgressCheckBox.setSelected(true);
        doneCheckBox.setSelected(true);

        createdCheckBox.addEventHandler(ActionEvent.ACTION, event -> {
            filterApplied(filterTextField.getText());
        });

        inProgressCheckBox.addEventHandler(ActionEvent.ACTION, event -> {
            filterApplied(filterTextField.getText());
        });

        doneCheckBox.addEventHandler(ActionEvent.ACTION, event -> {
            filterApplied(filterTextField.getText());
        });
    }

    private void filterApplied(String searchTerm)
    {
        tableView.getItems().clear();
        //This is using a simple loop over a collection
//        for (Task task : allTasks)
//        {
//            if (task.contains(searchTerm, createdCheckBox.isSelected(),
//                                        inProgressCheckBox.isSelected(), doneCheckBox.isSelected()))
//                tableView.getItems().add(task);
//        }

        //this is using a stream to achieve the same result in 1 line of code
        tableView.getItems().addAll(allTasks.stream()
                                            .filter(task -> task.contains(searchTerm, createdCheckBox.isSelected(),
                                                                inProgressCheckBox.isSelected(), doneCheckBox.isSelected()))
                                            .toList());
        updateLabels();
    }

    @FXML
    void viewCharts(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "charts-view.fxml");
    }

    private void updateLabels()
    {
        tasksShowingLabel.setText("Tasks Showing: " + tableView.getItems().size());
        avgDaysOverDueLabel.setText(String.format("Avg Days Over Due: %.1f", -tableView.getItems().stream()       //stream<Task>
                                                                .filter(task -> task.getStatus() != Status.DONE)  //stream<Task>
                                                                .filter(task -> task.getDaysUntilDue() < 0)       //stream<Task>
                                                                .mapToLong(task -> task.getDaysUntilDue()         //stream<long>
                                                                ).average().orElse(0)));

    }




}
