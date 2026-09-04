package com.rentsathi.ui.screens.admin;

import com.rentsathi.ui.screens.AdminLoginScreen;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class AdminDashboardScreen {

        // =========================================================
        // COLORS
        // =========================================================

        private static final String BG = "#F7F8FD";
        private static final String WHITE = "#FFFFFF";
        private static final String BLUE = "#4058D4";
        private static final String LIGHT_BLUE = "#E5EBFF";
        private static final String TEXT = "#172033";
        private static final String SECONDARY = "#52617A";
        private static final String BORDER = "#D2D8E8";
        private static final String GREEN = "#4CAF70";
        private static final String RED = "#E55353";
        private static final String ORANGE = "#E69A5A";

        // =========================================================
        // SHOW
        // =========================================================

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                // -----------------------------------------------------
                // TOP HEADER
                // -----------------------------------------------------

                root.setTop(createTopBar());

                // -----------------------------------------------------
                // LEFT SIDEBAR
                // -----------------------------------------------------

                root.setLeft(createSidebar(stage));

                // -----------------------------------------------------
                // DASHBOARD CONTENT
                // -----------------------------------------------------

                ScrollPane scrollPane = createDashboardScroll();

                root.setCenter(scrollPane);

                // -----------------------------------------------------
                // SCENE
                // -----------------------------------------------------

                Scene scene = new Scene(
                                root,
                                1500,
                                850);

                stage.setTitle(
                                "RentSathi - Admin Dashboard");

                stage.setMinWidth(1200);
                stage.setMinHeight(700);

                stage.setScene(scene);

                stage.show();
        }

        // =========================================================
        // TOP BAR
        // =========================================================

        private static HBox createTopBar() {

                HBox top = new HBox();

                top.setPrefHeight(62);
                top.setMinHeight(62);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                top.setPadding(
                                new Insets(0, 18, 0, 20));

                top.setSpacing(25);

                top.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Label title = new Label(
                                "Dashboard");

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                // Search box

                TextField search = new TextField();

                search.setPromptText(
                                "Search...");

                search.setPrefWidth(300);

                search.setPrefHeight(34);

                search.setStyle(
                                "-fx-background-color: #F1F3F9;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 0 15;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label notification = new Label(
                                "●");

                notification.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-text-fill: " + RED + ";");

                Label settings = new Label(
                                "⚙");

                settings.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                Circle profileCircle = new Circle(
                                20,
                                Color.web(BLUE));

                Label profileLetter = new Label(
                                "A");

                profileLetter.setStyle(
                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;");

                StackPane profile = new StackPane();

                profile.getChildren().addAll(
                                profileCircle,
                                profileLetter);

                top.getChildren().addAll(
                                title,
                                search,
                                spacer,
                                notification,
                                settings,
                                profile);

                return top;
        }

        // =========================================================
        // SIDEBAR
        // =========================================================

        private static VBox createSidebar(Stage stage) {

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(160);
                sidebar.setMinWidth(160);
                sidebar.setMaxWidth(160);

                sidebar.setPadding(
                                new Insets(15, 10, 12, 10));

                sidebar.setSpacing(8);

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 0 0;");

                // -----------------------------------------------------
                // LOGO
                // -----------------------------------------------------

                HBox logoBox = new HBox(8);

                logoBox.setAlignment(
                                Pos.CENTER_LEFT);

                Circle logoCircle = new Circle(
                                20,
                                Color.web(BLUE));

                Label r = new Label(
                                "R");

                r.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                StackPane logo = new StackPane();

                logo.getChildren().addAll(
                                logoCircle,
                                r);

                VBox logoText = new VBox(0);

                Label name = new Label(
                                "RentSathi");

                name.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label tagline = new Label(
                                "Rent Smart. Live Easy.");

                tagline.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                logoText.getChildren().addAll(
                                name,
                                tagline);

                logoBox.getChildren().addAll(
                                logo,
                                logoText);

                sidebar.getChildren().add(
                                logoBox);

                // -----------------------------------------------------
                // MENU
                // -----------------------------------------------------

                // -----------------------------------------------------
                // MENU
                // -----------------------------------------------------

                Button customer = menuButton(
                                "♙",
                                "Customers",
                                false);

                Button owner = menuButton(
                                "▣",
                                "Owners",
                                false);

                Button deliveryPartner = menuButton(
                                "▰",
                                "Delivery Partners",
                                false);

                // -----------------------------------------------------
                // NAVIGATION
                // -----------------------------------------------------

                customer.setOnAction(event -> {
                        AdminCustomersScreen.show(stage);
                });

                owner.setOnAction(event -> {
                      AdminOwnersScreen.show(stage);
                });

                deliveryPartner.setOnAction(event -> {
                       AdminDeliveryPartnersScreen.show(stage);
                });

                sidebar.getChildren().addAll(
                                customer,
                                owner,
                                deliveryPartner);

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                sidebar.getChildren().add(
                                spacer);

                sidebar.getChildren().add(
                                menuButton(
                                                "?",
                                                "Help Center",
                                                false));

                Button logout = menuButton(
                                "↪",
                                "Logout",
                                false);

                logout.setOnAction(event -> {
                        AdminLoginScreen.show(stage);
                });

                sidebar.getChildren().add(logout);

                return sidebar;
        }

        // =========================================================
        // MENU BUTTON
        // =========================================================

        private static Button menuButton(
                        String icon,
                        String text,
                        boolean active) {

                Button button = new Button(
                                icon + "   " + text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setPadding(
                                new Insets(9, 7, 9, 7));

                if (active) {

                        button.setStyle(
                                        "-fx-background-color: " +
                                                        LIGHT_BLUE + ";" +
                                                        "-fx-background-radius: 7;" +
                                                        "-fx-text-fill: " +
                                                        BLUE + ";" +
                                                        "-fx-font-weight: bold;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " +
                                                        SECONDARY + ";");
                }

                return button;
        }

        // =========================================================
        // SCROLL PANE
        // =========================================================

        private static ScrollPane createDashboardScroll() {

                VBox content = createDashboardContent();

                ScrollPane scroll = new ScrollPane(
                                content);

                scroll.setFitToWidth(true);

                scroll.setFitToHeight(false);

                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scroll.setPannable(true);

                scroll.setStyle(
                                "-fx-background-color: " + BG + ";" +
                                                "-fx-border-color: transparent;");

                /*
                 * FAST SCROLLING
                 *
                 * JavaFX ScrollPane normally moves very slowly.
                 * This multiplies the mouse-wheel movement.
                 */

                scroll.addEventFilter(
                                javafx.scene.input.ScrollEvent.SCROLL,
                                event -> {

                                        double current = scroll.getVvalue();

                                        double delta = event.getDeltaY();

                                        double speed = 0.008;

                                        double newValue = current - (delta * speed);

                                        newValue = Math.max(
                                                        0,
                                                        Math.min(
                                                                        1,
                                                                        newValue));

                                        scroll.setVvalue(
                                                        newValue);

                                        event.consume();
                                });

                return scroll;
        }

        // =========================================================
        // DASHBOARD CONTENT
        // =========================================================

        private static VBox createDashboardContent() {

                VBox content = new VBox();

                content.setPadding(
                                new Insets(22, 20, 30, 20));

                content.setSpacing(18);

                content.setFillWidth(true);

                content.setStyle(
                                "-fx-background-color: " + BG + ";");

                // -----------------------------------------------------
                // WELCOME
                // -----------------------------------------------------

                VBox welcome = new VBox(3);

                Label heading = new Label(
                                "Welcome back, Admin");

                heading.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label subtitle = new Label(
                                "Here's what's happening across RentSathi.");

                subtitle.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                welcome.getChildren().addAll(
                                heading,
                                subtitle);

                content.getChildren().add(
                                welcome);

                // -----------------------------------------------------
                // STAT CARDS
                // -----------------------------------------------------

                GridPane stats = new GridPane();

                stats.setHgap(10);
                stats.setVgap(10);

                for (int i = 0; i < 4; i++) {

                        ColumnConstraints cc = new ColumnConstraints();

                        cc.setPercentWidth(25);

                        stats.getColumnConstraints().add(
                                        cc);
                }

                stats.add(
                                statCard(
                                                "TOTAL USERS",
                                                "12,450",
                                                "+12%",
                                                BLUE,
                                                "♙"),
                                0,
                                0);

                stats.add(
                                statCard(
                                                "TOTAL OWNERS",
                                                "1,200",
                                                "+5%",
                                                BLUE,
                                                "▣"),
                                1,
                                0);

                stats.add(
                                statCard(
                                                "DELIVERY PARTNERS",
                                                "850",
                                                "+8%",
                                                ORANGE,
                                                "▰"),
                                2,
                                0);

                stats.add(
                                statCard(
                                                "ACTIVE LISTINGS",
                                                "4,320",
                                                "0%",
                                                BLUE,
                                                "▤"),
                                3,
                                0);

                stats.add(
                                statCard(
                                                "ACTIVE BOOKINGS",
                                                "520",
                                                "+15%",
                                                BLUE,
                                                "▣"),
                                0,
                                1);

                stats.add(
                                statCard(
                                                "TOTAL REVENUE",
                                                "₹24.5L",
                                                "+22%",
                                                GREEN,
                                                "▣"),
                                1,
                                1);

                stats.add(
                                statCard(
                                                "PENDING APPROVALS",
                                                "18",
                                                "-4%",
                                                ORANGE,
                                                "▣"),
                                2,
                                1);

                stats.add(
                                statCard(
                                                "OPEN DISPUTES",
                                                "5",
                                                "-1%",
                                                RED,
                                                "⚖"),
                                3,
                                1);

                content.getChildren().add(
                                stats);

                // -----------------------------------------------------
                // PLATFORM OVERVIEW
                // -----------------------------------------------------

                Label overview = new Label(
                                "Platform Overview");

                overview.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                content.getChildren().add(
                                overview);

                // -----------------------------------------------------
                // CHARTS
                // -----------------------------------------------------

                GridPane charts = new GridPane();

                charts.setHgap(10);
                charts.setVgap(10);

                ColumnConstraints chart1 = new ColumnConstraints();

                chart1.setPercentWidth(50);

                ColumnConstraints chart2 = new ColumnConstraints();

                chart2.setPercentWidth(50);

                charts.getColumnConstraints().addAll(
                                chart1,
                                chart2);

                charts.add(
                                createUserGrowthChart(),
                                0,
                                0);

                charts.add(
                                createBookingChart(),
                                1,
                                0);

                charts.add(
                                createRevenueChart(),
                                0,
                                1);

                charts.add(
                                createCategoryChart(),
                                1,
                                1);

                content.getChildren().add(
                                charts);

                // -----------------------------------------------------
                // BOTTOM SECTIONS
                // -----------------------------------------------------

                GridPane bottom = new GridPane();

                bottom.setHgap(15);

                ColumnConstraints left = new ColumnConstraints();

                left.setPercentWidth(50);

                ColumnConstraints right = new ColumnConstraints();

                right.setPercentWidth(50);

                bottom.getColumnConstraints().addAll(
                                left,
                                right);

                bottom.add(
                                createRecentActivity(),
                                0,
                                0);

                bottom.add(
                                createPendingActions(),
                                1,
                                0);

                content.getChildren().add(
                                bottom);

                return content;
        }

        // =========================================================
        // STAT CARD
        // =========================================================

        private static VBox statCard(
                        String title,
                        String value,
                        String change,
                        String color,
                        String icon) {

                VBox card = new VBox();

                card.setMinHeight(78);

                card.setPadding(
                                new Insets(11));

                card.setSpacing(5);

                card.setStyle(
                                cardStyle());

                HBox top = new HBox();

                Label titleLabel = new Label(
                                title);

                titleLabel.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label iconLabel = new Label(
                                icon);

                iconLabel.setAlignment(
                                Pos.CENTER);

                iconLabel.setPrefSize(
                                24,
                                24);

                iconLabel.setStyle(
                                "-fx-background-color: #E7ECFF;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-text-fill: " + color + ";");

                top.getChildren().addAll(
                                titleLabel,
                                spacer,
                                iconLabel);

                HBox valueRow = new HBox(7);

                Label valueLabel = new Label(
                                value);

                valueLabel.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label changeLabel = new Label(
                                change);

                changeLabel.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: " +
                                                (change.startsWith("-")
                                                                ? RED
                                                                : GREEN)
                                                +
                                                ";");

                valueRow.getChildren().addAll(
                                valueLabel,
                                changeLabel);

                card.getChildren().addAll(
                                top,
                                valueRow);

                return card;
        }

        // =========================================================
        // USER GROWTH
        // =========================================================

        private static VBox createUserGrowthChart() {

                NumberAxis yAxis = new NumberAxis();

                CategoryAxis xAxis = new CategoryAxis();

                LineChart<String, Number> chart = new LineChart<>(
                                xAxis,
                                yAxis);

                chart.setLegendVisible(false);

                chart.setAnimated(false);

                chart.setCreateSymbols(true);

                chart.setPrefHeight(210);

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                series.getData().add(
                                new XYChart.Data<>(
                                                "Jan",
                                                8000));

                series.getData().add(
                                new XYChart.Data<>(
                                                "Feb",
                                                9500));

                series.getData().add(
                                new XYChart.Data<>(
                                                "Mar",
                                                10200));

                series.getData().add(
                                new XYChart.Data<>(
                                                "Apr",
                                                11000));

                series.getData().add(
                                new XYChart.Data<>(
                                                "May",
                                                11800));

                series.getData().add(
                                new XYChart.Data<>(
                                                "Jun",
                                                12450));

                chart.getData().add(
                                series);

                return chartCard(
                                "User Growth",
                                chart);
        }

        // =========================================================
        // BOOKING CHART
        // =========================================================

        private static VBox createBookingChart() {

                NumberAxis yAxis = new NumberAxis();

                CategoryAxis xAxis = new CategoryAxis();

                BarChart<String, Number> chart = new BarChart<>(
                                xAxis,
                                yAxis);

                chart.setLegendVisible(false);

                chart.setAnimated(false);

                chart.setPrefHeight(210);

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                int[] values = { 45, 52, 38, 65, 80, 120, 110 };

                String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul" };

                for (int i = 0; i < months.length; i++) {

                        series.getData().add(
                                        new XYChart.Data<>(
                                                        months[i],
                                                        values[i]));
                }

                chart.getData().add(
                                series);

                return chartCard(
                                "Booking Growth",
                                chart);
        }

        // =========================================================
        // REVENUE CHART
        // =========================================================

        private static VBox createRevenueChart() {

                NumberAxis yAxis = new NumberAxis();

                CategoryAxis xAxis = new CategoryAxis();

                LineChart<String, Number> chart = new LineChart<>(
                                xAxis,
                                yAxis);

                chart.setLegendVisible(false);

                chart.setAnimated(false);

                chart.setPrefHeight(210);

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                series.getData().add(
                                new XYChart.Data<>(
                                                "Jan",
                                                50000));

                series.getData().add(
                                new XYChart.Data<>(
                                                "Feb",
                                                150000));

                series.getData().add(
                                new XYChart.Data<>(
                                                "Mar",
                                                250000));

                series.getData().add(
                                new XYChart.Data<>(
                                                "Apr",
                                                350000));

                series.getData().add(
                                new XYChart.Data<>(
                                                "May",
                                                450000));

                chart.getData().add(
                                series);

                return chartCard(
                                "Revenue",
                                chart);
        }

        // =========================================================
        // CATEGORY CHART
        // =========================================================

        private static VBox createCategoryChart() {

                StackPane chartArea = new StackPane();

                chartArea.setPrefHeight(
                                210);

                // Donut

                Circle outer = new Circle(
                                65,
                                Color.web(BLUE));

                Circle middle = new Circle(
                                43,
                                Color.web(WHITE));

                StackPane donut = new StackPane();

                donut.getChildren().addAll(
                                outer,
                                middle);

                chartArea.getChildren().add(
                                donut);

                // Legend

                VBox legend = new VBox(4);

                legend.setTranslateX(115);

                legend.getChildren().add(
                                legendItem(
                                                "Electronics",
                                                BLUE));

                legend.getChildren().add(
                                legendItem(
                                                "Furniture",
                                                "#59657D"));

                legend.getChildren().add(
                                legendItem(
                                                "Vehicles",
                                                "#A45B28"));

                legend.getChildren().add(
                                legendItem(
                                                "Tools",
                                                "#D3D8EA"));

                legend.getChildren().add(
                                legendItem(
                                                "Other",
                                                "#E3E6F3"));

                chartArea.getChildren().add(
                                legend);

                return chartCard(
                                "Rental Categories",
                                chartArea);
        }

        // =========================================================
        // LEGEND
        // =========================================================

        private static HBox legendItem(
                        String text,
                        String color) {

                HBox box = new HBox(5);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                Circle circle = new Circle(
                                5,
                                Color.web(color));

                Label label = new Label(text);

                label.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                box.getChildren().addAll(
                                circle,
                                label);

                return box;
        }

        // =========================================================
        // CHART CARD
        // =========================================================

        private static VBox chartCard(
                        String title,
                        Region chart) {

                VBox card = new VBox(5);

                card.setPadding(
                                new Insets(10));

                card.setStyle(
                                cardStyle());

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                VBox.setVgrow(
                                chart,
                                Priority.ALWAYS);

                card.getChildren().addAll(
                                titleLabel,
                                chart);

                return card;
        }

        // =========================================================
        // RECENT ACTIVITY
        // =========================================================

        private static VBox createRecentActivity() {

                VBox box = new VBox(8);

                Label title = new Label(
                                "Recent Activity");

                title.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                VBox card = new VBox();

                card.setStyle(
                                cardStyle());

                card.getChildren().add(
                                activity(
                                                "♙",
                                                "New Customer Registered",
                                                "Arjun P. joined the platform.",
                                                "2m ago",
                                                BLUE));

                card.getChildren().add(
                                activity(
                                                "▣",
                                                "New Owner Registered",
                                                "Sara K. joined the platform.",
                                                "15m ago",
                                                BLUE));

                card.getChildren().add(
                                activity(
                                                "□",
                                                "New Listing Added",
                                                "Sony A7 IV listed by Amit R.",
                                                "1h ago",
                                                ORANGE));

                card.getChildren().add(
                                activity(
                                                "✓",
                                                "New Booking Created",
                                                "#BK-8821 confirmed for tomorrow.",
                                                "3h ago",
                                                GREEN));

                card.getChildren().add(
                                activity(
                                                "!",
                                                "New Complaint Logged",
                                                "#CMP-104 regarding late delivery.",
                                                "5h ago",
                                                RED));

                box.getChildren().addAll(
                                title,
                                card);

                return box;
        }

        // =========================================================
        // ACTIVITY ROW
        // =========================================================

        private static HBox activity(
                        String icon,
                        String title,
                        String description,
                        String time,
                        String color) {

                HBox row = new HBox(9);

                row.setPadding(
                                new Insets(8));

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setStyle(
                                "-fx-border-color: " +
                                                BORDER +
                                                ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Circle circle = new Circle(
                                13,
                                Color.web("#E9EDFF"));

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-text-fill: " + color + ";" +
                                                "-fx-font-weight: bold;");

                StackPane iconPane = new StackPane();

                iconPane.getChildren().addAll(
                                circle,
                                iconLabel);

                VBox text = new VBox(1);

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label desc = new Label(description);

                desc.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                text.getChildren().addAll(
                                titleLabel,
                                desc);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label timeLabel = new Label(time);

                timeLabel.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                row.getChildren().addAll(
                                iconPane,
                                text,
                                spacer,
                                timeLabel);

                return row;
        }

        // =========================================================
        // PENDING ACTIONS
        // =========================================================

        private static VBox createPendingActions() {

                VBox box = new VBox(8);

                HBox heading = new HBox();

                Label title = new Label(
                                "Pending Actions");

                title.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label viewAll = new Label(
                                "View All");

                viewAll.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: " + BLUE + ";");

                heading.getChildren().addAll(
                                title,
                                spacer,
                                viewAll);

                VBox card = new VBox();

                card.setStyle(
                                cardStyle());

                card.getChildren().add(
                                actionRow(
                                                "♢",
                                                "Owner Verification",
                                                "3 profiles pending",
                                                "Review"));

                card.getChildren().add(
                                actionRow(
                                                "≡",
                                                "Listing Approval",
                                                "12 items pending",
                                                "Review"));

                card.getChildren().add(
                                actionRow(
                                                "♧",
                                                "Delivery Partner Approval",
                                                "2 applications pending",
                                                "Review"));

                card.getChildren().add(
                                actionRow(
                                                "⚖",
                                                "Dispute Resolution",
                                                "1 critical case",
                                                "Review"));

                box.getChildren().addAll(
                                heading,
                                card);

                return box;
        }

        // =========================================================
        // ACTION ROW
        // =========================================================

        private static HBox actionRow(
                        String icon,
                        String title,
                        String subtitle,
                        String buttonText) {

                HBox row = new HBox(8);

                row.setPadding(
                                new Insets(8));

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setStyle(
                                "-fx-border-color: " +
                                                BORDER +
                                                ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Label iconLabel = new Label(icon);

                iconLabel.setPrefSize(
                                27,
                                27);

                iconLabel.setAlignment(
                                Pos.CENTER);

                iconLabel.setStyle(
                                "-fx-background-color: #E9EDFF;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-text-fill: " + BLUE + ";");

                VBox text = new VBox(1);

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label sub = new Label(subtitle);

                sub.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                text.getChildren().addAll(
                                titleLabel,
                                sub);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button review = new Button(buttonText);

                review.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 5;");

                row.getChildren().addAll(
                                iconLabel,
                                text,
                                spacer,
                                review);

                return row;
        }

        // =========================================================
        // CARD STYLE
        // =========================================================

        private static String cardStyle() {

                return "-fx-background-color: white;" +
                                "-fx-border-color: " + BORDER + ";" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;";
        }
}
