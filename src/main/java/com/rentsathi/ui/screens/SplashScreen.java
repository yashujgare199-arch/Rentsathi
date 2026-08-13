package com.rentsathi.ui.screens;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen {

    private final Stage stage;

    public SplashScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        // -----------------------------
        // Logo Circle
        // -----------------------------
        Circle logoCircle = new Circle(55);
        logoCircle.setFill(Color.web("#F59E0B"));

        Label logoText = new Label("R");
        logoText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        logoText.setTextFill(Color.WHITE);

        StackPane logo = new StackPane();
        logo.getChildren().addAll(logoCircle, logoText);

        // -----------------------------
        // Application Name
        // -----------------------------
        Label title = new Label("RentSathi");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        title.setTextFill(Color.WHITE);

        // -----------------------------
        // Tagline
        // -----------------------------
        Label tagline = new Label("Rent Smart. Live Easy.");
        tagline.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        tagline.setTextFill(Color.LIGHTGRAY);

        // -----------------------------
        // Loading text
        // -----------------------------
        Label loading = new Label("Loading...");
        loading.setFont(Font.font("Arial", 14));
        loading.setTextFill(Color.GRAY);

        // -----------------------------
        // Content
        // -----------------------------
        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);

        content.getChildren().addAll(
                logo,
                title,
                tagline,
                loading
        );

        // -----------------------------
        // Background
        // -----------------------------
        Rectangle background = new Rectangle();

        background.setFill(Color.web("#111111"));

        background.widthProperty().bind(stage.widthProperty());
        background.heightProperty().bind(stage.heightProperty());

        // -----------------------------
        // Root
        // -----------------------------
        StackPane root = new StackPane();

        root.getChildren().addAll(
                background,
                content
        );

        // -----------------------------
        // Scene
        // -----------------------------
        Scene scene = new Scene(root, 1000, 650);

        stage.setTitle("RentSathi");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        // -----------------------------
        // Fade In Animation
        // -----------------------------
        FadeTransition fadeIn = new FadeTransition(
                Duration.seconds(1.2),
                content
        );

        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        fadeIn.play();

        // -----------------------------
        // Wait before next screen
        // -----------------------------
        PauseTransition pause = new PauseTransition(
                Duration.seconds(2.5)
        );

        pause.setOnFinished(event -> {
            showNextScreen();
        });

        pause.play();
    }

    private void showNextScreen() {

        // Login screen will be created next.
        // For now, keep a simple message.

        Label message = new Label("RentSathi is ready!");
        message.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        message.setTextFill(Color.WHITE);

        StackPane root = new StackPane(message);
        root.setStyle("-fx-background-color: #111111;");

        Scene scene = new Scene(root, 1000, 650);

        stage.setScene(scene);
    }
}