package com.hit.driver;

import com.hit.view.MainView;

import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        MainView view = new MainView();
        view.start(primaryStage);
    } // The Employee password is "Aa123456"
}



