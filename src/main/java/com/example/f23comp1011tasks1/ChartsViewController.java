package com.example.f23comp1011tasks1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class ChartsViewController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private void initialize()
    {
//        XYChart.Series<String, Integer> createdSeries = new XYChart.Series<>();
//        createdSeries.getData().add(new XYChart.Data<>("Jun",254));
//        createdSeries.getData().add(new XYChart.Data<>("Jul",373));
//        createdSeries.getData().add(new XYChart.Data<>("Aug",331));
//        createdSeries.getData().add(new XYChart.Data<>("Sept",318));
//        createdSeries.getData().add(new XYChart.Data<>("Oct",45));
//        createdSeries.setName("CREATED");
//
//        XYChart.Series<String, Integer> inProgressSeries = new XYChart.Series<>();
//        inProgressSeries.getData().add(new XYChart.Data<>("Jun",81));
//        inProgressSeries.getData().add(new XYChart.Data<>("Jul",94));
//        inProgressSeries.getData().add(new XYChart.Data<>("Aug",86));
//        inProgressSeries.getData().add(new XYChart.Data<>("Sept",86));
//        inProgressSeries.getData().add(new XYChart.Data<>("Oct",15));
//        inProgressSeries.setName("INPROGRESS");

        barChart.getData().addAll(DBUtility.getBarChartSeries(Status.CREATED));
        barChart.getData().addAll(DBUtility.getBarChartSeries(Status.INPROGRESS));
        barChart.getData().addAll(DBUtility.getBarChartSeries(Status.DONE));
    }

    @FXML
    void viewTables(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "table-view.fxml");
    }
}

