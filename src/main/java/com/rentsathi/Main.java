package com.rentsathi;

import javafx.application.Application;
import javafx.stage.Stage;
import com.rentsathi.ui.screens.SplashScreen;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SplashScreen.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}