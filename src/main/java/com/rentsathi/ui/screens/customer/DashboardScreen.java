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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.SVGPath;

public class DashboardScreen {

        private Stage stage;

        private static final String BLUE = "#3158D4";
        private static final String LIGHT_BLUE = "#E8EEFF";
        private static final String DARK_TEXT = "#17233D";
        private static final String SECONDARY_TEXT = "#66738F";
        private static final String BORDER = "#E0E5F0";
        private static final String BACKGROUND = "#F7F9FD";
        private static final String WHITE = "#FFFFFF";
        private static final String CARD_BG = "#F1F4FB";

        public DashboardScreen(Stage stage) {
                this.stage = stage;
        }

        public void show() {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BACKGROUND + ";");

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
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

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
                                1450,
                                820);

                scene.setFill(
                                Color.web(BACKGROUND));

                stage.setTitle(
                                "RentSathi - Dashboard");

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

                sidebar.setPrefWidth(265);
                sidebar.setMinWidth(265);
                sidebar.setMaxWidth(265);

                sidebar.setPadding(new Insets(28, 18, 20, 18));
                sidebar.setSpacing(10);

                sidebar.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E4E8F0;" +
                                                "-fx-border-width: 0 1 0 0;");

                // BRAND
                HBox logoBox = new HBox(11);
                logoBox.setAlignment(Pos.CENTER_LEFT);

                StackPane logo = new StackPane();
                logo.setPrefSize(52, 52);
                logo.setMinSize(52, 52);
                logo.setMaxSize(52, 52);
                logo.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-background-radius: 13px;");

                Label logoText = new Label("RS");
                logoText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                logoText.setTextFill(Color.WHITE);
                logo.getChildren().add(logoText);

                VBox brandText = new VBox(0);

                Label brand = new Label("RentSathi");
                brand.setFont(Font.font("Arial", FontWeight.BOLD, 21));
                brand.setTextFill(Color.web(DARK_TEXT));

                Label tagline = new Label("Rent Smart. Live Easy.");
                tagline.setFont(Font.font("Arial", FontWeight.NORMAL, 11));
                tagline.setTextFill(Color.web("#64738F"));

                brandText.getChildren().addAll(brand, tagline);
                logoBox.getChildren().addAll(logo, brandText);

                // NAVIGATION
                VBox navigation = new VBox(8);
                navigation.setPadding(new Insets(32, 0, 0, 0));

                Button customerButton = createNavigationButton("home", "Customer", true);

                Button ownerButton = createNavigationButton("owner", "Owner", false);

                Button deliveryButton = createNavigationButton("delivery", "Delivery Partner", false);

                Button adminButton = createNavigationButton("admin", "Admin", false);

                navigation.getChildren().addAll(
                                customerButton,
                                ownerButton,
                                deliveryButton,
                                adminButton);

                RegionSpacer spacer = new RegionSpacer();
                VBox.setVgrow(spacer, Priority.ALWAYS);

                Button helpButton = createBottomButton("help", "Help Center");

                Button logoutButton = createBottomButton("logout", "Logout");

                logoutButton.setOnAction(
                                event -> CustomerLoginScreen.show(stage));

                sidebar.getChildren().addAll(
                                logoBox,
                                navigation,
                                spacer,
                                helpButton,
                                logoutButton);

                return sidebar;
        }

        // =============================================================
        // NAVIGATION BUTTON
        // =============================================================

        private Button createNavigationButton(
                        String iconType,
                        String text,
                        boolean selected) {

                Button button = new Button();
                button.setMaxWidth(Double.MAX_VALUE);
                button.setPrefHeight(50);

                HBox content = new HBox(14);
                content.setAlignment(Pos.CENTER_LEFT);

                SVGPath icon = createIcon(iconType);
                icon.setScaleX(0.78);
                icon.setScaleY(0.78);

                Label textLabel = new Label(text);
                textLabel.setFont(
                                Font.font(
                                                "Arial",
                                                selected ? FontWeight.BOLD : FontWeight.NORMAL,
                                                14));

                if (selected) {
                        button.setStyle(
                                        "-fx-background-color: #E8EEFF;" +
                                                        "-fx-background-radius: 11px;" +
                                                        "-fx-border-color: #D7E1FF;" +
                                                        "-fx-border-width: 1px;" +
                                                        "-fx-border-radius: 11px;" +
                                                        "-fx-padding: 0 14px;" +
                                                        "-fx-cursor: hand;");
                        textLabel.setTextFill(Color.web(BLUE));
                } else {
                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-background-radius: 11px;" +
                                                        "-fx-padding: 0 14px;" +
                                                        "-fx-cursor: hand;");
                        textLabel.setTextFill(Color.web("#526A94"));
                }

                content.getChildren().addAll(icon, textLabel);
                button.setGraphic(content);

                return button;
        }

        // =============================================================
        // BOTTOM BUTTON
        // =============================================================

        private Button createBottomButton(
                        String iconType,
                        String text) {

                Button button = new Button();
                button.setMaxWidth(Double.MAX_VALUE);
                button.setPrefHeight(45);

                HBox content = new HBox(14);
                content.setAlignment(Pos.CENTER_LEFT);

                SVGPath icon = createIcon(iconType);
                icon.setScaleX(0.72);
                icon.setScaleY(0.72);

                Label textLabel = new Label(text);
                textLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
                textLabel.setTextFill(Color.web("#526A94"));

                content.getChildren().addAll(icon, textLabel);

                button.setGraphic(content);
                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 0 12px;");

                return button;
        }

        // =============================================================
        // TOP BAR
        // =============================================================

        private HBox createTopBar() {

                HBox topBar = new HBox();

                // FIX: compact professional header
                topBar.setPrefHeight(82);
                topBar.setMinHeight(82);
                topBar.setMaxHeight(82);

                topBar.setPadding(
                                new Insets(0, 30, 0, 38));

                topBar.setAlignment(Pos.CENTER_LEFT);

                topBar.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E3E8F2;" +
                                                "-fx-border-width: 0 0 1 0;");

                // =========================================================
                // DASHBOARD TITLE
                // =========================================================

                Label dashboardTitle = new Label("Dashboard");

                dashboardTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                24));

                dashboardTitle.setTextFill(
                                Color.web(DARK_TEXT));

                Label dashboardSubtitle = new Label(
                                "Welcome back! Here's what's happening today.");

                dashboardSubtitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                12));

                dashboardSubtitle.setTextFill(
                                Color.web("#71809B"));

                VBox titleBox = new VBox(
                                2,
                                dashboardTitle,
                                dashboardSubtitle);

                titleBox.setAlignment(
                                Pos.CENTER_LEFT);

                // =========================================================
                // SPACER
                // =========================================================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // =========================================================
                // TOP SEARCH
                // =========================================================

                HBox searchBox = new HBox(8);

                // FIX: smaller search box
                searchBox.setPrefWidth(280);
                searchBox.setMinWidth(280);
                searchBox.setMaxWidth(280);

                searchBox.setPrefHeight(44);
                searchBox.setMinHeight(44);
                searchBox.setMaxHeight(44);

                searchBox.setAlignment(
                                Pos.CENTER_LEFT);

                searchBox.setPadding(
                                new Insets(0, 12, 0, 13));

                searchBox.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #D9E0EC;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 11px;" +
                                                "-fx-background-radius: 11px;");

                SVGPath searchIcon = createIcon("search");

                searchIcon.setScaleX(0.68);
                searchIcon.setScaleY(0.68);

                TextField topSearch = new TextField();

                topSearch.setPromptText(
                                "Search rentals...");

                topSearch.setPrefHeight(38);
                topSearch.setMinHeight(38);
                topSearch.setMaxHeight(38);

                topSearch.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-background-insets: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: #17233D;" +
                                                "-fx-prompt-text-fill: #9AA5B8;");

                HBox.setHgrow(
                                topSearch,
                                Priority.ALWAYS);

                searchBox.getChildren().addAll(
                                searchIcon,
                                topSearch);

                // =========================================================
                // NOTIFICATION
                // =========================================================

                StackPane notification = new StackPane();

                notification.setPrefSize(42, 42);
                notification.setMinSize(42, 42);
                notification.setMaxSize(42, 42);

                SVGPath bell = createIcon("bell");

                bell.setScaleX(0.72);
                bell.setScaleY(0.72);

                notification.getChildren().add(
                                bell);

                // Red notification dot
                Circle notificationDot = new Circle(
                                4.2,
                                Color.web("#C83232"));

                StackPane.setAlignment(
                                notificationDot,
                                Pos.TOP_RIGHT);

                StackPane.setMargin(
                                notificationDot,
                                new Insets(
                                                6,
                                                7,
                                                0,
                                                0));

                notification.getChildren().add(
                                notificationDot);

                // =========================================================
                // PROFILE
                // =========================================================

                StackPane profileCircle = new StackPane();

                profileCircle.setPrefSize(36, 36);
                profileCircle.setMinSize(36, 36);
                profileCircle.setMaxSize(36, 36);

                profileCircle.setStyle(
                                "-fx-background-color: #E8EDF7;" +
                                                "-fx-background-radius: 50%;");

                Label profileLetter = new Label("A");

                profileLetter.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                profileLetter.setTextFill(
                                Color.web("#40516F"));

                profileCircle.getChildren().add(
                                profileLetter);

                Label profileArrow = new Label("⌄");

                profileArrow.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                profileArrow.setTextFill(
                                Color.web("#526A94"));

                HBox profileBox = new HBox(
                                5,
                                profileCircle,
                                profileArrow);

                profileBox.setAlignment(
                                Pos.CENTER);

                // =========================================================
                // HEADER
                // =========================================================

                topBar.getChildren().addAll(
                                titleBox,
                                spacer,
                                searchBox,
                                notification,
                                profileBox);

                HBox.setMargin(
                                searchBox,
                                new Insets(
                                                0,
                                                15,
                                                0,
                                                0));

                HBox.setMargin(
                                notification,
                                new Insets(
                                                0,
                                                12,
                                                0,
                                                0));

                return topBar;
        }

        // =============================================================
        // DASHBOARD CONTENT
        // =============================================================

        private VBox createDashboardContent() {

                VBox content = new VBox();

                content.setPadding(new Insets(20, 32, 28, 32));
                content.setSpacing(20);
                content.setStyle("-fx-background-color: " + BACKGROUND + ";");

                VBox welcomeCard = createWelcomeCard();

                HBox mainArea = new HBox(24);

                VBox leftContent = new VBox(24);
                VBox rightContent = new VBox(18);

                VBox categories = createCategoriesSection();
                VBox recommended = createRecommendedSection();

                leftContent.getChildren().addAll(categories, recommended);

                VBox booking = createUpcomingBooking();
                VBox quickActions = createQuickActions();

                rightContent.getChildren().addAll(booking, quickActions);

                HBox.setHgrow(leftContent, Priority.ALWAYS);

                mainArea.getChildren().addAll(
                                leftContent,
                                rightContent);

                content.getChildren().addAll(
                                welcomeCard,
                                mainArea);

                return content;
        }

        // =============================================================
        // WELCOME CARD
        // =============================================================

        private VBox createWelcomeCard() {

                VBox card = new VBox();

                card.setPadding(new Insets(24, 24, 20, 24));
                card.setSpacing(17);
                card.setPrefHeight(170);

                card.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to right, #FFFFFF 0%, #F7F9FF 62%, #EAF1FF 100%);" +
                                                "-fx-border-color: #E0E6F2;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 17px;" +
                                                "-fx-background-radius: 17px;" +
                                                "-fx-effect: dropshadow(" +
                                                "gaussian, rgba(45,70,120,0.12), 18, 0.12, 0, 5);");

                Label greeting =

                                new Label("Good morning, Alex 👋");

                new Label(
                                "Good morning, Sahil");

                greeting.setFont(
                                Font.font("Arial", FontWeight.BOLD, 29));
                greeting.setTextFill(Color.web(DARK_TEXT));

                Label subtitle = new Label("Find something perfect to rent today.");

                subtitle.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 15));
                subtitle.setTextFill(Color.web(SECONDARY_TEXT));

                VBox heading = new VBox(3, greeting, subtitle);

                HBox searchBar = new HBox();
                searchBar.setPrefHeight(52);
                searchBar.setMinHeight(52);
                searchBar.setMaxHeight(52);
                searchBar.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #DCE3F0;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 11px;" +
                                                "-fx-background-radius: 11px;");

                HBox searchInput = new HBox(10);
                searchInput.setAlignment(Pos.CENTER_LEFT);
                searchInput.setPadding(new Insets(0, 12, 0, 14));

                SVGPath searchIcon = createIcon("search");
                searchIcon.setScaleX(0.72);
                searchIcon.setScaleY(0.72);

                TextField searchField = new TextField();
                searchField.setPromptText(
                                "What are you looking for? (e.g. DSLR Camera, Drill)");
                searchField.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + DARK_TEXT + ";" +
                                                "-fx-prompt-text-fill: #A1AABA;");
                HBox.setHgrow(searchField, Priority.ALWAYS);

                searchInput.getChildren().addAll(searchIcon, searchField);

                HBox location = new HBox(9);

                location.setPrefWidth(185);
                location.setMinWidth(185);
                location.setMaxWidth(185);

                location.setAlignment(Pos.CENTER_LEFT);
                location.setPadding(new Insets(0, 13, 0, 13));

                SVGPath locationIcon = createIcon("location");
                locationIcon.setScaleX(0.68);
                locationIcon.setScaleY(0.68);

                Label locationText = new Label("Choose location");
                locationText.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 13));
                locationText.setTextFill(Color.web(DARK_TEXT));

                Label arrow = new Label("⌄");
                arrow.setFont(Font.font("Arial", FontWeight.BOLD, 13));
                arrow.setTextFill(Color.web("#71809B"));

                location.getChildren().addAll(
                                locationIcon,
                                locationText,
                                arrow);

                Button searchButton = new Button("Search");
                searchButton.setPrefWidth(88);
                searchButton.setMinWidth(88);
                searchButton.setMaxWidth(88);

                searchButton.setPrefHeight(40);
                searchButton.setMinHeight(40);
                searchButton.setMaxHeight(40);

                searchButton.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-cursor: hand;");

                searchButton.setOnAction(
                                event -> System.out.println(
                                                "Search clicked: " + searchField.getText()));

                searchBar.getChildren().addAll(
                                searchInput,
                                location,
                                searchButton);

                HBox.setHgrow(searchInput, Priority.ALWAYS);

                card.getChildren().addAll(
                                heading,
                                searchBar);

                return card;
        }

        // =============================================================
        // CATEGORIES
        // =============================================================

        private VBox createCategoriesSection() {

                VBox section = new VBox(13);

                HBox header = new HBox();

                Label title = new Label("Browse Categories");
                title.setFont(
                                Font.font("Arial", FontWeight.BOLD, 20));
                title.setTextFill(Color.web(DARK_TEXT));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label viewAll = new Label("View All  ›");
                viewAll.setFont(
                                Font.font("Arial", FontWeight.BOLD, 12));
                viewAll.setTextFill(Color.web(BLUE));

                header.setAlignment(Pos.CENTER_LEFT);
                header.getChildren().addAll(
                                title,
                                spacer,
                                viewAll);

                GridPane grid = new GridPane();
                grid.setHgap(12);
                grid.setVgap(12);

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
                                "electronics",
                                "furniture",
                                "vehicle",
                                "tools",
                                "appliances",
                                "events",
                                "sports",
                                "other"
                };

                for (int i = 0; i < names.length; i++) {

                        VBox category = createCategoryCard(
                                        icons[i],
                                        names[i]);

                        grid.add(
                                        category,
                                        i % 4,
                                        i / 4);

                        GridPane.setHgrow(
                                        category,
                                        Priority.ALWAYS);
                }

                for (int i = 0; i < 4; i++) {

                        ColumnConstraints column = new ColumnConstraints();

                        column.setPercentWidth(25);
                        grid.getColumnConstraints().add(column);
                }

                section.getChildren().addAll(
                                header,
                                grid);

                return section;
        }

        // =============================================================
        // CATEGORY CARD
        // =============================================================

        private VBox createCategoryCard(
                        String iconType,
                        String name) {

                VBox card = new VBox(9);

                card.setAlignment(Pos.CENTER);
                card.setPrefHeight(96);
                card.setMinHeight(96);

                card.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0E5EF;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 13px;" +
                                                "-fx-background-radius: 13px;" +
                                                "-fx-effect: dropshadow(" +
                                                "gaussian, rgba(40,60,100,0.07), 9, 0.08, 0, 2);" +
                                                "-fx-cursor: hand;");

                StackPane iconCircle = new StackPane();
                iconCircle.setPrefSize(44, 44);
                iconCircle.setMinSize(44, 44);
                iconCircle.setMaxSize(44, 44);

                iconCircle.setStyle(
                                "-fx-background-color: #E8EEFF;" +
                                                "-fx-background-radius: 50%;");

                SVGPath icon = createIcon(iconType);
                icon.setScaleX(0.85);
                icon.setScaleY(0.85);

                iconCircle.getChildren().add(icon);

                Label text = new Label(name);
                text.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 13));
                text.setTextFill(Color.web(DARK_TEXT));

                card.getChildren().addAll(
                                iconCircle,
                                text);

                if ("Electronics".equals(name)) {

                        card.setStyle(
                                        "-fx-background-color: #FFFFFF;" +
                                                        "-fx-border-color: " + BLUE + ";" +
                                                        "-fx-border-width: 2px;" +
                                                        "-fx-border-radius: 13px;" +
                                                        "-fx-background-radius: 13px;" +
                                                        "-fx-effect: dropshadow(" +
                                                        "gaussian, rgba(49,88,212,0.16), 12, 0.10, 0, 3);" +
                                                        "-fx-cursor: hand;");

                        card.setOnMouseClicked(
                                        event -> ElectronicsRentalScreen.show(stage));
                }

                return card;
        }

        // =============================================================
        // UPCOMING BOOKING
        // =============================================================

        private VBox createUpcomingBooking() {

                VBox card = new VBox();

                card.setPrefWidth(330);
                card.setMinWidth(330);
                card.setPadding(new Insets(18, 18, 16, 18));
                card.setSpacing(11);

                card.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0E5EF;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 15px;" +
                                                "-fx-background-radius: 15px;" +
                                                "-fx-effect: dropshadow(" +
                                                "gaussian, rgba(40,60,100,0.10), 16, 0.12, 0, 4);");

                HBox titleRow = new HBox(9);
                titleRow.setAlignment(Pos.CENTER_LEFT);

                StackPane titleIconBox = new StackPane();
                titleIconBox.setPrefSize(30, 30);
                titleIconBox.setMinSize(30, 30);
                titleIconBox.setMaxSize(30, 30);
                titleIconBox.setStyle(
                                "-fx-background-color: #EAF0FF;" +
                                                "-fx-background-radius: 8px;");

                SVGPath calendarIcon = createIcon("calendar");
                calendarIcon.setScaleX(0.65);
                calendarIcon.setScaleY(0.65);
                titleIconBox.getChildren().add(calendarIcon);

                Label title = new Label("Upcoming Booking");
                title.setFont(
                                Font.font("Arial", FontWeight.BOLD, 18));
                title.setTextFill(Color.web(DARK_TEXT));

                titleRow.getChildren().addAll(
                                titleIconBox,
                                title);

                VBox booking = new VBox(10);
                booking.setPadding(new Insets(13));

                booking.setStyle(
                                "-fx-background-color: #F5F7FC;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9px;" +
                                                "-fx-background-radius: 9px;");

                HBox topRow = new HBox();

                Label confirmed = new Label("Confirmed");
                confirmed.setPadding(new Insets(4, 7, 4, 7));
                confirmed.setStyle(
                                "-fx-background-color: #E7F5EB;" +
                                                "-fx-text-fill: #27834C;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;");

                Label bookingNumber = new Label("Booking #8492");
                bookingNumber.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 10));
                bookingNumber.setTextFill(Color.web("#71809B"));

                Region topSpacer = new Region();
                HBox.setHgrow(topSpacer, Priority.ALWAYS);

                topRow.getChildren().addAll(
                                confirmed,
                                topSpacer,
                                bookingNumber);

                Label item = new Label("Yamaha Portable Generator");

                item.setFont(
                                Font.font("Arial", FontWeight.BOLD, 13));
                item.setTextFill(Color.web(DARK_TEXT));

                Line line = new Line(0, 0, 270, 0);
                line.setStroke(Color.web(BORDER));

                HBox dateRow = createInfoRow(
                                "calendar",
                                "Oct 24 - Oct 26, 2023",
                                true);

                HBox pickupRow = createInfoRow(
                                "clock",
                                "Pickup at 10:00 AM",
                                false);

                HBox locationRow = createInfoRow(
                                "location",
                                "123 Main St Garage\nBrooklyn, NY",
                                false);

                booking.getChildren().addAll(
                                topRow,
                                item,
                                line,
                                dateRow,
                                pickupRow,
                                locationRow);

                Button details = new Button("View Booking Details");

                details.setMaxWidth(Double.MAX_VALUE);
                details.setPrefHeight(36);

                details.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: " + BLUE + ";" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-cursor: hand;");

                card.getChildren().addAll(
                                titleRow,
                                booking,
                                details);

                return card;
        }

        // =============================================================
        // QUICK ACTIONS
        // =============================================================

        private VBox createQuickActions() {

                VBox card = new VBox();

                card.setPrefWidth(330);
                card.setMinWidth(330);
                card.setPadding(new Insets(15, 16, 12, 16));
                card.setSpacing(8);

                card.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0E5EF;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 15px;" +
                                                "-fx-background-radius: 15px;" +
                                                "-fx-effect: dropshadow(" +
                                                "gaussian, rgba(40,60,100,0.08), 14, 0.10, 0, 3);");

                HBox titleRow = new HBox(9);
                titleRow.setAlignment(Pos.CENTER_LEFT);

                StackPane lightning = new StackPane();
                lightning.setPrefSize(30, 30);
                lightning.setMinSize(30, 30);
                lightning.setMaxSize(30, 30);
                lightning.setStyle(
                                "-fx-background-color: #EAF0FF;" +
                                                "-fx-background-radius: 8px;");

                Label lightningText = new Label("ϟ");
                lightningText.setFont(
                                Font.font("Arial", FontWeight.BOLD, 18));
                lightningText.setTextFill(Color.web(BLUE));
                lightning.getChildren().add(lightningText);

                Label title = new Label("Quick Actions");
                title.setFont(
                                Font.font("Arial", FontWeight.BOLD, 15));
                title.setTextFill(Color.web(DARK_TEXT));

                titleRow.getChildren().addAll(lightning, title);

                Button history = createActionButton("clock", "Rental History");

                Button saved = createActionButton("heart", "Saved Items");

                card.getChildren().addAll(
                                titleRow,
                                history,
                                saved);

                return card;
        }

        // =============================================================
        // ACTION BUTTON
        // =============================================================

        private Button createActionButton(
                        String iconType,
                        String text) {

                Button button = new Button();
                button.setMaxWidth(Double.MAX_VALUE);
                button.setPrefHeight(40);

                HBox content = new HBox(11);
                content.setAlignment(Pos.CENTER_LEFT);

                SVGPath icon = createIcon(iconType);
                icon.setScaleX(0.68);
                icon.setScaleY(0.68);

                Label textLabel = new Label(text);
                textLabel.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 13));
                textLabel.setTextFill(Color.web("#526A94"));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                SVGPath arrow = createIcon("chevron");
                arrow.setScaleX(0.55);
                arrow.setScaleY(0.55);

                content.getChildren().addAll(
                                icon,
                                textLabel,
                                spacer,
                                arrow);

                button.setGraphic(content);
                button.setStyle(
                                "-fx-background-color: #FAFBFE;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 0 6px;");

                return button;
        }

        // =============================================================
        // RECOMMENDED SECTION
        // =============================================================

        private VBox createRecommendedSection() {

                VBox section = new VBox(13);

                HBox header = new HBox();

                Label title = new Label("Recommended for You");
                title.setFont(
                                Font.font("Arial", FontWeight.BOLD, 20));
                title.setTextFill(Color.web(DARK_TEXT));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label viewAll = new Label("View All  ›");
                viewAll.setFont(
                                Font.font("Arial", FontWeight.BOLD, 12));
                viewAll.setTextFill(Color.web(BLUE));

                header.setAlignment(Pos.CENTER_LEFT);
                header.getChildren().addAll(
                                title,
                                spacer,
                                viewAll);

                HBox cards = new HBox(12);

                VBox cameraCard = createRentalCard(

                                "Sony A7III Camera Kit",
                                "₹45",
                                "Downtown NY (2 mi)",
                                "4.8",
                                "/images/camera.jpg");

                VBox sofaCard = createRentalCard(
                                "Premium 3 Seater Sofa",
                                "$30",
                                "Brooklyn, NY (3 mi)",
                                "4.6",
                                "/images/sofa.jpg");

                VBox drillCard = createRentalCard(
                                "DeWalt Cordless Drill",
                                "₹15",
                                "Brooklyn, NY (5mi)",
                                "4.7",
                                "/images/drill.jpg");

                VBox bikeCard = createRentalCard(
                                "Mountain Bike",
                                "$20",
                                "Downtown NY (4 mi)",
                                "4.9",
                                "/images/bike.jpg");

                cards.getChildren().addAll(
                                cameraCard,
                                sofaCard,
                                drillCard,
                                bikeCard);

                section.getChildren().addAll(
                                header,
                                cards);

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
                        String imagePath) {

                VBox card = new VBox();

                card.setPrefWidth(166);
                card.setMinWidth(166);
                card.setMaxWidth(166);

                card.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0E5EF;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 12px;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-effect: dropshadow(" +
                                                "gaussian, rgba(40,60,100,0.09), 12, 0.10, 0, 3);");

                StackPane imageContainer = new StackPane();
                imageContainer.setPrefHeight(105);
                imageContainer.setMinHeight(105);

                try {

                        URL url = DashboardScreen.class.getResource(imagePath);

                        if (url != null) {

                                Image image = new Image(url.toExternalForm());

                                ImageView imageView = new ImageView(image);

                                imageView.setFitWidth(155);
                                imageView.setFitHeight(105);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);

                                imageContainer.getChildren().add(imageView);

                        } else {

                                createProductPlaceholder(
                                                imageContainer,
                                                name);
                        }

                } catch (Exception e) {

                        createProductPlaceholder(
                                        imageContainer,
                                        name);
                }

                Label available = new Label("Available");
                available.setPadding(new Insets(3, 6, 3, 6));
                available.setStyle(
                                "-fx-background-color: #E7F5EB;" +
                                                "-fx-text-fill: #27834C;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;");

                StackPane.setAlignment(
                                available,
                                Pos.TOP_RIGHT);

                StackPane.setMargin(
                                available,
                                new Insets(7, 6, 0, 0));

                imageContainer.getChildren().add(available);

                VBox information = new VBox(6);
                information.setPadding(
                                new Insets(9, 10, 10, 10));

                Label itemName = new Label(name);
                itemName.setFont(
                                Font.font("Arial", FontWeight.BOLD, 11));
                itemName.setTextFill(Color.web(DARK_TEXT));
                itemName.setWrapText(true);

                Label locationLabel = new Label(location);

                locationLabel.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 9));
                locationLabel.setTextFill(Color.web(SECONDARY_TEXT));

                HBox bottomRow = new HBox();
                bottomRow.setAlignment(Pos.CENTER_LEFT);

                Label priceLabel = new Label(price + " / day");

                priceLabel.setFont(
                                Font.font("Arial", FontWeight.BOLD, 12));
                priceLabel.setTextFill(Color.web(BLUE));

                Region priceSpacer = new Region();
                HBox.setHgrow(priceSpacer, Priority.ALWAYS);

                Label star = new Label("★ " + rating);

                star.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 9));
                star.setTextFill(Color.web("#C4931D"));

                bottomRow.getChildren().addAll(
                                priceLabel,
                                priceSpacer,
                                star);

                information.getChildren().addAll(
                                itemName,
                                locationLabel,
                                bottomRow);

                card.getChildren().addAll(
                                imageContainer,
                                information);

                return card;
        }

        private HBox createInfoRow(
                        String iconType,
                        String text,
                        boolean bold) {

                HBox row = new HBox(8);
                row.setAlignment(Pos.TOP_LEFT);

                SVGPath icon = createIcon(iconType);
                icon.setScaleX(0.58);
                icon.setScaleY(0.58);

                Label label = new Label(text);
                label.setFont(
                                Font.font(
                                                "Arial",
                                                bold ? FontWeight.BOLD : FontWeight.NORMAL,
                                                11));
                label.setTextFill(
                                Color.web(
                                                bold ? DARK_TEXT : SECONDARY_TEXT));
                label.setWrapText(true);

                row.getChildren().addAll(icon, label);

                return row;
        }

        private void createProductPlaceholder(
                        StackPane container,
                        String name) {

                container.setStyle(
                                "-fx-background-color: #F1F4FB;");

                Label placeholder = new Label(
                                name.substring(
                                                0,
                                                Math.min(2, name.length())).toUpperCase());

                placeholder.setFont(
                                Font.font("Arial", FontWeight.BOLD, 28));
                placeholder.setTextFill(
                                Color.web("#B9C6E8"));

                container.getChildren().add(
                                placeholder);
        }

        private SVGPath createIcon(String type) {

                SVGPath icon = new SVGPath();

                icon.setFill(Color.TRANSPARENT);
                icon.setStroke(Color.web("#526A94"));
                icon.setStrokeWidth(1.8);

                switch (type) {

                        case "home":
                                icon.setContent(
                                                "M3 11 L12 3 L21 11 " +
                                                                "V21 H15 V15 H9 V21 H3 Z");
                                icon.setFill(Color.web(BLUE));
                                icon.setStroke(Color.TRANSPARENT);
                                break;

                        case "owner":
                                icon.setContent(
                                                "M4 21 V8 H14 V21 " +
                                                                "M14 12 H20 V21 " +
                                                                "M7 11 H11 M7 15 H11 M7 18 H11 " +
                                                                "M17 15 H18 M17 18 H18");
                                break;

                        case "delivery":
                                icon.setContent(
                                                "M3 6 H14 V17 H3 Z " +
                                                                "M14 10 H18 L21 13 V17 H14 " +
                                                                "M6 17 A2 2 0 1 0 6 21 A2 2 0 1 0 6 17 " +
                                                                "M18 17 A2 2 0 1 0 18 21 A2 2 0 1 0 18 17");
                                break;

                        case "admin":
                                icon.setContent(
                                                "M12 3 L20 6 V11 " +
                                                                "C20 16 16.5 19.5 12 21 " +
                                                                "C7.5 19.5 4 16 4 11 V6 Z " +
                                                                "M9 12 L11 14 L15 9");
                                break;

                        case "help":
                                icon.setContent(
                                                "M12 21 A9 9 0 1 0 12 3 " +
                                                                "A9 9 0 1 0 12 21 " +
                                                                "M9.5 9 A2.5 2.5 0 1 1 14 11 " +
                                                                "C13 12 12 12 12 14 " +
                                                                "M12 17 V17.1");
                                break;

                        case "logout":
                                icon.setContent(
                                                "M10 5 H4 V19 H10 " +
                                                                "M13 8 L18 12 L13 16 " +
                                                                "M18 12 H8");
                                break;

                        case "search":
                                icon.setContent(
                                                "M10.5 3 A7.5 7.5 0 1 0 10.5 18 " +
                                                                "A7.5 7.5 0 1 0 10.5 3 " +
                                                                "M16 16 L21 21");
                                break;

                        case "bell":
                                icon.setContent(
                                                "M6 17 H18 L16.5 15 V10 " +
                                                                "A4.5 4.5 0 0 0 7.5 10 V15 Z " +
                                                                "M10 19 A2 2 0 0 0 14 19");
                                break;

                        case "location":
                                icon.setContent(
                                                "M12 21 C12 21 5 14.5 5 9 " +
                                                                "A7 7 0 1 1 19 9 C19 14.5 12 21 12 21 Z " +
                                                                "M12 11 A2 2 0 1 0 12 7 A2 2 0 1 0 12 11");
                                break;

                        case "calendar":
                                icon.setContent(
                                                "M4 5 H20 V20 H4 Z " +
                                                                "M7 3 V7 M17 3 V7 " +
                                                                "M4 9 H20");
                                break;

                        case "clock":
                                icon.setContent(
                                                "M12 3 A9 9 0 1 0 12 21 " +
                                                                "A9 9 0 1 0 12 3 " +
                                                                "M12 7 V12 L15 14");
                                break;

                        case "heart":
                                icon.setContent(
                                                "M12 20 C10 18 4 14 4 9 " +
                                                                "C4 5 9 4 12 8 " +
                                                                "C15 4 20 5 20 9 " +
                                                                "C20 14 14 18 12 20 Z");
                                break;

                        case "chevron":
                                icon.setContent("M9 5 L16 12 L9 19");
                                break;

                        case "electronics":
                                icon.setContent(
                                                "M3 4 H21 V17 H3 Z " +
                                                                "M8 21 H16 M12 17 V21");
                                icon.setStroke(Color.web(BLUE));
                                break;

                        case "furniture":
                                icon.setContent(
                                                "M4 13 V9 H7 V7 H17 V9 H20 V13 " +
                                                                "M4 13 H20 V18 H4 Z " +
                                                                "M6 18 V21 M18 18 V21");
                                icon.setStroke(Color.web(BLUE));
                                break;

                        case "vehicle":
                                icon.setContent(
                                                "M5 16 L7 9 H17 L19 16 " +
                                                                "H21 V20 H3 V16 Z " +
                                                                "M7 16 A2 2 0 1 0 7 20 " +
                                                                "M17 16 A2 2 0 1 0 17 20");
                                icon.setStroke(Color.web(BLUE));
                                break;

                        case "tools":
                                icon.setContent(
                                                "M14 5 A4 4 0 0 0 19 10 " +
                                                                "L13 16 L8 11 L14 5 Z " +
                                                                "M4 18 L10 12");
                                icon.setStroke(Color.web(BLUE));
                                break;

                        case "appliances":
                                icon.setContent(
                                                "M5 3 H19 V21 H5 Z " +
                                                                "M8 7 H16 " +
                                                                "M8 17 A4 4 0 1 0 16 17 " +
                                                                "A4 4 0 1 0 8 17");
                                icon.setStroke(Color.web(BLUE));
                                break;

                        case "events":
                                icon.setContent(
                                                "M12 3 C14 7 20 8 20 13 " +
                                                                "C20 17 17 20 12 21 " +
                                                                "C7 20 4 17 4 13 " +
                                                                "C4 8 10 7 12 3 Z");
                                icon.setFill(Color.web(BLUE));
                                icon.setStroke(Color.TRANSPARENT);
                                break;

                        case "sports":
                                icon.setContent(
                                                "M12 3 A9 9 0 1 0 12 21 " +
                                                                "A9 9 0 1 0 12 3 " +
                                                                "M5 8 C9 10 15 10 19 8 " +
                                                                "M5 16 C9 14 15 14 19 16 " +
                                                                "M12 3 V21");
                                icon.setStroke(Color.web(BLUE));
                                break;

                        case "other":
                                icon.setContent(
                                                "M5 12 A2 2 0 1 0 5 12.1 " +
                                                                "M12 12 A2 2 0 1 0 12 12.1 " +
                                                                "M19 12 A2 2 0 1 0 19 12.1");
                                icon.setFill(Color.web("#526A94"));
                                icon.setStroke(Color.TRANSPARENT);
                                break;

                        default:
                                icon.setContent(
                                                "M12 3 A9 9 0 1 0 12 21 " +
                                                                "A9 9 0 1 0 12 3");
                }

                return icon;
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
