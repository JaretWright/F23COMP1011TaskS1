package com.example.f23comp1011tasks1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TableViewController {

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
    }
    @FXML
    void viewCharts(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "charts-view.fxml");
    }





}
