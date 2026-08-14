package com.rentsathi.ui.screens;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DeliveryPartnerLoginScreen {

    private static final String BLUE = "#3657C8";
    private static final String DARK_BLUE = "#29476B";
    private static final String PURPLE = "#7166E8";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String BORDER = "#C8CBD9";

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        Rectangle outerBorder = new Rectangle();

        outerBorder.setFill(Color.TRANSPARENT);
        outerBorder.setStroke(Color.web(PURPLE));
        outerBorder.setStrokeWidth(5);
        outerBorder.setArcWidth(24);
        outerBorder.setArcHeight(24);
        outerBorder.setMouseTransparent(true);

        outerBorder.widthProperty().bind(
                root.widthProperty()
        );

        outerBorder.heightProperty().bind(
                root.heightProperty()
        );

        VBox leftPanel = createLeftPanel();

        StackPane rightPanel = createLoginPanel(stage);

        root.setLeft(leftPanel);
        root.setCenter(rightPanel);

        leftPanel.prefWidthProperty().bind(
                root.widthProperty().multiply(0.5)
        );

        StackPane finalRoot = new StackPane();

        finalRoot.getChildren().addAll(
                root,
                outerBorder
        );

        Scene scene = new Scene(
                finalRoot,
                1500,
                830
        );

        scene.setFill(
                Color.web(BACKGROUND)
        );

        stage.setTitle(
                "RentSathi - Delivery Partner Sign In"
        );

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    private static VBox createLeftPanel() {

        VBox panel = new VBox();

        panel.setAlignment(
                Pos.TOP_LEFT
        );

        panel.setPadding(
                new Insets(38, 50, 35, 50)
        );

        panel.setSpacing(20);

        panel.setStyle(
                "-fx-background-color: #F0F0FA;"
        );

        HBox brand = createBrand();

        ImageView illustration = createIllustration();

        VBox imageBox = new VBox(
                illustration
        );

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setPadding(
                new Insets(165, 0, 0, 0)
        );

        Label title = new Label(
                "Deliver rentals. Earn on every trip."
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        title.setWrapText(true);

        Label description = new Label(
                "Join the fastest growing rental delivery network."
        );

        description.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 16px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        VBox information = new VBox(
                10,
                title,
                description
        );

        information.setPadding(
                new Insets(0, 0, 0, 50)
        );

        Label secure = new Label(
                "♢  Secure & Verified Platform"
        );

        secure.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        VBox.setVgrow(
                secure,
                javafx.scene.layout.Priority.ALWAYS
        );

        panel.getChildren().addAll(
                brand,
                imageBox,
                information,
                secure
        );

        return panel;
    }

    private static HBox createBrand() {

        URL url = DeliveryPartnerLoginScreen.class
                .getResource(
                        "/images/delivery-partner-login-illustration.png"
                );

        if (url == null) {
                throw new RuntimeException(
                "Logo not found: /images/delivery-partner-login-illustration.png"
                );
        }

        Image image = new Image(
                url.toExternalForm()
        );

        ImageView logo = new ImageView(image);

        logo.setFitWidth(45);
        logo.setFitHeight(45);
        logo.setPreserveRatio(true);

        Label title = new Label(
                "RentSathi"
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label tagline = new Label(
                "Rent Smart. Live Easy."
        );

        tagline.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #111827;"
        );

        VBox text = new VBox(
                2,
                title,
                tagline
        );

        HBox brand = new HBox(
                12,
                logo,
                text
        );

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        return brand;
    }

    private static ImageView createIllustration() {

        URL url = DeliveryPartnerLoginScreen.class
                .getResource(
                        "/images/delivery-partner-login-illustration.png"
                );

        if (url == null) {
            throw new RuntimeException(
                    "Delivery image not found: " +
                    "/images/delivery-partner-login-illustration.png"
            );
        }

        Image image = new Image(
                url.toExternalForm()
        );

        ImageView imageView = new ImageView(
                image
        );

        imageView.setFitWidth(470);
        imageView.setFitHeight(255);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    private static StackPane createLoginPanel(
            Stage stage
    ) {

        VBox card = new VBox();

        card.setMaxWidth(430);
        card.setPrefWidth(430);

        card.setSpacing(10);

        card.setPadding(
                new Insets(32, 0, 25, 0)
        );

        Label title = new Label(
                "Delivery Partner Sign In"
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        Label subtitle = new Label(
                "Manage your deliveries with RentSathi"
        );

        subtitle.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        VBox heading = new VBox(
                6,
                title,
                subtitle
        );

        Label emailLabel = new Label(
                "Email / Phone"
        );

        emailLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        TextField email = new TextField(
                "john.doe@example.com"
        );

        email.setPrefHeight(45);

        email.setStyle(
                "-fx-border-color: " + BLUE + ";" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );

        VBox emailBox = new VBox(
                6,
                emailLabel,
                email
        );

        Label passwordLabel = new Label(
                "Password"
        );

        passwordLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        Button forgot = new Button(
                "Forgot Password?"
        );

        forgot.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox passwordHeader = new HBox(
                passwordLabel,
                forgot
        );

        passwordHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setMargin(
                forgot,
                new Insets(0, 0, 0, 270)
        );

        PasswordField password = new PasswordField();

        password.setPromptText(
                "Enter your password"
        );

        password.setPrefHeight(45);

        password.setStyle(
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );

        VBox passwordBox = new VBox(
                6,
                passwordHeader,
                password
        );

        CheckBox remember = new CheckBox(
                "Remember me"
        );

        remember.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        HBox options = new HBox(
                remember
        );

        options.setAlignment(
                Pos.CENTER_LEFT
        );

        Button login = new Button(
                "Login   →"
        );

        login.setMaxWidth(
                Double.MAX_VALUE
        );

        login.setPrefHeight(45);

        login.setStyle(
                "-fx-background-color: #3657C8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        Line separatorLine = new Line(
                0,
                0,
                430,
                0
        );

        separatorLine.setStroke(
                Color.web(BORDER)
        );

        Label registerText = new Label(
                "Want to become a delivery partner?"
        );

        registerText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        Button register = new Button(
                "Register as Delivery Partner"
        );

        register.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox registerBox = new HBox(
                5,
                registerText,
                register
        );

        registerBox.setAlignment(
                Pos.CENTER
        );

        Button back = new Button(
                "← Back to Role Selection"
        );

        back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );

        back.setOnAction(
                event -> WelcomeScreen.show(stage)
        );

        card.getChildren().addAll(
                heading,
                new VBox(25),
                emailBox,
                passwordBox,
                options,
                login,
                new VBox(
                        18,
                        separatorLine
                ),
                registerBox,
                back
        );

        StackPane container = new StackPane(
                card
        );

        container.setAlignment(
                Pos.CENTER
        );

        container.setPadding(
                new Insets(0, 70, 0, 70)
        );

        return container;
    }
}