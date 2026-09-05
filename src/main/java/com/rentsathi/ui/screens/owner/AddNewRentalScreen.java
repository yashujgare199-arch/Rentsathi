package com.rentsathi.ui.screens.owner;

import com.rentsathi.dao.rental.RentalDAO;
import com.rentsathi.firebase.authentication.FirebaseSession;
import com.rentsathi.model.rental.RentalModel;
import com.rentsathi.ui.screens.OwnerLoginScreen;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddNewRentalScreen {

        private static final String BLUE = "#3155C9";
        private static final String LIGHT_BLUE = "#E8EDFF";
        private static final String BG = "#F7F7FC";
        private static final String BORDER = "#D2D5E2";
        private static final String TEXT = "#17213A";
        private static final String MUTED = "#60708F";
        private static final String WHITE = "#FFFFFF";
        private static final String RED = "#E53935";

        private static Stage stage;

        private static final List<Image> selectedImages = new ArrayList<>();

        // ============================================================
        // RENTAL FORM FIELDS
        // ============================================================

        // Basic Information
        private static TextField rentalNameField;
        private static ComboBox<String> categoryField;
        private static ComboBox<String> subCategoryField;
        private static TextArea descriptionField;

        // Pricing
        private static TextField pricePerDayField;
        private static TextField pricePerWeekField;
        private static TextField pricePerMonthField;
        private static TextField securityDepositField;

        // Availability
        private static DatePicker availableFromField;
        private static DatePicker availableUntilField;
        private static Spinner<Integer> minDaysField;
        private static Spinner<Integer> maxDaysField;

        // Location
        private static TextField addressField;
        private static TextField cityField;
        private static TextField stateField;
        private static TextField pinCodeField;

        // Rental Terms
        private static TextArea rentalRulesField;
        private static TextArea cancellationPolicyField;

        // =========================
        // SHOW SCREEN
        // =========================

        public static void show(Stage primaryStage) {

                stage = primaryStage;

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                // LEFT SIDEBAR
                VBox sidebar = createSidebar();

                // TOP BAR
                VBox centerArea = new VBox();

                HBox topBar = createTopBar();

                VBox content = createContent();

                centerArea.getChildren().addAll(
                                topBar,
                                content);

                VBox.setVgrow(content, Priority.ALWAYS);

                root.setLeft(sidebar);
                root.setCenter(centerArea);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                stage.setTitle("RentSathi - Add New Rental");
                stage.setScene(scene);
                stage.show();
        }

        // =========================
        // SIDEBAR
        // =========================

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(255);
                sidebar.setMinWidth(255);
                sidebar.setMaxWidth(255);

                sidebar.setPadding(
                                new Insets(20, 12, 15, 12));

                sidebar.setSpacing(8);

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1px 0 0;");

                // LOGO

                HBox logoBox = new HBox(12);
                logoBox.setAlignment(Pos.CENTER_LEFT);
                logoBox.setPadding(
                                new Insets(0, 8, 15, 8));

                StackPane logo = new StackPane();

                Circle circle = new Circle(
                                28,
                                Color.web(BLUE));

                Label r = new Label("RS");

                r.setStyle(
                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;");

                logo.getChildren().addAll(
                                circle,
                                r);

                VBox brand = new VBox(2);

                Label rentSathi = new Label(
                                "RentSathi");

                rentSathi.setStyle(
                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;");

                Label ownerPortal = new Label(
                                "Owner Portal");

                ownerPortal.setStyle(
                                "-fx-text-fill: " + MUTED + ";" +
                                                "-fx-font-size: 11px;");

                brand.getChildren().addAll(
                                rentSathi,
                                ownerPortal);

                logoBox.getChildren().addAll(
                                logo,
                                brand);

                // ADD LISTING

                Button addListing = new Button(
                                "+   Add New Listing");

                stylePrimaryButton(
                                addListing,
                                225,
                                42);

                addListing.setOnAction(
                                event -> show(stage));

                // NAVIGATION

                Button dashboard = navButton(
                                "▦",
                                "Dashboard",
                                false);

                dashboard.setOnAction(
                                event -> {
                                        try {
                                                OwnerDashboardScreen.show(stage);
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                });

                Button bookings = navButton(
                                "▣",
                                "Bookings",
                                false);

                Button deliveries = navButton(
                                "▱",
                                "Deliveries",
                                false);

                Button analytics = navButton(
                                "▥",
                                "Analytics",
                                false);

                VBox topNavigation = new VBox(4);

                topNavigation.getChildren().addAll(
                                addListing,
                                dashboard,
                                bookings,
                                deliveries,
                                analytics);

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                Separator separator = new Separator();

                Button settings = navButton(
                                "⚙",
                                "Settings",
                                false);

                Button support = navButton(
                                "?",
                                "Support",
                                false);

                Button logout = navButton(
                                "↪",
                                "Logout",
                                false);

                logout.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #D62828;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 10px 12px;" +
                                                "-fx-cursor: hand;");

                logout.setOnAction(
                                event -> {
                                        OwnerLoginScreen.show(stage);
                                });

                sidebar.getChildren().addAll(
                                logoBox,
                                topNavigation,
                                spacer,
                                separator,
                                settings,
                                support,
                                logout);

                return sidebar;
        }

        // =========================
        // TOP BAR
        // =========================

        private static HBox createTopBar() {

                HBox top = new HBox();

                top.setPrefHeight(70);
                top.setAlignment(Pos.CENTER_LEFT);

                top.setPadding(
                                new Insets(0, 25, 0, 25));

                top.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1px 0;");

                TextField search = new TextField();

                search.setPromptText(
                                "Search rentals, bookings...");

                search.setPrefWidth(330);
                search.setPrefHeight(40);

                search.setStyle(
                                "-fx-background-color: #F5F6FB;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-border-color: #D5D8E5;" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-padding: 0 18px;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label notification = new Label("♧");

                notification.setStyle(
                                "-fx-font-size: 23px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label question = new Label("?");

                question.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-text-fill: " + TEXT + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-padding: 2px 7px;");

                top.getChildren().addAll(
                                search,
                                spacer,
                                notification,
                                new Region(),
                                question);

                return top;
        }

        // =========================
        // MAIN CONTENT
        // =========================

        private static VBox createContent() {

                VBox content = new VBox();

                content.setPadding(
                                new Insets(25, 30, 20, 30));

                content.setSpacing(18);

                ScrollPane scroll = new ScrollPane();

                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                VBox page = new VBox(18);

                // TITLE

                VBox titleBox = new VBox(3);

                Label title = new Label(
                                "Add New Rental");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label subtitle = new Label(
                                "Provide detailed information to attract more renters.");

                subtitle.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                // MAIN TWO COLUMNS

                HBox columns = new HBox(20);

                VBox left = new VBox(18);

                VBox right = new VBox(18);

                HBox.setHgrow(
                                left,
                                Priority.ALWAYS);

                left.setPrefWidth(700);
                right.setPrefWidth(330);

                // LEFT

                left.getChildren().addAll(
                                createBasicInformation(),
                                createRentalImages(),
                                createLocation());

                // RIGHT

                right.getChildren().addAll(
                                createPricing(),
                                createAvailability(),
                                createRentalTerms());

                columns.getChildren().addAll(
                                left,
                                right);

                page.getChildren().addAll(
                                titleBox,
                                columns);

                scroll.setContent(page);

                VBox.setVgrow(
                                scroll,
                                Priority.ALWAYS);

                // BOTTOM BUTTONS

                HBox bottom = new HBox(10);

                bottom.setAlignment(
                                Pos.CENTER_RIGHT);

                Button saveDraft = new Button(
                                "Save Draft");

                styleSecondaryButton(
                                saveDraft,
                                100,
                                38);

                Button preview = new Button(
                                "Preview");

                styleOutlineButton(
                                preview,
                                90,
                                38);

                Button publish = new Button(
                                "⚑  Publish Rental");

                stylePrimaryButton(
                                publish,
                                145,
                                38);

                publish.setOnAction(
                                event -> publishRental());

                bottom.getChildren().addAll(
                                saveDraft,
                                preview,
                                publish);

                content.getChildren().addAll(
                                scroll,
                                bottom);

                return content;
        }

        // =========================
        // BASIC INFORMATION
        // =========================

        private static VBox createBasicInformation() {

                VBox box = card();

                HBox heading = sectionTitle(
                                "ⓘ",
                                "Basic Information");

                rentalNameField = new TextField();

                rentalNameField.setPromptText(
                                "e.g., Canon EOS R5 Camera Kit");

                rentalNameField.setPrefHeight(42);

                categoryField = new ComboBox<>();

                categoryField.getItems().addAll(
                                "Electronics",
                                "Tools & Hardware",
                                "Furniture",
                                "Vehicles",
                                "Appliances",
                                "Sports",
                                "Events & Party",
                                "Other");

                categoryField.setPromptText(
                                "Select Category");

                categoryField.setMaxWidth(
                                Double.MAX_VALUE);

                subCategoryField = new ComboBox<>();

                subCategoryField.getItems().addAll(
                                "Cameras",
                                "Laptops",
                                "Audio",
                                "Lighting",
                                "Power Tools",
                                "Hand Tools",
                                "Other");

                subCategoryField.setPromptText(
                                "Select Subcategory");

                subCategoryField.setMaxWidth(
                                Double.MAX_VALUE);

                descriptionField = new TextArea();

                descriptionField.setPromptText(
                                "Describe the item's condition, features, and any included accessories...");

                descriptionField.setPrefRowCount(3);
                descriptionField.setWrapText(true);

                GridPane categoryGrid = new GridPane();

                categoryGrid.setHgap(10);

                ColumnConstraints c1 = new ColumnConstraints();

                c1.setPercentWidth(50);

                ColumnConstraints c2 = new ColumnConstraints();

                c2.setPercentWidth(50);

                categoryGrid.getColumnConstraints()
                                .addAll(c1, c2);

                categoryGrid.add(
                                labeledField(
                                                "CATEGORY",
                                                categoryField),
                                0,
                                0);

                categoryGrid.add(
                                labeledField(
                                                "SUBCATEGORY",
                                                subCategoryField),
                                1,
                                0);

                box.getChildren().addAll(
                                heading,
                                labeledField(
                                                "RENTAL NAME *",
                                                rentalNameField),
                                categoryGrid,
                                labeledField(
                                                "DESCRIPTION",
                                                descriptionField));

                return box;
        }

        // =========================
        // PRICING
        // =========================

        private static VBox createPricing() {

                VBox box = card();

                HBox heading = sectionTitle(
                                "▣",
                                "Pricing");

                pricePerDayField = moneyField();

                pricePerWeekField = moneyField();

                pricePerMonthField = moneyField();

                securityDepositField = moneyField();

                box.getChildren().addAll(
                                heading,
                                labeledField(
                                                "PRICE PER DAY",
                                                pricePerDayField),
                                labeledField(
                                                "PRICE PER WEEK",
                                                pricePerWeekField),
                                labeledField(
                                                "PRICE PER MONTH",
                                                pricePerMonthField),
                                labeledField(
                                                "SECURITY DEPOSIT",
                                                securityDepositField));

                return box;
        }

        // =========================
        // AVAILABILITY
        // =========================

        private static VBox createAvailability() {

                VBox box = card();

                HBox heading = sectionTitle(
                                "▣",
                                "Availability");

                availableFromField = new DatePicker();

                availableFromField.setPromptText(
                                "dd-mm-yyyy");

                availableUntilField = new DatePicker();

                availableUntilField.setPromptText(
                                "dd-mm-yyyy");

                GridPane dates = new GridPane();

                dates.setHgap(10);

                ColumnConstraints c1 = new ColumnConstraints();

                c1.setPercentWidth(50);

                ColumnConstraints c2 = new ColumnConstraints();

                c2.setPercentWidth(50);

                dates.getColumnConstraints()
                                .addAll(c1, c2);

                dates.add(
                                labeledField(
                                                "AVAILABLE FROM",
                                                availableFromField),
                                0,
                                0);

                dates.add(
                                labeledField(
                                                "AVAILABLE UNTIL",
                                                availableUntilField),
                                1,
                                0);

                minDaysField = new Spinner<>(1, 365, 1);

                maxDaysField = new Spinner<>(1, 365, 30);

                GridPane days = new GridPane();

                days.setHgap(10);

                ColumnConstraints d1 = new ColumnConstraints();

                d1.setPercentWidth(50);

                ColumnConstraints d2 = new ColumnConstraints();

                d2.setPercentWidth(50);

                days.getColumnConstraints()
                                .addAll(d1, d2);

                days.add(
                                labeledField(
                                                "MIN. DAYS",
                                                minDaysField),
                                0,
                                0);

                days.add(
                                labeledField(
                                                "MAX. DAYS",
                                                maxDaysField),
                                1,
                                0);

                box.getChildren().addAll(
                                heading,
                                dates,
                                days);

                return box;
        }

        // =========================
        // RENTAL IMAGES
        // =========================

        private static VBox createRentalImages() {

                VBox box = card();

                HBox heading = sectionTitle(
                                "▣",
                                "Rental Images");

                VBox upload = new VBox(8);

                upload.setAlignment(
                                Pos.CENTER);

                upload.setPrefHeight(120);

                upload.setStyle(
                                "-fx-border-color: #BFC5D8;" +
                                                "-fx-border-style: dashed;" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-color: #FCFCFF;");

                Label uploadIcon = new Label("☁");

                uploadIcon.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-text-fill: #B8BED0;");

                Label uploadText = new Label(
                                "Click to upload or drag and drop");

                uploadText.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                Label uploadInfo = new Label(
                                "SVG, PNG, JPG or GIF (MAX. 800×400px)");

                uploadInfo.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #9299AC;");

                upload.getChildren().addAll(
                                uploadIcon,
                                uploadText,
                                uploadInfo);

                upload.setOnMouseClicked(
                                event -> chooseImages());

                HBox thumbnails = new HBox(10);

                thumbnails.setPadding(
                                new Insets(5, 0, 0, 0));

                box.getChildren().addAll(
                                heading,
                                upload,
                                thumbnails);

                return box;
        }

        // =========================
        // IMAGE CHOOSER
        // =========================

        private static void chooseImages() {

                FileChooser chooser = new FileChooser();

                chooser.setTitle(
                                "Select Rental Images");

                chooser.getExtensionFilters().add(
                                new FileChooser.ExtensionFilter(
                                                "Image Files",
                                                "*.png",
                                                "*.jpg",
                                                "*.jpeg",
                                                "*.gif"));

                List<File> files = chooser.showOpenMultipleDialog(
                                stage);

                if (files == null) {
                        return;
                }

                selectedImages.clear();

                for (File file : files) {

                        if (selectedImages.size() >= 4) {
                                break;
                        }

                        Image image = new Image(
                                        file.toURI().toString());

                        selectedImages.add(image);
                }
        }

        // =========================
        // LOCATION
        // =========================

        private static VBox createLocation() {

                VBox box = card();

                HBox heading = sectionTitle(
                                "⌖",
                                "Location");

                addressField = new TextField();

                addressField.setPromptText(
                                "Owner pickup address");

                cityField = new TextField();

                cityField.setPromptText(
                                "City");

                stateField = new TextField();

                stateField.setPromptText(
                                "State");

                pinCodeField = new TextField();

                pinCodeField.setPromptText(
                                "Zip/PIN");

                GridPane grid = new GridPane();

                grid.setHgap(10);

                ColumnConstraints c1 = new ColumnConstraints();

                c1.setPercentWidth(33.33);

                ColumnConstraints c2 = new ColumnConstraints();

                c2.setPercentWidth(33.33);

                ColumnConstraints c3 = new ColumnConstraints();

                c3.setPercentWidth(33.33);

                grid.getColumnConstraints()
                                .addAll(c1, c2, c3);

                grid.add(
                                labeledField(
                                                "CITY",
                                                cityField),
                                0,
                                0);

                grid.add(
                                labeledField(
                                                "STATE",
                                                stateField),
                                1,
                                0);

                grid.add(
                                labeledField(
                                                "PIN CODE",
                                                pinCodeField),
                                2,
                                0);

                box.getChildren().addAll(
                                heading,
                                labeledField(
                                                "OWNER PICKUP ADDRESS",
                                                addressField),
                                grid);

                return box;
        }

        // =========================
        // RENTAL TERMS
        // =========================

        private static VBox createRentalTerms() {

                VBox box = card();

                HBox heading = sectionTitle(
                                "⚒",
                                "Rental Terms");

                rentalRulesField = new TextArea();

                rentalRulesField.setPromptText(
                                "e.g., No smoking, handle with care...");

                rentalRulesField.setPrefRowCount(2);
                rentalRulesField.setWrapText(true);

                cancellationPolicyField = new TextArea();

                cancellationPolicyField.setPromptText(
                                "Describe cancellation fees or terms...");

                cancellationPolicyField.setPrefRowCount(2);
                cancellationPolicyField.setWrapText(true);

                box.getChildren().addAll(
                                heading,
                                labeledField(
                                                "RENTAL RULES",
                                                rentalRulesField),
                                labeledField(
                                                "CANCELLATION POLICY",
                                                cancellationPolicyField));

                return box;
        }

        // =========================
        // CARD
        // =========================

        private static VBox card() {

                VBox box = new VBox(12);

                box.setPadding(
                                new Insets(18));

                box.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 8px;");

                return box;
        }

        // =========================
        // SECTION TITLE
        // =========================

        private static HBox sectionTitle(
                        String icon,
                        String text) {

                HBox row = new HBox(8);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 15px;");

                Label title = new Label(text);

                title.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                row.getChildren().addAll(
                                iconLabel,
                                title);

                return row;
        }

        // =========================
        // LABELED FIELD
        // =========================

        private static VBox labeledField(
                        String labelText,
                        Control control) {

                VBox box = new VBox(5);

                Label label = new Label(labelText);

                label.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + MUTED + ";");

                styleControl(control);

                box.getChildren().addAll(
                                label,
                                control);

                return box;
        }

        // =========================
        // MONEY FIELD
        // =========================

        private static TextField moneyField() {

                TextField field = new TextField();

                field.setPromptText(
                                "₹ 0.00");

                field.setPrefHeight(38);

                return field;
        }

        // =========================
        // CONTROL STYLE
        // =========================

        private static void styleControl(
                        Control control) {

                control.setMaxWidth(
                                Double.MAX_VALUE);

                if (control instanceof TextInputControl) {

                        control.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: #D2D6E3;" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-background-radius: 5px;" +
                                                        "-fx-padding: 8px;");
                }

                if (control instanceof ComboBox) {

                        control.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: #D2D6E3;" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-background-radius: 5px;");

                        control.setPrefHeight(38);
                }

                if (control instanceof DatePicker) {

                        control.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: #D2D6E3;" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-background-radius: 5px;");

                        control.setPrefHeight(38);
                }

                if (control instanceof Spinner) {

                        control.setPrefHeight(38);
                }
        }

        // =========================
        // NAV BUTTON
        // =========================

        private static Button navButton(
                        String icon,
                        String text,
                        boolean selected) {

                Button button = new Button(
                                icon + "    " + text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(42);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setPadding(
                                new Insets(0, 12, 0, 12));

                button.setStyle(
                                navStyle(selected));

                return button;
        }

        private static String navStyle(
                        boolean selected) {

                if (selected) {

                        return "-fx-background-color: #DCE5FF;" +
                                        "-fx-text-fill: " + BLUE + ";" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-font-size: 14px;" +
                                        "-fx-background-radius: 7px;" +
                                        "-fx-cursor: hand;";
                }

                return "-fx-background-color: transparent;" +
                                "-fx-text-fill: #34496B;" +
                                "-fx-font-size: 14px;" +
                                "-fx-background-radius: 7px;" +
                                "-fx-cursor: hand;";
        }

        // =========================
        // PRIMARY BUTTON
        // =========================

        private static void stylePrimaryButton(
                        Button button,
                        double width,
                        double height) {

                button.setPrefWidth(width);
                button.setPrefHeight(height);

                button.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-cursor: hand;");
        }

        // =========================
        // SECONDARY BUTTON
        // =========================

        private static void styleSecondaryButton(
                        Button button,
                        double width,
                        double height) {

                button.setPrefWidth(width);
                button.setPrefHeight(height);

                button.setStyle(
                                "-fx-background-color: #EEF0F8;" +
                                                "-fx-text-fill: #33456C;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-cursor: hand;");
        }

        // =========================
        // OUTLINE BUTTON
        // =========================

        private static void styleOutlineButton(
                        Button button,
                        double width,
                        double height) {

                button.setPrefWidth(width);
                button.setPrefHeight(height);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-border-color: " + BLUE + ";" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-cursor: hand;");
        }

        // ============================================================
        // PUBLISH RENTAL
        // ============================================================

        private static void publishRental() {

                try {

                        // --------------------------------------------------------
                        // CHECK OWNER LOGIN
                        // --------------------------------------------------------

                        if (!FirebaseSession.isLoggedIn()) {

                                showError(
                                                "Authentication Required",
                                                "Please login as an owner before publishing a rental.");

                                return;
                        }

                        // --------------------------------------------------------
                        // BASIC VALIDATION
                        // --------------------------------------------------------

                        if (isEmpty(rentalNameField.getText())) {

                                showError(
                                                "Validation Error",
                                                "Please enter the rental name.");

                                rentalNameField.requestFocus();

                                return;
                        }

                        if (categoryField.getValue() == null) {

                                showError(
                                                "Validation Error",
                                                "Please select a category.");

                                categoryField.requestFocus();

                                return;
                        }

                        if (subCategoryField.getValue() == null) {

                                showError(
                                                "Validation Error",
                                                "Please select a subcategory.");

                                subCategoryField.requestFocus();

                                return;
                        }

                        // --------------------------------------------------------
                        // PRICE VALUES
                        // --------------------------------------------------------

                        double pricePerDay = parseAmount(
                                        pricePerDayField.getText());

                        double pricePerWeek = parseAmount(
                                        pricePerWeekField.getText());

                        double pricePerMonth = parseAmount(
                                        pricePerMonthField.getText());

                        double securityDeposit = parseAmount(
                                        securityDepositField.getText());

                        // --------------------------------------------------------
                        // DATE VALUES
                        // --------------------------------------------------------

                        String availableFrom = availableFromField.getValue() == null
                                        ? ""
                                        : availableFromField
                                                        .getValue()
                                                        .toString();

                        String availableUntil = availableUntilField.getValue() == null
                                        ? ""
                                        : availableUntilField
                                                        .getValue()
                                                        .toString();

                        // --------------------------------------------------------
                        // CREATE RENTAL MODEL
                        // --------------------------------------------------------

                        RentalModel rental = new RentalModel();

                        rental.setOwnerId(
                                        FirebaseSession.getUserId());

                        rental.setRentalName(
                                        rentalNameField
                                                        .getText()
                                                        .trim());

                        rental.setCategory(
                                        categoryField.getValue());

                        rental.setSubcategory(
                                        subCategoryField.getValue());

                        rental.setDescription(
                                        descriptionField
                                                        .getText()
                                                        .trim());

                        // --------------------------------------------------------
                        // PRICING
                        // --------------------------------------------------------

                        rental.setPricePerDay(
                                        pricePerDay);

                        rental.setPricePerWeek(
                                        pricePerWeek);

                        rental.setPricePerMonth(
                                        pricePerMonth);

                        rental.setSecurityDeposit(
                                        securityDeposit);

                        // --------------------------------------------------------
                        // AVAILABILITY
                        // --------------------------------------------------------

                        rental.setAvailableFrom(
                                        availableFrom);

                        rental.setAvailableUntil(
                                        availableUntil);

                        rental.setMinDays(
                                        minDaysField.getValue());

                        rental.setMaxDays(
                                        maxDaysField.getValue());

                        // --------------------------------------------------------
                        // LOCATION
                        // --------------------------------------------------------

                        rental.setAddress(
                                        addressField
                                                        .getText()
                                                        .trim());

                        rental.setCity(
                                        cityField
                                                        .getText()
                                                        .trim());

                        rental.setState(
                                        stateField
                                                        .getText()
                                                        .trim());

                        rental.setPinCode(
                                        pinCodeField
                                                        .getText()
                                                        .trim());

                        // --------------------------------------------------------
                        // RENTAL TERMS
                        // --------------------------------------------------------

                        rental.setRentalRules(
                                        rentalRulesField
                                                        .getText()
                                                        .trim());

                        rental.setCancellationPolicy(
                                        cancellationPolicyField
                                                        .getText()
                                                        .trim());

                        // --------------------------------------------------------
                        // SYSTEM INFORMATION
                        // --------------------------------------------------------

                        rental.setStatus(
                                        "PUBLISHED");

                        rental.setCreatedAt(
                                        java.time.LocalDateTime
                                                        .now()
                                                        .toString());

                        // --------------------------------------------------------
                        // SAVE TO FIRESTORE
                        // --------------------------------------------------------

                        // --------------------------------------------------------
                        // GEOCODE OWNER PICKUP LOCATION
                        // --------------------------------------------------------

                        double[] coordinates = geocodeAddress(
                                        rental.getAddress(),
                                        rental.getCity(),
                                        rental.getState(),
                                        rental.getPinCode());

                        if (coordinates == null) {

                                showError(
                                                "Location Error",
                                                "Could not find the owner pickup location. "
                                                                + "Please check the address, city, state and PIN code.");

                                return;
                        }

                        rental.setLatitude(
                                        coordinates[0]);

                        rental.setLongitude(
                                        coordinates[1]);

                        System.out.println(
                                        "OWNER PICKUP LATITUDE = "
                                                        + rental.getLatitude());

                        System.out.println(
                                        "OWNER PICKUP LONGITUDE = "
                                                        + rental.getLongitude());

                        // --------------------------------------------------------
                        // SAVE TO FIRESTORE
                        // --------------------------------------------------------

                        boolean success = RentalDAO.createRental(
                                        rental);

                        if (success) {

                                showSuccess(
                                                "Rental Published",
                                                "Your rental has been successfully published.");

                        } else {

                                showError(
                                                "Publication Failed",
                                                "Unable to publish the rental. Please try again.");
                        }

                } catch (NumberFormatException e) {

                        showError(
                                        "Invalid Price",
                                        "Please enter valid numeric values in the pricing fields.");

                } catch (Exception e) {

                        e.printStackTrace();

                        showError(
                                        "Error",
                                        "Something went wrong while publishing the rental.");
                }
        }
        // ============================================================
        // CHECK EMPTY
        // ============================================================

        private static boolean isEmpty(
                        String value) {

                return value == null
                                || value.trim().isEmpty();
        }

        // ============================================================
        // PARSE PRICE
        // ============================================================

        private static double parseAmount(
                        String value) {

                if (isEmpty(value)) {
                        return 0.0;
                }

                String cleaned = value.replaceAll(
                                "[^0-9.]",
                                "");

                if (cleaned.isEmpty()) {
                        return 0.0;
                }

                return Double.parseDouble(
                                cleaned);
        }

        // ============================================================
        // SUCCESS ALERT
        // ============================================================

        private static void showSuccess(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "RentSathi");

                alert.setHeaderText(
                                title);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        // ============================================================
        // ERROR ALERT
        // ============================================================

        private static void showError(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                                "RentSathi");

                alert.setHeaderText(
                                title);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        private static double[] geocodeAddress(
                        String address,
                        String city,
                        String state,
                        String pinCode) {

                try {

                        // =====================================================
                        // 1. TRY FULL ADDRESS
                        // =====================================================

                        String fullAddress = address + ", "
                                        + city + ", "
                                        + state + ", "
                                        + pinCode + ", India";

                        double[] coordinates = searchNominatim(fullAddress);

                        if (coordinates != null) {
                                return coordinates;
                        }

                        // =====================================================
                        // 2. FALLBACK: CITY + STATE + PIN
                        // =====================================================

                        String cityStatePin = city + ", "
                                        + state + ", "
                                        + pinCode + ", India";

                        System.out.println(
                                        "FULL ADDRESS NOT FOUND.");

                        System.out.println(
                                        "TRYING CITY + STATE + PIN = "
                                                        + cityStatePin);

                        coordinates = searchNominatim(cityStatePin);

                        if (coordinates != null) {
                                return coordinates;
                        }

                        // =====================================================
                        // 3. FALLBACK: PIN + CITY + INDIA
                        // =====================================================

                        String pinCity = pinCode + ", "
                                        + city + ", India";

                        System.out.println(
                                        "TRYING PIN + CITY = "
                                                        + pinCity);

                        coordinates = searchNominatim(pinCity);

                        if (coordinates != null) {
                                return coordinates;
                        }

                        System.out.println(
                                        "GEOCODING FAILED FOR ALL SEARCH OPTIONS.");

                        return null;

                } catch (Exception e) {

                        System.out.println(
                                        "GEOCODING EXCEPTION");

                        e.printStackTrace();

                        return null;
                }
        }

        private static double[] searchNominatim(
                        String searchAddress) {

                try {

                        System.out.println(
                                        "SEARCHING LOCATION = "
                                                        + searchAddress);

                        String encodedAddress = URLEncoder.encode(
                                        searchAddress,
                                        StandardCharsets.UTF_8);

                        String url = "https://nominatim.openstreetmap.org/search"
                                        + "?q="
                                        + encodedAddress
                                        + "&format=jsonv2"
                                        + "&limit=1"
                                        + "&countrycodes=in";

                        HttpClient client = HttpClient.newBuilder()
                                        .connectTimeout(
                                                        java.time.Duration.ofSeconds(15))
                                        .build();

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(
                                                        URI.create(url))
                                        .timeout(
                                                        java.time.Duration.ofSeconds(20))
                                        .header(
                                                        "User-Agent",
                                                        "RentSathi/1.0")
                                        .header(
                                                        "Accept",
                                                        "application/json")
                                        .GET()
                                        .build();

                        HttpResponse<String> response = client.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString());

                        System.out.println(
                                        "HTTP STATUS = "
                                                        + response.statusCode());

                        if (response.statusCode() != 200) {

                                System.out.println(
                                                "NOMINATIM ERROR = "
                                                                + response.body());

                                return null;
                        }

                        JsonArray results = JsonParser.parseString(
                                        response.body()).getAsJsonArray();

                        System.out.println(
                                        "RESULT COUNT = "
                                                        + results.size());

                        if (results.size() == 0) {

                                return null;
                        }

                        JsonObject result = results.get(0)
                                        .getAsJsonObject();

                        if (!result.has("lat")
                                        || !result.has("lon")) {

                                return null;
                        }

                        double latitude = result.get("lat")
                                        .getAsDouble();

                        double longitude = result.get("lon")
                                        .getAsDouble();

                        System.out.println(
                                        "LATITUDE = "
                                                        + latitude);

                        System.out.println(
                                        "LONGITUDE = "
                                                        + longitude);

                        if (result.has("display_name")) {

                                System.out.println(
                                                "FOUND LOCATION = "
                                                                + result.get(
                                                                                "display_name").getAsString());
                        }

                        return new double[] {
                                        latitude,
                                        longitude
                        };

                } catch (Exception e) {

                        System.out.println(
                                        "NOMINATIM SEARCH EXCEPTION");

                        e.printStackTrace();

                        return null;
                }
        }
}
