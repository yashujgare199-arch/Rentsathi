package com.rentsathi.ui.screens.owner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RentalRequestsScreen {

    // =========================
    // COLORS
    // =========================

    private static final String BLUE = "#3155C9";
    private static final String LIGHT_BLUE = "#DCE6FF";
    private static final String BG = "#F7F8FC";
    private static final String BORDER = "#D1D6E3";
    private static final String TEXT = "#182238";
    private static final String MUTED = "#60708F";
    private static final String WHITE = "#FFFFFF";
    private static final String RED = "#D93030";
    private static final String GREEN = "#2E7D32";

    // =========================
    // SHOW
    // =========================

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // SIDEBAR
        VBox sidebar = createSidebar(stage);

        // MAIN AREA
        VBox main = new VBox();

        HBox topBar = createTopBar();

        VBox content = createContent(stage);

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        main.getChildren().addAll(
                topBar,
                content
        );

        root.setLeft(sidebar);
        root.setCenter(main);

        Scene scene = new Scene(
                root,
                1500,
                830
        );

        stage.setTitle(
                "RentSathi - Rental Requests"
        );

        stage.setScene(scene);
        stage.show();
    }

    // =========================
    // SIDEBAR
    // =========================

    private static VBox createSidebar(Stage stage) {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(255);
        sidebar.setMinWidth(255);
        sidebar.setMaxWidth(255);

        sidebar.setPadding(
                new Insets(18, 14, 15, 14)
        );

        sidebar.setSpacing(6);

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1px 0 0;"
        );

        // -------------------------
        // LOGO
        // -------------------------

        HBox logoBox = new HBox(12);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.setPadding(
                new Insets(5, 8, 20, 8)
        );

        StackPane logo = new StackPane();

        Circle circle = new Circle(
                25,
                Color.web(BLUE)
        );

        Label rs = new Label("RS");

        rs.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        logo.getChildren().addAll(
                circle,
                rs
        );

        VBox brand = new VBox(2);

        Label rentSathi =
                new Label("RentSathi");

        rentSathi.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label owner =
                new Label("Owner Portal");

        owner.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        brand.getChildren().addAll(
                rentSathi,
                owner
        );

        logoBox.getChildren().addAll(
                logo,
                brand
        );

        // -------------------------
        // POST NEW LISTING
        // -------------------------

        Button postListing =
                new Button(
                        "+  Post New Listing"
                );

        stylePrimaryButton(
                postListing,
                225,
                42
        );

        postListing.setOnAction(
                event ->
                        AddNewRentalScreen.show(stage)
        );

        // -------------------------
        // DASHBOARD
        // -------------------------

        Button dashboard =
                navigationButton(
                        "▦",
                        "Dashboard",
                        false
                );

        dashboard.setOnAction(
                event ->
                        OwnerDashboardScreen.show(stage)
        );

        // -------------------------
        // MANAGE LISTINGS
        // -------------------------

        Button listings =
                navigationButton(
                        "▣",
                        "Manage Listings",
                        false
                );

        listings.setOnAction(
                event ->
                        MyListingsScreen.show(stage)
        );

        // -------------------------
        // BOOKINGS
        // -------------------------

        Button bookings =
                navigationButton(
                        "▣",
                        "Bookings",
                        true
                );

        bookings.setOnAction(
                event ->
                        RentalRequestsScreen.show(stage)
        );

        // -------------------------
        // DELIVERIES
        // -------------------------

        Button deliveries =
                navigationButton(
                        "▱",
                        "Deliveries",
                        false
                );

        // -------------------------
        // ANALYTICS
        // -------------------------

        Button analytics =
                navigationButton(
                        "▥",
                        "Analytics",
                        false
                );

        VBox navigation =
                new VBox(5);

        navigation.getChildren().addAll(
                postListing,
                dashboard,
                listings,
                bookings,
                deliveries,
                analytics
        );

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Separator separator =
                new Separator();

        // -------------------------
        // SETTINGS
        // -------------------------

        Button settings =
                navigationButton(
                        "⚙",
                        "Settings",
                        false
                );

        // -------------------------
        // LOGOUT
        // -------------------------

        Button logout =
                navigationButton(
                        "↪",
                        "Logout",
                        false
                );

        logout.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #D93030;" +
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
                navigation,
                spacer,
                separator,
                settings,
                logout
        );

        return sidebar;
    }

    // =========================
    // TOP BAR
    // =========================

    private static HBox createTopBar() {

        HBox top =
                new HBox();

        top.setPrefHeight(70);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        top.setPadding(
                new Insets(
                        0,
                        25,
                        0,
                        25
                )
        );

        top.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1px 0;"
        );

        TextField search =
                new TextField();

        search.setPromptText(
                "Search requests..."
        );

        search.setPrefWidth(330);
        search.setPrefHeight(40);

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #C9CFDE;" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-padding: 0 15px;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label notification =
                new Label("♧");

        notification.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label help =
                new Label("?");

        help.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-border-color: " + TEXT + ";" +
                "-fx-border-radius: 50%;" +
                "-fx-padding: 1px 6px;"
        );

        top.getChildren().addAll(
                search,
                spacer,
                notification,
                new Region(),
                help
        );

        return top;
    }

    // =========================
    // CONTENT
    // =========================

    private static VBox createContent(
            Stage stage
    ) {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        28,
                        30,
                        30,
                        30
                )
        );

        // -------------------------
        // TITLE
        // -------------------------

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Rental Requests"
                );

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Manage incoming booking requests for your listed items."
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        // -------------------------
        // TABS
        // -------------------------

        HBox tabs =
                createTabs();

        // -------------------------
        // REQUEST CARDS
        // -------------------------

        HBox cards =
                new HBox(20);

        VBox request1 =
                createRequestCard(
                        "Sony Alpha A7 IV Camera",
                        "Requested by Alex Carter",
                        "Oct 15 - Oct 18, 2023",
                        "$145.00",
                        "Delivery to Downtown",
                        "Today, 10:42 AM",
                        true
                );

        VBox request2 =
                createRequestCard(
                        "Trek Mountain Bike",
                        "Requested by Maria G.",
                        "Oct 18 - Oct 21, 2023",
                        "$45.00",
                        "Pickup at Location",
                        "Yesterday, 4:15 PM",
                        false
                );

        HBox.setHgrow(
                request1,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                request2,
                Priority.ALWAYS
        );

        cards.getChildren().addAll(
                request1,
                request2
        );

        VBox.setVgrow(
                cards,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                titleBox,
                tabs,
                cards
        );

        return content;
    }

    // =========================
    // TABS
    // =========================

    private static HBox createTabs() {

        HBox tabs =
                new HBox(32);

        tabs.setAlignment(
                Pos.CENTER_LEFT
        );

        Button pending =
                tabButton(
                        "Pending   3",
                        true
                );

        Button accepted =
                tabButton(
                        "Accepted",
                        false
                );

        Button completed =
                tabButton(
                        "Completed",
                        false
                );

        Button rejected =
                tabButton(
                        "Rejected",
                        false
                );

        pending.setOnAction(
                event ->
                        selectTab(
                                pending,
                                accepted,
                                completed,
                                rejected
                        )
        );

        accepted.setOnAction(
                event ->
                        selectTab(
                                accepted,
                                pending,
                                completed,
                                rejected
                        )
        );

        completed.setOnAction(
                event ->
                        selectTab(
                                completed,
                                pending,
                                accepted,
                                rejected
                        )
        );

        rejected.setOnAction(
                event ->
                        selectTab(
                                rejected,
                                pending,
                                accepted,
                                completed
                        )
        );

        tabs.getChildren().addAll(
                pending,
                accepted,
                completed,
                rejected
        );

        return tabs;
    }

    private static Button tabButton(
            String text,
            boolean selected
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(38);

        button.setPadding(
                new Insets(
                        0,
                        7,
                        0,
                        7
                )
        );

        button.setStyle(
                tabStyle(selected)
        );

        return button;
    }

    private static void selectTab(
            Button selected,
            Button a,
            Button b,
            Button c
    ) {

        selected.setStyle(
                tabStyle(true)
        );

        a.setStyle(
                tabStyle(false)
        );

        b.setStyle(
                tabStyle(false)
        );

        c.setStyle(
                tabStyle(false)
        );
    }

    private static String tabStyle(
            boolean selected
    ) {

        if (selected) {

            return
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: transparent transparent " +
                    BLUE + " transparent;" +
                    "-fx-border-width: 0 0 2px 0;" +
                    "-fx-cursor: hand;";
        }

        return
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #394B6A;" +
                "-fx-cursor: hand;";
    }

    // =========================
    // REQUEST CARD
    // =========================

    private static VBox createRequestCard(
            String item,
            String requestedBy,
            String period,
            String amount,
            String logistics,
            String requestDate,
            boolean camera
    ) {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefHeight(270);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        // -------------------------
        // TOP
        // -------------------------

        HBox top =
                new HBox(12);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane image =
                createProductImage(camera);

        VBox itemInfo =
                new VBox(4);

        HBox.setHgrow(
                itemInfo,
                Priority.ALWAYS
        );

        Label itemName =
                new Label(item);

        itemName.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label requester =
                new Label(
                        "♙  " + requestedBy
                );

        requester.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #405171;"
        );

        itemInfo.getChildren().addAll(
                itemName,
                requester
        );

        Label pending =
                new Label(
                        "◷ Pending"
                );

        pending.setStyle(
                "-fx-background-color: #FFF3D6;" +
                "-fx-text-fill: #D48A00;" +
                "-fx-font-size: 11px;" +
                "-fx-padding: 6px 10px;" +
                "-fx-background-radius: 12px;"
        );

        top.getChildren().addAll(
                image,
                itemInfo,
                pending
        );

        Separator separator1 =
                new Separator();

        // -------------------------
        // DETAILS
        // -------------------------

        GridPane details =
                new GridPane();

        details.setHgap(35);
        details.setVgap(14);

        VBox periodBox =
                detail(
                        "RENTAL PERIOD",
                        "▣  " + period
                );

        VBox amountBox =
                detail(
                        "TOTAL AMOUNT",
                        "▣  " + amount
                );

        VBox logisticsBox =
                detail(
                        "LOGISTICS",
                        "▱  " + logistics
                );

        VBox dateBox =
                detail(
                        "REQUEST DATE",
                        "◷  " + requestDate
                );

        ColumnConstraints c1 =
                new ColumnConstraints();

        c1.setPercentWidth(50);

        ColumnConstraints c2 =
                new ColumnConstraints();

        c2.setPercentWidth(50);

        details.getColumnConstraints()
                .addAll(c1, c2);

        details.add(
                periodBox,
                0,
                0
        );

        details.add(
                amountBox,
                1,
                0
        );

        details.add(
                logisticsBox,
                0,
                1
        );

        details.add(
                dateBox,
                1,
                1
        );

        Separator separator2 =
                new Separator();

        // -------------------------
        // ACTIONS
        // -------------------------

        HBox actions =
                new HBox(8);

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        Region actionSpacer =
                new Region();

        HBox.setHgrow(
                actionSpacer,
                Priority.ALWAYS
        );

        Button detailsButton =
                new Button(
                        "◉ Details"
                );

        styleDetailsButton(
                detailsButton
        );

        Button reject =
                new Button(
                        "× Reject"
                );

        styleRejectButton(
                reject
        );

        Button accept =
                new Button(
                        camera
                                ? "✓ Accept Request"
                                : "✓ Accept"
                );

        styleAcceptButton(
                accept
        );

        reject.setOnAction(
                event ->
                        showMessage(
                                "Request Rejected",
                                "The rental request has been rejected."
                        )
        );

        accept.setOnAction(
                event ->
                        showMessage(
                                "Request Accepted",
                                "The rental request has been accepted."
                        )
        );

        detailsButton.setOnAction(
                event ->
                        showMessage(
                                "Rental Details",
                                item +
                                "\n\n" +
                                requestedBy +
                                "\n" +
                                period +
                                "\n" +
                                amount
                        )
        );

        actions.getChildren().addAll(
                detailsButton,
                reject,
                accept
        );

        card.getChildren().addAll(
                top,
                separator1,
                details,
                separator2,
                actions
        );

        return card;
    }

    // =========================
    // PRODUCT IMAGE
    // =========================

    private static StackPane createProductImage(
            boolean camera
    ) {

        StackPane box =
                new StackPane();

        box.setPrefSize(
                62,
                62
        );

        box.setMinSize(
                62,
                62
        );

        box.setMaxSize(
                62,
                62
        );

        box.setStyle(
                "-fx-background-color: #EEF0F7;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: #D0D5E2;" +
                "-fx-border-radius: 7px;"
        );

        Label icon =
                new Label(
                        camera
                                ? "▣"
                                : "♧"
                );

        icon.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-text-fill: #53627F;"
        );

        box.getChildren().add(
                icon
        );

        return box;
    }

    // =========================
    // DETAIL
    // =========================

    private static VBox detail(
            String title,
            String value
    ) {

        VBox box =
                new VBox(5);

        Label heading =
                new Label(title);

        heading.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #34496B;"
        );

        box.getChildren().addAll(
                heading,
                valueLabel
        );

        return box;
    }

    // =========================
    // BUTTON STYLES
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
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleDetailsButton(
            Button button
    ) {

        button.setPrefHeight(34);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #304B79;" +
                "-fx-border-color: transparent;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleRejectButton(
            Button button
    ) {

        button.setPrefHeight(34);

        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: " + RED + ";" +
                "-fx-border-color: " + RED + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleAcceptButton(
            Button button
    ) {

        button.setPrefHeight(34);

        button.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================
    // NAVIGATION BUTTON
    // =========================

    private static Button navigationButton(
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
                new Insets(
                        0,
                        12,
                        0,
                        12
                )
        );

        if (selected) {

            button.setStyle(
                    "-fx-background-color: " +
                    LIGHT_BLUE + ";" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 7px;" +
                    "-fx-cursor: hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #34496B;" +
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 7px;" +
                    "-fx-cursor: hand;"
            );
        }

        return button;
    }

    // =========================
    // MESSAGE
    // =========================

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("RentSathi");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
