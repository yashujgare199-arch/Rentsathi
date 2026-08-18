package com.rentsathi.ui.screens;

import java.io.File;
import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CreatePartnerScreen {

    private Stage stage;

    // =============================================================
    // COLORS
    // =============================================================

    private static final String BLUE = "#3657C8";
    private static final String DARK_BLUE = "#29476B";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String BORDER = "#C8CBD9";
    private static final String ERROR = "#C62828";

    // =============================================================
    // FORM FIELDS
    // =============================================================

    private TextField fullNameField;
    private TextField emailField;
    private TextField phoneField;

    private PasswordField passwordField;
    private PasswordField confirmPasswordField;

    private TextField addressField;
    private TextField cityField;
    private TextField stateField;
    private TextField vehicleNumberField;

    private ComboBox<String> vehicleTypeComboBox;

    // =============================================================
    // ERROR LABELS
    // =============================================================

    private Label fullNameErrorLabel;
    private Label emailErrorLabel;
    private Label phoneErrorLabel;
    private Label passwordErrorLabel;
    private Label confirmPasswordErrorLabel;
    private Label addressErrorLabel;
    private Label cityErrorLabel;
    private Label stateErrorLabel;
    private Label vehicleTypeErrorLabel;
    private Label vehicleNumberErrorLabel;

    // =============================================================
    // CONSTRUCTOR
    // =============================================================

    public CreatePartnerScreen(Stage stage) {
        this.stage = stage;
    }

    // =============================================================
    // SHOW PAGE
    // =============================================================

    public void show() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        // =========================================================
        // LEFT PANEL
        // =========================================================

        VBox leftPanel = createLeftPanel();

        leftPanel.prefWidthProperty().bind(
                root.widthProperty().multiply(0.5)
        );

        // =========================================================
        // RIGHT PANEL
        // =========================================================

        VBox rightPanel = createRightPanel();

        rightPanel.prefWidthProperty().bind(
                root.widthProperty().multiply(0.5)
        );

        // =========================================================
        // MAIN LAYOUT
        // =========================================================

        root.setLeft(leftPanel);
        root.setCenter(rightPanel);

        // =========================================================
        // SCENE
        // =========================================================

        Scene scene = new Scene(
                root,
                1500,
                830
        );

        scene.setFill(
                Color.web(BACKGROUND)
        );

        stage.setTitle(
                "RentSathi - Create Partner Account"
        );

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    // =============================================================
    // LEFT PANEL
    // =============================================================

    private VBox createLeftPanel() {

        VBox panel = new VBox();

        panel.setPrefWidth(750);

        panel.setPadding(
                new Insets(35, 35, 40, 35)
        );

        panel.setSpacing(20);

        panel.setStyle(
                "-fx-background-color: #17191f;"
        );

        // =========================================================
        // BACKGROUND IMAGE
        // =========================================================

        try {

            Image image = loadImage(
                    "/images/delivery-partner.png"
            );

            ImageView imageView =
                    new ImageView(image);

            imageView.setFitWidth(680);
            imageView.setFitHeight(750);

            imageView.setPreserveRatio(false);

            StackPane imagePane =
                    new StackPane();

            imagePane.setPrefWidth(680);
            imagePane.setPrefHeight(750);

            imagePane.getChildren().add(
                    imageView
            );

            // =====================================================
            // DARK OVERLAY
            // =====================================================

            javafx.scene.shape.Rectangle overlay =
                    new javafx.scene.shape.Rectangle();

            overlay.setFill(
                    Color.rgb(0, 0, 0, 0.45)
            );

            overlay.widthProperty().bind(
                    imagePane.widthProperty()
            );

            overlay.heightProperty().bind(
                    imagePane.heightProperty()
            );

            // =====================================================
            // BRAND
            // =====================================================

            HBox brand = createBrand();

            StackPane.setAlignment(
                    brand,
                    Pos.TOP_LEFT
            );

            StackPane.setMargin(
                    brand,
                    new Insets(25, 0, 0, 25)
            );

            // =====================================================
            // BOTTOM TEXT
            // =====================================================

            VBox bottomContent =
                    new VBox(8);

            Label title =
                    new Label(
                            "Become a\nDelivery Partner"
                    );

            title.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            43
                    )
            );

            title.setTextFill(
                    Color.WHITE
            );

            Label subtitle =
                    new Label(
                            "Deliver rentals and earn with RentSathi."
                    );

            subtitle.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            16
                    )
            );

            subtitle.setTextFill(
                    Color.WHITE
            );

            Label description =
                    new Label(
                            "Join our network of trusted delivery professionals."
                    );

            description.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            14
                    )
            );

            description.setTextFill(
                    Color.web("#E5E7EB")
            );

            bottomContent.getChildren().addAll(
                    title,
                    subtitle,
                    description
            );

            StackPane.setAlignment(
                    bottomContent,
                    Pos.BOTTOM_LEFT
            );

            StackPane.setMargin(
                    bottomContent,
                    new Insets(0, 25, 35, 30)
            );

            imagePane.getChildren().addAll(
                    overlay,
                    brand,
                    bottomContent
            );

            panel.getChildren().add(
                    imagePane
            );

            VBox.setVgrow(
                    imagePane,
                    Priority.ALWAYS
            );

        } catch (Exception e) {

            panel.setStyle(
                    "-fx-background-color: #374577;"
            );

            HBox brand = createBrand();

            Label title =
                    new Label(
                            "Become a\nDelivery Partner"
                    );

            title.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            43
                    )
            );

            title.setTextFill(
                    Color.WHITE
            );

            Label subtitle =
                    new Label(
                            "Deliver rentals and earn with RentSathi."
                    );

            subtitle.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            16
                    )
            );

            subtitle.setTextFill(
                    Color.WHITE
            );

            Label description =
                    new Label(
                            "Join our network of trusted delivery professionals."
                    );

            description.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            14
                    )
            );

            description.setTextFill(
                    Color.WHITE
            );

            VBox text =
                    new VBox(
                            8,
                            title,
                            subtitle,
                            description
                    );

            VBox.setVgrow(
                    text,
                    Priority.ALWAYS
            );

            panel.getChildren().addAll(
                    brand,
                    text
            );
        }

        return panel;
    }

    // =============================================================
    // BRAND
    // =============================================================

    private HBox createBrand() {

        HBox brand =
                new HBox(10);

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label("🚚");

        icon.setFont(
                Font.font(
                        "Arial",
                        25
                )
        );

        Label title =
                new Label(
                        "RentSathi"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.WHITE
        );

        brand.getChildren().addAll(
                icon,
                title
        );

        return brand;
    }

    // =============================================================
    // RIGHT PANEL
    // =============================================================

    private VBox createRightPanel() {

        VBox panel =
                new VBox();

        panel.setPadding(
                new Insets(32, 75, 22, 75)
        );

        panel.setSpacing(7);

        panel.setStyle(
                "-fx-background-color: white;"
        );

        // =========================================================
        // HEADING
        // =========================================================

        Label title =
                new Label(
                        "Create Partner Account"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        31
                )
        );

        title.setTextFill(
                Color.web("#111827")
        );

        Label subtitle =
                new Label(
                        "Enter your details to start the application process."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        subtitle.setTextFill(
                Color.web(DARK_BLUE)
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        // =========================================================
        // FULL NAME
        // =========================================================

        Label fullNameLabel =
                createLabel(
                        "Full Name",
                        true
                );

        fullNameField =
                createTextField(
                        "John Doe"
                );

        fullNameErrorLabel =
                createErrorLabel(
                        "Full name is required."
                );

        fullNameErrorLabel.setVisible(false);
        fullNameErrorLabel.setManaged(false);

        VBox fullNameBox =
                new VBox(
                        4,
                        fullNameLabel,
                        fullNameField,
                        fullNameErrorLabel
                );

        // =========================================================
        // EMAIL
        // =========================================================

        Label emailLabel =
                createLabel(
                        "Email Address",
                        true
                );

        emailField =
                createTextField(
                        "john@example.com"
                );

        emailErrorLabel =
                createErrorLabel(
                        "Please enter a valid email address."
                );

        emailErrorLabel.setVisible(false);
        emailErrorLabel.setManaged(false);

        VBox emailBox =
                new VBox(
                        4,
                        emailLabel,
                        emailField,
                        emailErrorLabel
                );

        // =========================================================
        // NAME + EMAIL ROW
        // =========================================================

        HBox nameEmailRow =
                new HBox(24);

        HBox.setHgrow(
                fullNameBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                emailBox,
                Priority.ALWAYS
        );

        nameEmailRow.getChildren().addAll(
                fullNameBox,
                emailBox
        );

        // =========================================================
        // PHONE
        // =========================================================

        Label phoneLabel =
                createLabel(
                        "Phone Number",
                        true
                );

        phoneField =
                createTextField(
                        "+1 (555) 000-0000"
                );

        phoneErrorLabel =
                createErrorLabel(
                        "Phone number is required."
                );

        phoneErrorLabel.setVisible(false);
        phoneErrorLabel.setManaged(false);

        VBox phoneBox =
                new VBox(
                        4,
                        phoneLabel,
                        phoneField,
                        phoneErrorLabel
                );

        // =========================================================
        // PASSWORD
        // =========================================================

        Label passwordLabel =
                createLabel(
                        "Password",
                        true
                );

        HBox passwordBox =
                createPasswordBox(
                        false
                );

        passwordErrorLabel =
                createErrorLabel(
                        "Password must be at least 8 characters."
                );

        passwordErrorLabel.setVisible(false);
        passwordErrorLabel.setManaged(false);

        VBox passwordContainer =
                new VBox(
                        4,
                        passwordLabel,
                        passwordBox,
                        passwordErrorLabel
                );

        // =========================================================
        // CONFIRM PASSWORD
        // =========================================================

        Label confirmPasswordLabel =
                createLabel(
                        "Confirm Password",
                        true
                );

        HBox confirmPasswordBox =
                createPasswordBox(
                        true
                );

        confirmPasswordErrorLabel =
                createErrorLabel(
                        "Passwords do not match."
                );

        confirmPasswordErrorLabel.setVisible(false);
        confirmPasswordErrorLabel.setManaged(false);

        VBox confirmPasswordContainer =
                new VBox(
                        4,
                        confirmPasswordLabel,
                        confirmPasswordBox,
                        confirmPasswordErrorLabel
                );

        // =========================================================
        // PASSWORD ROW
        // =========================================================

        HBox passwordRow =
                new HBox(24);

        HBox.setHgrow(
                passwordContainer,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                confirmPasswordContainer,
                Priority.ALWAYS
        );

        passwordRow.getChildren().addAll(
                passwordContainer,
                confirmPasswordContainer
        );

        // =========================================================
        // SEPARATOR
        // =========================================================

        javafx.scene.shape.Line separator =
                new javafx.scene.shape.Line();

        separator.setStroke(
                Color.web(BORDER)
        );

        separator.setStrokeWidth(1);

        separator.setStartX(0);
        separator.setEndX(450);

        // =========================================================
        // ADDRESS
        // =========================================================

        Label addressLabel =
                createLabel(
                        "Street Address",
                        true
                );

        addressField =
                createTextField(
                        "123 Delivery Route Ln."
                );

        addressErrorLabel =
                createErrorLabel(
                        "Street address is required."
                );

        addressErrorLabel.setVisible(false);
        addressErrorLabel.setManaged(false);

        VBox addressBox =
                new VBox(
                        4,
                        addressLabel,
                        addressField,
                        addressErrorLabel
                );

        // =========================================================
        // CITY
        // =========================================================

        Label cityLabel =
                createLabel(
                        "City",
                        true
                );

        cityField =
                createTextField(
                        "Metropolis"
                );

        cityErrorLabel =
                createErrorLabel(
                        "City is required."
                );

        cityErrorLabel.setVisible(false);
        cityErrorLabel.setManaged(false);

        VBox cityBox =
                new VBox(
                        4,
                        cityLabel,
                        cityField,
                        cityErrorLabel
                );

        // =========================================================
        // STATE
        // =========================================================

        Label stateLabel =
                createLabel(
                        "State/Province",
                        true
                );

        stateField =
                createTextField(
                        "NY"
                );

        stateErrorLabel =
                createErrorLabel(
                        "State/Province is required."
                );

        stateErrorLabel.setVisible(false);
        stateErrorLabel.setManaged(false);

        VBox stateBox =
                new VBox(
                        4,
                        stateLabel,
                        stateField,
                        stateErrorLabel
                );

        // =========================================================
        // CITY + STATE ROW
        // =========================================================

        HBox cityStateRow =
                new HBox(24);

        HBox.setHgrow(
                cityBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                stateBox,
                Priority.ALWAYS
        );

        cityStateRow.getChildren().addAll(
                cityBox,
                stateBox
        );

        // =========================================================
        // VEHICLE DETAILS BOX
        // =========================================================

        VBox vehicleDetails =
                new VBox(8);

        vehicleDetails.setPadding(
                new Insets(12, 15, 12, 15)
        );

        vehicleDetails.setStyle(
                "-fx-background-color: #F1F3FB;" +
                "-fx-border-color: #C8CBD9;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );

        Label vehicleTitle =
                new Label(
                        "Vehicle Details"
                );

        vehicleTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        16
                )
        );

        vehicleTitle.setTextFill(
                Color.web("#111827")
        );

        // =========================================================
        // VEHICLE TYPE
        // =========================================================

        Label vehicleTypeLabel =
                createLabel(
                        "Vehicle Type",
                        true
                );

        vehicleTypeComboBox =
                new ComboBox<>();

        vehicleTypeComboBox.getItems().addAll(
                "Car",
                "Van",
                "Pickup Truck",
                "Mini Truck",
                "SUV",
                "Motorcycle",
                "Other"
        );

        vehicleTypeComboBox.setPromptText(
                "Select vehicle type"
        );

        vehicleTypeComboBox.setPrefHeight(
                42
        );

        vehicleTypeComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        vehicleTypeComboBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;"
        );

        vehicleTypeErrorLabel =
                createErrorLabel(
                        "Please select a vehicle type."
                );

        vehicleTypeErrorLabel.setVisible(false);
        vehicleTypeErrorLabel.setManaged(false);

        VBox vehicleTypeBox =
                new VBox(
                        4,
                        vehicleTypeLabel,
                        vehicleTypeComboBox,
                        vehicleTypeErrorLabel
                );

        // =========================================================
        // VEHICLE NUMBER
        // =========================================================

        Label vehicleNumberLabel =
                createLabel(
                        "Vehicle Number (Plate)",
                        true
                );

        vehicleNumberField =
                createTextField(
                        "ABC-1234"
                );

        vehicleNumberErrorLabel =
                createErrorLabel(
                        "Vehicle number is required."
                );

        vehicleNumberErrorLabel.setVisible(false);
        vehicleNumberErrorLabel.setManaged(false);

        VBox vehicleNumberBox =
                new VBox(
                        4,
                        vehicleNumberLabel,
                        vehicleNumberField,
                        vehicleNumberErrorLabel
                );

        // =========================================================
        // VEHICLE ROW
        // =========================================================

        HBox vehicleRow =
                new HBox(16);

        HBox.setHgrow(
                vehicleTypeBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                vehicleNumberBox,
                Priority.ALWAYS
        );

        vehicleRow.getChildren().addAll(
                vehicleTypeBox,
                vehicleNumberBox
        );

        vehicleDetails.getChildren().addAll(
                vehicleTitle,
                vehicleRow
        );

        // =========================================================
        // INFORMATION BOX
        // =========================================================

        HBox informationBox =
                new HBox(10);

        informationBox.setAlignment(
                Pos.CENTER_LEFT
        );

        informationBox.setPadding(
                new Insets(9, 12, 9, 12)
        );

        informationBox.setStyle(
                "-fx-background-color: #EDF3FF;" +
                "-fx-border-color: #B9C8ED;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );

        Label infoIcon =
                new Label("ⓘ");

        infoIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        infoIcon.setTextFill(
                Color.web(DARK_BLUE)
        );

        Label infoText =
                new Label(
                        "Your application will be reviewed by RentSathi administrators.\n"
                                + "Please ensure all details are accurate before submitting."
                );

        infoText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        infoText.setTextFill(
                Color.web(DARK_BLUE)
        );

        informationBox.getChildren().addAll(
                infoIcon,
                infoText
        );

        // =========================================================
        // APPLY BUTTON
        // =========================================================

        Button applyButton =
                new Button(
                        "Apply as Delivery Partner   →"
                );

        applyButton.setMaxWidth(
                Double.MAX_VALUE
        );

        applyButton.setPrefHeight(
                45
        );

        applyButton.setStyle(
                "-fx-background-color: #3657C8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        // =========================================================
        // SIGN IN
        // =========================================================

        Label accountText =
                new Label(
                        "Already have an account?"
                );

        accountText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        Button signInButton =
                new Button(
                        "Sign In"
                );

        signInButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        signInButton.setOnAction(
                event ->DeliveryPartnerLoginScreen.show(stage)
        );

        HBox signInBox =
                new HBox(
                        4,
                        accountText,
                        signInButton
                );

        signInBox.setAlignment(
                Pos.CENTER
        );

        // =========================================================
        // BACK TO ROLE SELECTION
        // =========================================================

        Button backButton =
                new Button(
                        "← Back to Role Selection"
                );

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );

        // =========================================================
        // BACK BUTTON → DELIVERY LOGIN
        // =========================================================

        backButton.setOnAction(
                event -> DeliveryPartnerLoginScreen.show(stage)
        );

        // =========================================================
        // APPLY ACTION
        // =========================================================

        applyButton.setOnAction(
                event -> {

                    if (validateForm()) {

                        System.out.println(
                                "Delivery Partner Application Submitted"
                        );

                        System.out.println(
                                "Name: "
                                        + fullNameField.getText()
                        );

                        System.out.println(
                                "Email: "
                                        + emailField.getText()
                        );

                        System.out.println(
                                "Phone: "
                                        + phoneField.getText()
                        );

                        System.out.println(
                                "Address: "
                                        + addressField.getText()
                        );

                        System.out.println(
                                "City: "
                                        + cityField.getText()
                        );

                        System.out.println(
                                "State: "
                                        + stateField.getText()
                        );

                        System.out.println(
                                "Vehicle Type: "
                                        + vehicleTypeComboBox.getValue()
                        );

                        System.out.println(
                                "Vehicle Number: "
                                        + vehicleNumberField.getText()
                        );

                        showSuccessMessage();
                    }
                }
        );

        // =========================================================
        // ADD EVERYTHING TO RIGHT PANEL
        // =========================================================

        panel.getChildren().addAll(

                heading,

                nameEmailRow,

                phoneBox,

                passwordRow,

                separator,

                addressBox,

                cityStateRow,

                vehicleDetails,

                informationBox,

                applyButton,

                signInBox,

                backButton
        );

        return panel;
    }

    // =============================================================
    // CREATE TEXT FIELD
    // =============================================================

    private TextField createTextField(
            String prompt
    ) {

        TextField field =
                new TextField();

        field.setPromptText(
                prompt
        );

        field.setPrefHeight(
                42
        );

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );

        return field;
    }

    // =============================================================
    // CREATE LABEL
    // =============================================================

    private Label createLabel(
            String text,
            boolean required
    ) {

        Label label =
                new Label(
                        required
                                ? text + " *"
                                : text
                );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        label.setTextFill(
                Color.web("#111827")
        );

        return label;
    }

    // =============================================================
    // CREATE ERROR LABEL
    // =============================================================

    private Label createErrorLabel(
            String text
    ) {

        Label label =
                new Label(
                        "ⓘ " + text
                );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        12
                )
        );

        label.setTextFill(
                Color.web(ERROR)
        );

        return label;
    }

    // =============================================================
    // PASSWORD BOX
    // =============================================================

    private HBox createPasswordBox(
            boolean confirm
    ) {

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "••••••••"
        );

        password.setPrefHeight(
                42
        );

        password.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 40px 0 12px;"
        );

        Button visibilityButton =
                new Button(
                        "◉"
                );

        visibilityButton.setPrefWidth(
                42
        );

        visibilityButton.setPrefHeight(
                42
        );

        visibilityButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #64748B;" +
                "-fx-cursor: hand;"
        );

        StackPane passwordPane =
                new StackPane();

        passwordPane.getChildren().addAll(
                password,
                visibilityButton
        );

        StackPane.setAlignment(
                visibilityButton,
                Pos.CENTER_RIGHT
        );

        HBox.setHgrow(
                passwordPane,
                Priority.ALWAYS
        );

        visibilityButton.setOnAction(
                event -> {

                    // Password visibility is intentionally
                    // kept simple for this page.
                    Alert alert =
                            new Alert(
                                    Alert.AlertType.INFORMATION
                            );

                    alert.setTitle(
                            "Password"
                    );

                    alert.setHeaderText(
                            null
                    );

                    alert.setContentText(
                            "Password visibility control."
                    );

                    // Do not show popup in normal use
                    // This button can be upgraded later
                }
        );

        HBox box =
                new HBox(
                        passwordPane
                );

        if (confirm) {

            confirmPasswordField =
                    password;

        } else {

            passwordField =
                    password;
        }

        return box;
    }

    // =============================================================
    // VALIDATE FORM
    // =============================================================

    private boolean validateForm() {

        boolean valid = true;

        // =========================================================
        // FULL NAME
        // =========================================================

        String fullName =
                fullNameField.getText().trim();

        if (fullName.isEmpty()) {

            fullNameField.setStyle(
                    errorFieldStyle()
            );

            fullNameErrorLabel.setVisible(true);
            fullNameErrorLabel.setManaged(true);

            valid = false;

        } else {

            fullNameField.setStyle(
                    normalFieldStyle()
            );

            fullNameErrorLabel.setVisible(false);
            fullNameErrorLabel.setManaged(false);
        }

        // =========================================================
        // EMAIL
        // =========================================================

        String email =
                emailField.getText().trim();

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )) {

            emailField.setStyle(
                    errorFieldStyle()
            );

            emailErrorLabel.setVisible(true);
            emailErrorLabel.setManaged(true);

            valid = false;

        } else {

            emailField.setStyle(
                    normalFieldStyle()
            );

            emailErrorLabel.setVisible(false);
            emailErrorLabel.setManaged(false);
        }

        // =========================================================
        // PHONE
        // =========================================================

        String phone =
                phoneField.getText().trim();

        if (phone.isEmpty()) {

            phoneField.setStyle(
                    errorFieldStyle()
            );

            phoneErrorLabel.setVisible(true);
            phoneErrorLabel.setManaged(true);

            valid = false;

        } else {

            phoneField.setStyle(
                    normalFieldStyle()
            );

            phoneErrorLabel.setVisible(false);
            phoneErrorLabel.setManaged(false);
        }

        // =========================================================
        // PASSWORD
        // =========================================================

        String password =
                passwordField.getText();

        if (password.length() < 8) {

            passwordField.setStyle(
                    errorFieldStyle()
            );

            passwordErrorLabel.setVisible(true);
            passwordErrorLabel.setManaged(true);

            valid = false;

        } else {

            passwordField.setStyle(
                    normalFieldStyle()
            );

            passwordErrorLabel.setVisible(false);
            passwordErrorLabel.setManaged(false);
        }

        // =========================================================
        // CONFIRM PASSWORD
        // =========================================================

        String confirmPassword =
                confirmPasswordField.getText();

        if (!password.equals(
                confirmPassword
        )) {

            confirmPasswordField.setStyle(
                    errorFieldStyle()
            );

            confirmPasswordErrorLabel.setVisible(true);
            confirmPasswordErrorLabel.setManaged(true);

            valid = false;

        } else {

            confirmPasswordField.setStyle(
                    normalFieldStyle()
            );

            confirmPasswordErrorLabel.setVisible(false);
            confirmPasswordErrorLabel.setManaged(false);
        }

        // =========================================================
        // ADDRESS
        // =========================================================

        if (addressField.getText().trim().isEmpty()) {

            addressField.setStyle(
                    errorFieldStyle()
            );

            addressErrorLabel.setVisible(true);
            addressErrorLabel.setManaged(true);

            valid = false;

        } else {

            addressField.setStyle(
                    normalFieldStyle()
            );

            addressErrorLabel.setVisible(false);
            addressErrorLabel.setManaged(false);
        }

        // =========================================================
        // CITY
        // =========================================================

        if (cityField.getText().trim().isEmpty()) {

            cityField.setStyle(
                    errorFieldStyle()
            );

            cityErrorLabel.setVisible(true);
            cityErrorLabel.setManaged(true);

            valid = false;

        } else {

            cityField.setStyle(
                    normalFieldStyle()
            );

            cityErrorLabel.setVisible(false);
            cityErrorLabel.setManaged(false);
        }

        // =========================================================
        // STATE
        // =========================================================

        if (stateField.getText().trim().isEmpty()) {

            stateField.setStyle(
                    errorFieldStyle()
            );

            stateErrorLabel.setVisible(true);
            stateErrorLabel.setManaged(true);

            valid = false;

        } else {

            stateField.setStyle(
                    normalFieldStyle()
            );

            stateErrorLabel.setVisible(false);
            stateErrorLabel.setManaged(false);
        }

        // =========================================================
        // VEHICLE TYPE
        // =========================================================

        if (vehicleTypeComboBox.getValue() == null) {

            vehicleTypeErrorLabel.setVisible(true);
            vehicleTypeErrorLabel.setManaged(true);

            valid = false;

        } else {

            vehicleTypeErrorLabel.setVisible(false);
            vehicleTypeErrorLabel.setManaged(false);
        }

        // =========================================================
        // VEHICLE NUMBER
        // =========================================================

        if (vehicleNumberField.getText().trim().isEmpty()) {

            vehicleNumberField.setStyle(
                    errorFieldStyle()
            );

            vehicleNumberErrorLabel.setVisible(true);
            vehicleNumberErrorLabel.setManaged(true);

            valid = false;

        } else {

            vehicleNumberField.setStyle(
                    normalFieldStyle()
            );

            vehicleNumberErrorLabel.setVisible(false);
            vehicleNumberErrorLabel.setManaged(false);
        }

        return valid;
    }

    // =============================================================
    // NORMAL FIELD STYLE
    // =============================================================

    private String normalFieldStyle() {

        return
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;";
    }

    // =============================================================
    // ERROR FIELD STYLE
    // =============================================================

    private String errorFieldStyle() {

        return
                "-fx-background-color: white;" +
                "-fx-border-color: " + ERROR + ";" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;";
    }

    // =============================================================
    // SUCCESS MESSAGE
    // =============================================================

    private void showSuccessMessage() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "RentSathi"
        );

        alert.setHeaderText(
                "Application Submitted"
        );

        alert.setContentText(
                "Your Delivery Partner application has been submitted successfully.\n\n"
                        + "RentSathi administrators will review your application."
        );

        alert.showAndWait();
    }

    // =============================================================
    // LOAD IMAGE
    // =============================================================

    private Image loadImage(
            String resourcePath
    ) {

        URL resource =
                CreatePartnerScreen.class
                        .getResource(resourcePath);

        if (resource != null) {

            return new Image(
                    resource.toExternalForm()
            );
        }

        String relativePath =
                resourcePath.startsWith("/")
                        ? resourcePath.substring(1)
                        : resourcePath;

        File file =
                new File(
                        "src/main/resources",
                        relativePath
                );

        if (file.exists()) {

            return new Image(
                    file.toURI().toString()
            );
        }

        throw new RuntimeException(
                "Resource not found: "
                        + resourcePath
        );
    }
}
