package com.example.f23comp1011tasks1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TableViewController {

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

        //configure the TextField to have a listener
        //this is an anymous inner class
        filterTextField.textProperty().addListener((observableValue, oldValue, newValue) ->{
                System.out.printf("Old Value: %s, New Value: %s%n", oldValue, newValue);}
        );
    }
    @FXML
    void viewCharts(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "charts-view.fxml");
    }
}
