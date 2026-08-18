package com.rentsathi.ui.screens.customer;

import java.net.URL;

import com.rentsathi.ui.screens.CustomerLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

public class DashboardScreen {

    private Stage stage;


    private static final String BLUE = "#3657C8";
    private static final String LIGHT_BLUE = "#DCE7FF";
    private static final String DARK_TEXT = "#20222A";
    private static final String SECONDARY_TEXT = "#64708A";
    private static final String BORDER = "#C9CEDD";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String WHITE = "#FFFFFF";
    private static final String CARD_BG = "#F3F5FC";



    public DashboardScreen(Stage stage) {
        this.stage = stage;
    }


    public void show() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        // =========================================================
        // SIDEBAR
        // =========================================================

        VBox sidebar = createSidebar();

        // =========================================================
        // MAIN AREA
        // =========================================================

        BorderPane mainArea = new BorderPane();

        // Top header
        HBox topBar = createTopBar();

        mainArea.setTop(topBar);

        // Dashboard content
        VBox content = createDashboardContent();

        ScrollPane scrollPane = new ScrollPane(content);

scrollPane.setFitToWidth(true);
scrollPane.setFitToHeight(false);

scrollPane.setPannable(true);

scrollPane.setHbarPolicy(
        ScrollPane.ScrollBarPolicy.NEVER
);

scrollPane.setVbarPolicy(
        ScrollPane.ScrollBarPolicy.AS_NEEDED
);

scrollPane.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-background: transparent;"
);

        mainArea.setCenter(scrollPane);

        // =========================================================
        // ROOT
        // =========================================================

        root.setLeft(sidebar);
        root.setCenter(mainArea);

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
                "RentSathi - Dashboard"
        );

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    // =============================================================
    // SIDEBAR
    // =============================================================

    private VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(232);

        sidebar.setPadding(
                new Insets(18, 15, 20, 15)
        );

        sidebar.setSpacing(10);

        sidebar.setStyle(
                "-fx-background-color: #F9F9FE;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        // =========================================================
        // LOGO
        // =========================================================

        HBox logoBox = new HBox(10);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane logo = new StackPane();

        logo.setPrefSize(40, 40);
        logo.setMinSize(40, 40);
        logo.setMaxSize(40, 40);

        logo.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 8px;"
        );

        Label logoText = new Label("RS");

        logoText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        logoText.setTextFill(
                Color.WHITE
        );

        logo.getChildren().add(
                logoText
        );

        VBox brandText = new VBox(0);

        Label brand = new Label(
                "RentSathi"
        );

        brand.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        brand.setTextFill(
                Color.web("#111827")
        );

        Label tagline = new Label(
                "Rent Smart. Live Easy."
        );

        tagline.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        10
                )
        );

        tagline.setTextFill(
                Color.web(DARK_TEXT)
        );

        brandText.getChildren().addAll(
                brand,
                tagline
        );

        logoBox.getChildren().addAll(
                logo,
                brandText
        );

        // =========================================================
        // NAVIGATION
        // =========================================================

        VBox navigation = new VBox(7);

        navigation.setPadding(
                new Insets(25, 0, 0, 0)
        );

        Button customerButton =
                createNavigationButton(
                        "♟",
                        "Customer",
                        true
                );

        Button ownerButton =
                createNavigationButton(
                        "▤",
                        "Owner",
                        false
                );

        Button deliveryButton =
                createNavigationButton(
                        "▱",
                        "Delivery Partner",
                        false
                );

        Button adminButton =
                createNavigationButton(
                        "♧",
                        "Admin",
                        false
                );

        navigation.getChildren().addAll(
                customerButton,
                ownerButton,
                deliveryButton,
                adminButton
        );

        // =========================================================
        // SPACER
        // =========================================================

        RegionSpacer spacer =
                new RegionSpacer();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        // =========================================================
        // BOTTOM BUTTONS
        // =========================================================

        Button helpButton =
                createBottomButton(
                        "?",
                        "Help Center"
                );

        Button logoutButton =
                createBottomButton(
                        "↪",
                        "Logout"
                );

        logoutButton.setOnAction(
                event -> {
                CustomerLoginScreen.show(stage);
                } 
        );

        sidebar.getChildren().addAll(
                logoBox,
                navigation,
                spacer,
                helpButton,
                logoutButton
        );

        return sidebar;
    }

    // =============================================================
    // NAVIGATION BUTTON
    // =============================================================

    private Button createNavigationButton(
            String icon,
            String text,
            boolean selected
    ) {

        Button button =
                new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(40);

        HBox content =
                new HBox(15);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        if (selected) {

            textLabel.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            14
                    )
            );

            button.setStyle(
                    "-fx-background-color: #D8E3FB;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-cursor: hand;" +
                    "-fx-padding: 0 12px;"
            );

            iconLabel.setTextFill(
                    Color.web(BLUE)
            );

            textLabel.setTextFill(
                    Color.web("#52688C")
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #52688C;" +
                    "-fx-cursor: hand;" +
                    "-fx-padding: 0 12px;"
            );

            iconLabel.setTextFill(
                    Color.web("#52688C")
            );

            textLabel.setTextFill(
                    Color.web("#52688C")
            );
        }

        content.getChildren().addAll(
                iconLabel,
                textLabel
        );

        button.setGraphic(
                content
        );

        return button;
    }

    // =============================================================
    // BOTTOM BUTTON
    // =============================================================

    private Button createBottomButton(
            String icon,
            String text
    ) {

        Button button =
                new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(40);

        HBox content =
                new HBox(15);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        iconLabel.setTextFill(
                Color.web("#53637F")
        );

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        textLabel.setTextFill(
                Color.web("#52688C")
        );

        content.getChildren().addAll(
                iconLabel,
                textLabel
        );

        button.setGraphic(
                content
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0 12px;"
        );

        return button;
    }

    // =============================================================
    // TOP BAR
    // =============================================================

    private HBox createTopBar() {

        HBox topBar =
                new HBox();

        topBar.setPrefHeight(70);

        topBar.setPadding(
                new Insets(0, 25, 0, 30)
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        Label dashboardTitle =
                new Label(
                        "Dashboard"
                );

        dashboardTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        dashboardTitle.setTextFill(
                Color.web("#20222A")
        );

        // =========================================================
        // SPACER
        // =========================================================

        javafx.scene.layout.Region spacer =
                new javafx.scene.layout.Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // =========================================================
        // SEARCH
        // =========================================================

        HBox searchBox =
                new HBox();

        searchBox.setPrefWidth(245);
        searchBox.setPrefHeight(38);

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPadding(
                new Insets(0, 12, 0, 12)
        );

        searchBox.setStyle(
                "-fx-background-color: #FAFAFD;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        Label searchIcon =
                new Label("⌕");

        searchIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        searchIcon.setTextFill(
                Color.web("#52688C")
        );

        TextField topSearch =
                new TextField();

        topSearch.setPromptText(
                "Search rentals..."
        );

        topSearch.setPrefHeight(34);

        topSearch.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 13px;"
        );

        HBox.setHgrow(
                topSearch,
                Priority.ALWAYS
        );

        searchBox.getChildren().addAll(
                searchIcon,
                topSearch
        );

        // =========================================================
        // NOTIFICATION
        // =========================================================

        StackPane notification =
                new StackPane();

        notification.setPrefSize(
                45,
                45
        );

        Label bell =
                new Label("♧");

        bell.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        bell.setTextFill(
                Color.web("#53637F")
        );

        Circle notificationDot =
                new Circle(
                        4,
                        Color.web("#A83232")
                );

        StackPane.setAlignment(
                notificationDot,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                notificationDot,
                new Insets(
                        8,
                        9,
                        0,
                        0
                )
        );

        notification.getChildren().addAll(
                bell,
                notificationDot
        );

        // =========================================================
        // PROFILE
        // =========================================================

        StackPane profile =
                new StackPane();

        Circle profileCircle =
                new Circle(
                        15
                );

        profileCircle.setFill(
                Color.web("#D8DCE8")
        );

        Label profileIcon =
                new Label("●");

        profileIcon.setTextFill(
                Color.web("#53637F")
        );

        profile.getChildren().addAll(
                profileCircle,
                profileIcon
        );

        topBar.getChildren().addAll(
                dashboardTitle,
                spacer,
                searchBox,
                notification,
                profile
        );

        HBox.setMargin(
                searchBox,
                new Insets(
                        0,
                        18,
                        0,
                        0
                )
        );

        HBox.setMargin(
                notification,
                new Insets(
                        0,
                        8,
                        0,
                        0
                )
        );

        return topBar;
    }

    // =============================================================
    // DASHBOARD CONTENT
    // =============================================================

    private VBox createDashboardContent() {

        VBox content =
                new VBox();

        content.setPadding(
                new Insets(
                        30,
                        30,
                        25,
                        30
                )
        );

        content.setSpacing(25);

        // =========================================================
        // WELCOME CARD
        // =========================================================

        VBox welcomeCard =
                createWelcomeCard();

        // =========================================================
        // MAIN GRID
        // =========================================================

        HBox mainArea =
                new HBox(30);

        VBox leftContent =
                new VBox(25);

        VBox rightContent =
                new VBox(25);

        // Browse categories
        VBox categories =
                createCategoriesSection();

        // Recommended
        VBox recommended =
                createRecommendedSection();

        leftContent.getChildren().addAll(
                categories,
                recommended
        );

        // Right side
        VBox booking =
                createUpcomingBooking();

        VBox quickActions =
                createQuickActions();

        rightContent.getChildren().addAll(
                booking,
                quickActions
        );

        HBox.setHgrow(
                leftContent,
                Priority.ALWAYS
        );

        mainArea.getChildren().addAll(
                leftContent,
                rightContent
        );

        content.getChildren().addAll(
                welcomeCard,
                mainArea
        );

        return content;
    }

    // =============================================================
    // WELCOME CARD
    // =============================================================

    private VBox createWelcomeCard() {

        VBox card =
                new VBox();

        card.setPadding(
                new Insets(28, 30, 30, 30)
        );

        card.setSpacing(20);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        Label greeting =
                new Label(
                        "Good morning, Alex"
                );

        greeting.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        31
                )
        );

        greeting.setTextFill(
                Color.web(DARK_TEXT)
        );

        Label subtitle =
                new Label(
                        "Find something perfect to rent today."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        16
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        VBox heading =
                new VBox(4);

        heading.getChildren().addAll(
                greeting,
                subtitle
        );

        // =========================================================
        // SEARCH BAR
        // =========================================================

        HBox searchBar =
                new HBox();

        searchBar.setPrefHeight(50);

        searchBar.setStyle(
                "-fx-background-color: #F3F5FC;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        // Search input
        HBox searchInput =
                new HBox(10);

        searchInput.setAlignment(
                Pos.CENTER_LEFT
        );

        searchInput.setPadding(
                new Insets(0, 12, 0, 15)
        );

        Label searchIcon =
                new Label("⌕");

        searchIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        searchIcon.setTextFill(
                Color.web("#52688C")
        );

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "What are you looking for? (e.g. DSLR Camera, Drill)"
        );

        searchField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 14px;"
        );

        HBox.setHgrow(
                searchField,
                Priority.ALWAYS
        );

        searchInput.getChildren().addAll(
                searchIcon,
                searchField
        );

        // Location
        HBox location =
                new HBox(10);

        location.setAlignment(
                Pos.CENTER_LEFT
        );

        location.setPadding(
                new Insets(0, 15, 0, 15)
        );

        Label locationIcon =
                new Label("⌖");

        locationIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        locationIcon.setTextFill(
                Color.web("#52688C")
        );

        Label locationText =
                new Label(
                        "Choose location"
                );

        locationText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        locationText.setTextFill(
                Color.web(DARK_TEXT)
        );

        Label arrow =
                new Label("⌄");

        arrow.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        location.getChildren().addAll(
                locationIcon,
                locationText,
                arrow
        );

        // Search button
        Button searchButton =
                new Button(
                        "Search"
                );

        searchButton.setPrefWidth(
                90
        );

        searchButton.setPrefHeight(
                40
        );

        searchButton.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        searchButton.setOnAction(
                event -> System.out.println(
                        "Search clicked"
                )
        );

        searchBar.getChildren().addAll(
                searchInput,
                location,
                searchButton
        );

        HBox.setHgrow(
                searchInput,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                heading,
                searchBar
        );

        return card;
    }

    // =============================================================
    // CATEGORIES
    // =============================================================

    private VBox createCategoriesSection() {

        VBox section =
                new VBox(15);

        Label title =
                new Label(
                        "Browse Categories"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        title.setTextFill(
                Color.web(DARK_TEXT)
        );

        GridPane grid =
                new GridPane();

        grid.setHgap(15);
        grid.setVgap(15);

        String[] names = {
                "Electronics",
                "Furniture",
                "Vehicles",
                "Tools",
                "Appliances",
                "Events & Party",
                "Sports",
                "Other"
        };

        String[] icons = {
                "▣",
                "▤",
                "▱",
                "⚒",
                "▥",
                "♢",
                "◎",
                "•••"
        };

        for (int i = 0; i < names.length; i++) {

            VBox category =
                    createCategoryCard(
                            icons[i],
                            names[i]
                    );

            grid.add(
                    category,
                    i % 4,
                    i / 4
            );

            GridPane.setHgrow(
                    category,
                    Priority.ALWAYS
            );
        }

        for (int i = 0; i < 4; i++) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(
                    25
            );

            grid.getColumnConstraints().add(
                    column
            );
        }

        section.getChildren().addAll(
                title,
                grid
        );

        return section;
    }

    // =============================================================
    // CATEGORY CARD
    // =============================================================

    private VBox createCategoryCard(
            String icon,
            String name
    ) {

        VBox card =
                new VBox(10);

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefHeight(105);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        StackPane iconCircle =
                new StackPane();

        iconCircle.setPrefSize(
                46,
                46
        );

        iconCircle.setMaxSize(
                46,
                46
        );

        iconCircle.setStyle(
                "-fx-background-color: #D9E5FF;" +
                "-fx-background-radius: 50%;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        iconLabel.setTextFill(
                Color.web("#5E6F8D")
        );

        iconCircle.getChildren().add(
                iconLabel
        );

        Label text =
                new Label(name);

        text.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        text.setTextFill(
                Color.web(DARK_TEXT)
        );

        card.getChildren().addAll(
                iconCircle,
                text
        );

        if ("Electronics".equals(name)) {

            card.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " + BLUE + ";" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-background-radius: 10px;" +
                    "-fx-cursor: hand;"
            );

            card.setOnMouseClicked(
                    event -> ElectronicsRentalScreen.show(stage)
            );
        }

        return card;
    }

    // =============================================================
    // UPCOMING BOOKING
    // =============================================================

    private VBox createUpcomingBooking() {

        VBox card =
                new VBox();

        card.setPrefWidth(290);

        card.setPadding(
                new Insets(20, 16, 15, 16)
        );

        card.setSpacing(12);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 2 1 1 1;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        Label title =
                new Label(
                        "⇥  Upcoming Booking"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        title.setTextFill(
                Color.web(DARK_TEXT)
        );

        // Booking box
        VBox booking =
                new VBox(12);

        booking.setPadding(
                new Insets(15)
        );

        booking.setStyle(
                "-fx-background-color: #F4F6FB;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        // Top row
        HBox topRow =
                new HBox();

        Label confirmed =
                new Label(
                        "Confirmed"
                );

        confirmed.setPadding(
                new Insets(4, 7, 4, 7)
        );

        confirmed.setStyle(
                "-fx-background-color: #DCE6FF;" +
                "-fx-text-fill: #273D87;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 4px;"
        );

        Label bookingNumber =
                new Label(
                        "Booking #8492"
                );

        bookingNumber.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        11
                )
        );

        bookingNumber.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        javafx.scene.layout.Region topSpacer =
                new javafx.scene.layout.Region();

        HBox.setHgrow(
                topSpacer,
                Priority.ALWAYS
        );

        topRow.getChildren().addAll(
                confirmed,
                topSpacer,
                bookingNumber
        );

        Label item =
                new Label(
                        "Yamaha Portable Generator"
                );

        item.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        item.setTextFill(
                Color.web(DARK_TEXT)
        );

        Line line =
                new Line(
                        0,
                        0,
                        250,
                        0
                );

        line.setStroke(
                Color.web(BORDER)
        );

        Label date =
                new Label(
                        "▣   Oct 24 - Oct 26, 2023"
                );

        date.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        date.setTextFill(
                Color.web(DARK_TEXT)
        );

        Label pickup =
                new Label(
                        "     Pickup at 10:00 AM"
                );

        pickup.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        12
                )
        );

        pickup.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Label location =
                new Label(
                        "⌖   123 Main St Garage\n"
                                + "     Brooklyn, NY"
                );

        location.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        12
                )
        );

        location.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        booking.getChildren().addAll(
                topRow,
                item,
                line,
                date,
                pickup,
                location
        );

        Button details =
                new Button(
                        "View Booking Details"
                );

        details.setMaxWidth(
                Double.MAX_VALUE
        );

        details.setPrefHeight(38);

        details.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BLUE + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );

        card.getChildren().addAll(
                title,
                booking,
                details
        );

        return card;
    }

    // =============================================================
    // QUICK ACTIONS
    // =============================================================

    private VBox createQuickActions() {

        VBox card =
                new VBox();

        card.setPrefWidth(290);

        card.setPadding(
                new Insets(18, 18, 15, 18)
        );

        card.setSpacing(10);

        card.setStyle(
                "-fx-background-color: #F1F3FB;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        Label title =
                new Label(
                        "Quick Actions"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        title.setTextFill(
                Color.web(DARK_TEXT)
        );

        Button history =
                createActionButton(
                        "◷",
                        "Rental History"
                );

        Button saved =
                createActionButton(
                        "♡",
                        "Saved Items"
                );

        Button messages =
                createActionButton(
                        "▤",
                        "Messages"
                );

        // Message badge
        StackPane messagePane =
                new StackPane();

        messagePane.setAlignment(
                Pos.CENTER_LEFT
        );

        messagePane.getChildren().add(
                messages
        );

        Circle badge =
                new Circle(
                        9,
                        Color.web("#B83232")
                );

        Label badgeText =
                new Label("2");

        badgeText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        badgeText.setTextFill(
                Color.WHITE
        );

        StackPane badgePane =
                new StackPane(
                        badge,
                        badgeText
                );

        StackPane.setAlignment(
                badgePane,
                Pos.CENTER_RIGHT
        );

        StackPane.setMargin(
                badgePane,
                new Insets(
                        0,
                        2,
                        0,
                        0
                )
        );

        messagePane.getChildren().add(
                badgePane
        );

        card.getChildren().addAll(
                title,
                history,
                saved,
                messagePane
        );

        return card;
    }

    // =============================================================
    // ACTION BUTTON
    // =============================================================

    private Button createActionButton(
            String icon,
            String text
    ) {

        Button button =
                new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(38);

        HBox content =
                new HBox(12);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        iconLabel.setTextFill(
                Color.web("#53637F")
        );

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        textLabel.setTextFill(
                Color.web("#53637F")
        );

        javafx.scene.layout.Region spacer =
                new javafx.scene.layout.Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label arrow =
                new Label("›");

        arrow.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        arrow.setTextFill(
                Color.web("#53637F")
        );

        content.getChildren().addAll(
                iconLabel,
                textLabel,
                spacer,
                arrow
        );

        button.setGraphic(
                content
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0;"
        );

        return button;
    }

    // =============================================================
    // RECOMMENDED SECTION
    // =============================================================

    private VBox createRecommendedSection() {

        VBox section =
                new VBox(15);

        // =========================================================
        // HEADER
        // =========================================================

        HBox header =
                new HBox();

        Label title =
                new Label(
                        "Recommended for You"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        title.setTextFill(
                Color.web(DARK_TEXT)
        );

        javafx.scene.layout.Region spacer =
                new javafx.scene.layout.Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button viewAll =
                new Button(
                        "View All"
                );

        viewAll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );

        header.getChildren().addAll(
                title,
                spacer,
                viewAll
        );

        // =========================================================
        // CARDS
        // =========================================================

        HBox cards =
                new HBox(15);

        VBox cameraCard =
                createRentalCard(
                        "Sony A7III Camera Kit",
                        "$45",
                        "Downtown NY (2 mi)",
                        "4.9",
                        "/images/camera.jpg"
                );

        VBox drillCard =
                createRentalCard(
                        "DeWalt Cordless Drill",
                        "$15",
                        "Brooklyn, NY (5mi)",
                        "4.7",
                        "/images/drill.jpg"
                );

        HBox.setHgrow(
                cameraCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                drillCard,
                Priority.ALWAYS
        );

        cards.getChildren().addAll(
                cameraCard,
                drillCard
        );

        section.getChildren().addAll(
                header,
                cards
        );

        return section;
    }

    // =============================================================
    // RENTAL CARD
    // =============================================================

    private VBox createRentalCard(
            String name,
            String price,
            String location,
            String rating,
            String imagePath
    ) {

        VBox card =
                new VBox();

        card.setPrefWidth(300);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        // =========================================================
        // IMAGE
        // =========================================================

        StackPane imageContainer =
                new StackPane();

        imageContainer.setPrefHeight(
                150
        );

        try {

            URL url =
                    DashboardScreen.class.getResource(
                            imagePath
                    );

            if (url != null) {

                Image image =
                        new Image(
                                url.toExternalForm()
                        );

                ImageView imageView =
                        new ImageView(image);

                imageView.setFitWidth(300);
                imageView.setFitHeight(150);

                imageView.setPreserveRatio(false);

                imageContainer.getChildren().add(
                        imageView
                );
            }

        } catch (Exception e) {

            imageContainer.setStyle(
                    "-fx-background-color: #E4E8F2;"
            );
        }

        // =========================================================
        // AVAILABLE BADGE
        // =========================================================

        Label available =
                new Label(
                        "Available"
                );

        available.setPadding(
                new Insets(4, 7, 4, 7)
        );

        available.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #A54A17;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 4px;"
        );

        StackPane.setAlignment(
                available,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                available,
                new Insets(
                        8,
                        7,
                        0,
                        0
                )
        );

        imageContainer.getChildren().add(
                available
        );

        // =========================================================
        // CARD INFORMATION
        // =========================================================

        VBox information =
                new VBox();

        information.setPadding(
                new Insets(12, 15, 12, 15)
        );

        information.setSpacing(8);

        HBox nameRating =
                new HBox();

        Label itemName =
                new Label(name);

        itemName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        itemName.setTextFill(
                Color.web(DARK_TEXT)
        );

        Label star =
                new Label(
                        "☆ " + rating
                );

        star.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        star.setTextFill(
                Color.web("#9B5724")
        );

        javafx.scene.layout.Region spacer =
                new javafx.scene.layout.Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        nameRating.getChildren().addAll(
                itemName,
                spacer,
                star
        );

        Label locationLabel =
                new Label(
                        "⌖ " + location
                );

        locationLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        12
                )
        );

        locationLabel.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Line line =
                new Line(
                        0,
                        0,
                        270,
                        0
                );

        line.setStroke(
                Color.web(BORDER)
        );

        HBox priceRow =
                new HBox();

        Label priceLabel =
                new Label(
                        price
                );

        priceLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        priceLabel.setTextFill(
                Color.web(BLUE)
        );

        Label day =
                new Label(
                        " /day"
                );

        day.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        day.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Button details =
                new Button(
                        "View Details"
                );

        details.setPrefWidth(110);

        details.setPrefHeight(32);

        details.setStyle(
                "-fx-background-color: #E7EAF4;" +
                "-fx-border-color: #C6CBDA;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + DARK_TEXT + ";" +
                "-fx-font-size: 12px;" +
                "-fx-cursor: hand;"
        );

        javafx.scene.layout.Region priceSpacer =
                new javafx.scene.layout.Region();

        HBox.setHgrow(
                priceSpacer,
                Priority.ALWAYS
        );

        priceRow.getChildren().addAll(
                priceLabel,
                day,
                priceSpacer,
                details
        );

        information.getChildren().addAll(
                nameRating,
                locationLabel,
                line,
                priceRow
        );

        card.getChildren().addAll(
                imageContainer,
                information
        );

        return card;
    }

    // =============================================================
    // REGION SPACER CLASS
    // =============================================================

    private static class RegionSpacer
            extends javafx.scene.layout.Region {

        public RegionSpacer() {
            setMinHeight(10);
        }
    }
}
