package com.rentsathi.ui.screens;

import com.rentsathi.firebase.authentication.FirebaseAuthService;
import com.rentsathi.ui.screens.owner.OwnerDashboardScreen;

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
    private static final String ERROR = "#D32F2F";

    // ============================================================
    // SHOW OWNER LOGIN SCREEN
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
                "RentSathi - Owner Sign In"
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

    // ============================================================
    // ILLUSTRATION
    // ============================================================

    private static ImageView createIllustration() {

        Image image = loadImage(
                "/images/owner.png"
        );

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(440);
        imageView.setFitHeight(330);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    // ============================================================
    // LOAD IMAGE
    // ============================================================

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
                "\nExpected file: " +
                file.getAbsolutePath()
        );
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
                email.setPromptText("Enter your mail");
                email.setPrefHeight(38);
                
        

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
        // PASSWORD FIELD
        // ========================================================

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
                "-fx-padding: 0 45px 0 12px;"
        );

        // ========================================================
        // VISIBLE PASSWORD FIELD
        // ========================================================

        TextField visiblePassword = new TextField();

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

        // Keep both password fields synchronized
        visiblePassword.textProperty()
                .bindBidirectional(
                        password.textProperty()
                );

        // ========================================================
        // PASSWORD VIEW BUTTON
        // ========================================================

        Button passwordToggleButton =
                new Button("👁");

        passwordToggleButton.setFocusTraversable(false);

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

                        passwordToggleButton.setText("👁");

                    } else {

                        // Show password
                        password.setVisible(false);
                        password.setManaged(false);

                        visiblePassword.setVisible(true);
                        visiblePassword.setManaged(true);

                        passwordToggleButton.setText("🙈");
                    }
                }
        );

        // ========================================================
        // PASSWORD BOX
        // ========================================================

        VBox passwordBox = new VBox(
                6,
                passwordLabel,
                passwordFieldBox
        );

        // ========================================================
        // LOGIN ERROR
        // ========================================================
        // IMPORTANT:
        // This was missing in your previous code.
        // The login button uses this variable.

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
                "-fx-background-color: #4A63DF;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        // ========================================================
        // LOGIN ACTION
        // ========================================================

        login.setOnAction(event -> {

            System.out.println(
                    "OWNER LOGIN BUTTON CLICKED"
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
            // Disable login button
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
                        "OWNER LOGIN SUCCESSFUL"
                );

                OwnerDashboardScreen.show(
                        stage
                );

            }

            // ----------------------------------------------------
            // LOGIN FAILED
            // ----------------------------------------------------

            else {

                System.out.println(
                        "OWNER LOGIN FAILED"
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

        HBox separator = createSeparator();

        // ========================================================
        // CREATE ACCOUNT
        // ========================================================

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

        createAccount.setOnAction(
                event ->
                        new OwnerCreateScreen(stage).show()
        );

        HBox account = new HBox(
                5,
                accountText,
                createAccount
        );

        account.setAlignment(
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
        // ADD EVERYTHING TO CARD
        // ========================================================

        card.getChildren().addAll(
                heading,
                new VBox(25),
                emailBox,
                passwordBox,
                loginError,
                options,
                login,
                separator,
                account,
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

    // ============================================================
    // SEPARATOR
    // ============================================================

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