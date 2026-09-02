package com.rentsathi.ui.screens;

import com.rentsathi.firebase.authentication.FirebaseAuthService;
import com.rentsathi.ui.screens.delivery.DeliveryPartnerDashboard;

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
    private static final String ERROR = "#D32F2F";

    // ============================================================
    // SHOW DELIVERY PARTNER LOGIN SCREEN
    // ============================================================

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

    // ============================================================
    // LEFT PANEL
    // ============================================================

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

        // Label secure = new Label(
        //         "♢  Secure & Verified Platform"
        // );

        // secure.setStyle(
        //         "-fx-font-family: 'Arial';" +
        //         "-fx-font-size: 13px;" +
        //         "-fx-text-fill: " + DARK_BLUE + ";"
        // );

        // VBox.setVgrow(
                
        //         javafx.scene.layout.Priority.ALWAYS
        // );

        panel.getChildren().addAll(
                brand,
                imageBox,
                information
        );

        return panel;
    }

    // ============================================================
    // BRAND
    // ============================================================

    private static HBox createBrand() {

        URL url = DeliveryPartnerLoginScreen.class
                .getResource(
                        "/images/logo.png"
                );

        if (url == null) {

            throw new RuntimeException(
                    "Logo not found: " +
                    "/images/logo.png"
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

    // ============================================================
    // ILLUSTRATION
    // ============================================================

    private static ImageView createIllustration() {

        URL url = DeliveryPartnerLoginScreen.class
                .getResource(
                        "/images/delivery.png"
                );

        if (url == null) {

            throw new RuntimeException(
                    "Delivery image not found: " +
                    "/images/delivery.png"
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

    // ============================================================
    // LOGIN PANEL
    // ============================================================

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

        // ========================================================
        // HEADING
        // ========================================================

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

        // ========================================================
        // EMAIL
        // ========================================================

        Label emailLabel = new Label(
                "Email Address"
        );

        emailLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        TextField email = new TextField();

        email.setPromptText(
                "Enter your email address"
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

        // ========================================================
        // PASSWORD LABEL
        // ========================================================

        Label passwordLabel = new Label(
                "Password"
        );

        passwordLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        // ========================================================
        // FORGOT PASSWORD
        // ========================================================

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

        // ========================================================
        // PASSWORD FIELD
        // ========================================================

        PasswordField password =
                new PasswordField();

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
                "-fx-padding: 0 45px 0 12px;"
        );

        // ========================================================
        // VISIBLE PASSWORD FIELD
        // ========================================================

        TextField visiblePassword =
                new TextField();

        visiblePassword.setPromptText(
                "Enter your password"
        );

        visiblePassword.setPrefHeight(45);

        visiblePassword.setStyle(
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 45px 0 12px;"
        );

        visiblePassword.setVisible(false);
        visiblePassword.setManaged(false);

        // Synchronize both password fields
        visiblePassword.textProperty()
                .bindBidirectional(
                        password.textProperty()
                );

        // ========================================================
        // PASSWORD TOGGLE BUTTON
        // ========================================================

        Button passwordToggleButton =
                new Button("👁");

        passwordToggleButton.setFocusTraversable(
                false
        );

        passwordToggleButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 16px;" +
                "-fx-cursor: hand;"
        );

        // ========================================================
        // PASSWORD FIELD CONTAINER
        // ========================================================

        StackPane passwordFieldBox =
                new StackPane();

        passwordFieldBox.setPrefHeight(45);

        passwordFieldBox.getChildren().addAll(
                password,
                visiblePassword,
                passwordToggleButton
        );

        StackPane.setAlignment(
                passwordToggleButton,
                Pos.CENTER_RIGHT
        );

        StackPane.setMargin(
                passwordToggleButton,
                new Insets(0, 8, 0, 0)
        );

        // ========================================================
        // SHOW / HIDE PASSWORD
        // ========================================================

        passwordToggleButton.setOnAction(
                event -> {

                    if (visiblePassword.isVisible()) {

                        // Hide password
                        visiblePassword.setVisible(false);
                        visiblePassword.setManaged(false);

                        password.setVisible(true);
                        password.setManaged(true);

                        passwordToggleButton.setText(
                                "👁"
                        );

                    } else {

                        // Show password
                        password.setVisible(false);
                        password.setManaged(false);

                        visiblePassword.setVisible(true);
                        visiblePassword.setManaged(true);

                        passwordToggleButton.setText(
                                "🙈"
                        );
                    }
                }
        );

        // ========================================================
        // PASSWORD BOX
        // ========================================================

        VBox passwordBox = new VBox(
                6,
                passwordHeader,
                passwordFieldBox
        );

        // ========================================================
        // LOGIN ERROR
        // ========================================================

        Label loginError = new Label();

        loginError.setStyle(
                "-fx-text-fill: " + ERROR + ";" +
                "-fx-font-size: 13px;"
        );

        loginError.setVisible(false);
        loginError.setManaged(false);

        // ========================================================
        // REMEMBER ME
        // ========================================================

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

        // ========================================================
        // LOGIN BUTTON
        // ========================================================

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

        // ========================================================
        // FIREBASE LOGIN ACTION
        // ========================================================

        login.setOnAction(event -> {

            System.out.println(
                    "DELIVERY PARTNER LOGIN BUTTON CLICKED"
            );

            String emailText =
                    email.getText().trim();

            String passwordText =
                    password.getText();

            // ----------------------------------------------------
            // Hide previous error
            // ----------------------------------------------------

            loginError.setVisible(false);
            loginError.setManaged(false);

            // ----------------------------------------------------
            // Validate email
            // ----------------------------------------------------

            if (emailText.isEmpty()) {

                loginError.setText(
                        "ⓘ Please enter your email address."
                );

                loginError.setVisible(true);
                loginError.setManaged(true);

                return;
            }

            // ----------------------------------------------------
            // Validate password
            // ----------------------------------------------------

            if (passwordText.isEmpty()) {

                loginError.setText(
                        "ⓘ Please enter your password."
                );

                loginError.setVisible(true);
                loginError.setManaged(true);

                return;
            }

            // ----------------------------------------------------
            // Disable button during authentication
            // ----------------------------------------------------

            login.setDisable(true);

            login.setText(
                    "Logging in..."
            );

            // ----------------------------------------------------
            // Firebase Authentication
            // ----------------------------------------------------

            boolean success =
                    FirebaseAuthService.login(
                            emailText,
                            passwordText
                    );

            // ----------------------------------------------------
            // LOGIN SUCCESS
            // ----------------------------------------------------

            if (success) {

                System.out.println(
                        "DELIVERY PARTNER LOGIN SUCCESSFUL"
                );

                DeliveryPartnerDashboard.show(
                        stage
                );
            }

            // ----------------------------------------------------
            // LOGIN FAILED
            // ----------------------------------------------------

            else {

                System.out.println(
                        "DELIVERY PARTNER LOGIN FAILED"
                );

                loginError.setText(
                        "ⓘ Invalid email or password. Please try again."
                );

                loginError.setVisible(true);
                loginError.setManaged(true);

                login.setDisable(false);

                login.setText(
                        "Login   →"
                );
            }
        });

        // ========================================================
        // SEPARATOR
        // ========================================================

        Line separatorLine = new Line(
                0,
                0,
                430,
                0
        );

        separatorLine.setStroke(
                Color.web(BORDER)
        );

        // ========================================================
        // REGISTER
        // ========================================================

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

        register.setOnAction(
                event ->
                        new CreatePartnerScreen(stage).show()
        );

        HBox registerBox = new HBox(
                5,
                registerText,
                register
        );

        registerBox.setAlignment(
                Pos.CENTER
        );

        // ========================================================
        // BACK BUTTON
        // ========================================================

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
                event ->
                        WelcomeScreen.show(stage)
        );

        // ========================================================
        // ADD COMPONENTS TO CARD
        // ========================================================

        card.getChildren().addAll(
                heading,
                new VBox(25),
                emailBox,
                passwordBox,
                loginError,
                options,
                login,
                new VBox(
                        18,
                        separatorLine
                ),
                registerBox,
                back
        );

        // ========================================================
        // CONTAINER
        // ========================================================

        StackPane container =
                new StackPane(card);

        container.setAlignment(
                Pos.CENTER
        );

        container.setPadding(
                new Insets(0, 70, 0, 70)
        );

        return container;
    }
}