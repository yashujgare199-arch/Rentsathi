package com.rentsathi;

import com.rentsathi.ui.screens.SplashScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        SplashScreen splashScreen = new SplashScreen(stage);

        splashScreen.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}