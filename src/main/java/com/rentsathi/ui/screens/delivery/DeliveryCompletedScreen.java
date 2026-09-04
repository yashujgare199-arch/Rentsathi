
package com.rentsathi.ui.screens.delivery;

import com.rentsathi.model.rental.RentalRequest;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DeliveryCompletedScreen {

    private static final String BLUE = "#3657C8";
    private static final String GREEN = "#218739";
    private static final String TEXT = "#182235";
    private static final String MUTED = "#64748B";
    private static final String BACKGROUND = "#F8F9FD";

    public static void show(
            Stage stage,
            RentalRequest request) {

        StackPane root =
                new StackPane();

        root.setStyle(
                "-fx-background-color: "
                        + BACKGROUND
                        + ";"
        );

        VBox content =
                new VBox(18);

        content.setAlignment(
                Pos.CENTER
        );

        content.setPadding(
                new Insets(40)
        );

        // =====================================================
        // SUCCESS CIRCLE
        // =====================================================

        Circle circle =
                new Circle(65);

        circle.setFill(
                Color.web("#E4F7E8")
        );

        circle.setStroke(
                Color.web(GREEN)
        );

        circle.setStrokeWidth(3);

        // =====================================================
        // CHECK MARK
        // =====================================================

        Line check1 =
                new Line(
                        -25,
                        5,
                        -8,
                        22
                );

        Line check2 =
                new Line(
                        -8,
                        22,
                        28,
                        -22
                );

        check1.setStroke(
                Color.web(GREEN)
        );

        check2.setStroke(
                Color.web(GREEN)
        );

        check1.setStrokeWidth(7);
        check2.setStrokeWidth(7);

        check1.setStrokeLineCap(
                javafx.scene.shape.StrokeLineCap.ROUND
        );

        check2.setStrokeLineCap(
                javafx.scene.shape.StrokeLineCap.ROUND
        );

        StackPane successIcon =
                new StackPane();

        successIcon.getChildren().addAll(
                circle,
                check1,
                check2
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Delivery Completed!"
                );

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        // =====================================================
        // MESSAGE
        // =====================================================

        Label message =
                new Label(
                        "The rental has been successfully delivered."
                );

        message.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        // =====================================================
        // REQUEST ID
        // =====================================================

        String requestId =
                request != null
                        ? request.getRequestId()
                        : "N/A";

        Label requestLabel =
                new Label(
                        "Request ID: "
                                + requestId
                );

        requestLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button dashboard =
                new Button(
                        "Back to Dashboard"
                );

        dashboard.setPrefWidth(
                210
        );

        dashboard.setPrefHeight(
                42
        );

        dashboard.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        dashboard.setOnAction(
                event ->
                        DeliveryPartnerDashboard.show(
                                stage
                        )
        );

        content.getChildren().addAll(
                successIcon,
                title,
                message,
                requestLabel,
                dashboard
        );

        root.getChildren().add(
                content
        );

        Scene scene =
                new Scene(
                        root,
                        1200,
                        750
                );

        stage.setTitle(
                "RentSathi - Delivery Completed"
        );

        stage.setScene(scene);

        stage.setMinWidth(
                1000
        );

        stage.setMinHeight(
                650
        );

        stage.show();

        // =====================================================
        // ANIMATION
        // =====================================================

        successIcon.setScaleX(0);
        successIcon.setScaleY(0);
        title.setOpacity(0);
        message.setOpacity(0);
        requestLabel.setOpacity(0);
        dashboard.setOpacity(0);

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(550),
                        successIcon
                );

        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(1);
        scale.setToY(1);

        FadeTransition titleFade =
                new FadeTransition(
                        Duration.millis(450),
                        title
                );

        titleFade.setFromValue(0);
        titleFade.setToValue(1);

        FadeTransition messageFade =
                new FadeTransition(
                        Duration.millis(450),
                        message
                );

        messageFade.setFromValue(0);
        messageFade.setToValue(1);

        FadeTransition requestFade =
                new FadeTransition(
                        Duration.millis(450),
                        requestLabel
                );

        requestFade.setFromValue(0);
        requestFade.setToValue(1);

        FadeTransition buttonFade =
                new FadeTransition(
                        Duration.millis(450),
                        dashboard
                );

        buttonFade.setFromValue(0);
        buttonFade.setToValue(1);

        scale.setOnFinished(
                event -> {

                    titleFade.play();

                    titleFade.setOnFinished(
                            e1 -> {

                                messageFade.play();

                                messageFade.setOnFinished(
                                        e2 -> {

                                            requestFade.play();

                                            requestFade.setOnFinished(
                                                    e3 -> buttonFade.play()
                                            );
                                        }
                                );
                            }
                    );
                }
        );

        scale.play();
    }
}
