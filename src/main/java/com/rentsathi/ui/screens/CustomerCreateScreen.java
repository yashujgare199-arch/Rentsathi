package com.rentsathi.ui.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CustomerCreateScreen {

    private Stage stage;

    // Form fields
    private TextField fullNameField;
    private TextField emailField;
    private TextField phoneField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;

    // Normal text fields used when password is visible
    private TextField visiblePasswordField;
    private TextField visibleConfirmPasswordField;

    // Error labels
    private Label emailErrorLabel;
    private Label phoneErrorLabel;
    private Label passwordErrorLabel;
    private Label confirmPasswordErrorLabel;

    // Password strength
    private Label strengthLabel;
    private Region strength1;
    private Region strength2;
    private Region strength3;

    public CustomerCreateScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        // =========================================================
        // LEFT SIDE
        // =========================================================

        VBox leftPanel = new VBox();
        leftPanel.setPadding(new Insets(35, 30, 30, 30));
        leftPanel.setSpacing(15);
        leftPanel.setPrefWidth(570);

        // Gradient background
        leftPanel.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #d7dcf7, #374577);"
        );

        // Logo / Brand
        HBox logoBox = new HBox(5);
        logoBox.setAlignment(Pos.CENTER_LEFT);

        Label logoIcon = new Label("real_estate_agent");
        logoIcon.setTextFill(Color.WHITE);
        logoIcon.setFont(Font.font("Arial", FontWeight.NORMAL, 22));

        Label logoText = new Label("RentSathi");
        logoText.setTextFill(Color.WHITE);
        logoText.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        logoBox.getChildren().addAll(logoIcon, logoText);

        Label tagline = new Label("Rent Smart. Live Easy.");
        tagline.setTextFill(Color.WHITE);
        tagline.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        // Bottom content
        VBox bottomContent = new VBox(12);

        Label mainText = new Label(
                "Find the right rental for your needs."
        );
        mainText.setTextFill(Color.WHITE);
        mainText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        mainText.setWrapText(true);

        Label description = new Label(
                "Join thousands of users who trust RentSathi for seamless, "
                        + "secure, and smart renting experiences."
        );
        description.setTextFill(Color.web("#e8eaf5"));
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        description.setWrapText(true);

        bottomContent.getChildren().addAll(
                mainText,
                description
        );

        VBox.setVgrow(bottomContent, Priority.ALWAYS);

        leftPanel.getChildren().addAll(
                logoBox,
                tagline,
                bottomContent
        );

        // =========================================================
        // RIGHT SIDE
        // =========================================================

        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(35, 70, 30, 70));
        rightPanel.setSpacing(7);
        rightPanel.setPrefWidth(610);
        rightPanel.setStyle("-fx-background-color: white;");

        // Heading
        Label title = new Label(
                "Create Your Customer\nAccount"
        );
        title.setFont(Font.font("Arial", FontWeight.BOLD, 31));
        title.setTextFill(Color.BLACK);

        Label subtitle = new Label(
                "Start finding the perfect rentals on RentSathi."
        );
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitle.setTextFill(Color.web("#263650"));

        VBox headingBox = new VBox(5);
        headingBox.getChildren().addAll(title, subtitle);

        // =========================================================
        // FULL NAME
        // =========================================================

        Label fullNameLabel = createLabel("Full Name *");

        fullNameField = createTextField("John Doe");

        // =========================================================
        // EMAIL
        // =========================================================

        Label emailLabel = createLabel("Email Address *");

        emailField = createTextField("johndoe@example.com");

        emailErrorLabel = createErrorLabel(
                "Please enter a valid email address."
        );

        emailErrorLabel.setVisible(false);
        emailErrorLabel.setManaged(false);

        // =========================================================
        // PHONE
        // =========================================================

        Label phoneLabel = createLabel("Phone Number *");

        phoneField = createTextField("9876543210");

        phoneErrorLabel = createErrorLabel(
                "Phone number must be 10 digits."
        );

        phoneErrorLabel.setVisible(false);
        phoneErrorLabel.setManaged(false);

        // Allow only numbers
        phoneField.textProperty().addListener((obs, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {
                phoneField.setText(
                        newValue.replaceAll("[^\\d]", "")
                );
            }

            if (phoneField.getText().length() > 10) {
                phoneField.setText(
                        phoneField.getText().substring(0, 10)
                );
            }
        });

        // =========================================================
        // PASSWORD
        // =========================================================

        Label passwordLabel = createLabel("Password *");

        HBox passwordBox = createPasswordBox(false);

        VBox strengthBox = createPasswordStrength();

        Label passwordHelp = new Label(
                "Must be at least 8 characters long."
        );
        passwordHelp.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        passwordHelp.setTextFill(Color.web("#17213c"));

        passwordErrorLabel = createErrorLabel(
                "Password must be at least 8 characters."
        );

        passwordErrorLabel.setVisible(false);
        passwordErrorLabel.setManaged(false);

        // =========================================================
        // CONFIRM PASSWORD
        // =========================================================

        Label confirmPasswordLabel = createLabel(
                "Confirm Password *"
        );

        HBox confirmPasswordBox = createConfirmPasswordBox();

        confirmPasswordErrorLabel = createErrorLabel(
                "Passwords do not match."
        );

        confirmPasswordErrorLabel.setVisible(false);
        confirmPasswordErrorLabel.setManaged(false);

        // =========================================================
        // ADDRESS + CITY
        // =========================================================

        Label addressLabel = createLabel("Address");

        TextField addressField = createTextField("Street Address");

        Label cityLabel = createLabel("City");

        TextField cityField = createTextField("City");

        HBox addressRow = new HBox(15);

        VBox addressBox = new VBox(5);
        VBox cityBox = new VBox(5);

        HBox.setHgrow(addressBox, Priority.ALWAYS);

        addressBox.getChildren().add(addressField);
        cityBox.getChildren().add(cityField);

        addressRow.getChildren().addAll(
                addressBox,
                cityBox
        );

        // =========================================================
        // TERMS CHECKBOX
        // =========================================================

        CheckBox termsCheckBox = new CheckBox();

        Label termsText = new Label(
                "I agree to the Terms and Conditions and Privacy Policy."
        );

        termsText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        termsText.setTextFill(Color.web("#27344f"));

        HBox termsBox = new HBox(8);
        termsBox.setAlignment(Pos.CENTER_LEFT);

        termsBox.getChildren().addAll(
                termsCheckBox,
                termsText
        );

        // =========================================================
        // CREATE ACCOUNT BUTTON
        // =========================================================

        Button createAccountButton = new Button(
                "Create Account"
        );

        createAccountButton.setMaxWidth(Double.MAX_VALUE);
        createAccountButton.setPrefHeight(40);

        createAccountButton.setStyle(
                "-fx-background-color: #354fc4;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );

        // =========================================================
        // SIGN IN
        // =========================================================

        Label alreadyAccount = new Label(
                "Already have an account?"
        );

        alreadyAccount.setFont(
                Font.font("Arial", FontWeight.NORMAL, 13)
        );

        alreadyAccount.setTextFill(
                Color.web("#35405b")
        );

        Button signInButton = new Button("Sign In");

        signInButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #294bc2;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0;"
        );

        // =========================================================
// SIGN IN ACTION
// =========================================================

        signInButton.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RentSathi");
                alert.setHeaderText("Sign In");
                alert.setContentText("Login screen will open here.");
                alert.showAndWait();
        });

        HBox signInBox = new HBox(4);
        signInBox.setAlignment(Pos.CENTER);

        signInBox.getChildren().addAll(
                alreadyAccount,
                signInButton
        );

        // =========================================================
// BACK TO ROLE SELECTION
// =========================================================

Button back = new Button(
        "← Back to Role Selection"
);

back.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-text-fill: #29476B;" +
        "-fx-font-size: 13px;" +
        "-fx-cursor: hand;"
);

back.setOnAction(
        event -> CustomerLoginScreen.show(stage)
);

        // =========================================================
        // CREATE ACCOUNT ACTION
        // =========================================================

        createAccountButton.setOnAction(event -> {

            boolean valid = validateForm(termsCheckBox);

            if (valid) {

                String name = fullNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String city = cityField.getText();

                System.out.println("Customer Account Created");
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Address: " + address);
                System.out.println("City: " + city);

                showSuccessMessage();
            }
        });

        // =========================================================
        // SIGN IN ACTION
        // =========================================================

        
        // =========================================================
        // ADD EVERYTHING TO RIGHT PANEL
        // =========================================================

        rightPanel.getChildren().addAll(

                headingBox,

                fullNameLabel,
                fullNameField,

                emailLabel,
                emailField,
                emailErrorLabel,

                phoneLabel,
                phoneField,
                phoneErrorLabel,

                passwordLabel,
                passwordBox,
                strengthBox,
                passwordHelp,
                passwordErrorLabel,

                confirmPasswordLabel,
                confirmPasswordBox,
                confirmPasswordErrorLabel,

                addressLabel,
                addressRow,

                termsBox,

                createAccountButton,

                signInBox,

                back
        );

        // =========================================================
        // MAIN LAYOUT
        // =========================================================

        HBox mainLayout = new HBox();

        mainLayout.getChildren().addAll(
                leftPanel,
                rightPanel
        );

        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        // Outer container
        StackPane root = new StackPane(mainLayout);

        root.setPadding(new Insets(15));
        root.setStyle(
                "-fx-background-color: #17191f;"
        );

        // =========================================================
        // SCENE
        // =========================================================

        Scene scene = new Scene(
                root,
                1220,
                900
        );

        stage.setTitle("RentSathi - Create Customer Account");
        stage.setScene(scene);
        stage.show();
    }

    // =============================================================
    // CREATE LABEL
    // =============================================================

    private Label createLabel(String text) {

        Label label = new Label(text);

        label.setFont(
                Font.font("Arial", FontWeight.BOLD, 12)
        );

        label.setTextFill(
                Color.web("#17213c")
        );

        return label;
    }

    // =============================================================
    // CREATE TEXT FIELD
    // =============================================================

    private TextField createTextField(String prompt) {

        TextField field = new TextField();

        field.setPromptText(prompt);

        field.setPrefHeight(38);

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #c8cedc;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 14px;"
        );

        return field;
    }

    // =============================================================
    // CREATE ERROR LABEL
    // =============================================================

    private Label createErrorLabel(String text) {

        Label label = new Label(text);

        label.setTextFill(
                Color.web("#d93025")
        );

        label.setFont(
                Font.font("Arial", FontWeight.NORMAL, 12)
        );

        return label;
    }

    // =============================================================
    // PASSWORD BOX
    // =============================================================

    private HBox createPasswordBox(boolean confirm) {

        PasswordField password = new PasswordField();

        password.setPromptText("Password");
        password.setPrefHeight(38);

        password.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #c8cedc;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 6 0 0 6;" +
                "-fx-background-radius: 6 0 0 6;" +
                "-fx-font-size: 14px;"
        );

        Button visibilityButton = new Button("visibility_off");

        visibilityButton.setPrefHeight(38);
        visibilityButton.setPrefWidth(120);

        visibilityButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #c8cedc;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 0 6 6 0;" +
                "-fx-text-fill: #64748b;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );

        HBox box = new HBox();

        HBox.setHgrow(password, Priority.ALWAYS);

        box.getChildren().addAll(
                password,
                visibilityButton
        );

        if (!confirm) {

            passwordField = password;

            visiblePasswordField =
                    new TextField();

            visiblePasswordField.setPrefHeight(38);
            visiblePasswordField.setVisible(false);
            visiblePasswordField.setManaged(false);

            visiblePasswordField.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #c8cedc;" +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 6 0 0 6;" +
                    "-fx-background-radius: 6 0 0 6;" +
                    "-fx-font-size: 14px;"
            );

            HBox.setHgrow(
                    visiblePasswordField,
                    Priority.ALWAYS
            );

            box.getChildren().add(
                    0,
                    visiblePasswordField
            );

            password.textProperty().addListener(
                    (obs, oldValue, newValue) -> {

                        visiblePasswordField.setText(newValue);

                        updatePasswordStrength(newValue);
                    }
            );

            visiblePasswordField.textProperty().addListener(
                    (obs, oldValue, newValue) -> {

                        if (visiblePasswordField.isFocused()) {

                            password.setText(newValue);

                            updatePasswordStrength(newValue);
                        }
                    }
            );

            visibilityButton.setOnAction(event -> {

                if (password.isVisible()) {

                    visiblePasswordField.setText(
                            password.getText()
                    );

                    password.setVisible(false);
                    password.setManaged(false);

                    visiblePasswordField.setVisible(true);
                    visiblePasswordField.setManaged(true);

                    visibilityButton.setText(
                            "visibility"
                    );

                } else {

                    password.setText(
                            visiblePasswordField.getText()
                    );

                    visiblePasswordField.setVisible(false);
                    visiblePasswordField.setManaged(false);

                    password.setVisible(true);
                    password.setManaged(true);

                    visibilityButton.setText(
                            "visibility_off"
                    );
                }
            });
        }

        return box;
    }

    // =============================================================
    // CONFIRM PASSWORD BOX
    // =============================================================

    private HBox createConfirmPasswordBox() {

        PasswordField password = new PasswordField();

        password.setPromptText("Confirm Password");

        password.setPrefHeight(38);

        password.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #c8cedc;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 6 0 0 6;" +
                "-fx-background-radius: 6 0 0 6;" +
                "-fx-font-size: 14px;"
        );

        Button visibilityButton =
                new Button("visibility_off");

        visibilityButton.setPrefHeight(38);
        visibilityButton.setPrefWidth(120);

        visibilityButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #c8cedc;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 0 6 6 0;" +
                "-fx-text-fill: #64748b;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );

        visibleConfirmPasswordField =
                new TextField();

        visibleConfirmPasswordField.setPrefHeight(38);

        visibleConfirmPasswordField.setVisible(false);
        visibleConfirmPasswordField.setManaged(false);

        visibleConfirmPasswordField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #c8cedc;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 6 0 0 6;" +
                "-fx-background-radius: 6 0 0 6;" +
                "-fx-font-size: 14px;"
        );

        HBox.setHgrow(
                password,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                visibleConfirmPasswordField,
                Priority.ALWAYS
        );

        HBox box = new HBox();

        box.getChildren().addAll(
                visibleConfirmPasswordField,
                password,
                visibilityButton
        );

        confirmPasswordField = password;

        password.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    visibleConfirmPasswordField.setText(
                            newValue
                    );
                }
        );

        visibleConfirmPasswordField.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (visibleConfirmPasswordField.isFocused()) {

                        confirmPasswordField.setText(
                                newValue
                        );
                    }
                }
        );

        visibilityButton.setOnAction(event -> {

            if (password.isVisible()) {

                visibleConfirmPasswordField.setText(
                        password.getText()
                );

                password.setVisible(false);
                password.setManaged(false);

                visibleConfirmPasswordField.setVisible(true);
                visibleConfirmPasswordField.setManaged(true);

                visibilityButton.setText(
                        "visibility"
                );

            } else {

                password.setText(
                        visibleConfirmPasswordField.getText()
                );

                visibleConfirmPasswordField.setVisible(false);
                visibleConfirmPasswordField.setManaged(false);

                password.setVisible(true);
                password.setManaged(true);

                visibilityButton.setText(
                        "visibility_off"
                );
            }
        });

        return box;
    }

    // =============================================================
    // PASSWORD STRENGTH
    // =============================================================

    private VBox createPasswordStrength() {

        HBox bars = new HBox(8);

        strength1 = new Region();
        strength2 = new Region();
        strength3 = new Region();

        strength1.setPrefHeight(4);
        strength2.setPrefHeight(4);
        strength3.setPrefHeight(4);

        strength1.setStyle(
                "-fx-background-color: #e1e5ee;"
        );

        strength2.setStyle(
                "-fx-background-color: #e1e5ee;"
        );

        strength3.setStyle(
                "-fx-background-color: #e1e5ee;"
        );

        HBox.setHgrow(
                strength1,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                strength2,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                strength3,
                Priority.ALWAYS
        );

        bars.getChildren().addAll(
                strength1,
                strength2,
                strength3
        );

        strengthLabel = new Label("Weak");

        strengthLabel.setFont(
                Font.font("Arial", FontWeight.NORMAL, 11)
        );

        strengthLabel.setTextFill(
                Color.web("#d35400")
        );

        HBox strengthBottom = new HBox();

        strengthBottom.setAlignment(
                Pos.CENTER_RIGHT
        );

        strengthBottom.getChildren().add(
                strengthLabel
        );

        VBox box = new VBox(3);

        box.getChildren().addAll(
                bars,
                strengthBottom
        );

        return box;
    }

    // =============================================================
    // UPDATE PASSWORD STRENGTH
    // =============================================================

    private void updatePasswordStrength(String password) {

        if (password == null) {
            return;
        }

        strength1.setStyle(
                "-fx-background-color: #e1e5ee;"
        );

        strength2.setStyle(
                "-fx-background-color: #e1e5ee;"
        );

        strength3.setStyle(
                "-fx-background-color: #e1e5ee;"
        );

        if (password.length() == 0) {

            strengthLabel.setText("");

        } else if (password.length() < 8) {

            strength1.setStyle(
                    "-fx-background-color: #c45720;"
            );

            strengthLabel.setText("Weak");

        } else if (password.length() < 12) {

            strength1.setStyle(
                    "-fx-background-color: #c45720;"
            );

            strength2.setStyle(
                    "-fx-background-color: #c45720;"
            );

            strengthLabel.setText("Medium");

        } else {

            strength1.setStyle(
                    "-fx-background-color: #2e9d57;"
            );

            strength2.setStyle(
                    "-fx-background-color: #2e9d57;"
            );

            strength3.setStyle(
                    "-fx-background-color: #2e9d57;"
            );

            strengthLabel.setText("Strong");
        }
    }

    // =============================================================
    // VALIDATE FORM
    // =============================================================

    private boolean validateForm(
            CheckBox termsCheckBox
    ) {

        boolean valid = true;

        // Email
        String email = emailField.getText().trim();

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

        // Phone
        String phone = phoneField.getText().trim();

        if (!phone.matches("\\d{10}")) {

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

        // Password
        String password = passwordField.getText();

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

        // Confirm password
        String confirmPassword =
                confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {

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

        // Terms
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
                "-fx-border-color: #c8cedc;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-font-size: 14px;";
    }

    // =============================================================
    // ERROR FIELD STYLE
    // =============================================================

    private String errorFieldStyle() {

        return
                "-fx-background-color: white;" +
                "-fx-border-color: #d93025;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-font-size: 14px;";
    }

    // =============================================================
    // SUCCESS MESSAGE
    // =============================================================

    private void showSuccessMessage() {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle("RentSathi");
        alert.setHeaderText(
                "Customer Account Created"
        );

        alert.setContentText(
                "Your RentSathi customer account has been created successfully."
        );

        alert.showAndWait();
    }

    // =============================================================
    // ALERT
    // =============================================================

    private void showAlert(String message) {

        Alert alert = new Alert(
                Alert.AlertType.WARNING
        );

        alert.setTitle("RentSathi");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}