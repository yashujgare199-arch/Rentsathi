package com.rentsathi.ui.screens.owner;

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

    private static final List<Image> selectedImages =
            new ArrayList<>();

    // =========================
    // SHOW SCREEN
    // =========================

    public static void show(Stage primaryStage) {

        stage = primaryStage;

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // LEFT SIDEBAR
        VBox sidebar = createSidebar();

        // TOP BAR
        VBox centerArea = new VBox();

        HBox topBar = createTopBar();

        VBox content = createContent();

        centerArea.getChildren().addAll(
                topBar,
                content
        );

        VBox.setVgrow(content, Priority.ALWAYS);

        root.setLeft(sidebar);
        root.setCenter(centerArea);

        Scene scene = new Scene(
                root,
                1500,
                830
        );

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
                new Insets(20, 12, 15, 12)
        );

        sidebar.setSpacing(8);

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1px 0 0;"
        );

        // LOGO

        HBox logoBox = new HBox(12);
        logoBox.setAlignment(Pos.CENTER_LEFT);
        logoBox.setPadding(
                new Insets(0, 8, 15, 8)
        );

        StackPane logo = new StackPane();

        Circle circle = new Circle(
                28,
                Color.web(BLUE)
        );

        Label r = new Label("RS");

        r.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        logo.getChildren().addAll(
                circle,
                r
        );

        VBox brand = new VBox(2);

        Label rentSathi = new Label(
                "RentSathi"
        );

        rentSathi.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Label ownerPortal = new Label(
                "Owner Portal"
        );

        ownerPortal.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 11px;"
        );

        brand.getChildren().addAll(
                rentSathi,
                ownerPortal
        );

        logoBox.getChildren().addAll(
                logo,
                brand
        );

        // ADD LISTING

        Button addListing = new Button(
                "+   Add New Listing"
        );

        stylePrimaryButton(
                addListing,
                225,
                42
        );

        addListing.setOnAction(
                event -> show(stage)
        );

        // NAVIGATION

        Button dashboard = navButton(
                "▦",
                "Dashboard",
                false
        );

        dashboard.setOnAction(
                event -> {
                    try {
                        OwnerDashboardScreen.show(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        Button listings = navButton(
                "▣",
                "Manage Listings",
                false
        );

        listings.setStyle(
                navStyle(true)
        );

        listings.setOnAction(
                event -> {
                    try {
                        MyListingsScreen.show(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        Button bookings = navButton(
                "▣",
                "Bookings",
                false
        );

        Button deliveries = navButton(
                "▱",
                "Deliveries",
                false
        );

        Button analytics = navButton(
                "▥",
                "Analytics",
                false
        );

        VBox topNavigation = new VBox(4);

        topNavigation.getChildren().addAll(
                addListing,
                dashboard,
                listings,
                bookings,
                deliveries,
                analytics
        );

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Separator separator = new Separator();

        Button settings = navButton(
                "⚙",
                "Settings",
                false
        );

        Button support = navButton(
                "?",
                "Support",
                false
        );

        Button logout = navButton(
                "↪",
                "Logout",
                false
        );

        logout.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #D62828;" +
                "-fx-font-size: 14px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 10px 12px;" +
                "-fx-cursor: hand;"
        );

        logout.setOnAction(
                event -> stage.close()
        );

        sidebar.getChildren().addAll(
                logoBox,
                topNavigation,
                spacer,
                separator,
                settings,
                support,
                logout
        );

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
                new Insets(0, 25, 0, 25)
        );

        top.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1px 0;"
        );

        TextField search = new TextField();

        search.setPromptText(
                "Search rentals, bookings..."
        );

        search.setPrefWidth(330);
        search.setPrefHeight(40);

        search.setStyle(
                "-fx-background-color: #F5F6FB;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #D5D8E5;" +
                "-fx-border-radius: 20;" +
                "-fx-padding: 0 18px;"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label notification = new Label("♧");

        notification.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label question = new Label("?");

        question.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 50%;" +
                "-fx-padding: 2px 7px;"
        );

        top.getChildren().addAll(
                search,
                spacer,
                notification,
                new Region(),
                question
        );

        return top;
    }

    // =========================
    // MAIN CONTENT
    // =========================

    private static VBox createContent() {

        VBox content = new VBox();

        content.setPadding(
                new Insets(25, 30, 20, 30)
        );

        content.setSpacing(18);

        ScrollPane scroll = new ScrollPane();

        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        VBox page = new VBox(18);

        // TITLE

        VBox titleBox = new VBox(3);

        Label title = new Label(
                "Add New Rental"
        );

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle = new Label(
                "Provide detailed information to attract more renters."
        );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        // MAIN TWO COLUMNS

        HBox columns = new HBox(20);

        VBox left = new VBox(18);

        VBox right = new VBox(18);

        HBox.setHgrow(
                left,
                Priority.ALWAYS
        );

        left.setPrefWidth(700);
        right.setPrefWidth(330);

        // LEFT

        left.getChildren().addAll(
                createBasicInformation(),
                createRentalImages(),
                createLocation()
        );

        // RIGHT

        right.getChildren().addAll(
                createPricing(),
                createAvailability(),
                createRentalTerms()
        );

        columns.getChildren().addAll(
                left,
                right
        );

        page.getChildren().addAll(
                titleBox,
                columns
        );

        scroll.setContent(page);

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        // BOTTOM BUTTONS

        HBox bottom = new HBox(10);

        bottom.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button saveDraft = new Button(
                "Save Draft"
        );

        styleSecondaryButton(
                saveDraft,
                100,
                38
        );

        Button preview = new Button(
                "Preview"
        );

        styleOutlineButton(
                preview,
                90,
                38
        );

        Button publish = new Button(
                "⚑  Publish Rental"
        );

        stylePrimaryButton(
                publish,
                145,
                38
        );

        publish.setOnAction(
                event -> {

                    Alert alert = new Alert(
                            Alert.AlertType.INFORMATION
                    );

                    alert.setTitle(
                            "RentSathi"
                    );

                    alert.setHeaderText(
                            "Rental Published"
                    );

                    alert.setContentText(
                            "Your rental has been successfully published."
                    );

                    alert.showAndWait();
                }
        );

        bottom.getChildren().addAll(
                saveDraft,
                preview,
                publish
        );

        content.getChildren().addAll(
                scroll,
                bottom
        );

        return content;
    }

    // =========================
    // BASIC INFORMATION
    // =========================

    private static VBox createBasicInformation() {

        VBox box = card();

        HBox heading = sectionTitle(
                "ⓘ",
                "Basic Information"
        );

        TextField rentalName =
                new TextField();

        rentalName.setPromptText(
                "e.g., Canon EOS R5 Camera Kit"
        );

        rentalName.setPrefHeight(42);

        ComboBox<String> category =
                new ComboBox<>();

        category.getItems().addAll(
                "Electronics",
                "Tools & Hardware",
                "Furniture",
                "Vehicles",
                "Appliances",
                "Sports",
                "Events & Party",
                "Other"
        );

        category.setPromptText(
                "Select Category"
        );

        category.setMaxWidth(
                Double.MAX_VALUE
        );

        ComboBox<String> subCategory =
                new ComboBox<>();

        subCategory.getItems().addAll(
                "Cameras",
                "Laptops",
                "Audio",
                "Lighting",
                "Power Tools",
                "Hand Tools",
                "Other"
        );

        subCategory.setPromptText(
                "Select Subcategory"
        );

        subCategory.setMaxWidth(
                Double.MAX_VALUE
        );

        TextArea description =
                new TextArea();

        description.setPromptText(
                "Describe the item's condition, features, and any included accessories..."
        );

        description.setPrefRowCount(3);
        description.setWrapText(true);

        GridPane categoryGrid =
                new GridPane();

        categoryGrid.setHgap(10);

        ColumnConstraints c1 =
                new ColumnConstraints();

        c1.setPercentWidth(50);

        ColumnConstraints c2 =
                new ColumnConstraints();

        c2.setPercentWidth(50);

        categoryGrid.getColumnConstraints()
                .addAll(c1, c2);

        categoryGrid.add(
                labeledField(
                        "CATEGORY",
                        category
                ),
                0,
                0
        );

        categoryGrid.add(
                labeledField(
                        "SUBCATEGORY",
                        subCategory
                ),
                1,
                0
        );

        box.getChildren().addAll(
                heading,
                labeledField(
                        "RENTAL NAME *",
                        rentalName
                ),
                categoryGrid,
                labeledField(
                        "DESCRIPTION",
                        description
                )
        );

        return box;
    }

    // =========================
    // PRICING
    // =========================

    private static VBox createPricing() {

        VBox box = card();

        HBox heading = sectionTitle(
                "▣",
                "Pricing"
        );

        TextField perDay =
                moneyField();

        TextField perWeek =
                moneyField();

        TextField perMonth =
                moneyField();

        TextField security =
                moneyField();

        box.getChildren().addAll(
                heading,
                labeledField(
                        "PRICE PER DAY",
                        perDay
                ),
                labeledField(
                        "PRICE PER WEEK",
                        perWeek
                ),
                labeledField(
                        "PRICE PER MONTH",
                        perMonth
                ),
                labeledField(
                        "SECURITY DEPOSIT",
                        security
                )
        );

        return box;
    }

    // =========================
    // AVAILABILITY
    // =========================

    private static VBox createAvailability() {

        VBox box = card();

        HBox heading = sectionTitle(
                "▣",
                "Availability"
        );

        DatePicker from =
                new DatePicker();

        from.setPromptText(
                "dd-mm-yyyy"
        );

        DatePicker until =
                new DatePicker();

        until.setPromptText(
                "dd-mm-yyyy"
        );

        GridPane dates =
                new GridPane();

        dates.setHgap(10);

        ColumnConstraints c1 =
                new ColumnConstraints();

        c1.setPercentWidth(50);

        ColumnConstraints c2 =
                new ColumnConstraints();

        c2.setPercentWidth(50);

        dates.getColumnConstraints()
                .addAll(c1, c2);

        dates.add(
                labeledField(
                        "AVAILABLE FROM",
                        from
                ),
                0,
                0
        );

        dates.add(
                labeledField(
                        "AVAILABLE UNTIL",
                        until
                ),
                1,
                0
        );

        Spinner<Integer> minDays =
                new Spinner<>(
                        1,
                        365,
                        1
                );

        Spinner<Integer> maxDays =
                new Spinner<>(
                        1,
                        365,
                        30
                );

        GridPane days =
                new GridPane();

        days.setHgap(10);

        ColumnConstraints d1 =
                new ColumnConstraints();

        d1.setPercentWidth(50);

        ColumnConstraints d2 =
                new ColumnConstraints();

        d2.setPercentWidth(50);

        days.getColumnConstraints()
                .addAll(d1, d2);

        days.add(
                labeledField(
                        "MIN. DAYS",
                        minDays
                ),
                0,
                0
        );

        days.add(
                labeledField(
                        "MAX. DAYS",
                        maxDays
                ),
                1,
                0
        );

        box.getChildren().addAll(
                heading,
                dates,
                days
        );

        return box;
    }

    // =========================
    // RENTAL IMAGES
    // =========================

    private static VBox createRentalImages() {

        VBox box = card();

        HBox heading = sectionTitle(
                "▣",
                "Rental Images"
        );

        VBox upload =
                new VBox(8);

        upload.setAlignment(
                Pos.CENTER
        );

        upload.setPrefHeight(120);

        upload.setStyle(
                "-fx-border-color: #BFC5D8;" +
                "-fx-border-style: dashed;" +
                "-fx-border-radius: 5px;" +
                "-fx-background-color: #FCFCFF;"
        );

        Label uploadIcon =
                new Label("☁");

        uploadIcon.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-text-fill: #B8BED0;"
        );

        Label uploadText =
                new Label(
                        "Click to upload or drag and drop"
                );

        uploadText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label uploadInfo =
                new Label(
                        "SVG, PNG, JPG or GIF (MAX. 800×400px)"
                );

        uploadInfo.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #9299AC;"
        );

        upload.getChildren().addAll(
                uploadIcon,
                uploadText,
                uploadInfo
        );

        upload.setOnMouseClicked(
                event -> chooseImages()
        );

        HBox thumbnails =
                new HBox(10);

        thumbnails.setPadding(
                new Insets(5, 0, 0, 0)
        );

        box.getChildren().addAll(
                heading,
                upload,
                thumbnails
        );

        return box;
    }

    // =========================
    // IMAGE CHOOSER
    // =========================

    private static void chooseImages() {

        FileChooser chooser =
                new FileChooser();

        chooser.setTitle(
                "Select Rental Images"
        );

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files",
                        "*.png",
                        "*.jpg",
                        "*.jpeg",
                        "*.gif"
                )
        );

        List<File> files =
                chooser.showOpenMultipleDialog(
                        stage
                );

        if (files == null) {
            return;
        }

        selectedImages.clear();

        for (File file : files) {

            if (selectedImages.size() >= 4) {
                break;
            }

            Image image =
                    new Image(
                            file.toURI().toString()
                    );

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
                "Location"
        );

        TextField address =
                new TextField();

        address.setPromptText(
                "Street address or pickup location"
        );

        TextField city =
                new TextField();

        city.setPromptText(
                "City"
        );

        TextField state =
                new TextField();

        state.setPromptText(
                "State"
        );

        TextField pin =
                new TextField();

        pin.setPromptText(
                "Zip/PIN"
        );

        GridPane grid =
                new GridPane();

        grid.setHgap(10);

        ColumnConstraints c1 =
                new ColumnConstraints();

        c1.setPercentWidth(33.33);

        ColumnConstraints c2 =
                new ColumnConstraints();

        c2.setPercentWidth(33.33);

        ColumnConstraints c3 =
                new ColumnConstraints();

        c3.setPercentWidth(33.33);

        grid.getColumnConstraints()
                .addAll(c1, c2, c3);

        grid.add(
                labeledField(
                        "CITY",
                        city
                ),
                0,
                0
        );

        grid.add(
                labeledField(
                        "STATE",
                        state
                ),
                1,
                0
        );

        grid.add(
                labeledField(
                        "PIN CODE",
                        pin
                ),
                2,
                0
        );

        box.getChildren().addAll(
                heading,
                labeledField(
                        "ADDRESS",
                        address
                ),
                grid
        );

        return box;
    }

    // =========================
    // RENTAL TERMS
    // =========================

    private static VBox createRentalTerms() {

        VBox box = card();

        HBox heading = sectionTitle(
                "⚒",
                "Rental Terms"
        );

        TextArea rules =
                new TextArea();

        rules.setPromptText(
                "e.g., No smoking, handle with care..."
        );

        rules.setPrefRowCount(2);
        rules.setWrapText(true);

        TextArea cancellation =
                new TextArea();

        cancellation.setPromptText(
                "Describe cancellation fees or terms..."
        );

        cancellation.setPrefRowCount(2);
        cancellation.setWrapText(true);

        box.getChildren().addAll(
                heading,
                labeledField(
                        "RENTAL RULES",
                        rules
                ),
                labeledField(
                        "CANCELLATION POLICY",
                        cancellation
                )
        );

        return box;
    }

    // =========================
    // CARD
    // =========================

    private static VBox card() {

        VBox box = new VBox(12);

        box.setPadding(
                new Insets(18)
        );

        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        return box;
    }

    // =========================
    // SECTION TITLE
    // =========================

    private static HBox sectionTitle(
            String icon,
            String text
    ) {

        HBox row = new HBox(8);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 15px;"
        );

        Label title =
                new Label(text);

        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        row.getChildren().addAll(
                iconLabel,
                title
        );

        return row;
    }

    // =========================
    // LABELED FIELD
    // =========================

    private static VBox labeledField(
            String labelText,
            Control control
    ) {

        VBox box = new VBox(5);

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        styleControl(control);

        box.getChildren().addAll(
                label,
                control
        );

        return box;
    }

    // =========================
    // MONEY FIELD
    // =========================

    private static TextField moneyField() {

        TextField field =
                new TextField();

        field.setPromptText(
                "$ 0.00"
        );

        field.setPrefHeight(38);

        return field;
    }

    // =========================
    // CONTROL STYLE
    // =========================

    private static void styleControl(
            Control control
    ) {

        control.setMaxWidth(
                Double.MAX_VALUE
        );

        if (control instanceof TextInputControl) {

            control.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #D2D6E3;" +
                    "-fx-border-radius: 5px;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-padding: 8px;"
            );
        }

        if (control instanceof ComboBox) {

            control.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #D2D6E3;" +
                    "-fx-border-radius: 5px;" +
                    "-fx-background-radius: 5px;"
            );

            control.setPrefHeight(38);
        }

        if (control instanceof DatePicker) {

            control.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #D2D6E3;" +
                    "-fx-border-radius: 5px;" +
                    "-fx-background-radius: 5px;"
            );

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
            boolean selected
    ) {

        Button button =
                new Button(
                        icon + "    " + text
                );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(42);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(0, 12, 0, 12)
        );

        button.setStyle(
                navStyle(selected)
        );

        return button;
    }

    private static String navStyle(
            boolean selected
    ) {

        if (selected) {

            return
                    "-fx-background-color: #DCE5FF;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 7px;" +
                    "-fx-cursor: hand;";
        }

        return
                "-fx-background-color: transparent;" +
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
            double height
    ) {

        button.setPrefWidth(width);
        button.setPrefHeight(height);

        button.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================
    // SECONDARY BUTTON
    // =========================

    private static void styleSecondaryButton(
            Button button,
            double width,
            double height
    ) {

        button.setPrefWidth(width);
        button.setPrefHeight(height);

        button.setStyle(
                "-fx-background-color: #EEF0F8;" +
                "-fx-text-fill: #33456C;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================
    // OUTLINE BUTTON
    // =========================

    private static void styleOutlineButton(
            Button button,
            double width,
            double height
    ) {

        button.setPrefWidth(width);
        button.setPrefHeight(height);

        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-border-color: " + BLUE + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );
    }
}
