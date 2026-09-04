package com.rentsathi.ui.screens.customer;

import com.rentsathi.ui.screens.CustomerLoginScreen;
import com.rentsathi.model.rental.RentalModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.control.DatePicker;

public class RentalDetailsScreen {

        private static final String BLUE = "#3657C8";
        private static final String DARK = "#111827";
        private static final String TEXT = "#52688C";
        private static final String BG = "#F8F8FD";
        private static final String BORDER = "#C8CBD9";

        private static class DateSelection {
                DatePicker startDate;
                DatePicker endDate;
        }

        public static void show(
                        Stage stage,
                        RentalModel rental) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                VBox sidebar = createSidebar(stage);

                BorderPane mainArea = new BorderPane();

                HBox topBar = createTopBar();

                mainArea.setTop(topBar);

                VBox content = createContent(
                                stage,
                                rental);

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                mainArea.setCenter(scrollPane);

                root.setLeft(sidebar);
                root.setCenter(mainArea);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                scene.setFill(
                                Color.web(BG));

                stage.setTitle(
                                "RentSathi - Rental Details");

                stage.setScene(scene);

                stage.setWidth(1500);
                stage.setHeight(830);

                stage.setMinWidth(1000);
                stage.setMinHeight(650);

                stage.show();
        }

        private static VBox createSidebar(Stage stage) {

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(250);

                sidebar.setPadding(
                                new Insets(18, 10, 15, 10));

                sidebar.setStyle(
                                "-fx-background-color: #FAFAFF;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 0 0;");

                HBox logoBox = new HBox(10);

                logoBox.setAlignment(
                                Pos.CENTER_LEFT);

                StackPane logo = new StackPane();

                logo.setPrefSize(35, 35);
                logo.setMaxSize(35, 35);

                logo.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-background-radius: 6px;");

                Label logoText = new Label("RS");

                logoText.setTextFill(
                                Color.WHITE);

                logoText.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                logo.getChildren().add(
                                logoText);

                VBox brand = new VBox(0);

                Label brandName = new Label("RentSathi");

                brandName.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label tagline = new Label(
                                "Rent Smart. Live Easy.");

                tagline.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                brand.getChildren().addAll(
                                brandName,
                                tagline);

                logoBox.getChildren().addAll(
                                logo,
                                brand);

                VBox navigation = new VBox(5);

                navigation.setPadding(
                                new Insets(35, 0, 0, 0));

                Button dashboard = createNavButton(
                                "▦",
                                "Dashboard",
                                false);

                Button listings = createNavButton(
                                "▣",
                                "Rental Listings",
                                true);

                Button history = createNavButton(
                                "▤",
                                "Order History",
                                false);

                Button earnings = createNavButton(
                                "▱",
                                "Earnings",
                                false);

                Button support = createNavButton(
                                "♧",
                                "Support",
                                false);

                navigation.getChildren().addAll(
                                dashboard,
                                listings,
                                history,
                                earnings,
                                support);

                dashboard.setOnAction(
                                event -> new DashboardScreen(stage).show());

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                Button newListing = new Button(
                                "New Listing");

                newListing.setMaxWidth(
                                Double.MAX_VALUE);

                newListing.setPrefHeight(40);

                newListing.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                Region line = new Region();

                line.setPrefHeight(1);

                line.setStyle(
                                "-fx-background-color: #D9DCE7;");

                Button settings = createBottomButton(
                                "⚙",
                                "Settings");

                Button logout = createBottomButton(
                                "↪",
                                "Logout");

                logout.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #B3261E;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 7px;");

                logout.setOnAction(
                                event -> CustomerLoginScreen.show(stage));

                sidebar.getChildren().addAll(
                                logoBox,
                                navigation,
                                spacer,
                                newListing,
                                line,
                                settings,
                                logout);

                return sidebar;
        }

        private static Button createNavButton(
                        String icon,
                        String text,
                        boolean selected) {

                Button button = new Button();

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(40);

                HBox box = new HBox(12);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;");

                Label textLabel = new Label(text);

                textLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;");

                box.getChildren().addAll(
                                iconLabel,
                                textLabel);

                button.setGraphic(box);

                if (selected) {

                        button.setStyle(
                                        "-fx-background-color: #DCE7FF;" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 0 12px;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " + TEXT + ";" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 0 12px;");
                }

                return button;
        }

        private static Button createBottomButton(
                        String icon,
                        String text) {

                Button button = new Button();

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(40);

                HBox box = new HBox(12);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-size: 17px;");

                Label textLabel = new Label(text);

                textLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                box.getChildren().addAll(
                                iconLabel,
                                textLabel);

                button.setGraphic(box);

                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 5px;");

                return button;
        }

        private static HBox createTopBar() {

                HBox topBar = new HBox();

                topBar.setPrefHeight(45);

                topBar.setPadding(
                                new Insets(
                                                0,
                                                18,
                                                0,
                                                20));

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                topBar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Label brand = new Label("RentSathi");

                brand.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label notification = new Label("♧");

                notification.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label help = new Label("?");

                help.setMinSize(19, 19);

                help.setAlignment(
                                Pos.CENTER);

                help.setStyle(
                                "-fx-border-color: " + DARK + ";" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;");

                StackPane profile = new StackPane();

                profile.setPrefSize(
                                34,
                                34);

                profile.setMinSize(
                                34,
                                34);

                profile.setMaxSize(
                                34,
                                34);

                Circle profileCircle = new Circle(
                                17,
                                Color.web("#DCE2EF"));

                Label profileText = new Label("A");

                profileText.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                profile.getChildren().addAll(
                                profileCircle,
                                profileText);

                topBar.getChildren().addAll(
                                brand,
                                spacer,
                                notification,
                                help,
                                profile);

                HBox.setMargin(
                                notification,
                                new Insets(0, 20, 0, 0));

                HBox.setMargin(
                                help,
                                new Insets(0, 20, 0, 0));

                return topBar;
        }

        private static VBox createContent(
                        Stage stage,
                        RentalModel rental) {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                40,
                                                30,
                                                45,
                                                30));

                content.setStyle(
                                "-fx-background-color: " + BG + ";");

                HBox layout = new HBox(30);

                VBox left = createImageSection();

                VBox right = createDetailsSection(
                                stage,
                                rental);

                HBox.setHgrow(
                                left,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                right,
                                Priority.ALWAYS);

                left.setPrefWidth(600);
                right.setPrefWidth(450);

                layout.getChildren().addAll(
                                left,
                                right);

                content.getChildren().add(
                                layout);

                return content;
        }

        private static VBox createImageSection() {

                VBox box = new VBox(16);

                ImageView mainImageView = new ImageView();

                mainImageView.setFitWidth(600);
                mainImageView.setFitHeight(500);
                mainImageView.setPreserveRatio(true);

                String[] images = {

                                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=1000",

                                "https://images.unsplash.com/photo-1502920917128-1aa500764cbd?w=1000",

                                "https://images.unsplash.com/photo-1510127034890-ba27508e9f1c?w=1000",

                                "https://images.unsplash.com/photo-1495707902641-75cac588d2e9?w=1000"
                };

                Image firstImage = new Image(
                                images[0],
                                800,
                                600,
                                true,
                                true,
                                true);

                mainImageView.setImage(
                                firstImage);

                StackPane imageFrame = new StackPane();

                imageFrame.setPrefSize(
                                600,
                                500);

                imageFrame.setStyle(
                                "-fx-background-color: #E8E9EE;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                imageFrame.getChildren().add(
                                mainImageView);

                HBox thumbnails = new HBox(16);

                thumbnails.setAlignment(
                                Pos.CENTER_LEFT);

                for (int i = 0; i < images.length; i++) {

                        final int index = i;

                        Image image = new Image(
                                        images[i],
                                        160,
                                        120,
                                        true,
                                        true,
                                        true);

                        ImageView imageView = new ImageView(image);

                        imageView.setFitWidth(120);
                        imageView.setFitHeight(115);
                        imageView.setPreserveRatio(true);

                        StackPane thumbnail = new StackPane();

                        thumbnail.setPrefSize(
                                        120,
                                        115);

                        thumbnail.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " +
                                                        (i == 0 ? BLUE : BORDER) +
                                                        ";" +
                                                        "-fx-border-width: " +
                                                        (i == 0 ? "2px" : "1px") +
                                                        ";" +
                                                        "-fx-border-radius: 8px;" +
                                                        "-fx-background-radius: 8px;" +
                                                        "-fx-cursor: hand;");

                        thumbnail.getChildren().add(
                                        imageView);

                        thumbnail.setOnMouseClicked(
                                        event -> {

                                                Image selectedImage = new Image(
                                                                images[index],
                                                                1000,
                                                                800,
                                                                true,
                                                                true,
                                                                true);

                                                mainImageView.setImage(
                                                                selectedImage);

                                                for (javafx.scene.Node node : thumbnails.getChildren()) {

                                                        node.setStyle(
                                                                        "-fx-background-color: white;" +
                                                                                        "-fx-border-color: " + BORDER
                                                                                        + ";" +
                                                                                        "-fx-border-width: 1px;" +
                                                                                        "-fx-border-radius: 8px;" +
                                                                                        "-fx-background-radius: 8px;" +
                                                                                        "-fx-cursor: hand;");
                                                }

                                                thumbnail.setStyle(
                                                                "-fx-background-color: white;" +
                                                                                "-fx-border-color: " + BLUE + ";" +
                                                                                "-fx-border-width: 2px;" +
                                                                                "-fx-border-radius: 8px;" +
                                                                                "-fx-background-radius: 8px;" +
                                                                                "-fx-cursor: hand;");
                                        });

                        thumbnails.getChildren().add(
                                        thumbnail);
                }

                box.getChildren().addAll(
                                imageFrame,
                                thumbnails);

                return box;
        }

        private static VBox createDetailsSection(
                        Stage stage,
                        RentalModel rental) {

                VBox card = new VBox(14);

                card.setPadding(
                                new Insets(22));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                HBox categoryRow = new HBox(8);

                Label subcategory = new Label(
                                rental.getSubcategory());

                subcategory.setStyle(
                                "-fx-background-color: #EEF1F7;" +
                                                "-fx-text-fill: " + TEXT + ";" +
                                                "-fx-padding: 6px 10px;" +
                                                "-fx-background-radius: 4px;" +
                                                "-fx-font-size: 12px;");

                Label category = new Label(
                                rental.getCategory());

                category.setStyle(
                                "-fx-background-color: #DCE7FF;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-padding: 6px 10px;" +
                                                "-fx-background-radius: 4px;" +
                                                "-fx-font-size: 12px;");

                Label location = new Label(
                                "⌖  "
                                                + rental.getCity()
                                                + ", "
                                                + rental.getState());

                location.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                categoryRow.getChildren().addAll(
                                category,
                                subcategory,
                                location);

                Label title = new Label(
                                rental.getRentalName());

                title.setWrapText(true);

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                HBox ratingRow = new HBox(8);

                Label rating = new Label(
                                "★ 0.0");

                rating.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label reviews = new Label(
                                "(No reviews yet)");

                reviews.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-underline: true;");

                Label dot = new Label("•");

                Label owner = new Label(
                                "●  Owner ID: "
                                                + rental.getOwnerId());

                owner.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                ratingRow.getChildren().addAll(
                                rating,
                                reviews,
                                dot,
                                owner);

                Separator separator = new Separator();

                Label price = new Label(
                                "₹"
                                                + rental.getPricePerDay()
                                                + " /day");

                price.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label weekly = new Label(
                                "₹"
                                                + rental.getPricePerWeek()
                                                + " / week");

                weekly.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label deposit = new Label(
                                "Security Deposit: ₹"
                                                + rental.getSecurityDeposit());
                deposit.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                HBox pricing = new HBox(
                                20,
                                weekly,
                                deposit);
                Label rulesTitle = new Label(
                                "Rental Rules");

                rulesTitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label rules = new Label(
                                rental.getRentalRules());
                Label cancellationTitle = new Label(
                                "Cancellation Policy");

                cancellationTitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label cancellation = new Label(
                                rental.getCancellationPolicy());

                cancellation.setWrapText(true);

                cancellation.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                rules.setWrapText(true);

                rules.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                VBox bookingBox = new VBox(14);

                bookingBox.setPadding(
                                new Insets(16));

                bookingBox.setStyle(
                                "-fx-background-color: #F3F5FC;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;");

                ToggleGroup group = new ToggleGroup();

                ToggleButton daily = new ToggleButton("Daily");

                ToggleButton weeklyButton = new ToggleButton("Weekly");

                daily.setToggleGroup(group);
                weeklyButton.setToggleGroup(group);

                daily.setSelected(true);

                daily.setMaxWidth(
                                Double.MAX_VALUE);

                weeklyButton.setMaxWidth(
                                Double.MAX_VALUE);

                daily.setPrefHeight(40);
                weeklyButton.setPrefHeight(40);

                Runnable updatePeriodStyle = () -> {

                        if (daily.isSelected()) {

                                daily.setStyle(
                                                "-fx-background-color: " + BLUE + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-background-radius: 5px;" +
                                                                "-fx-cursor: hand;");

                                weeklyButton.setStyle(
                                                "-fx-background-color: white;" +
                                                                "-fx-text-fill: " + DARK + ";" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-background-radius: 5px;" +
                                                                "-fx-cursor: hand;");

                        } else {

                                weeklyButton.setStyle(
                                                "-fx-background-color: " + BLUE + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-background-radius: 5px;" +
                                                                "-fx-cursor: hand;");

                                daily.setStyle(
                                                "-fx-background-color: white;" +
                                                                "-fx-text-fill: " + DARK + ";" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-background-radius: 5px;" +
                                                                "-fx-cursor: hand;");
                        }
                };

                updatePeriodStyle.run();

                daily.setOnAction(
                                event -> {

                                        daily.setSelected(true);
                                        updatePeriodStyle.run();

                                        System.out.println(
                                                        "Rental period: DAILY");
                                });

                weeklyButton.setOnAction(
                                event -> {

                                        weeklyButton.setSelected(true);
                                        updatePeriodStyle.run();

                                        System.out.println(
                                                        "Rental period: WEEKLY");
                                });

                HBox period = new HBox(
                                daily,
                                weeklyButton);

                HBox.setHgrow(
                                daily,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                weeklyButton,
                                Priority.ALWAYS);

                DateSelection selection = new DateSelection();

                VBox start = createDateBox(
                                "START DATE",
                                rental.getAvailableFrom(),
                                selection);

                VBox end = createDateBox(
                                "END DATE",
                                rental.getAvailableUntil(),
                                selection);

                HBox dates = new HBox(
                                10,
                                start,
                                end);
                Label rentalDuration = new Label(
                                "Rental period: "
                                                + rental.getMinDays()
                                                + " - "
                                                + rental.getMaxDays()
                                                + " days");

                rentalDuration.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                HBox.setHgrow(
                                start,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                end,
                                Priority.ALWAYS);

                Separator bookingSeparator = new Separator();

                Label totalText = new Label(
                                "Total (3 days)");

                totalText.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label total = new Label(
                                "₹135");

                total.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                HBox totalRow = new HBox(
                                totalText,
                                new Region(),
                                total);

                HBox.setHgrow(
                                totalRow.getChildren().get(1),
                                Priority.ALWAYS);

                bookingBox.getChildren().addAll(
                                period,
                                rentalDuration,
                                dates,
                                bookingSeparator,
                                totalRow);

                Button bookNow = new Button(
                                "Book Now");
                bookNow.setOnAction(
                                event -> BookRentalScreen.show(
                                                stage,
                                                rental,
                                                selection.startDate.getValue(),
                                                selection.endDate.getValue(),
                                                "Pickup"));

                bookNow.setMaxWidth(
                                Double.MAX_VALUE);

                bookNow.setPrefHeight(45);

                bookNow.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-cursor: hand;");

                Button wishlist = new Button(
                                "♡  Add to Wishlist");

                wishlist.setMaxWidth(
                                Double.MAX_VALUE);

                wishlist.setPrefHeight(45);

                wishlist.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BLUE + ";" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-cursor: hand;");

                Label availability = new Label(
                                "⚡  Available Now • Instant Booking");

                availability.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                availability.setAlignment(
                                Pos.CENTER);

                availability.setMaxWidth(
                                Double.MAX_VALUE);

                card.getChildren().addAll(
                                categoryRow,
                                title,
                                ratingRow,
                                separator,
                                price,
                                pricing,
                                rulesTitle,
                                rules,
                                cancellationTitle,
                                cancellation,
                                bookingBox,
                                bookNow,
                                wishlist,
                                availability);

                return card;
        }

        private static VBox createDateBox(
                        String title,
                        String date,
                        DateSelection selection) {

                VBox box = new VBox(6);

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                DatePicker datePicker = new DatePicker();

                if (title.equals("START DATE")) {
                        selection.startDate = datePicker;
                } else if (title.equals("END DATE")) {
                        selection.endDate = datePicker;
                }

                datePicker.setPrefHeight(38);

                datePicker.setMaxWidth(
                                Double.MAX_VALUE);

                datePicker.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-font-size: 12px;");

                try {
                        datePicker.setValue(
                                        java.time.LocalDate.parse(
                                                        date,
                                                        java.time.format.DateTimeFormatter.ofPattern(
                                                                        "MMM d, yyyy")));
                } catch (Exception ignored) {
                }

                box.getChildren().addAll(
                                titleLabel,
                                datePicker);

                return box;
        }
}