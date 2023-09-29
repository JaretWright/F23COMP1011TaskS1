package com.example.f23comp1011tasks1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("create-task-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Task Tamer 3000");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/icon.jpg")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}