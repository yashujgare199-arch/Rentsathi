package com.rentsathi.ui.screens.delivery;

import com.rentsathi.ui.screens.DeliveryPartnerLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DeliveryPartnerDashboardScreen {

    // ==============================
    // COLORS
    // ==============================

    private static final String BG = "#F8F9FF";
    private static final String WHITE = "#FFFFFF";
    private static final String BLUE = "#4058D4";
    private static final String LIGHT_BLUE = "#E3E9FF";
    private static final String TEXT = "#172033";
    private static final String SECONDARY = "#52617A";
    private static final String BORDER = "#CDD4E5";
    private static final String GREEN = "#2E8B57";
    private static final String ORANGE = "#D86B20";

    // ==============================
    // SHOW DASHBOARD
    // ==============================

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // ==============================
        // SIDEBAR
        // ==============================

        VBox sidebar = createSidebar(stage);

        root.setLeft(sidebar);

        // ==============================
        // MAIN CONTENT
        // ==============================

        VBox mainContent = new VBox();

        mainContent.setSpacing(20);
        mainContent.setPadding(new Insets(0, 25, 25, 25));

        // ==============================
        // TOP BAR
        // ==============================

        HBox topBar = new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(15, 0, 15, 0));

        Label pageTitle = new Label("Dashboard");

        pageTitle.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region topSpacer = new Region();

        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        Label notification = new Label("🔔");
        notification.setStyle("-fx-font-size: 20px;");

        Circle profileCircle = new Circle(18);

        profileCircle.setFill(Color.web(BLUE));

        Label profileText = new Label("A");

        profileText.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        StackPaneWrapper profile = new StackPaneWrapper(
                profileCircle,
                profileText
        );

        topBar.getChildren().addAll(
                pageTitle,
                topSpacer,
                notification,
                new Region(),
                profile
        );

        mainContent.getChildren().add(topBar);

        // ==============================
        // GREETING
        // ==============================

        VBox greeting = new VBox(5);

        Label greetingTitle = new Label(
                "Good morning, Alex"
        );

        greetingTitle.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label greetingSubtitle = new Label(
                "Manage your deliveries and earnings."
        );

        greetingSubtitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        greeting.getChildren().addAll(
                greetingTitle,
                greetingSubtitle
        );

        mainContent.getChildren().add(greeting);

        // ==============================
        // STAT CARDS
        // ==============================

        HBox stats = new HBox(15);

        stats.getChildren().addAll(
                statCard(
                        "AVAILABLE DELIVERIES",
                        "12",
                        "🚚",
                        BLUE
                ),

                statCard(
                        "TODAY'S DELIVERIES",
                        "8",
                        "🏃",
                        ORANGE
                ),

                statCard(
                        "COMPLETED DELIVERIES",
                        "142",
                        "✓",
                        BLUE
                ),

                statCard(
                        "TODAY'S EARNINGS",
                        "$124.50",
                        "▣",
                        GREEN
                )
        );

        mainContent.getChildren().add(stats);

        // ==============================
        // BODY
        // ==============================

        HBox body = new HBox(25);

        VBox availableJobs = new VBox(15);

        HBox availableTitle = new HBox();

        Label availableLabel = new Label(
                "Available Delivery Jobs"
        );

        availableLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region availableSpacer = new Region();

        HBox.setHgrow(
                availableSpacer,
                Priority.ALWAYS
        );

        Button viewAll = new Button("View All");

        viewAll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        availableTitle.getChildren().addAll(
                availableLabel,
                availableSpacer,
                viewAll
        );

        // ==============================
        // DELIVERY JOB 1
        // ==============================

        VBox job1 = deliveryJob(
                "DJI Mavic Air 2",
                "Downtown Hub, 124 Main St",
                "Westside, Apt 4B",
                "3.2 mi",
                "25 mins",
                "+$12.00",
                "5 mins ago",
                stage
        );

        // ==============================
        // DELIVERY JOB 2
        // ==============================

        VBox job2 = deliveryJob(
                "Bose Pro PA System",
                "AudioTech Rentals, North Park",
                "Convention Center, Hall B",
                "8.4 mi",
                "45 mins",
                "+$24.50",
                "12 mins ago",
                stage
        );

        availableJobs.getChildren().addAll(
                availableTitle,
                job1,
                job2
        );

        HBox.setHgrow(
                availableJobs,
                Priority.ALWAYS
        );

        // ==============================
        // ACTIVE DELIVERY
        // ==============================

        VBox activeDelivery = createActiveDelivery(stage);

        body.getChildren().addAll(
                availableJobs,
                activeDelivery
        );

        mainContent.getChildren().add(body);

        root.setCenter(mainContent);

        // ==============================
        // SCENE
        // ==============================

        Scene scene = new Scene(
                root,
                1500,
                830
        );

        stage.setTitle("RentSathi - Delivery Dashboard");

        stage.setScene(scene);

        stage.show();
    }

    // ==========================================================
    // SIDEBAR
    // ==========================================================

    private static VBox createSidebar(Stage stage) {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(220);

        sidebar.setPadding(
                new Insets(20, 15, 15, 15)
        );

        sidebar.setSpacing(15);

        sidebar.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        // ==============================
        // LOGO
        // ==============================

        HBox logoBox = new HBox(10);

        logoBox.setAlignment(Pos.CENTER_LEFT);

        Circle logoCircle = new Circle(18);

        logoCircle.setFill(
                Color.web(BLUE)
        );

        Label logoLetter = new Label("R");

        logoLetter.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        StackPaneWrapper logo = new StackPaneWrapper(
                logoCircle,
                logoLetter
        );

        VBox logoText = new VBox(0);

        Label rentSathi = new Label("RentSathi");

        rentSathi.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label tagline = new Label(
                "Rent Smart. Live Easy."
        );

        tagline.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        logoText.getChildren().addAll(
                rentSathi,
                tagline
        );

        logoBox.getChildren().addAll(
                logo,
                logoText
        );

        sidebar.getChildren().add(
                logoBox
        );

        // ==============================
        // MENU
        // ==============================

        VBox menu = new VBox(8);

        Button customer = sidebarButton(
                "♙",
                "Customer",
                false
        );

        Button owner = sidebarButton(
                "▣",
                "Owner",
                false
        );

        Button delivery = sidebarButton(
                "🚚",
                "Delivery Partner",
                true
        );

        Button admin = sidebarButton(
                "♙",
                "Admin",
                false
        );

        menu.getChildren().addAll(
                customer,
                owner,
                delivery,
                admin
        );

        sidebar.getChildren().add(menu);

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(spacer);

        // ==============================
        // BOTTOM MENU
        // ==============================

        Button help = sidebarButton(
                "?",
                "Help Center",
                false
        );

        Button logout = sidebarButton(
                "↪",
                "Logout",
                false
        );
        logout.setOnAction(e->{
            DeliveryPartnerLoginScreen.show(stage);
        });

        sidebar.getChildren().addAll(
                help,
                logout
        );

        return sidebar;
    }

    // ==========================================================
    // SIDEBAR BUTTON
    // ==========================================================

    private static Button sidebarButton(
            String icon,
            String text,
            boolean active
    ) {

        Button button = new Button(
                icon + "    " + text
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(12, 10, 12, 10)
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: " + LIGHT_BLUE + ";" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + SECONDARY + ";" +
                    "-fx-font-size: 13px;" +
                    "-fx-cursor: hand;"
            );
        }

        return button;
    }

    // ==========================================================
    // STAT CARD
    // ==========================================================

    private static VBox statCard(
            String title,
            String value,
            String icon,
            String iconColor
    ) {

        VBox card = new VBox();

        card.setPrefHeight(95);

        card.setSpacing(12);

        card.setPadding(
                new Insets(15)
        );

        card.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        HBox top = new HBox();

        Label titleLabel = new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label iconLabel = new Label(icon);

        iconLabel.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-text-fill: " + iconColor + ";"
        );

        top.getChildren().addAll(
                titleLabel,
                spacer,
                iconLabel
        );

        Label valueLabel = new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        card.getChildren().addAll(
                top,
                valueLabel
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

    // ==========================================================
    // DELIVERY JOB
    // ==========================================================

    private static VBox deliveryJob(
            String item,
            String pickup,
            String destination,
            String distance,
            String time,
            String earning,
            String posted,
            Stage stage
    ) {

        VBox card = new VBox();

        card.setSpacing(10);

        card.setPadding(
                new Insets(15)
        );

        card.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        // ==============================
        // HEADER
        // ==============================

        HBox header = new HBox();

        Label itemLabel = new Label(item);

        itemLabel.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label earningLabel = new Label(
                earning
        );

        earningLabel.setStyle(
                "-fx-background-color: #EEF1FF;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 10 6 10;" +
                "-fx-background-radius: 5;"
        );

        header.getChildren().addAll(
                itemLabel,
                spacer,
                earningLabel
        );

        // ==============================
        // LOCATIONS
        // ==============================

        HBox locations = new HBox(35);

        VBox pickupBox = new VBox(3);

        Label pickupTitle = new Label(
                "▣ Pickup"
        );

        pickupTitle.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label pickupLabel = new Label(
                pickup
        );

        pickupLabel.setWrapText(true);

        pickupLabel.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        pickupBox.getChildren().addAll(
                pickupTitle,
                pickupLabel
        );

        VBox destinationBox = new VBox(3);

        Label destinationTitle = new Label(
                "⌖ Delivery"
        );

        destinationTitle.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label destinationLabel = new Label(
                destination
        );

        destinationLabel.setWrapText(true);

        destinationLabel.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        destinationBox.getChildren().addAll(
                destinationTitle,
                destinationLabel
        );

        locations.getChildren().addAll(
                pickupBox,
                destinationBox
        );

        // ==============================
        // INFO
        // ==============================

        HBox info = new HBox(20);

        Label distanceLabel = new Label(
                "↗ " + distance
        );

        Label timeLabel = new Label(
                "◷ " + time
        );

        Label postedLabel = new Label(
                "◷ " + posted
        );

        distanceLabel.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        timeLabel.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        postedLabel.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Region infoSpacer = new Region();

        HBox.setHgrow(
                infoSpacer,
                Priority.ALWAYS
        );

        info.getChildren().addAll(
                distanceLabel,
                timeLabel,
                infoSpacer,
                postedLabel
        );

        // ==============================
        // BUTTONS
        // ==============================

        HBox buttons = new HBox(10);

        Region buttonSpacer = new Region();

        HBox.setHgrow(
                buttonSpacer,
                Priority.ALWAYS
        );

        Button accept = new Button(
                "Accept Job"
        );

        accept.setPrefWidth(110);

        accept.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        );

        // ==========================================
        // IMPORTANT FIX
        // stage is passed into deliveryJob()
        // ==========================================

        accept.setOnAction(event -> {

            ActiveDeliveryScreen.show(stage);

        });

        Button decline = new Button(
                "Decline"
        );

        decline.setPrefWidth(90);

        decline.setStyle(
                "-fx-background-color: #EEF0FA;" +
                "-fx-text-fill: " + SECONDARY + ";" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        );

        buttons.getChildren().addAll(
                buttonSpacer,
                accept,
                decline
        );

        card.getChildren().addAll(
                header,
                locations,
                info,
                buttons
        );

        return card;
    }

    // ==========================================================
    // ACTIVE DELIVERY
    // ==========================================================

    private static VBox createActiveDelivery(
            Stage stage
    ) {

        VBox card = new VBox();

        card.setPrefWidth(300);

        card.setSpacing(12);

        card.setPadding(
                new Insets(15)
        );

        card.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        Label title = new Label(
                "Active Delivery"
        );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        // ==============================
        // MAP PLACEHOLDER
        // ==============================

        VBox map = new VBox();

        map.setPrefHeight(180);

        map.setAlignment(
                Pos.CENTER
        );

        map.setStyle(
                "-fx-background-color: #E8EEF7;" +
                "-fx-background-radius: 8;"
        );

        Label mapText = new Label(
                "📍\nLive Route Map"
        );

        mapText.setAlignment(
                Pos.CENTER
        );

        mapText.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        map.getChildren().add(
                mapText
        );

        // ==============================
        // DELIVERY ITEM
        // ==============================

        Label item = new Label(
                "Sony A7 III Camera Kit"
        );

        item.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label order = new Label(
                "Order #RD-8942"
        );

        order.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label customer = new Label(
                "Customer: Sarah Jenkins"
        );

        customer.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label destination = new Label(
                "⌖ Destination\n894 Westside Ave, Apt 4B"
        );

        destination.setWrapText(true);

        destination.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Button track = new Button(
                "➤ Track & Update"
        );

        track.setMaxWidth(
                Double.MAX_VALUE
        );

        track.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        );

        track.setOnAction(event -> {

            ActiveDeliveryScreen.show(stage);

        });

        card.getChildren().addAll(
                title,
                map,
                item,
                order,
                customer,
                destination,
                track
        );

        return card;
    }

    // ==========================================================
    // SIMPLE STACKPANE WRAPPER
    // ==========================================================

    private static class StackPaneWrapper
            extends javafx.scene.layout.StackPane {

        StackPaneWrapper(
                javafx.scene.Node... nodes
        ) {

            getChildren().addAll(nodes);

            setAlignment(
                    Pos.CENTER
            );
        }
    }
}