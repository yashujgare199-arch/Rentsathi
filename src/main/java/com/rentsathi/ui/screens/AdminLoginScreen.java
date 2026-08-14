package com.rentsathi.ui.screens;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AdminLoginScreen {

    private static final String BLUE = "#3657C8";
    private static final String DARK_BLUE = "#29476B";
    private static final String PURPLE = "#7166E8";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String BORDER = "#C8CBD9";
    private static final String ERROR = "#C62828";

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
                "RentSathi - Admin Sign In"
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

        StackPane graphic = createAdminGraphic();

        VBox graphicBox = new VBox(
                graphic
        );

        graphicBox.setAlignment(
                Pos.CENTER
        );

        graphicBox.setPadding(
                new Insets(55, 0, 0, 0)
        );

        Label title = new Label(
                "Manage RentSathi with confidence."
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        title.setWrapText(true);

        Label description = new Label(
                "Secure access to the centralized administration dashboard " +
                "for oversight and control."
        );

        description.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 16px;" +
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-line-spacing: 5px;"
        );

        description.setWrapText(true);

        VBox information = new VBox(
                10,
                title,
                description
        );

        information.setPadding(
                new Insets(0, 0, 0, 10)
        );

        panel.getChildren().addAll(
                brand,
                graphicBox,
                information
        );

        return panel;
    }

    private static HBox createBrand() {

        Image image = new Image(
                AdminLoginScreen.class
                        .getResource(
                                "/images/admin-login-illustration.png"
                        )
                        .toExternalForm()
        );

        ImageView logo = new ImageView(
                image
        );

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
                "-fx-font-size: 13px;" +
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

    private static StackPane createAdminGraphic() {

        StackPane graphic = new StackPane();

        graphic.setPrefSize(
                500,
                390
        );

        Rectangle background = new Rectangle(
                500,
                390
        );

        background.setFill(
                Color.web("#F0F0FA")
        );

        background.setStroke(
                Color.web("#D9DCEA")
        );

        background.setStrokeWidth(1);

        for (int x = 0; x <= 500; x += 40) {

            Line line = new Line(
                    x,
                    0,
                    x,
                    390
            );

            line.setStroke(
                    Color.web("#D9DCEA")
            );

            graphic.getChildren().add(
                    line
            );
        }

        for (int y = 0; y <= 390; y += 40) {

            Line line = new Line(
                    0,
                    y,
                    500,
                    y
            );

            line.setStroke(
                    Color.web("#D9DCEA")
            );

            graphic.getChildren().add(
                    line
            );
        }

        Circle outerCircle = new Circle(
                190
        );

        outerCircle.setFill(
                Color.web("#3657C8", 0.08)
        );

        Circle middleCircle = new Circle(
                125
        );

        middleCircle.setFill(
                Color.TRANSPARENT
        );

        middleCircle.setStroke(
                Color.web("#3657C8", 0.20)
        );

        middleCircle.setStrokeWidth(1);

        Circle innerCircle = new Circle(
                70
        );

        innerCircle.setFill(
                Color.web("#3657C8", 0.18)
        );

        StackPane circles = new StackPane(
                outerCircle,
                middleCircle,
                innerCircle
        );

        circles.setTranslateY(
                5
        );

        Label adminIcon = new Label(
                "ADMIN"
        );

        adminIcon.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 38px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        graphic.getChildren().addAll(
                background,
                circles,
                adminIcon
        );

        StackPane.setAlignment(
                background,
                Pos.CENTER
        );

        StackPane.setAlignment(
                circles,
                Pos.CENTER
        );

        StackPane.setAlignment(
                adminIcon,
                Pos.CENTER
        );

        return graphic;
    }

    private static StackPane createLoginPanel(
            Stage stage
    ) {

        VBox card = new VBox();

        card.setPrefWidth(
                430
        );

        card.setMaxWidth(
                430
        );

        card.setPadding(
                new Insets(35, 0, 25, 0)
        );

        card.setSpacing(
                10
        );

        Label title = new Label(
                "Admin Sign In"
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        Label subtitle = new Label(
                "Access the RentSathi administration panel"
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
                "Admin Email"
        );

        emailLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        TextField email = new TextField(
                "admin@rentsathi"
        );

        email.setPrefHeight(
                45
        );

        email.setStyle(
                "-fx-border-color: " + ERROR + ";" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );

        Label emailError = new Label(
                "Please enter a valid email address."
        );

        emailError.setStyle(
                "-fx-text-fill: " + ERROR + ";" +
                "-fx-font-size: 13px;"
        );

        VBox emailBox = new VBox(
                5,
                emailLabel,
                email,
                emailError
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
                "password"
        );

        password.setPrefHeight(
                45
        );

        password.setStyle(
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );

        Button forgot = new Button(
                "Forgot Password"
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

        VBox passwordBox = new VBox(
                6,
                passwordHeader,
                password
        );

        CheckBox remember = new CheckBox(
                "Remember this device"
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
                "Secure Login"
        );

        login.setMaxWidth(
                Double.MAX_VALUE
        );

        login.setPrefHeight(
                45
        );

        login.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        Label protectedText = new Label(
                "🔒  Protected Administrator Access"
        );

        protectedText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        protectedText.setAlignment(
                Pos.CENTER
        );

        protectedText.setMaxWidth(
                Double.MAX_VALUE
        );

        Line separator = new Line(
                0,
                0,
                430,
                0
        );

        separator.setStroke(
                Color.web(BORDER)
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
                new VBox(20),
                emailBox,
                passwordBox,
                options,
                login,
                protectedText,
                separator,
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
