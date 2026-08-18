package com.rentsathi.ui.screens.delivery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ActiveDeliveryScreen {

    private static final String BG = "#F7F8FD";
    private static final String WHITE = "#FFFFFF";
    private static final String BLUE = "#4058D4";
    private static final String LIGHT_BLUE = "#E5EBFF";
    private static final String TEXT = "#172033";
    private static final String SECONDARY = "#52617A";
    private static final String BORDER = "#CDD4E5";
    private static final String GREEN = "#3AA76D";
    private static final String RED = "#E14B4B";

    // =========================================================
    // SHOW
    // =========================================================

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // -----------------------------------------------------
        // TOP BAR
        // -----------------------------------------------------

        root.setTop(createTopBar());

        // -----------------------------------------------------
        // LEFT SIDEBAR
        // -----------------------------------------------------

        root.setLeft(createSidebar());

        // -----------------------------------------------------
        // MAIN AREA
        // -----------------------------------------------------

        BorderPane mainArea = new BorderPane();

        mainArea.setPadding(
                new Insets(18)
        );

        mainArea.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // -----------------------------------------------------
        // CENTER = MAP
        // -----------------------------------------------------

        VBox mapSection = createMapSection();

        BorderPane.setMargin(
                mapSection,
                new Insets(0, 18, 0, 0)
        );

        mainArea.setCenter(mapSection);

        // -----------------------------------------------------
        // RIGHT = DELIVERY INFORMATION
        // -----------------------------------------------------

        VBox rightPanel = createRightPanel();

        mainArea.setRight(rightPanel);

        // -----------------------------------------------------
        // CENTER INTO ROOT
        // -----------------------------------------------------

        root.setCenter(mainArea);

        // -----------------------------------------------------
        // SCENE
        // -----------------------------------------------------

        Scene scene = new Scene(
                root,
                1500,
                850
        );

        stage.setTitle(
                "RentSathi - Active Delivery"
        );

        stage.setScene(scene);

        stage.setMinWidth(1200);
        stage.setMinHeight(700);

        stage.show();
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    private static HBox createTopBar() {

        HBox top = new HBox();

        top.setPrefHeight(70);
        top.setMinHeight(70);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        top.setPadding(
                new Insets(0, 22, 0, 24)
        );

        top.setSpacing(28);

        top.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        Label logo = new Label(
                "RentSathi"
        );

        logo.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Button dashboard = topButton(
                "Dashboard",
                false
        );

        Button active = topButton(
                "Active Delivery",
                true
        );

        Button history = topButton(
                "History",
                false
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label notification = new Label(
                "♧"
        );

        notification.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label settings = new Label(
                "⚙"
        );

        settings.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Circle circle = new Circle(
                24,
                Color.web(BLUE)
        );

        Label letter = new Label(
                "A"
        );

        letter.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        StackPane profile = new StackPane();

        profile.getChildren().addAll(
                circle,
                letter
        );

        top.getChildren().addAll(
                logo,
                dashboard,
                active,
                history,
                spacer,
                notification,
                settings,
                profile
        );

        return top;
    }

    // =========================================================
    // TOP BUTTON
    // =========================================================

    private static Button topButton(
            String text,
            boolean selected
    ) {

        Button button = new Button(
                text
        );

        button.setPadding(
                new Insets(10, 5, 10, 5)
        );

        if (selected) {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: transparent transparent " +
                    BLUE + " transparent;" +
                    "-fx-border-width: 0 0 2 0;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + TEXT + ";"
            );
        }

        return button;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private static VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(210);
        sidebar.setMinWidth(210);
        sidebar.setMaxWidth(210);

        sidebar.setPadding(
                new Insets(22, 14, 15, 14)
        );

        sidebar.setSpacing(10);

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        HBox logoBox = new HBox(10);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle logoCircle = new Circle(
                30,
                Color.web(BLUE)
        );

        Label r = new Label("R");

        r.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        StackPane logo = new StackPane();

        logo.getChildren().addAll(
                logoCircle,
                r
        );

        VBox logoText = new VBox(2);

        Label name = new Label(
                "RentSathi"
        );

        name.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label tagline = new Label(
                "Rent Smart. Live Easy."
        );

        tagline.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        logoText.getChildren().addAll(
                name,
                tagline
        );

        logoBox.getChildren().addAll(
                logo,
                logoText
        );

        sidebar.getChildren().add(
                logoBox
        );

        // -----------------------------------------------------
        // MENU
        // -----------------------------------------------------

        Button customer = sideButton(
                "♙",
                "Customer",
                false
        );

        Button owner = sideButton(
                "▣",
                "Owner",
                false
        );

        Button delivery = sideButton(
                "▰",
                "Delivery Partner",
                true
        );

        Button admin = sideButton(
                "♙",
                "Admin",
                false
        );

        sidebar.getChildren().addAll(
                customer,
                owner,
                delivery,
                admin
        );

        // -----------------------------------------------------
        // SPACER
        // -----------------------------------------------------

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(
                spacer
        );

        // -----------------------------------------------------
        // BOTTOM
        // -----------------------------------------------------

        sidebar.getChildren().add(
                sideButton(
                        "?",
                        "Help Center",
                        false
                )
        );

        sidebar.getChildren().add(
                sideButton(
                        "↪",
                        "Logout",
                        false
                )
        );

        return sidebar;
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private static Button sideButton(
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
                new Insets(11, 10, 11, 10)
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    LIGHT_BLUE + ";" +
                    "-fx-text-fill: " +
                    BLUE + ";" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " +
                    SECONDARY + ";"
            );
        }

        return button;
    }

    // =========================================================
    // MAP SECTION
    // =========================================================

    private static VBox createMapSection() {

        VBox box = new VBox();

        box.setMinWidth(0);
        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setMaxHeight(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        // -----------------------------------------------------
        // MAP HEADER
        // -----------------------------------------------------

        HBox header = new HBox();

        header.setPrefHeight(75);

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(15, 18, 15, 18)
        );

        Label title = new Label(
                "⌖   Active Route"
        );

        title.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label time = new Label(
                "◷  15 mins away"
        );

        time.setPadding(
                new Insets(9, 13, 9, 13)
        );

        time.setStyle(
                "-fx-background-color: #F0F2FA;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        header.getChildren().addAll(
                title,
                spacer,
                time
        );

        // -----------------------------------------------------
        // MAP
        // -----------------------------------------------------

        StackPane map = createMap();

        VBox.setVgrow(
                map,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                header,
                map
        );

        return box;
    }

    // =========================================================
    // MAP
    // =========================================================

    private static StackPane createMap() {

        StackPane map = new StackPane();

        map.setMinWidth(0);
        map.setMinHeight(0);

        map.setStyle(
                "-fx-background-color: #E5F0F4;"
        );

        // -----------------------------------------------------
        // ROADS
        // -----------------------------------------------------

        Pane roads = new Pane();

        roads.prefWidthProperty().bind(
                map.widthProperty()
        );

        roads.prefHeightProperty().bind(
                map.heightProperty()
        );

        addRoad(
                roads,
                0.05, 0.22,
                1.0, 0.30
        );

        addRoad(
                roads,
                0.0, 0.42,
                1.0, 0.47
        );

        addRoad(
                roads,
                0.0, 0.62,
                1.0, 0.58
        );

        addRoad(
                roads,
                0.0, 0.80,
                1.0, 0.74
        );

        addRoad(
                roads,
                0.05, 0.10,
                0.75, 1.0
        );

        addRoad(
                roads,
                0.0, 0.70,
                1.0, 0.15
        );

        map.getChildren().add(
                roads
        );

        // -----------------------------------------------------
        // CITY
        // -----------------------------------------------------

        Label city = new Label(
                "Bengaluru"
        );

        city.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #18233A;"
        );

        map.getChildren().add(
                city
        );

        // -----------------------------------------------------
        // ROUTE
        // -----------------------------------------------------

        Pane route = new Pane();

        route.prefWidthProperty().bind(
                map.widthProperty()
        );

        route.prefHeightProperty().bind(
                map.heightProperty()
        );

        Line line = new Line();

        line.startXProperty().bind(
                map.widthProperty().multiply(0.25)
        );

        line.startYProperty().bind(
                map.heightProperty().multiply(0.78)
        );

        line.endXProperty().bind(
                map.widthProperty().multiply(0.82)
        );

        line.endYProperty().bind(
                map.heightProperty().multiply(0.43)
        );

        line.setStroke(
                Color.web(BLUE)
        );

        line.setStrokeWidth(5);

        route.getChildren().add(
                line
        );

        // Pickup

        Circle pickup = new Circle(
                14,
                Color.web(GREEN)
        );

        pickup.layoutXProperty().bind(
                map.widthProperty().multiply(0.25)
        );

        pickup.layoutYProperty().bind(
                map.heightProperty().multiply(0.78)
        );

        // Current position

        Circle current = new Circle(
                15,
                Color.web(BLUE)
        );

        current.layoutXProperty().bind(
                map.widthProperty().multiply(0.50)
        );

        current.layoutYProperty().bind(
                map.heightProperty().multiply(0.62)
        );

        // Destination

        Circle destination = new Circle(
                15,
                Color.web(RED)
        );

        destination.layoutXProperty().bind(
                map.widthProperty().multiply(0.82)
        );

        destination.layoutYProperty().bind(
                map.heightProperty().multiply(0.43)
        );

        route.getChildren().addAll(
                pickup,
                current,
                destination
        );

        map.getChildren().add(
                route
        );

        // -----------------------------------------------------
        // LABELS
        // -----------------------------------------------------

        Label pickupText = new Label(
                "Pickup"
        );

        pickupText.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        pickupText.layoutXProperty().bind(
                map.widthProperty().multiply(0.22)
        );

        pickupText.layoutYProperty().bind(
                map.heightProperty().multiply(0.70)
        );

        Label dropText = new Label(
                "Drop-off"
        );

        dropText.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        dropText.layoutXProperty().bind(
                map.widthProperty().multiply(0.78)
        );

        dropText.layoutYProperty().bind(
                map.heightProperty().multiply(0.35)
        );

        map.getChildren().addAll(
                pickupText,
                dropText
        );

        return map;
    }

    // =========================================================
    // ROAD
    // =========================================================

    private static void addRoad(
            Pane pane,
            double sx,
            double sy,
            double ex,
            double ey
    ) {

        Line road = new Line();

        road.startXProperty().bind(
                pane.widthProperty().multiply(sx)
        );

        road.startYProperty().bind(
                pane.heightProperty().multiply(sy)
        );

        road.endXProperty().bind(
                pane.widthProperty().multiply(ex)
        );

        road.endYProperty().bind(
                pane.heightProperty().multiply(ey)
        );

        road.setStroke(
                Color.web("#C7D3DC")
        );

        road.setStrokeWidth(6);

        pane.getChildren().add(
                road
        );
    }

    // =========================================================
    // RIGHT PANEL
    // =========================================================

    private static VBox createRightPanel() {

        VBox panel = new VBox(15);

        // THIS IS IMPORTANT
        panel.setPrefWidth(360);
        panel.setMinWidth(360);
        panel.setMaxWidth(360);

        panel.setFillWidth(true);

        // -----------------------------------------------------
        // DELIVERY CARD
        // -----------------------------------------------------

        VBox deliveryCard = new VBox(13);

        deliveryCard.setPadding(
                new Insets(16)
        );

        deliveryCard.setStyle(
                cardStyle()
        );

        // ID

        HBox idRow = new HBox();

        VBox idBox = new VBox(3);

        Label idTitle = new Label(
                "DELIVERY ID"
        );

        idTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label id = new Label(
                "#DLV-1025"
        );

        id.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        idBox.getChildren().addAll(
                idTitle,
                id
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label active = new Label(
                "▣ Active"
        );

        active.setPadding(
                new Insets(7, 10, 7, 10)
        );

        active.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;"
        );

        idRow.getChildren().addAll(
                idBox,
                spacer,
                active
        );

        deliveryCard.getChildren().add(
                idRow
        );

        deliveryCard.getChildren().add(
                separator()
        );

        // -----------------------------------------------------
        // PRODUCT
        // -----------------------------------------------------

        HBox product = new HBox(12);

        product.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon = new Label(
                "♫"
        );

        icon.setPrefSize(
                60,
                60
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setStyle(
                "-fx-background-color: #F0F2FA;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 28px;"
        );

        VBox productText = new VBox(3);

        Label productName = new Label(
                "Yamaha F310 Acoustic\nGuitar"
        );

        productName.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label category = new Label(
                "Musical Instruments"
        );

        category.setStyle(
                "-fx-text-fill: " + BLUE + ";"
        );

        productText.getChildren().addAll(
                productName,
                category
        );

        product.getChildren().addAll(
                icon,
                productText
        );

        deliveryCard.getChildren().add(
                product
        );

        // -----------------------------------------------------
        // CUSTOMER
        // -----------------------------------------------------

        HBox customer = new HBox(10);

        customer.setAlignment(
                Pos.CENTER_LEFT
        );

        customer.setPadding(
                new Insets(10)
        );

        customer.setStyle(
                "-fx-background-color: #F0F2FA;" +
                "-fx-background-radius: 8;"
        );

        Circle avatarCircle = new Circle(
                23,
                Color.web("#DDE6FF")
        );

        Label ps = new Label(
                "PS"
        );

        ps.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        StackPane avatar = new StackPane();

        avatar.getChildren().addAll(
                avatarCircle,
                ps
        );

        VBox customerText = new VBox(2);

        Label customerName = new Label(
                "Priya Sharma"
        );

        customerName.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label customerType = new Label(
                "Customer"
        );

        customerType.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        customerText.getChildren().addAll(
                customerName,
                customerType
        );

        Region customerSpacer = new Region();

        HBox.setHgrow(
                customerSpacer,
                Priority.ALWAYS
        );

        Button call = smallButton("☎");
        Button message = smallButton("▤");

        customer.getChildren().addAll(
                avatar,
                customerText,
                customerSpacer,
                call,
                message
        );

        deliveryCard.getChildren().add(
                customer
        );

        deliveryCard.getChildren().add(
                separator()
        );

        // -----------------------------------------------------
        // DISTANCE + FEE
        // -----------------------------------------------------

        HBox details = new HBox(30);

        VBox distance = detail(
                "Distance",
                "♧  3.8 km"
        );

        VBox fee = detail(
                "Delivery Fee",
                "▣  $12.00"
        );

        details.getChildren().addAll(
                distance,
                fee
        );

        deliveryCard.getChildren().add(
                details
        );

        // -----------------------------------------------------
        // PICKUP
        // -----------------------------------------------------

        VBox pickup = new VBox(3);

        Label pickupTitle = new Label(
                "⌖  Pickup"
        );

        pickupTitle.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label pickupAddress = new Label(
                "HSR Layout, Sector 2"
        );

        pickupAddress.setStyle(
                "-fx-text-fill: " + TEXT + ";"
        );

        pickup.getChildren().addAll(
                pickupTitle,
                pickupAddress
        );

        // -----------------------------------------------------
        // DESTINATION
        // -----------------------------------------------------

        VBox destination = new VBox(3);

        Label destinationTitle = new Label(
                "⚑  Destination"
        );

        destinationTitle.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label destinationAddress = new Label(
                "BTM Layout, Stage 1"
        );

        destinationAddress.setStyle(
                "-fx-text-fill: " + TEXT + ";"
        );

        destination.getChildren().addAll(
                destinationTitle,
                destinationAddress
        );

        deliveryCard.getChildren().addAll(
                pickup,
                destination
        );

        // -----------------------------------------------------
        // STATUS CARD
        // -----------------------------------------------------

        VBox statusCard = createStatusCard();

        panel.getChildren().addAll(
                deliveryCard,
                statusCard
        );

        return panel;
    }

    // =========================================================
    // STATUS
    // =========================================================

    private static VBox createStatusCard() {

        VBox card = new VBox(10);

        card.setPadding(
                new Insets(16)
        );

        card.setStyle(
                cardStyle()
        );

        Label title = new Label(
                "Delivery Status"
        );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        card.getChildren().add(
                title
        );

        card.getChildren().add(
                status(
                        "●",
                        "Assigned",
                        "10:45 AM",
                        true
                )
        );

        card.getChildren().add(
                status(
                        "●",
                        "Reached Pickup",
                        "11:02 AM",
                        true
                )
        );

        card.getChildren().add(
                status(
                        "◉",
                        "Picked Up",
                        "Action Required",
                        true
                )
        );

        card.getChildren().add(
                status(
                        "○",
                        "On the Way",
                        "",
                        false
                )
        );

        card.getChildren().add(
                status(
                        "○",
                        "Delivered",
                        "",
                        false
                )
        );

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Button start = new Button(
                "▶  Start Delivery"
        );

        start.setMaxWidth(
                Double.MAX_VALUE
        );

        start.setPrefHeight(
                45
        );

        start.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;"
        );

        card.getChildren().addAll(
                spacer,
                start
        );

        return card;
    }

    // =========================================================
    // STATUS ROW
    // =========================================================

    private static VBox status(
            String icon,
            String title,
            String time,
            boolean active
    ) {

        VBox box = new VBox(2);

        HBox row = new HBox(8);

        Label iconLabel = new Label(
                icon
        );

        Label titleLabel = new Label(
                title
        );

        if (active) {

            iconLabel.setStyle(
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-weight: bold;"
            );

            titleLabel.setStyle(
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-weight: bold;"
            );

        } else {

            iconLabel.setStyle(
                    "-fx-text-fill: #AAB3C8;"
            );

            titleLabel.setStyle(
                    "-fx-text-fill: " + SECONDARY + ";"
            );
        }

        row.getChildren().addAll(
                iconLabel,
                titleLabel
        );

        box.getChildren().add(
                row
        );

        if (!time.isEmpty()) {

            Label timeLabel = new Label(
                    "    " + time
            );

            timeLabel.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: " + SECONDARY + ";"
            );

            box.getChildren().add(
                    timeLabel
            );
        }

        return box;
    }

    // =========================================================
    // DETAIL
    // =========================================================

    private static VBox detail(
            String title,
            String value
    ) {

        VBox box = new VBox(3);

        Label t = new Label(
                title
        );

        t.setStyle(
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label v = new Label(
                value
        );

        v.setStyle(
                "-fx-text-fill: " + TEXT + ";"
        );

        box.getChildren().addAll(
                t,
                v
        );

        return box;
    }

    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private static Button smallButton(
            String text
    ) {

        Button button = new Button(
                text
        );

        button.setPrefSize(
                34,
                30
        );

        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #BFC6D8;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );

        return button;
    }

    // =========================================================
    // SEPARATOR
    // =========================================================

    private static Region separator() {

        Region line = new Region();

        line.setPrefHeight(1);

        line.setMaxHeight(1);

        line.setStyle(
                "-fx-background-color: " + BORDER + ";"
        );

        return line;
    }

    // =========================================================
    // CARD STYLE
    // =========================================================

    private static String cardStyle() {

        return
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;";
    }
}