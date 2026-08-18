package com.rentsathi.ui.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class OwnerCreateScreen {

    private Stage stage;

    // =============================================================
    // FORM FIELDS
    // =============================================================

    private TextField fullNameField;
    private TextField phoneField;
    private TextField emailField;
    private TextField businessNameField;

    private PasswordField passwordField;
    private PasswordField confirmPasswordField;

    private TextField addressField;
    private TextField cityField;

    // =============================================================
    // ERROR LABELS
    // =============================================================

    private Label fullNameErrorLabel;
    private Label phoneErrorLabel;
    private Label emailErrorLabel;
    private Label passwordErrorLabel;
    private Label confirmPasswordErrorLabel;
    private Label addressErrorLabel;
    private Label cityErrorLabel;

    // =============================================================
    // COLORS
    // =============================================================

    private static final String BLUE = "#3657C8";
    private static final String DARK_BLUE = "#29476B";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String BORDER = "#C8CBD9";
    private static final String ERROR = "#C62828";

    // =============================================================
    // CONSTRUCTOR
    // =============================================================

    public OwnerCreateScreen(Stage stage) {
        this.stage = stage;
    }

    // =============================================================
    // SHOW PAGE
    // =============================================================

    public void show() {

        // =========================================================
        // ROOT
        // =========================================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        // =========================================================
        // LEFT PANEL
        // =========================================================

        VBox leftPanel = createLeftPanel();

        leftPanel.setPrefWidth(750);

        // =========================================================
        // RIGHT PANEL
        // =========================================================

        VBox rightPanel = createRightPanel();

        rightPanel.setPrefWidth(750);

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
                "RentSathi - Owner Create Account"
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
                new Insets(38, 50, 40, 50)
        );

        panel.setSpacing(20);

        panel.setStyle(
                "-fx-background-color: #F0F0FA;"
        );

        // =========================================================
        // BRAND
        // =========================================================

        HBox brand = createBrand();

        // =========================================================
        // IMAGE
        // =========================================================

        StackPane imageContainer =
                new StackPane();

        imageContainer.setPrefHeight(500);

        imageContainer.setMaxHeight(
                Double.MAX_VALUE
        );

        try {

            Image image = new Image(
                    OwnerCreateScreen.class
                            .getResource(
                                    "/images/owner-create-illustration.png"
                            )
                            .toExternalForm()
            );

            ImageView imageView =
                    new ImageView(image);

            imageView.setFitWidth(650);
            imageView.setFitHeight(500);

            imageView.setPreserveRatio(false);

            imageContainer.getChildren().add(
                    imageView
            );

        } catch (Exception e) {

            // Fallback if image is not available
            imageContainer.setStyle(
                    "-fx-background-color: #E8EAF5;"
            );
        }

        // =========================================================
        // BOTTOM TEXT
        // =========================================================

        VBox bottomText =
                new VBox(8);

        Label title =
                new Label(
                        "Become a RentSathi Owner"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web("#111827")
        );

        Label description =
                new Label(
                        "List your rental items and start earning on our\n"
                                + "trusted, secure platform designed for\n"
                                + "professionals."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        16
                )
        );

        description.setTextFill(
                Color.web(DARK_BLUE)
        );

        description.setWrapText(true);

        bottomText.getChildren().addAll(
                title,
                description
        );

        // =========================================================
        // ADD CONTENT
        // =========================================================

        panel.getChildren().addAll(
                brand,
                imageContainer,
                bottomText
        );

        return panel;
    }

    // =============================================================
    // BRAND
    // =============================================================

    private HBox createBrand() {

        HBox brand =
                new HBox(12);

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        // Logo
        try {

            Image image = new Image(
                    OwnerCreateScreen.class
                            .getResource(
                                    "/images/logo.png"
                            )
                            .toExternalForm()
            );

            ImageView logo =
                    new ImageView(image);

            logo.setFitWidth(45);
            logo.setFitHeight(45);
            logo.setPreserveRatio(true);

            brand.getChildren().add(
                    logo
            );

        } catch (Exception e) {

            Label logoText =
                    new Label("🏠");

            logoText.setFont(
                    Font.font(
                            "Arial",
                            32
                    )
            );

            brand.getChildren().add(
                    logoText
            );
        }

        VBox text =
                new VBox(2);

        Label title =
                new Label(
                        "RentSathi"
                );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label tagline =
                new Label(
                        "Rent Smart. Live Easy."
                );

        tagline.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #111827;"
        );

        text.getChildren().addAll(
                title,
                tagline
        );

        brand.getChildren().add(
                text
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
                new Insets(42, 80, 25, 80)
        );

        panel.setSpacing(8);

        panel.setStyle(
                "-fx-background-color: white;"
        );

        // =========================================================
        // HEADING
        // =========================================================

        Label title =
                new Label(
                        "Create Account"
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
                        "List your rental items and start earning."
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
                new VBox(5);

        heading.getChildren().addAll(
                title,
                subtitle
        );

        // =========================================================
        // FULL NAME + PHONE
        // =========================================================

        Label fullNameLabel =
                createLabel(
                        "Full Name",
                        true
                );

        fullNameField =
                createTextField(
                        "Jane Doe"
                );

        fullNameErrorLabel =
                createErrorLabel(
                        "Please enter your full name."
                );

        fullNameErrorLabel.setVisible(false);
        fullNameErrorLabel.setManaged(false);

        VBox fullNameBox =
                new VBox(4);

        fullNameBox.getChildren().addAll(
                fullNameLabel,
                fullNameField,
                fullNameErrorLabel
        );

        // Phone

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
                        "Please enter a valid phone number."
                );

        phoneErrorLabel.setVisible(false);
        phoneErrorLabel.setManaged(false);

        VBox phoneBox =
                new VBox(4);

        phoneBox.getChildren().addAll(
                phoneLabel,
                phoneField,
                phoneErrorLabel
        );

        HBox namePhoneRow =
                new HBox(15);

        HBox.setHgrow(
                fullNameBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                phoneBox,
                Priority.ALWAYS
        );

        namePhoneRow.getChildren().addAll(
                fullNameBox,
                phoneBox
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
                        "owner@example.com"
                );

        emailErrorLabel =
                createErrorLabel(
                        "Please enter a valid email address."
                );

        emailErrorLabel.setVisible(false);
        emailErrorLabel.setManaged(false);

        VBox emailBox =
                new VBox(4);

        emailBox.getChildren().addAll(
                emailLabel,
                emailField,
                emailErrorLabel
        );

        // =========================================================
        // BUSINESS / OWNER NAME
        // =========================================================

        Label businessLabel =
                createLabel(
                        "Business / Owner Name",
                        false
                );

        businessNameField =
                createTextField(
                        "Doe Rentals LLC"
                );

        VBox businessBox =
                new VBox(4);

        businessBox.getChildren().addAll(
                businessLabel,
                businessNameField
        );

        // =========================================================
        // PASSWORD + CONFIRM PASSWORD
        // =========================================================

        Label passwordLabel =
                createLabel(
                        "Password",
                        true
                );

        passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "••••••••"
        );

        stylePasswordField(
                passwordField
        );

        passwordErrorLabel =
                createErrorLabel(
                        "Password must be at least 8 characters."
                );

        passwordErrorLabel.setVisible(false);
        passwordErrorLabel.setManaged(false);

        VBox passwordBox =
                new VBox(4);

        passwordBox.getChildren().addAll(
                passwordLabel,
                passwordField,
                passwordErrorLabel
        );

        Label confirmPasswordLabel =
                createLabel(
                        "Confirm Password",
                        true
                );

        confirmPasswordField =
                new PasswordField();

        confirmPasswordField.setPromptText(
                "••••••••"
        );

        stylePasswordField(
                confirmPasswordField
        );

        confirmPasswordErrorLabel =
                createErrorLabel(
                        "Passwords do not match."
                );

        confirmPasswordErrorLabel.setVisible(false);
        confirmPasswordErrorLabel.setManaged(false);

        VBox confirmPasswordBox =
                new VBox(4);

        confirmPasswordBox.getChildren().addAll(
                confirmPasswordLabel,
                confirmPasswordField,
                confirmPasswordErrorLabel
        );

        HBox passwordRow =
                new HBox(15);

        HBox.setHgrow(
                passwordBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                confirmPasswordBox,
                Priority.ALWAYS
        );

        passwordRow.getChildren().addAll(
                passwordBox,
                confirmPasswordBox
        );

        // =========================================================
        // ADDRESS + CITY
        // =========================================================

        Label addressLabel =
                createLabel(
                        "Address",
                        true
                );

        addressField =
                createTextField(
                        "123 Main St"
                );

        addressErrorLabel =
                createErrorLabel(
                        "Please enter your address."
                );

        addressErrorLabel.setVisible(false);
        addressErrorLabel.setManaged(false);

        VBox addressBox =
                new VBox(4);

        addressBox.getChildren().addAll(
                addressLabel,
                addressField,
                addressErrorLabel
        );

        Label cityLabel =
                createLabel(
                        "City",
                        true
                );

        cityField =
                createTextField(
                        "New York"
                );

        cityErrorLabel =
                createErrorLabel(
                        "Please enter your city."
                );

        cityErrorLabel.setVisible(false);
        cityErrorLabel.setManaged(false);

        VBox cityBox =
                new VBox(4);

        cityBox.getChildren().addAll(
                cityLabel,
                cityField,
                cityErrorLabel
        );

        HBox addressRow =
                new HBox(15);

        HBox.setHgrow(
                addressBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                cityBox,
                Priority.ALWAYS
        );

        addressRow.getChildren().addAll(
                addressBox,
                cityBox
        );

        // =========================================================
        // TERMS
        // =========================================================

        CheckBox termsCheckBox =
                new CheckBox();

        Label termsText =
                new Label(
                        "I agree to the "
                );

        Label termsLink =
                new Label(
                        "Terms and Conditions"
                );

        termsLink.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        Label privacyText =
                new Label(
                        " and Privacy Policy."
                );

        HBox terms =
                new HBox(3);

        terms.setAlignment(
                Pos.CENTER_LEFT
        );

        terms.getChildren().addAll(
                termsCheckBox,
                termsText,
                termsLink,
                privacyText
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
                "-fx-background-color: #F1F3FB;" +
                "-fx-border-color: #C8CBD9;" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        Label infoIcon =
                new Label("ⓘ");

        infoIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        infoIcon.setTextFill(
                Color.web(DARK_BLUE)
        );

        Label infoText =
                new Label(
                        "Your owner account may require verification before listing\n"
                                + "rentals."
                );

        infoText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        infoText.setTextFill(
                Color.web("#4B5563")
        );

        infoText.setWrapText(true);

        informationBox.getChildren().addAll(
                infoIcon,
                infoText
        );

        // =========================================================
        // CREATE OWNER ACCOUNT BUTTON
        // =========================================================

        Button createButton =
                new Button(
                        "Create Owner Account"
                );

        createButton.setMaxWidth(
                Double.MAX_VALUE
        );

        createButton.setPrefHeight(
                38
        );

        createButton.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        // =========================================================
        // SIGN IN
        // =========================================================

        Label alreadyAccount =
                new Label(
                        "Already have an account?"
                );

        alreadyAccount.setStyle(
                "-fx-text-fill: #4B5563;" +
                "-fx-font-size: 13px;"
        );

        Label signIn =
                new Label(
                        "Sign In"
                );

        signIn.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        signIn.setOnMouseClicked(
                event -> OwnerLoginScreen.show(stage)
        );

        HBox signInBox =
                new HBox(4);

        signInBox.setAlignment(
                Pos.CENTER
        );

        signInBox.getChildren().addAll(
                alreadyAccount,
                signIn
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

        // IMPORTANT:
        // Back button goes to Owner Login page
        backButton.setOnAction(
                event -> OwnerLoginScreen.show(stage)
        );

        // =========================================================
        // CREATE ACCOUNT ACTION
        // =========================================================

        createButton.setOnAction(
                event -> {

                    if (validateForm(
                            termsCheckBox
                    )) {

                        System.out.println(
                                "Owner Account Created"
                        );

                        System.out.println(
                                "Name: "
                                        + fullNameField.getText()
                        );

                        System.out.println(
                                "Phone: "
                                        + phoneField.getText()
                        );

                        System.out.println(
                                "Email: "
                                        + emailField.getText()
                        );

                        System.out.println(
                                "Business: "
                                        + businessNameField.getText()
                        );

                        System.out.println(
                                "Address: "
                                        + addressField.getText()
                        );

                        System.out.println(
                                "City: "
                                        + cityField.getText()
                        );

                        showSuccessMessage();
                    }
                }
        );

        // =========================================================
        // ADD CONTENT TO RIGHT PANEL
        // =========================================================

        panel.getChildren().addAll(

                heading,

                namePhoneRow,

                emailBox,

                businessBox,

                passwordRow,

                addressRow,

                terms,

                informationBox,

                createButton,

                signInBox,

                backButton
        );

        return panel;
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
                                : text + " (Optional)"
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
                38
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
    // PASSWORD FIELD STYLE
    // =============================================================

    private void stylePasswordField(
            PasswordField field
    ) {

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );
    }

    // =============================================================
    // ERROR LABEL
    // =============================================================

    private Label createErrorLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setTextFill(
                Color.web(ERROR)
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        11
                )
        );

        return label;
    }

    // =============================================================
    // VALIDATE FORM
    // =============================================================

    private boolean validateForm(
            CheckBox termsCheckBox
    ) {

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

            fullNameErrorLabel.setVisible(
                    true
            );

            fullNameErrorLabel.setManaged(
                    true
            );

            valid = false;

        } else {

            fullNameField.setStyle(
                    normalFieldStyle()
            );

            fullNameErrorLabel.setVisible(
                    false
            );

            fullNameErrorLabel.setManaged(
                    false
            );
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

            phoneErrorLabel.setVisible(
                    true
            );

            phoneErrorLabel.setManaged(
                    true
            );

            valid = false;

        } else {

            phoneField.setStyle(
                    normalFieldStyle()
            );

            phoneErrorLabel.setVisible(
                    false
            );

            phoneErrorLabel.setManaged(
                    false
            );
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

            emailErrorLabel.setVisible(
                    true
            );

            emailErrorLabel.setManaged(
                    true
            );

            valid = false;

        } else {

            emailField.setStyle(
                    normalFieldStyle()
            );

            emailErrorLabel.setVisible(
                    false
            );

            emailErrorLabel.setManaged(
                    false
            );
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

            passwordErrorLabel.setVisible(
                    true
            );

            passwordErrorLabel.setManaged(
                    true
            );

            valid = false;

        } else {

            passwordField.setStyle(
                    normalFieldStyle()
            );

            passwordErrorLabel.setVisible(
                    false
            );

            passwordErrorLabel.setManaged(
                    false
            );
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

            confirmPasswordErrorLabel.setVisible(
                    true
            );

            confirmPasswordErrorLabel.setManaged(
                    true
            );

            valid = false;

        } else {

            confirmPasswordField.setStyle(
                    normalFieldStyle()
            );

            confirmPasswordErrorLabel.setVisible(
                    false
            );

            confirmPasswordErrorLabel.setManaged(
                    false
            );
        }

        // =========================================================
        // ADDRESS
        // =========================================================

        String address =
                addressField.getText().trim();

        if (address.isEmpty()) {

            addressField.setStyle(
                    errorFieldStyle()
            );

            addressErrorLabel.setVisible(
                    true
            );

            addressErrorLabel.setManaged(
                    true
            );

            valid = false;

        } else {

            addressField.setStyle(
                    normalFieldStyle()
            );

            addressErrorLabel.setVisible(
                    false
            );

            addressErrorLabel.setManaged(
                    false
            );
        }

        // =========================================================
        // CITY
        // =========================================================

        String city =
                cityField.getText().trim();

        if (city.isEmpty()) {

            cityField.setStyle(
                    errorFieldStyle()
            );

            cityErrorLabel.setVisible(
                    true
            );

            cityErrorLabel.setManaged(
                    true
            );

            valid = false;

        } else {

            cityField.setStyle(
                    normalFieldStyle()
            );

            cityErrorLabel.setVisible(
                    false
            );

            cityErrorLabel.setManaged(
                    false
            );
        }

        // =========================================================
        // TERMS
        // =========================================================

        if (!termsCheckBox.isSelected()) {

            showAlert(
                    "Please accept the Terms and Conditions."
            );

            valid = false;
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
                "Owner Account Created"
        );

        alert.setContentText(
                "Your RentSathi owner account has been created successfully."
        );

        alert.showAndWait();
    }

    // =============================================================
    // ALERT
    // =============================================================

    private void showAlert(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alert.setTitle(
                "RentSathi"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}