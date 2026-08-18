package com.rentsathi.ui.screens.owner;

import com.rentsathi.ui.screens.OwnerLoginScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class MyListingsScreen {

    private static final String BLUE = "#3657C8";
    private static final String LIGHT_BLUE = "#DCE6FF";
    private static final String BG = "#F8F8FD";
    private static final String BORDER = "#CDD2E0";
    private static final String TEXT = "#101828";
    private static final String MUTED = "#5E6B85";

    private static final double SCENE_WIDTH = 1500;
    private static final double SCENE_HEIGHT = 830;
    private static final double SIDEBAR_WIDTH = 255;

    private final Stage stage;

    public MyListingsScreen(Stage stage) {
        this.stage = stage;
    }

    public static void show(Stage stage) {
        new MyListingsScreen(stage).show();
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG + ";");

        root.setLeft(createSidebar());

        VBox content = createContent();

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.web(BG));

        stage.setTitle("RentSathi - My Listings");
        stage.setScene(scene);
        stage.show();
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(SIDEBAR_WIDTH);
        sidebar.setMinWidth(SIDEBAR_WIDTH);
        sidebar.setMaxWidth(SIDEBAR_WIDTH);
        sidebar.setPadding(new Insets(22, 14, 14, 14));

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1px 0 0;"
        );

        HBox brand = createBrand();

        VBox navigation = new VBox(7);
        navigation.setPadding(new Insets(30, 0, 0, 0));

        Button dashboard = sidebarButton(FontAwesomeSolid.TH_LARGE, "Dashboard", false);
        dashboard.setOnAction(event -> {
            try {
                OwnerDashboardScreen.show(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button listings = sidebarButton(FontAwesomeSolid.ARCHIVE, "Manage Listings", true);

        Button bookings = sidebarButton(FontAwesomeSolid.CALENDAR_ALT, "Bookings", false);

        bookings.setOnAction(
            event -> RentalRequestsScreen.show(stage)
        );
        Button deliveries = sidebarButton(FontAwesomeSolid.TRUCK, "Deliveries", false);
        Button analytics = sidebarButton(FontAwesomeSolid.CHART_BAR, "Analytics", false);

        navigation.getChildren().addAll(
                dashboard, listings, bookings, deliveries, analytics
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button postListing = new Button("+  Post New Listing");

        postListing.setOnAction(
            event -> AddNewRentalScreen.show(stage)
        );
        postListing.setMaxWidth(Double.MAX_VALUE);
        postListing.setPrefHeight(42);
        postListing.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        Button settings = sidebarButton(FontAwesomeSolid.COG, "Settings", false);

        Button logout = sidebarButton(FontAwesomeSolid.SIGN_OUT_ALT, "Logout", false);
        logout.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #C62828;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
        logout.setOnAction(event -> OwnerLoginScreen.show(stage));

        VBox bottom = new VBox(7);
        bottom.getChildren().addAll(postListing, settings, logout);

        sidebar.getChildren().addAll(brand, navigation, spacer, bottom);
        return sidebar;
    }

    private HBox createBrand() {
        Circle circle = new Circle(22, Color.web(BLUE));

        Label rs = new Label("RS");
        rs.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        StackPane logo = new StackPane(circle, rs);

        Label name = new Label("RentSathi");
        name.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label tagline = new Label("Rent Smart. Live\nEasy.");
        tagline.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        VBox text = new VBox(1, name, tagline);

        HBox brand = new HBox(12, logo, text);
        brand.setAlignment(Pos.CENTER_LEFT);

        return brand;
    }

    private Button sidebarButton(
            FontAwesomeSolid icon,
            String text,
            boolean active
    ) {
        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(16);
        iconView.setIconColor(Color.web(active ? BLUE : "#42526B"));

        Button button = new Button(text, iconView);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(40);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setGraphicTextGap(14);

        if (active) {
            button.setStyle(
                    "-fx-background-color: " + LIGHT_BLUE + ";" +
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

    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private VBox createContent() {
        VBox content = new VBox(18);
        content.setPadding(new Insets(0, 30, 30, 30));
        content.setFillWidth(true);

        HBox topBar = createTopBar();
        VBox.setMargin(topBar, new Insets(0, -30, 0, -30));

        HBox breadcrumb = createBreadcrumb();
        HBox heading = createHeading();
        HBox tabs = createTabs();
        VBox table = createListingsTable();

        content.getChildren().addAll(
                topBar,
                breadcrumb,
                heading,
                tabs,
                table
        );

        return content;
    }

    private HBox createTopBar() {
        HBox bar = new HBox();
        bar.setPrefHeight(70);
        bar.setMinHeight(70);
        bar.setPadding(new Insets(0, 22, 0, 22));
        bar.setAlignment(Pos.CENTER_RIGHT);

        bar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1px 0;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        FontIcon bell = new FontIcon(FontAwesomeSolid.BELL);
        bell.setIconSize(18);
        bell.setIconColor(Color.web(TEXT));

        Circle redDot = new Circle(4, Color.web("#A52B25"));

        StackPane bellBox = new StackPane(bell);
        StackPane.setAlignment(redDot, Pos.TOP_RIGHT);
        bellBox.getChildren().add(redDot);

        bar.getChildren().addAll(spacer, bellBox);
        return bar;
    }

    private HBox createBreadcrumb() {
        HBox box = new HBox(8);
        box.setAlignment(Pos.CENTER_LEFT);

        Label workspace = new Label("Workspace");
        workspace.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label arrow = new Label("›");
        arrow.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #98A2B3;"
        );

        Label current = new Label("Manage Listings");
        current.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        box.getChildren().addAll(workspace, arrow, current);
        return box;
    }

    private HBox createHeading() {
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_LEFT);

        VBox titleBox = new VBox(4);

        Label title = new Label("My Listings");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle = new Label(
                "Manage your rental inventory and track performance."
        );
        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        titleBox.getChildren().addAll(title, subtitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        TextField search = new TextField();
        search.setPromptText("Search your listings...");
        search.setPrefWidth(225);
        search.setPrefHeight(36);
        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 0 12px;"
        );

        Button add = new Button("+  Add New Rental");
        add.setPrefHeight(36);
        add.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        HBox right = new HBox(14, search, add);
        right.setAlignment(Pos.CENTER);

        box.getChildren().addAll(titleBox, spacer, right);
        return box;
    }

    private HBox createTabs() {
        HBox tabs = new HBox(27);
        tabs.setAlignment(Pos.CENTER_LEFT);

        String[] names = {
                "All Listings (42)",
                "Active (28)",
                "Rented (8)",
                "Inactive (3)",
                "Drafts (2)",
                "Pending Approval  1"
        };

        for (int i = 0; i < names.length; i++) {
            Label tab = new Label(names[i]);
            tab.setPadding(new Insets(0, 0, 10, 0));

            if (i == 0) {
                tab.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " + BLUE + ";" +
                        "-fx-border-color: " + BLUE + ";" +
                        "-fx-border-width: 0 0 2px 0;" +
                        "-fx-cursor: hand;"
                );
            } else {
                tab.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-text-fill: #344054;" +
                        "-fx-cursor: hand;"
                );
            }

            tabs.getChildren().add(tab);
        }

        return tabs;
    }

    // =========================================================
    // LISTINGS TABLE
    // =========================================================

    private VBox createListingsTable() {
        VBox wrapper = new VBox();

        wrapper.setMaxWidth(Double.MAX_VALUE);
        wrapper.setPrefWidth(1180);

        wrapper.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        HBox toolbar = new HBox(8);
        toolbar.setPadding(new Insets(10, 14, 10, 14));
        toolbar.setAlignment(Pos.CENTER_LEFT);

        Button filter = smallIconButton(FontAwesomeSolid.FILTER);
        Button sort = smallIconButton(FontAwesomeSolid.SORT_AMOUNT_DOWN);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label showing = new Label("Showing 1–10 of 42 listings");
        showing.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        toolbar.getChildren().addAll(filter, sort, spacer, showing);

        GridPane header = createTableHeader();

        VBox rows = new VBox();

        rows.getChildren().addAll(
                listingRow(
                        "Sony A7III Mirrorless Camera",
                        "LST-8492",
                        "Photography",
                        "Downtown\nCore",
                        "$45",
                        "15",
                        "4.9 (24)",
                        "Active",
                        "sony"
                ),
                listingRow(
                        "Makita 18V Cordless Drill Set",
                        "LST-7731",
                        "Tools & Hardware",
                        "Westside\nDistrict",
                        "$15",
                        "8",
                        "4.7 (12)",
                        "Rented",
                        "drill"
                ),
                listingRow(
                        "DJI Mavic Air 2 Drone + Extras",
                        "LST-9012",
                        "Electronics",
                        "Downtown\nCore",
                        "$65",
                        "4",
                        "5.0 (4)",
                        "Inactive",
                        "drone"
                ),
                listingRow(
                        "Yamaha APX600 Acoustic Guitar",
                        "LST-6544",
                        "Musical\nInstruments",
                        "North Suburbs",
                        "$20",
                        "0",
                        "New",
                        "Active",
                        "guitar"
                )
        );

        HBox footer = createFooter();

        wrapper.getChildren().addAll(
                toolbar,
                header,
                rows,
                footer
        );

        return wrapper;
    }

    private GridPane createTableHeader() {
        GridPane grid = new GridPane();
        grid.setMaxWidth(Double.MAX_VALUE);
        grid.setPadding(new Insets(9, 10, 9, 10));

        grid.setStyle(
                "-fx-background-color: #F0F1FA;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px 0 1px 0;"
        );

        /*
         * IMPORTANT:
         * These widths must match the widths used in listingRow().
         *
         * 35  = checkbox
         * 250 = item
         * 175 = details
         * 155 = category
         * 135 = location
         * 70  = price
         * 95  = stats
         * 85  = status
         * 50  = actions
         *
         * Total = 1050
         */
        double[] widths = {
                35, 250, 175, 155, 135, 70, 95, 85, 50
        };

        for (double width : widths) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(width);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            grid.getColumnConstraints().add(column);
        }

        addHeader(grid, "", 0);
        addHeader(grid, "ITEM", 1);
        addHeader(grid, "DETAILS", 2);
        addHeader(grid, "CATEGORY", 3);
        addHeader(grid, "LOCATION", 4);
        addHeader(grid, "PRICE", 5);
        addHeader(grid, "STATS", 6);
        addHeader(grid, "STATUS", 7);
        addHeader(grid, "ACTIONS", 8);

        return grid;
    }

    private void addHeader(GridPane grid, String text, int column) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #52607A;"
        );
        grid.add(label, column, 0);
    }

    // =========================================================
    // LISTING ROW
    // =========================================================

    private HBox listingRow(
            String name,
            String id,
            String category,
            String location,
            String price,
            String bookings,
            String rating,
            String status,
            String imageType
    ) {
        HBox row = new HBox();

        row.setMinHeight(78);
        row.setPrefHeight(78);
        row.setMaxHeight(78);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(8, 10, 8, 10));

        row.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1px 0;"
        );

        CheckBox checkbox = new CheckBox();
        checkbox.setPrefWidth(35);
        checkbox.setMinWidth(35);
        checkbox.setMaxWidth(35);
        checkbox.setAlignment(Pos.CENTER);

        VBox image = createProductImage(imageType);

        VBox details = new VBox(3);

        Label productName = new Label(truncate(name, 30));
        productName.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label listingId = new Label("ID: " + id);
        listingId.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        details.getChildren().addAll(productName, listingId);

        Label categoryLabel = new Label(category);
        categoryLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #52607A;"
        );

        Label locationLabel = new Label("⌖  " + location);
        locationLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #52607A;"
        );
        locationLabel.setWrapText(true);

        VBox priceBox = new VBox(0);

        Label priceLabel = new Label(price);
        priceLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label perDay = new Label("/day");
        perDay.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        priceBox.getChildren().addAll(priceLabel, perDay);

        VBox stats = new VBox(3);

        Label stars = new Label("★ " + rating);
        stars.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #B27A21;"
        );

        Label booking = new Label(bookings + " Bookings");
        booking.setStyle(
                "-fx-background-color: #EEF0F8;" +
                "-fx-text-fill: #52607A;" +
                "-fx-font-size: 9px;" +
                "-fx-padding: 5px;" +
                "-fx-background-radius: 4px;"
        );

        stats.getChildren().addAll(stars, booking);

        Label statusLabel = createStatus(status);

        Button actions = new Button("⋮");
        actions.setPrefWidth(50);
        actions.setMinWidth(50);
        actions.setMaxWidth(50);
        actions.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 18px;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-cursor: hand;"
        );

        /*
         * IMPORTANT:
         * The ITEM column contains both the image and product details.
         * Keeping them inside one 250px cell makes every row line up
         * exactly with the table header.
         */
        HBox itemCell = new HBox(10, image, details);
        itemCell.setAlignment(Pos.CENTER_LEFT);

        setWidth(checkbox, 35);
        setWidth(itemCell, 250);
        setWidth(categoryLabel, 175);
        setWidth(locationLabel, 135);
        setWidth(priceBox, 70);
        setWidth(stats, 95);
        setWidth(statusLabel, 85);
        setWidth(actions, 50);

        row.getChildren().addAll(
                checkbox,
                itemCell,
                categoryLabel,
                locationLabel,
                priceBox,
                stats,
                statusLabel,
                actions
        );

        return row;
    }

    // =========================================================
    // PRODUCT IMAGE
    // =========================================================

    private VBox createProductImage(String type) {
        VBox box = new VBox();
        box.setPrefWidth(65);
        box.setMinWidth(65);
        box.setMaxWidth(65);
        box.setPrefHeight(55);
        box.setAlignment(Pos.CENTER_LEFT);

        String emoji;

        switch (type) {
            case "sony":
                emoji = "📷";
                break;
            case "drill":
                emoji = "🔧";
                break;
            case "drone":
                emoji = "🚁";
                break;
            case "guitar":
                emoji = "🎸";
                break;
            default:
                emoji = "📦";
        }

        // Image occupies the left part of the ITEM cell.
        Label image = new Label(emoji);
        image.setPrefWidth(65);
        image.setMinWidth(65);
        image.setMaxWidth(65);
        image.setPrefHeight(55);
        image.setAlignment(Pos.CENTER);

        image.setStyle(
                "-fx-background-color: #E9ECF2;" +
                "-fx-font-size: 25px;" +
                "-fx-background-radius: 4px;"
        );

        box.getChildren().add(image);
        return box;
    }

    // =========================================================
    // STATUS
    // =========================================================

    private Label createStatus(String status) {
        Label label = new Label("● " + status);

        String background;
        String color;

        if (status.equals("Active")) {
            background = "#E4F3E7";
            color = "#357A45";
        } else if (status.equals("Rented")) {
            background = "#E1E8F8";
            color = "#4B6191";
        } else {
            background = "#E6E8F0";
            color = "#626B7F";
        }

        label.setStyle(
                "-fx-background-color: " + background + ";" +
                "-fx-text-fill: " + color + ";" +
                "-fx-font-size: 9px;" +
                "-fx-padding: 6px 8px;" +
                "-fx-background-radius: 15px;"
        );

        label.setAlignment(Pos.CENTER);
        return label;
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(12, 14, 12, 14));
        footer.setAlignment(Pos.CENTER_LEFT);

        Label rows = new Label("Rows per page:");
        rows.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Button ten = new Button("10  ▾");
        ten.setPrefHeight(25);
        ten.setStyle(
                "-fx-background-color: #EEF0F8;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 10px;" +
                "-fx-background-radius: 5px;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox pages = new HBox(15);
        pages.setAlignment(Pos.CENTER);

        Label left = new Label("‹");

        Label one = new Label("1");
        one.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 7px 10px;" +
                "-fx-background-radius: 5px;"
        );

        Label two = new Label("2");
        Label three = new Label("3");
        Label dots = new Label("...");
        Label five = new Label("5");
        Label right = new Label("›");

        pages.getChildren().addAll(
                left, one, two, three, dots, five, right
        );

        footer.getChildren().addAll(
                rows, ten, spacer, pages
        );

        return footer;
    }

    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private Button smallIconButton(FontAwesomeSolid icon) {
        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(12);
        iconView.setIconColor(Color.web(MUTED));

        Button button = new Button("", iconView);
        button.setPrefSize(26, 26);
        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 4px;" +
                "-fx-background-radius: 4px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private void setWidth(javafx.scene.Node node, double width) {
        node.prefWidth(width);
        node.minWidth(width);
        node.maxWidth(width);
    }

    private String truncate(String text, int length) {
        if (text.length() <= length) {
            return text;
        }

        return text.substring(0, length - 3) + "...";
    }
}