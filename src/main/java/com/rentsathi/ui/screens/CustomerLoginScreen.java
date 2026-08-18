package com.rentsathi.ui.screens;

import com.rentsathi.firebase.authentication.FirebaseAuthService;

import com.rentsathi.ui.screens.customer.DashboardScreen;

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

public class CustomerLoginScreen {

        private static final String BLUE = "#3657C8";
        private static final String DARK_BLUE = "#29476B";
        private static final String PURPLE = "#7166E8";
        private static final String BACKGROUND = "#F8F8FD";
        private static final String BORDER = "#C8CBD9";
        private static final String ERROR = "#C62828";

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BACKGROUND + ";");

                Rectangle outerBorder = new Rectangle();

                outerBorder.setFill(Color.TRANSPARENT);
                outerBorder.setStroke(Color.web(PURPLE));
                outerBorder.setStrokeWidth(5);
                outerBorder.setArcWidth(24);
                outerBorder.setArcHeight(24);
                outerBorder.setMouseTransparent(true);

                outerBorder.widthProperty().bind(
                                root.widthProperty());

                outerBorder.heightProperty().bind(
                                root.heightProperty());

                VBox leftPanel = createLeftPanel();

                StackPane rightPanel = createLoginPanel(stage);

                root.setLeft(leftPanel);
                root.setCenter(rightPanel);

                leftPanel.prefWidthProperty().bind(
                                root.widthProperty().multiply(0.5));

                StackPane finalRoot = new StackPane();

                finalRoot.getChildren().addAll(
                                root,
                                outerBorder);

                Scene scene = new Scene(
                                finalRoot,
                                1500,
                                830);

                scene.setFill(
                                Color.web(BACKGROUND));

                stage.setTitle(
                                "RentSathi - Customer Login");

                stage.setScene(scene);

                stage.setMinWidth(1100);
                stage.setMinHeight(700);

                stage.show();
        }

        private static VBox createLeftPanel() {

                VBox panel = new VBox();

                panel.setAlignment(
                                Pos.TOP_CENTER);

                panel.setPadding(
                                new Insets(38, 50, 40, 50));

                panel.setSpacing(25);

                panel.setStyle(
                                "-fx-background-color: #F0F0FA;");

                HBox brand = createBrand();

                Image image = new Image(
                                CustomerLoginScreen.class
                                                .getResource(
                                                                "/images/customer-login-illustration.png")
                                                .toExternalForm());

                ImageView illustration = new ImageView(image);

                illustration.setFitWidth(390);
                illustration.setFitHeight(230);
                illustration.setPreserveRatio(true);

                VBox imageBox = new VBox(
                                illustration);

                imageBox.setAlignment(
                                Pos.CENTER);

                imageBox.setPadding(
                                new Insets(270, 0, 0, 0));

                Label title = new Label(
                                "Find the right rental for your needs.");

                title.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 21px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #111827;");

                Label description = new Label(
                                "Access a wide range of properties and equipment with ease and\n" +
                                                "security. Join our community today.");

                description.setAlignment(
                                Pos.CENTER);

                description.setTextAlignment(
                                javafx.scene.text.TextAlignment.CENTER);

                description.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-text-fill: " + DARK_BLUE + ";" +
                                                "-fx-line-spacing: 5px;");

                VBox text = new VBox(
                                10,
                                title,
                                description);

                text.setAlignment(
                                Pos.CENTER);

                panel.getChildren().addAll(
                                brand,
                                imageBox,
                                text);

                return panel;
        }

        private static HBox createBrand() {

                Image image = new Image(
                                CustomerLoginScreen.class
                                                .getResource(
                                                                "/images/logo.png")
                                                .toExternalForm());

                ImageView logo = new ImageView(image);

                logo.setFitWidth(45);
                logo.setFitHeight(45);
                logo.setPreserveRatio(true);

                Label title = new Label(
                                "RentSathi");

                title.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 21px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label tagline = new Label(
                                "Rent Smart. Live Easy.");

                tagline.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: #111827;");

                VBox text = new VBox(
                                2,
                                title,
                                tagline);

                HBox brand = new HBox(
                                12,
                                logo,
                                text);

                brand.setAlignment(
                                Pos.CENTER_LEFT);

                return brand;
        }

        private static StackPane createLoginPanel(
                        Stage stage) {

                VBox card = new VBox();

                card.setPrefWidth(420);
                card.setMaxWidth(420);

                card.setPadding(
                                new Insets(32, 32, 28, 32));

                card.setSpacing(10);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;");

                Label title = new Label(
                                "Welcome Back");

                title.setMaxWidth(
                                Double.MAX_VALUE);

                title.setAlignment(
                                Pos.CENTER);

                title.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #111827;");

                Label subtitle = new Label(
                                "Sign in to your RentSathi customer account");

                subtitle.setMaxWidth(
                                Double.MAX_VALUE);

                subtitle.setAlignment(
                                Pos.CENTER);

                subtitle.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + DARK_BLUE + ";");

                VBox heading = new VBox(
                                5,
                                title,
                                subtitle);

                heading.setAlignment(
                                Pos.CENTER);

                heading.setPadding(
                                new Insets(0, 0, 20, 0));

                Label emailLabel = new Label(
                                "Email Address");

                emailLabel.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #111827;");

                TextField email = new TextField();
                email.setPromptText("Enter your email");

                email.setPrefHeight(38);

                email.setStyle(
                                "-fx-border-color: " + ERROR + ";" +
                                                "-fx-border-width: 2px;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-padding: 0 12px;");

                Label emailError = new Label(
                                "ⓘ  Please enter a valid email address.");

                emailError.setStyle(
                                "-fx-text-fill: " + ERROR + ";" +
                                                "-fx-font-size: 13px;");
                emailError.setVisible(false);
                emailError.setManaged(false);

                VBox emailBox = new VBox(
                                5,
                                emailLabel,
                                email,
                                emailError);

                Label passwordLabel = new Label(
                                "Password");

                passwordLabel.setStyle(
                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #111827;");

                PasswordField password = new PasswordField();

                password.setPromptText("Enter your password");
                password.setPrefHeight(38);

                password.setStyle(
                                "-fx-border-color: " + ERROR + ";" +
                                                "-fx-border-width: 2px;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-padding: 0 45px 0 12px;");

                Label passwordError = new Label(
                                "ⓘ  Incorrect password. Please try again.");

                passwordError.setStyle(
                                "-fx-text-fill: " + ERROR + ";" +
                                                "-fx-font-size: 13px;");

                passwordError.setVisible(false);
                passwordError.setManaged(false);

                // ============================================================
                // SHOW / HIDE PASSWORD
                // ============================================================

                // Normal TextField used when password is visible
                TextField visiblePassword = new TextField();

                visiblePassword.setPromptText("Enter your password");
                visiblePassword.setPrefHeight(38);

                visiblePassword.setStyle(
                                "-fx-border-color: " + ERROR + ";" +
                                                "-fx-border-width: 2px;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-padding: 0 45px 0 12px;");

                // Initially hidden
                visiblePassword.setVisible(false);
                visiblePassword.setManaged(false);

                // Keep both fields synchronized
                visiblePassword.textProperty()
                                .bindBidirectional(password.textProperty());

                // Eye button
                Button passwordToggleButton = new Button("👁");

                passwordToggleButton.setFocusTraversable(false);

                passwordToggleButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-cursor: hand;");

                // Container for password field + eye button
                StackPane passwordFieldBox = new StackPane();

                passwordFieldBox.setPrefHeight(38);

                passwordFieldBox.getChildren().addAll(
                                password,
                                visiblePassword,
                                passwordToggleButton);

                // Put eye button on the right
                StackPane.setAlignment(
                                passwordToggleButton,
                                Pos.CENTER_RIGHT);

                StackPane.setMargin(
                                passwordToggleButton,
                                new Insets(0, 8, 0, 0));

                // ============================================================
                // TOGGLE PASSWORD
                // ============================================================

                passwordToggleButton.setOnAction(event -> {

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
                });

                // ============================================================
                // PASSWORD BOX
                // ============================================================

                VBox passwordBox = new VBox(
                                5,
                                passwordLabel,
                                passwordFieldBox,
                                passwordError);

                CheckBox remember = new CheckBox(
                                "Remember me");

                remember.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + DARK_BLUE + ";");

                Button forgot = new Button(
                                "Forgot Password?");

                forgot.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                HBox options = new HBox(
                                remember,
                                forgot);

                options.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setMargin(
                                forgot,
                                new Insets(0, 0, 0, 145));

                Button login = new Button(
                                "Login   →");

                login.setMaxWidth(
                                Double.MAX_VALUE);

                login.setPrefHeight(38);

                login.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                login.setOnAction(e -> {

                        String emailText = email.getText().trim();
                        String passwordText = password.getText();

                        // Hide previous errors
                        emailError.setVisible(false);
                        emailError.setManaged(false);

                        passwordError.setVisible(false);
                        passwordError.setManaged(false);

                        // Basic validation
                        if (emailText.isEmpty() || !emailText.contains("@")) {

                                emailError.setVisible(true);
                                emailError.setManaged(true);

                                return;
                        }

                        if (passwordText.isEmpty()) {

                                passwordError.setText(
                                                "ⓘ  Please enter your password.");

                                passwordError.setVisible(true);
                                passwordError.setManaged(true);

                                return;
                        }

                        // Disable button while Firebase login is running
                        login.setDisable(true);
                        login.setText("Logging in...");

                        boolean success = FirebaseAuthService.login(
                                        emailText,
                                        passwordText);

                        if (success) {

                                // Firebase login successful
                                new DashboardScreen(stage).show();

                        } else {

                                // Firebase login failed
                                passwordError.setText(
                                                "ⓘ  Incorrect email or password. Please try again.");

                                passwordError.setVisible(true);
                                passwordError.setManaged(true);

                                login.setDisable(false);
                                login.setText("Login   →");
                        }
                });

                Button disabledLogin = new Button(
                                "Login (Disabled)");

                disabledLogin.setMaxWidth(
                                Double.MAX_VALUE);

                disabledLogin.setPrefHeight(38);

                disabledLogin.setDisable(true);

                disabledLogin.setStyle(
                                "-fx-background-color: #E7E8EF;" +
                                                "-fx-text-fill: #9EA1AD;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-background-radius: 7px;");

                HBox orLine = createOrLine();

                Label accountText = new Label(
                                "Don't have an account?");

                Label createAccount = new Label(
                                "Create Customer Account");

                createAccount.setStyle(
                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                createAccount.setOnMouseClicked(event -> {
                        CustomerCreateScreen customerCreateScreen = new CustomerCreateScreen(stage);
                        customerCreateScreen.show();
                });

                HBox account = new HBox(
                                4,
                                accountText,
                                createAccount);

                account.setAlignment(
                                Pos.CENTER);

                accountText.setStyle(
                                "-fx-text-fill: #111827;" +
                                                "-fx-font-size: 13px;");

                Button back = new Button(
                                "← Back to Role Selection");

                back.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " + DARK_BLUE + ";" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-cursor: hand;");

                back.setOnAction(
                                event -> WelcomeScreen.show(stage));

                card.getChildren().addAll(
                                heading,
                                emailBox,
                                passwordBox,
                                options,
                                login,
                                disabledLogin,
                                orLine,
                                account,
                                back);

                StackPane container = new StackPane();

                container.setAlignment(
                                Pos.CENTER);

                container.getChildren().add(
                                card);

                return container;
        }

        private static HBox createOrLine() {

                Line left = new Line(
                                0,
                                0,
                                160,
                                0);

                left.setStroke(
                                Color.web(BORDER));

                Line right = new Line(
                                0,
                                0,
                                160,
                                0);

                right.setStroke(
                                Color.web(BORDER));

                Label or = new Label(
                                "OR");

                or.setStyle(
                                "-fx-text-fill: " + DARK_BLUE + ";" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 10px;");

                HBox box = new HBox(
                                left,
                                or,
                                right);

                box.setAlignment(
                                Pos.CENTER);

                box.setPadding(
                                new Insets(8, 0, 8, 0));

                return box;
        }
}