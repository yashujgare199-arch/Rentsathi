package com.rentsathi.ui.screens;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
public class SplashScreen {

    private static final String PRIMARY_BLUE = "#3657C8";
    private static final String DARK_BLUE = "#29476B";
    private static final String BORDER_PURPLE = "#7166E8";
    private static final String LIGHT_GREY = "#E5E8EF";

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: white;"
        );

        Rectangle border = new Rectangle();

        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.web(BORDER_PURPLE));
        border.setStrokeWidth(4);

        border.widthProperty().bind(root.widthProperty());
        border.heightProperty().bind(root.heightProperty());

        Image logoImage = new Image(
        SplashScreen.class
                .getResource("/images/logo.png")
                .toExternalForm()
);

        ImageView logo = new ImageView(logoImage);

        logo.setFitWidth(150);
        logo.setFitHeight(150);
        logo.setPreserveRatio(true);

        logo.setOpacity(0);
        logo.setScaleX(0.75);
        logo.setScaleY(0.75);

        Label title = new Label("RentSathi");

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 58px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + PRIMARY_BLUE + ";"
        );

        title.setOpacity(0);

        Label tagline = new Label("Rent Smart. Live Easy.");

        tagline.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 22px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        tagline.setOpacity(0);

        VBox centerContent = new VBox();

        centerContent.setAlignment(Pos.CENTER);
        centerContent.setSpacing(8);

        centerContent.getChildren().addAll(
                logo,
                title,
                tagline
        );

        centerContent.setTranslateY(-25);

        ProgressBar progressBar = new ProgressBar(0);

        progressBar.setPrefWidth(280);
        progressBar.setPrefHeight(5);

        progressBar.setStyle(
                "-fx-accent: #4969E8;" +
                "-fx-control-inner-background: " + LIGHT_GREY + ";" +
                "-fx-background-color: transparent;"
        );

        Label loadingText = new Label(
                "Loading RentSathi..."
        );

        loadingText.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 17px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        VBox loadingBox = new VBox();

        loadingBox.setAlignment(Pos.CENTER);
        loadingBox.setSpacing(12);

        loadingBox.getChildren().addAll(
                progressBar,
                loadingText
        );

        loadingBox.setTranslateY(-8);

        StackPane centerPane = new StackPane();

        centerPane.getChildren().add(centerContent);

        StackPane.setAlignment(
                centerContent,
                Pos.CENTER
        );

        root.setCenter(centerPane);
        root.setBottom(loadingBox);

        BorderPane.setAlignment(
                loadingBox,
                Pos.CENTER
        );

        StackPane finalRoot = new StackPane();

        finalRoot.getChildren().addAll(
                root,
                border
        );

              
        Scene scene = new Scene(
        finalRoot,
        1500,
        780
     );

        scene.setFill(Color.WHITE);

        stage.setTitle("RentSathi");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();

        playAnimation(
                logo,
                title,
                tagline,
                progressBar
        );
    }

    private static void playAnimation(
            ImageView logo,
            Label title,
            Label tagline,
            ProgressBar progressBar
    ) {

        FadeTransition logoFade =
                new FadeTransition(
                        Duration.millis(700),
                        logo
                );

        logoFade.setFromValue(0);
        logoFade.setToValue(1);

        ScaleTransition logoScale =
                new ScaleTransition(
                        Duration.millis(700),
                        logo
                );

        logoScale.setFromX(0.75);
        logoScale.setFromY(0.75);
        logoScale.setToX(1);
        logoScale.setToY(1);

        ParallelTransition logoAnimation =
                new ParallelTransition(
                        logoFade,
                        logoScale
                );

        FadeTransition titleFade =
                new FadeTransition(
                        Duration.millis(600),
                        title
                );

        titleFade.setFromValue(0);
        titleFade.setToValue(1);

        FadeTransition taglineFade =
                new FadeTransition(
                        Duration.millis(600),
                        tagline
                );

        taglineFade.setFromValue(0);
        taglineFade.setToValue(1);

        SequentialTransition intro =
                new SequentialTransition(
                        logoAnimation,
                        titleFade,
                        taglineFade
                );

        intro.play();

        Timeline loadingAnimation =
                new Timeline(

                        new KeyFrame(
                                Duration.ZERO,
                                event ->
                                        progressBar.setProgress(0)
                        ),

                        new KeyFrame(
                                Duration.millis(500),
                                event ->
                                        progressBar.setProgress(0.15)
                        ),

                        new KeyFrame(
                                Duration.millis(1000),
                                event ->
                                        progressBar.setProgress(0.35)
                        ),

                        new KeyFrame(
                                Duration.millis(1500),
                                event ->
                                        progressBar.setProgress(0.55)
                        ),

                        new KeyFrame(
                                Duration.millis(2000),
                                event ->
                                        progressBar.setProgress(0.75)
                        ),

                        new KeyFrame(
                                Duration.millis(2500),
                                event ->
                                        progressBar.setProgress(1.0)
                        )
                );

        loadingAnimation.setOnFinished(event -> {


            PauseTransition pause =
                new PauseTransition(
                    Duration.millis(300)
                );

            pause.setOnFinished(e ->
                WelcomeScreen.show(
                    (Stage) progressBar
                            .getScene()
                            .getWindow()
                )
            );

            pause.play();
        });

    loadingAnimation.play();
        
    }
}