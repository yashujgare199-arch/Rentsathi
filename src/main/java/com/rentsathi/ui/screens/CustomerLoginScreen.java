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
import javafx.scene.shape.SVGPath;
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
                root.widthProperty().multiply(0.57)
                );

                StackPane finalRoot = new StackPane();

                finalRoot.getChildren().addAll(
                                root,
                                outerBorder);

                Scene scene = new Scene(
                                finalRoot,
                                1600,
                                900);

                scene.setFill(
                                Color.web(BACKGROUND));

                stage.setTitle(
                                "RentSathi - Customer Login");

                stage.setScene(scene);

                leftPanel.prefWidthProperty()
        .bind(root.widthProperty().multiply(0.57));

rightPanel.prefWidthProperty()
        .bind(root.widthProperty().multiply(0.43));

                stage.show();
        }

        private static VBox createLeftPanel() {

    VBox panel = new VBox();

    panel.setAlignment(Pos.TOP_CENTER);
    panel.setFillWidth(true);

    panel.setPadding(
            new Insets(28, 35, 25, 35)
    );

    panel.setSpacing(0);

    panel.setStyle(
            "-fx-background-color: #F3F5FF;"
    );

    // ============================================================
    // 1. LOGO
    // ============================================================

    HBox brand = createBrand();

    brand.setAlignment(Pos.CENTER_LEFT);

    brand.setMaxWidth(Double.MAX_VALUE);

    // ============================================================
    // 2. HERO TEXT
    // ============================================================

    VBox hero = new VBox();

    hero.setAlignment(Pos.TOP_LEFT);

    hero.setSpacing(0);

    hero.setPadding(
            new Insets(38, 0, 0, 5)
    );

    hero.setMaxWidth(650);

    Label title1 = new Label(
            "Everything You Need,"
    );

    title1.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 34px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #12254A;"
    );

    Label title2 = new Label(
            "On Rent."
    );

    title2.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 34px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #3657D6;"
    );

    Label description = new Label(
            "From electronics to furniture, tools to appliances –\n" +
            "rent everything you need in just a few clicks."
    );

    description.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 15px;" +
            "-fx-text-fill: #405577;" +
            "-fx-line-spacing: 5px;"
    );

    description.setPadding(
            new Insets(12, 0, 0, 0)
    );

    hero.getChildren().addAll(
            title1,
            title2,
            description
    );

    // ============================================================
    // 3. FEATURES
    // ============================================================

    HBox features = new HBox();

    features.setAlignment(Pos.CENTER);
    features.setSpacing(15);

    features.setPadding(
            new Insets(26, 0, 12, 0)
    );

    features.setMaxWidth(520);

    features.getChildren().addAll(

            createFeature(
                    "home",
                    "Wide Range",
                    "of Products"
            ),

            createFeature(
                    "shield",
                    "Safe &",
                    "Trusted"
            ),

            createFeature(
                    "offer",
                    "Affordable",
                    "Prices"
            ),

            createFeature(
                    "support",
                    "24/7",
                    "Support"
            )
    );

    // ============================================================
    // 4. PRODUCT IMAGE
    // ============================================================

    Image image = new Image(
            CustomerLoginScreen.class
                    .getResource(
                            "/images/customer-login-products.png"
                    )
                    .toExternalForm()
    );

    ImageView products = new ImageView(image);

    products.setPreserveRatio(true);
    products.setSmooth(true);

    products.setFitWidth(620);
    products.setFitHeight(340);

    StackPane imageContainer = new StackPane();

    imageContainer.setAlignment(
            Pos.CENTER
    );

    imageContainer.setMaxWidth(
            Double.MAX_VALUE
    );

    imageContainer.setPadding(
            new Insets(0, 0, 0, 0)
    );

    imageContainer.getChildren().add(
            products
    );

    // ============================================================
    // 5. STATISTICS
    // ============================================================

    HBox stats = createStats();

    StackPane statsContainer = new StackPane();

    statsContainer.setAlignment(
            Pos.CENTER
    );

    statsContainer.setMaxWidth(
            Double.MAX_VALUE
    );

    statsContainer.setPadding(
            new Insets(5, 0, 0, 0)
    );

    statsContainer.getChildren().add(
            stats
    );

    // ============================================================
    // ADD EVERYTHING
    // ============================================================

    panel.getChildren().addAll(
            brand,
            hero,
            features,
            imageContainer,
            statsContainer
    );

    return panel;
}

private static HBox createStats() {

    HBox stats = new HBox();

    stats.setAlignment(
            Pos.CENTER
    );

    stats.setSpacing(0);

    stats.setPrefWidth(540);
    stats.setMinWidth(540);
    stats.setMaxWidth(540);

    stats.setPadding(
            new Insets(12, 10, 12, 10)
    );

    stats.setStyle(
            "-fx-background-color: rgba(255,255,255,0.96);" +
            "-fx-background-radius: 16px;" +
            "-fx-border-color: #D6DFF2;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 16px;"
    );

    VBox customers = createStat(
            "10K+",
            "Happy Customers"
    );

    VBox rating = createStat(
            "4.8/5",
            "User Rating"
    );

    VBox categories = createStat(
            "100+",
            "Categories"
    );

    stats.getChildren().addAll(
            customers,
            createDivider(),
            rating,
            createDivider(),
            categories
    );

    return stats;
}

private static VBox createStat(
        String number,
        String description) {

    Label numberLabel = new Label(
            number
    );

    numberLabel.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #3657D6;"
    );

    Label descriptionLabel = new Label(
            description
    );

    descriptionLabel.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 10px;" +
            "-fx-text-fill: #405577;"
    );

    VBox box = new VBox();

    box.setAlignment(
            Pos.CENTER
    );

    box.setSpacing(3);

    box.setPrefWidth(170);
    box.setMinWidth(170);
    box.setMaxWidth(170);

    box.getChildren().addAll(
            numberLabel,
            descriptionLabel
    );

    return box;
}

private static Line createDivider() {

    Line line = new Line(
            0,
            0,
            0,
            35
    );

    line.setStroke(
            Color.web("#D6DFF2")
    );

    line.setStrokeWidth(
            1
    );

    return line;
}
private static VBox createFeature(
        String iconType,
        String titleText,
        String subtitleText) {

    SVGPath icon = createIcon(iconType);

    icon.setScaleX(0.85);
    icon.setScaleY(0.85);

    Label title = new Label(
            titleText
    );

    title.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #13294B;"
    );

    Label subtitle = new Label(
            subtitleText
    );

    subtitle.setStyle(
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #405577;"
    );

    VBox box = new VBox();

    box.setAlignment(
            Pos.CENTER
    );

    box.setSpacing(5);

    box.setPrefWidth(115);
    box.setMinWidth(115);
    box.setMaxWidth(115);

    box.getChildren().addAll(
            icon,
            title,
            subtitle
    );

    return box;
}

private static SVGPath createIcon(String type) {

    SVGPath icon = new SVGPath();

    icon.setFill(Color.TRANSPARENT);
    icon.setStroke(Color.web("#5268D8"));
    icon.setStrokeWidth(1.8);

    switch (type) {

        // =========================
        // HOME - OUTLINE
        // =========================
        case "home":

            icon.setContent(
                "M3 11 " +
                "L12 3 " +
                "L21 11 " +
                "M5 10 V21 H19 V10 " +
                "M9 21 V15 H15 V21"
            );

            break;


        // =========================
        // SHIELD - OUTLINE + CHECK
        // =========================
        case "shield":

            icon.setContent(
                "M12 3 " +
                "L19 6 V11 " +
                "C19 16 16 20 12 21 " +
                "C8 20 5 16 5 11 V6 Z " +

                "M8.5 12 " +
                "L11 14.5 " +
                "L15.5 9.5"
            );

            break;


        // =========================
        // TAG - OUTLINE
        // =========================
        case "offer":

            icon.setContent(
                "M20 13 " +
                "L13 20 " +
                "C12.45 20.55 11.55 20.55 11 20 " +
                "L3 12 " +
                "V4 H11 L20 13 Z " +

                "M7.5 7.5 " +
                "A1.5 1.5 0 1 0 7.5 7.51"
            );

            break;


        // =========================
        // HEADSET - OUTLINE
        // =========================
        case "support":

            icon.setContent(
                "M4 13 " +
                "V11 " +
                "C4 6.03 7.58 3 12 3 " +
                "C16.42 3 20 6.03 20 11 V13 " +

                "M4 13 H6 V18 H4 Z " +
                "M18 13 H20 V18 H18 Z " +

                "M18 18 " +
                "C18 20 16 21 14 21 H12"
            );

            break;


        default:

            icon.setContent(
                "M12 3 " +
                "A9 9 0 1 0 12 21 " +
                "A9 9 0 1 0 12 3"
            );
    }

    icon.setScaleX(1.0);
    icon.setScaleY(1.0);

    return icon;
}

      private static HBox createBrand() {

    Image image = new Image(
            CustomerLoginScreen.class
                    .getResource(
                            "/images/logo.png"
                    )
                    .toExternalForm()
    );

    ImageView logo = new ImageView(image);

    logo.setFitWidth(300);
    logo.setFitHeight(90);

    logo.setPreserveRatio(true);
    logo.setSmooth(true);

    HBox brand = new HBox(
            logo
    );

    brand.setAlignment(
            Pos.CENTER_LEFT
    );

    brand.setPadding(
            new Insets(0, 0, 0, 10)
    );

    return brand;
}

        private static StackPane createLoginPanel(
                        Stage stage) {

                VBox card = new VBox();

                card.setPrefWidth(470);
                card.setMaxWidth(470);

                card.setPadding(
                                new Insets(32, 32, 28, 32));

                card.setSpacing(10);

                card.setStyle(
        "-fx-background-color: white;" +
        "-fx-border-color: #E1E6F0;" +
        "-fx-border-width: 1px;" +
        "-fx-border-radius: 20px;" +
        "-fx-background-radius: 20px;" +
        "-fx-effect: dropshadow(gaussian, rgba(20,40,80,0.15), 25, 0.2, 0, 8);"
                );

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