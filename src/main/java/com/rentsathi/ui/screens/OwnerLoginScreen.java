package com.rentsathi.ui.screens;

import java.io.File;
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

public class OwnerLoginScreen {

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
                "RentSathi - Owner Sign In"
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
            new Insets(120, 0, 0, 0)
        );

        imageBox.setMinHeight(400);

        Label title = new Label(
                "Turn your rental items into\nincome."
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        Label description = new Label(
                "List your assets, manage bookings, and track your earnings\n" +
                "all in one place with RentSathi's professional owner\n" +
                "dashboard."
        );

        description.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 16px;" +
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-line-spacing: 5px;"
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

        Image image = loadImage(
                "/images/logo.png"
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
                "RENT SMART. LIVE EASY."
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

        Image image = loadImage(
            "/images/owner-login-illustration.png"
        );

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(440);
        imageView.setFitHeight(330);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    private static Image loadImage(
            String resourcePath
    ) {

        URL resource = OwnerLoginScreen.class
                .getResource(resourcePath);

        if (resource != null) {
            return new Image(
                    resource.toExternalForm()
            );
        }

        String relativePath = resourcePath.startsWith("/")
                ? resourcePath.substring(1)
                : resourcePath;

        File file = new File(
                "src/main/resources",
                relativePath
        );

        if (file.exists()) {
            return new Image(
                    file.toURI().toString()
            );
        }

        throw new RuntimeException(
                "Resource not found: " + resourcePath +
                "\nExpected file: " + file.getAbsolutePath()
        );
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
                "Owner Sign In"
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        Label subtitle = new Label(
                "Sign in to manage your rental business"
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

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label emailLabel = new Label(
                "Email Address"
        );

        emailLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        TextField email = new TextField(
                "owner@rentsathi.com"
        );

        email.setPrefHeight(45);

        email.setStyle(
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
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

        PasswordField password = new PasswordField();

        password.setText(
                "password123"
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
                passwordLabel,
                password
        );

        CheckBox remember = new CheckBox(
                "Remember me"
        );

        remember.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
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

        HBox options = new HBox(
                remember,
                forgot
        );

        options.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setMargin(
                forgot,
                new Insets(0, 0, 0, 165)
        );

        Button login = new Button(
                "Login   →"
        );

        login.setMaxWidth(
                Double.MAX_VALUE
        );

        login.setPrefHeight(45);

        login.setStyle(
                "-fx-background-color: #4A63DF;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        HBox separator = createSeparator();

        Label accountText = new Label(
                "Don't have an owner account?"
        );

        accountText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        Button createAccount = new Button(
                "Create Owner Account"
        );

        createAccount.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox account = new HBox(
                5,
                accountText,
                createAccount
        );

        account.setAlignment(
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
                separator,
                account,
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

    private static HBox createSeparator() {

        Line left = new Line(
                0,
                0,
                180,
                0
        );

        left.setStroke(
                Color.web(BORDER)
        );

        Line right = new Line(
                0,
                0,
                180,
                0
        );

        right.setStroke(
                Color.web(BORDER)
        );

        Label or = new Label(
                "OR"
        );

        or.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-padding: 0 10px;"
        );

        HBox box = new HBox(
                left,
                or,
                right
        );

        box.setAlignment(
                Pos.CENTER
        );

        return box;
    }
}