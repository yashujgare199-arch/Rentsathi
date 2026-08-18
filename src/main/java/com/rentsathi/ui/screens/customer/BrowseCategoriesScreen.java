package com.rentsathi.ui.screens.customer;

import com.rentsathi.ui.screens.CustomerLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BrowseCategoriesScreen {

    private static final String BLUE = "#3657C8";
    private static final String DARK_TEXT = "#111827";
    private static final String SECONDARY_TEXT = "#52688C";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String BORDER = "#C8CBD9";

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        VBox sidebar = createSidebar(stage);

        BorderPane mainArea = new BorderPane();

        HBox topBar = createTopBar();

        mainArea.setTop(topBar);

        VBox content = createContent(stage);

        ScrollPane scrollPane = new ScrollPane(content);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        mainArea.setCenter(scrollPane);

        root.setLeft(sidebar);
        root.setCenter(mainArea);

        Scene scene = new Scene(
                root,
                1500,
                830
        );

        scene.setFill(
                Color.web(BACKGROUND)
        );

        stage.setTitle(
                "RentSathi - Browse Categories"
        );

        stage.setScene(scene);

        stage.setWidth(1500);
        stage.setHeight(830);

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.show();
    }

    private static VBox createSidebar(Stage stage) {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(205);

        sidebar.setPadding(
                new Insets(18, 10, 15, 10)
        );

        sidebar.setStyle(
                "-fx-background-color: #FAFAFF;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        HBox logoBox = new HBox(8);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane logo = new StackPane();

        logo.setPrefSize(35, 35);
        logo.setMaxSize(35, 35);

        logo.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 6px;"
        );

        Label logoText = new Label("RS");

        logoText.setTextFill(Color.WHITE);

        logoText.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-font-size: 13px;"
        );

        logo.getChildren().add(logoText);

        VBox brand = new VBox(0);

        Label brandName = new Label("RentSathi");

        brandName.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label tagline = new Label(
                "Rental Marketplace"
        );

        tagline.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " + SECONDARY_TEXT + ";"
        );

        brand.getChildren().addAll(
                brandName,
                tagline
        );

        logoBox.getChildren().addAll(
                logo,
                brand
        );

        VBox navigation = new VBox(3);

        navigation.setPadding(
                new Insets(20, 0, 0, 0)
        );

        Button explore = createNavButton(
                "◉",
                "Explore",
                true
        );

        Button dashboard = createNavButton(
                "▦",
                "Dashboard",
                false
        );

        Button listings = createNavButton(
                "▣",
                "Rental Listings",
                false
        );

        Button history = createNavButton(
                "▤",
                "Order History",
                false
        );

        Button earnings = createNavButton(
                "▱",
                "Earnings",
                false
        );

        Button support = createNavButton(
                "♧",
                "Support",
                false
        );

        navigation.getChildren().addAll(
                explore,
                dashboard,
                listings,
                history,
                earnings,
                support
        );

        explore.setOnAction(
                event ->
                        BrowseCategoriesScreen.show(stage)
        );

        dashboard.setOnAction(
                event ->
                        new DashboardScreen(stage).show()
        );

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Button newListing = new Button(
                "New Listing"
        );

        newListing.setMaxWidth(
                Double.MAX_VALUE
        );

        newListing.setPrefHeight(30);

        newListing.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );

        Region line = new Region();

        line.setPrefHeight(1);

        line.setStyle(
                "-fx-background-color: #D9DCE7;"
        );

        Button settings = createBottomButton(
                "⚙",
                "Settings"
        );

        Button logout = createBottomButton(
                "↪",
                "Logout"
        );

        logout.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #B3261E;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 7px;"
        );

        logout.setOnAction(
                event ->
                        CustomerLoginScreen.show(stage)
        );

        sidebar.getChildren().addAll(
                logoBox,
                navigation,
                spacer,
                newListing,
                line,
                settings,
                logout
        );

        return sidebar;
    }

    private static Button createNavButton(
            String icon,
            String text,
            boolean selected
    ) {

        Button button = new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(35);

        HBox box = new HBox(12);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel = new Label(icon);

        iconLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;"
        );

        Label textLabel = new Label(text);

        textLabel.setStyle(
                "-fx-font-size: 12px;" +
                (selected
                        ? "-fx-font-weight: bold;"
                        : "")
        );

        box.getChildren().addAll(
                iconLabel,
                textLabel
        );

        button.setGraphic(box);

        if (selected) {

            button.setStyle(
                    "-fx-background-color: #E0E8FF;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-background-radius: 7px;" +
                    "-fx-cursor: hand;" +
                    "-fx-padding: 0 10px;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + SECONDARY_TEXT + ";" +
                    "-fx-cursor: hand;" +
                    "-fx-padding: 0 10px;"
            );
        }

        return button;
    }

    private static Button createBottomButton(
            String icon,
            String text
    ) {

        Button button = new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(34);

        HBox box = new HBox(12);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel = new Label(icon);

        iconLabel.setStyle(
                "-fx-font-size: 16px;"
        );

        Label textLabel = new Label(text);

        textLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + SECONDARY_TEXT + ";"
        );

        box.getChildren().addAll(
                iconLabel,
                textLabel
        );

        button.setGraphic(box);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5px;"
        );

        return button;
    }

    private static HBox createTopBar() {

        HBox topBar = new HBox();

        topBar.setPrefHeight(45);

        topBar.setPadding(
                new Insets(0, 18, 0, 20)
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        HBox searchBox = new HBox(8);

        searchBox.setPrefWidth(210);
        searchBox.setPrefHeight(32);

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPadding(
                new Insets(0, 10, 0, 10)
        );

        searchBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        Label searchIcon = new Label("⌕");

        searchIcon.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-text-fill: " + SECONDARY_TEXT + ";"
        );

        Label searchText = new Label(
                "Search..."
        );

        searchText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #7B8498;"
        );

        searchBox.getChildren().addAll(
                searchIcon,
                searchText
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label notification = new Label("♧");

        notification.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label help = new Label("?");

        help.setMinSize(
                18,
                18
        );

        help.setAlignment(
                Pos.CENTER
        );

        help.setStyle(
                "-fx-border-color: " + SECONDARY_TEXT + ";" +
                "-fx-border-radius: 50%;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + SECONDARY_TEXT + ";"
        );

        Circle profileCircle = new Circle(
                14,
                Color.web("#D8DDE9")
        );

        StackPane profile =
                new StackPane(profileCircle);

        topBar.getChildren().addAll(
                searchBox,
                spacer,
                notification,
                help,
                profile
        );

        HBox.setMargin(
                notification,
                new Insets(0, 18, 0, 0)
        );

        HBox.setMargin(
                help,
                new Insets(0, 18, 0, 0)
        );

        return topBar;
    }

    private static VBox createContent(Stage stage) {

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(
                        50,
                        25,
                        50,
                        25
                )
        );

        content.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        Label title = new Label(
                "Browse Categories"
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK_TEXT + ";"
        );

        Label subtitle = new Label(
                "Find rentals by category."
        );

        subtitle.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + SECONDARY_TEXT + ";"
        );

        VBox heading = new VBox(5);

        heading.getChildren().addAll(
                title,
                subtitle
        );

        GridPane grid = new GridPane();

        grid.setHgap(12);
        grid.setVgap(12);

        grid.setMaxWidth(
                Double.MAX_VALUE
        );

        String[][] categories = {

                {
                        "▣",
                        "Electronics",
                        "450+ items",
                        "View Category"
                },

                {
                        "▤",
                        "Furniture",
                        "820+ items",
                        "View Category"
                },

                {
                        "▱",
                        "Vehicles",
                        "120+ items",
                        "View Category"
                },

                {
                        "⚒",
                        "Tools",
                        "340+ items",
                        "View Category"
                },

                {
                        "▥",
                        "Appliances",
                        "290+ items",
                        "View Category"
                },

                {
                        "◎",
                        "Sports Equipment",
                        "510+ items",
                        "View Category"
                },

                {
                        "▣",
                        "Event Equipment",
                        "180+ items",
                        "View Category"
                },

                {
                        "▣",
                        "Photography",
                        "220+ items",
                        "View Category"
                },

                {
                        "♢",
                        "Party Supplies",
                        "310+ items",
                        "View Category"
                },

                {
                        "♜",
                        "Outdoor Equipment",
                        "400+ items",
                        "View Category"
                },

                {
                        "•••",
                        "Other",
                        "150+ items",
                        "View All"
                }
        };

        for (int i = 0; i < categories.length; i++) {

            VBox card = createCategoryCard(
                    stage,
                    categories[i][0],
                    categories[i][1],
                    categories[i][2],
                    categories[i][3]
            );

            grid.add(
                    card,
                    i % 4,
                    i / 4
            );

            GridPane.setHgrow(
                    card,
                    Priority.ALWAYS
            );
        }

        content.getChildren().addAll(
                heading,
                grid
        );

        return content;
    }

    private static VBox createCategoryCard(
            Stage stage,
            String icon,
            String category,
            String itemCount,
            String buttonText
    ) {

        VBox card = new VBox(8);

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefHeight(165);

        card.setMinHeight(165);

        card.setMaxHeight(165);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );

        StackPane iconCircle = new StackPane();

        iconCircle.setPrefSize(
                52,
                52
        );

        iconCircle.setMinSize(
                52,
                52
        );

        iconCircle.setMaxSize(
                52,
                52
        );

        iconCircle.setStyle(
                "-fx-background-color: #DCE7FF;" +
                "-fx-background-radius: 50%;"
        );

        Label iconLabel = new Label(icon);

        iconLabel.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        iconCircle.getChildren().add(
                iconLabel
        );

        Label name = new Label(
                category
        );

        name.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK_TEXT + ";"
        );

        Label count = new Label(
                itemCount
        );

        count.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Button view = new Button(
                buttonText
        );

        view.setPrefHeight(30);

        view.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 12px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 2px;"
        );

        view.setOnMouseEntered(
                event ->
                        view.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-text-fill: #203B9C;" +
                                "-fx-font-size: 12px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-cursor: hand;" +
                                "-fx-padding: 2px;"
                        )
        );

        view.setOnMouseExited(
                event ->
                        view.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-text-fill: " + BLUE + ";" +
                                "-fx-font-size: 12px;" +
                                "-fx-cursor: hand;" +
                                "-fx-padding: 2px;"
                        )
        );

        card.getChildren().addAll(
                iconCircle,
                name,
                count,
                view
        );

        /*
         * ONLY the Electronics "View Category"
         * button is clickable.
         */
        if ("Electronics".equals(category)) {

            view.setOnAction(
                    event -> {
                        System.out.println(
                                ">>> ELECTRONICS BUTTON CLICKED"
                        );

                        ElectronicsRentalScreen.show(stage);
                    }
            );
        }

        return card;
    }
}