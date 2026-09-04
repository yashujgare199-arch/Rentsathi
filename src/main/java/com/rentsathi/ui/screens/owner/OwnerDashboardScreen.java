package com.rentsathi.ui.screens.owner;

import com.rentsathi.ui.screens.OwnerLoginScreen;
import com.rentsathi.ui.screens.owner.RentalRequestsScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class OwnerDashboardScreen {

    private static final String BLUE = "#3657C8";
    private static final String LIGHT_BLUE = "#E4EAFF";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String BORDER = "#CDD2E0";
    private static final String TEXT = "#101828";
    private static final String MUTED = "#5E6B85";

    private final Stage stage;

    public OwnerDashboardScreen(Stage stage) {
        this.stage = stage;
    }

    public static void show(Stage stage) {
        new OwnerDashboardScreen(stage).show();
    }

    public void show() {

        BorderPane root = new BorderPane();
        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        VBox sidebar = createSidebar();
        root.setLeft(sidebar);

        VBox main = createMainContent();

        ScrollPane scrollPane = new ScrollPane(main);
        scrollPane.setFitToWidth(true);
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

        root.setCenter(scrollPane);

        Scene scene = new Scene(
                root,
                1500,
                830
        );

        scene.setFill(
                Color.web(BACKGROUND)
        );

        stage.setTitle(
                "RentSathi - Owner Dashboard"
        );

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    private VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(255);
        sidebar.setMinWidth(255);
        sidebar.setMaxWidth(255);

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1px 0 0;"
        );

        sidebar.setPadding(
                new Insets(25, 14, 15, 14)
        );

        HBox brand = createBrand();

        VBox navigation = new VBox(8);

        navigation.setPadding(
                new Insets(28, 0, 0, 0)
        );

        Button dashboard = sidebarButton(
                FontAwesomeSolid.TH_LARGE,
                "Dashboard",
                true
        );
        

        Button bookings = sidebarButton(
                FontAwesomeSolid.CALENDAR_ALT,
                "Bookings",
                false
        );
        bookings.setOnAction(
            event -> RentalRequestsScreen.show(stage)
        );

        Button deliveries = sidebarButton(
                FontAwesomeSolid.TRUCK,
                "Owner Deliveries",
                false
        );

        Button analytics = sidebarButton(
                FontAwesomeSolid.CHART_BAR,
                "Analytics",
                false
        );

        navigation.getChildren().addAll(
                dashboard,
                bookings,
                deliveries,
                analytics
        );

        Region spacer = new Region();
        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Button newListing = new Button(
                "+  Post New Listing"
        );
        newListing.setOnAction(
                event -> AddNewRentalScreen.show(stage)
        );

        newListing.setMaxWidth(
                Double.MAX_VALUE
        );

        newListing.setPrefHeight(42);

        newListing.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        Button settings = sidebarButton(
                FontAwesomeSolid.COG,
                "Settings",
                false
        );

        Button logout = sidebarButton(
                FontAwesomeSolid.SIGN_OUT_ALT,
                "Logout",
                false
        );

        logout.setOnAction(event -> {
            OwnerLoginScreen.show(stage);
        });

        VBox bottom = new VBox(7);

        bottom.setPadding(
                new Insets(0, 0, 0, 0)
        );

        bottom.getChildren().addAll(
                newListing,
                settings,
                logout
        );

        sidebar.getChildren().addAll(
                brand,
                navigation,
                spacer,
                bottom
        );

        return sidebar;
    }

    private HBox createBrand() {

        Circle circle = new Circle(22);
        circle.setFill(
                Color.web(BLUE)
        );

        Label r = new Label("R");

        r.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        StackPane logo = new StackPane(
                circle,
                r
        );

        Label name = new Label(
                "RentSathi"
        );

        name.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label tagline = new Label(
                "Rent Smart. Live\nEasy."
        );

        tagline.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        VBox text = new VBox(
                1,
                name,
                tagline
        );

        HBox brand = new HBox(
                12,
                logo,
                text
        );

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        return brand;
    }

    private Button sidebarButton(
            FontAwesomeSolid icon,
            String text,
            boolean active
    ) {

        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(17);

        iconView.setIconColor(
                Color.web(
                        active ? BLUE : "#42526B"
                )
        );

        Button button = new Button(
                text,
                iconView
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(42);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setGraphicTextGap(14);

        if (active) {

            button.setStyle(
                    "-fx-background-color: #DCE5FF;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 7px;" +
                    "-fx-cursor: hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #42526B;" +
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 7px;" +
                    "-fx-cursor: hand;"
            );
        }

        return button;
    }

    private VBox createMainContent() {

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(0, 28, 35, 28)
        );

        content.setFillWidth(true);

        HBox topBar = createTopBar();

        VBox.setMargin(
                topBar,
                new Insets(0, -28, 0, -28)
        );

        VBox heading = new VBox(5);

        Label title = new Label(
                "Good morning, Sahil "
        );

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle = new Label(
                "Manage your rentals and grow your earnings."
        );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        heading.getChildren().addAll(
                title,
                subtitle
        );

        HBox headingRow = new HBox(
                heading
        );

        headingRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Region headingSpacer = new Region();

        HBox.setHgrow(
                headingSpacer,
                Priority.ALWAYS
        );

        Button report = new Button(
                "⇩  Report"
        );

        report.setPrefHeight(36);

        report.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );

        headingRow.getChildren().addAll(
                headingSpacer,
                report
        );

        HBox stats = createStatistics();

        HBox middle = createMiddleSection();

        Label topTitle = new Label(
                "Top Performing Listings"
        );

        topTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        HBox products = createProducts();

        content.getChildren().addAll(
                topBar,
                headingRow,
                stats,
                middle,
                topTitle,
                products
        );

        return content;
    }

    private HBox createTopBar() {

        HBox bar = new HBox();

        bar.setPrefHeight(72);
        bar.setMinHeight(72);

        bar.setAlignment(
                Pos.CENTER_LEFT
        );

        bar.setPadding(
                new Insets(0, 20, 0, 25)
        );

        bar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1px 0;"
        );

        Label title = new Label(
                "Dashboard"
        );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        FontIcon bell = new FontIcon(
                FontAwesomeSolid.BELL
        );

        bell.setIconSize(18);
        bell.setIconColor(
                Color.web("#344054")
        );

        StackPane bellBox = new StackPane(
                bell
        );

        Circle notification = new Circle(
                4,
                Color.web("#A52B25")
        );

        StackPane.setAlignment(
                notification,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                notification,
                new Insets(5, 0, 0, 0)
        );

        bellBox.getChildren().add(
                notification
        );

        FontIcon help = new FontIcon(
                FontAwesomeSolid.QUESTION_CIRCLE
        );

        help.setIconSize(18);
        help.setIconColor(
                Color.web("#344054")
        );

        Circle avatarCircle = new Circle(
                17
        );

        avatarCircle.setFill(
                Color.web("#D9E0F5")
        );

        Label avatar = new Label(
                "JD"
        );

        avatar.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        StackPane avatarBox = new StackPane(
                avatarCircle,
                avatar
        );

        Label profile = new Label(
                "Profile"
        );

        profile.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        HBox profileBox = new HBox(
                8,
                avatarBox,
                profile
        );

        profileBox.setAlignment(
                Pos.CENTER
        );

        profileBox.setPadding(
                new Insets(6, 13, 6, 8)
        );

        profileBox.setStyle(
                "-fx-background-color: #F8F9FE;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 22px;" +
                "-fx-background-radius: 22px;"
        );

        bar.getChildren().addAll(
                title,
                spacer,
                bellBox,
                new Region(),
                help,
                new Region(),
                profileBox
        );

        return bar;
    }

    private HBox createStatistics() {

        HBox box = new HBox(14);

        box.setFillHeight(true);

        box.getChildren().addAll(
                statCard(
                        "Total Listings",
                        "12",
                        "↑2",
                        FontAwesomeSolid.ARCHIVE,
                        "#DCE6FF"
                ),
                statCard(
                        "Active Rentals",
                        "5",
                        "",
                        FontAwesomeSolid.PLAY,
                        "#F8E0CE"
                ),
                statCard(
                        "Pending Requests",
                        "3",
                        "",
                        FontAwesomeSolid.CALENDAR_CHECK,
                        "#F7D9D5"
                ),
                statCard(
                        "Monthly Earnings",
                        "₹1,250.00",
                        "",
                        FontAwesomeSolid.MONEY_BILL,
                        "#DCE6FF"
                )
        );

        return box;
    }

    private VBox statCard(
            String heading,
            String value,
            String change,
            FontAwesomeSolid icon,
            String iconBackground
    ) {

        VBox card = new VBox(12);

        card.setPrefHeight(102);

        card.setPadding(
                new Insets(15)
        );

        HBox top = new HBox();

        Label title = new Label(
                heading
        );

        title.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        StackPane iconCircle = new StackPane();

        Circle circle = new Circle(
                18
        );

        circle.setFill(
                Color.web(iconBackground)
        );

        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(13);
        iconView.setIconColor(
                Color.web(BLUE)
        );

        iconCircle.getChildren().addAll(
                circle,
                iconView
        );

        top.getChildren().addAll(
                title,
                spacer,
                iconCircle
        );

        Label amount = new Label(
                value
        );

        amount.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        HBox bottom = new HBox(7);

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        bottom.getChildren().add(
                amount
        );

        if (!change.isEmpty()) {

            Label growth = new Label(
                    change
            );

            growth.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #63A46C;"
            );

            bottom.getChildren().add(
                    growth
            );
        }

        card.getChildren().addAll(
                top,
                bottom
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

    private HBox createMiddleSection() {

        HBox middle = new HBox(16);

        VBox performance = createPerformance();

        VBox requests = createRecentRequests();

        HBox.setHgrow(
                performance,
                Priority.ALWAYS
        );

        middle.getChildren().addAll(
                performance,
                requests
        );

        return middle;
    }

    private VBox createPerformance() {

        VBox card = new VBox(15);

        card.setPrefHeight(310);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        HBox header = new HBox();

        Label title = new Label(
                "Rental Performance"
        );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button month = new Button(
                "This Month⌄"
        );

        month.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        header.getChildren().addAll(
                title,
                spacer,
                month
        );

        HBox chart = createChart();

        VBox.setVgrow(
                chart,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                header,
                chart
        );

        return card;
    }

    private HBox createChart() {

        HBox chart = new HBox();

        chart.setAlignment(
                Pos.BOTTOM_CENTER
        );

        chart.setPadding(
                new Insets(10, 10, 0, 10)
        );

        String[] months = {
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct"
        };

        double[] values = {
                105,
                145,
                205,
                85,
                120
        };

        for (int i = 0; i < months.length; i++) {

            VBox column = new VBox(7);

            column.setAlignment(
                    Pos.BOTTOM_CENTER
            );

            Region bar = new Region();

            bar.setPrefWidth(40);
            bar.setPrefHeight(
                    values[i]
            );

            bar.setStyle(
                    "-fx-background-color: " +
                    (i == 2 ? BLUE : "#B7C2F4") +
                    ";"
            );

            Label month = new Label(
                    months[i]
            );

            month.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: " +
                    (i == 2 ? BLUE : MUTED) + ";"
            );

            if (i == 2) {
                month.setStyle(
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " + BLUE + ";"
                );
            }

            column.getChildren().addAll(
                    bar,
                    month
            );

            HBox.setHgrow(
                    column,
                    Priority.ALWAYS
            );

            chart.getChildren().add(
                    column
            );
        }

        return chart;
    }

    private VBox createRecentRequests() {

        VBox card = new VBox();

        card.setPrefWidth(350);
        card.setMinWidth(350);
        card.setMaxWidth(350);

        card.setPrefHeight(310);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        HBox header = new HBox();

        header.setPadding(
                new Insets(18, 16, 12, 16)
        );

        Label title = new Label(
                "Recent Requests"
        );

        title.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label viewAll = new Label(
                "View All"
        );

        viewAll.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-cursor: hand;"
        );

        header.getChildren().addAll(
                title,
                spacer,
                viewAll
        );

        VBox request1 = request(
                "SJ",
                "Sarah J.",
                "Sony Alpha A7 III",
                "Oct 15 - Oct 18",
                "₹135.00",
                "PENDING"
        );

        VBox request2 = request(
                "MR",
                "Mike R.",
                "DJI Mavic 3",
                "Oct 10 - Oct 12",
                "₹250.00",
                "COMPLETED"
        );

        card.getChildren().addAll(
                header,
                request1,
                request2
        );

        return card;
    }

    private VBox request(
            String initials,
            String name,
            String product,
            String date,
            String price,
            String status
    ) {

        VBox box = new VBox(7);

        box.setPadding(
                new Insets(10, 16, 12, 16)
        );

        HBox top = new HBox(9);

        Circle avatar = new Circle(
                17,
                Color.web("#E8EBF3")
        );

        Label initialsLabel = new Label(
                initials
        );

        initialsLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #667085;"
        );

        StackPane avatarBox = new StackPane(
                avatar,
                initialsLabel
        );

        VBox person = new VBox(1);

        Label personName = new Label(
                name
        );

        personName.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label item = new Label(
                product
        );

        item.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        person.getChildren().addAll(
                personName,
                item
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label statusLabel = new Label(
                status
        );

        statusLabel.setStyle(
                "-fx-background-color: " +
                (status.equals("PENDING")
                        ? "#F8DEC9"
                        : "#ECEEF4") +
                ";" +
                "-fx-text-fill: " +
                (status.equals("PENDING")
                        ? "#A75A2A"
                        : "#667085") +
                ";" +
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 5px 7px;" +
                "-fx-background-radius: 5px;"
        );

        top.getChildren().addAll(
                avatarBox,
                person,
                spacer,
                statusLabel
        );

        HBox details = new HBox();

        Label calendar = new Label(
                "▣  " + date
        );

        calendar.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Region detailSpacer = new Region();

        HBox.setHgrow(
                detailSpacer,
                Priority.ALWAYS
        );

        Label amount = new Label(
                price
        );

        amount.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        details.getChildren().addAll(
                calendar,
                detailSpacer,
                amount
        );

        box.getChildren().addAll(
                top,
                details
        );

        if (status.equals("PENDING")) {

            Button view = new Button(
                    "View Details"
            );

            view.setMaxWidth(
                    Double.MAX_VALUE
            );

            view.setPrefHeight(28);

            view.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " + BLUE + ";" +
                    "-fx-border-radius: 6px;" +
                    "-fx-background-radius: 6px;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-size: 10px;" +
                    "-fx-cursor: hand;"
            );

            box.getChildren().add(
                    view
            );
        }

        box.setStyle(
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px 0 0 0;"
        );

        return box;
    }

    private HBox createProducts() {

        HBox box = new HBox(14);

        box.getChildren().addAll(
                productCard(
                        "Sony Alpha A7 III Body",
                        "4.9",
                        "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&w=700&q=80"
                ),
                productCard(
                        "DJI Mavic 3 Drone Combo",
                        "5.0",
                        "https://images.unsplash.com/photo-1473968512647-3e447244af8f?auto=format&fit=crop&w=700&q=80"
                ),
                productCard(
                        "Aputure 120d II Light Kit",
                        "4.8",
                        "https://images.unsplash.com/photo-1531058020387-3be344556be6?auto=format&fit=crop&w=700&q=80"
                )
        );

        return box;
    }

    private VBox productCard(
            String name,
            String rating,
            String imageUrl
    ) {

        VBox card = new VBox();

        card.setPrefWidth(300);
        card.setMinWidth(300);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        StackPane imageBox = new StackPane();

        imageBox.setPrefHeight(150);

        ImageView imageView;

        try {

            Image image = new Image(
                    imageUrl,
                    300,
                    150,
                    false,
                    true
            );

            imageView = new ImageView(
                    image
            );

            imageView.setFitWidth(300);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(false);

        } catch (Exception e) {

            Rectangle placeholder =
                    new Rectangle(
                            300,
                            150
                    );

            placeholder.setFill(
                    Color.web("#E9ECF3")
            );

            imageView = new ImageView();
            imageBox.getChildren().add(
                    placeholder
            );
        }

        if (imageView.getImage() != null) {
            imageBox.getChildren().add(
                    imageView
            );
        }

        Label ratingLabel = new Label(
                "★ " + rating
        );

        ratingLabel.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #B27A21;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 5px 7px;" +
                "-fx-background-radius: 5px;"
        );

        StackPane.setAlignment(
                ratingLabel,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                ratingLabel,
                new Insets(8)
        );

        imageBox.getChildren().add(
                ratingLabel
        );

        Label nameLabel = new Label(
                name
        );

        nameLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        nameLabel.setPadding(
                new Insets(12)
        );

        card.getChildren().addAll(
                imageBox,
                nameLabel
        );

        return card;
    }
}