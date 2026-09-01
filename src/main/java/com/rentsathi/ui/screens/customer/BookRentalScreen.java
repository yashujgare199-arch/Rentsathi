package com.rentsathi.ui.screens.customer;

import com.rentsathi.ui.screens.CustomerLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.control.ToggleButton;
import com.rentsathi.model.rental.RentalModel;
import java.time.LocalDate;

public class BookRentalScreen {

        private static final String BLUE = "#3657C8";
        private static final String DARK = "#111827";
        private static final String TEXT = "#52688C";
        private static final String BG = "#F8F8FD";
        private static final String BORDER = "#C8CBD9";

        private static String selectedFulfillmentMethod = "Pickup";

        public static void show(
                        Stage stage,
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate,
                        String fulfillmentMethod) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                root.setLeft(createSidebar(stage));

                BorderPane mainArea = new BorderPane();

                mainArea.setTop(createTopBar());

                VBox content = createContent(
                                stage,
                                rental,
                                startDate,
                                endDate);

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                mainArea.setCenter(scrollPane);

                root.setCenter(mainArea);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                scene.setFill(
                                Color.web(BG));

                stage.setTitle(
                                "RentSathi - Book Rental");

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

                Label brand = new Label("RentSathi");

                brand.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label tagline = new Label(
                                "Rental Marketplace");

                tagline.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                VBox brandBox = new VBox(2);

                brandBox.getChildren().addAll(
                                brand,
                                tagline);

                Button newListing = new Button(
                                "+  New Listing");

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

                VBox.setMargin(
                                newListing,
                                new Insets(25, 0, 15, 0));

                VBox navigation = new VBox(4);

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

                Button settings = createBottomButton(
                                "⚙",
                                "Settings");

                Button logout = createBottomButton(
                                "↪",
                                "Logout");

                logout.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #B3261E;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 7px;");

                logout.setOnAction(
                                event -> CustomerLoginScreen.show(stage));

                sidebar.getChildren().addAll(
                                brandBox,
                                newListing,
                                navigation,
                                spacer,
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

                button.setPrefHeight(38);

                HBox box = new HBox(12);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-size: 17px;");

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

                button.setPrefHeight(38);

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
                                new Insets(0, 18, 0, 20));

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                topBar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                HBox search = new HBox(8);

                search.setPrefWidth(210);
                search.setPrefHeight(32);

                search.setAlignment(
                                Pos.CENTER_LEFT);

                search.setPadding(
                                new Insets(0, 10, 0, 10));

                search.setStyle(
                                "-fx-background-color: #F8F8FD;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;");

                Label searchIcon = new Label("⌕");

                searchIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label searchText = new Label("Search...");

                searchText.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #7B8498;");

                search.getChildren().addAll(
                                searchIcon,
                                searchText);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label notification = new Label("♧");

                notification.setStyle(
                                "-fx-font-size: 20px;");

                Label help = new Label("?");

                help.setMinSize(19, 19);

                help.setAlignment(
                                Pos.CENTER);

                help.setStyle(
                                "-fx-border-color: " + DARK + ";" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;");

                Circle profileCircle = new Circle(
                                16,
                                Color.web("#DCE2EF"));

                Label profileText = new Label("A");

                profileText.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                StackPane profile = new StackPane(
                                profileCircle,
                                profileText);

                profile.setMinSize(
                                32,
                                32);

                profile.setMaxSize(
                                32,
                                32);

                topBar.getChildren().addAll(
                                search,
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
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate) {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                35,
                                                30,
                                                45,
                                                30));

                Label back = new Label(
                                "←  Back to Listing");

                back.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-cursor: hand;");

                back.setOnMouseClicked(
                                event -> ElectronicsRentalScreen.show(stage));

                Label title = new Label(
                                "Book Rental");

                title.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                HBox main = new HBox(18);

                VBox productCard = createProductCard(rental);

                VBox details = createRentalDetails(
                                stage,
                                rental,
                                startDate,
                                endDate);

                HBox.setHgrow(
                                productCard,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                details,
                                Priority.ALWAYS);

                main.getChildren().addAll(
                                productCard,
                                details);

                content.getChildren().addAll(
                                back,
                                title,
                                main);

                return content;
        }

        private static VBox createProductCard(
                        RentalModel rental) {

                VBox card = new VBox(10);

                card.setPadding(
                                new Insets(14));

                card.setPrefWidth(450);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;");

                Image image = new Image(
                                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=800",
                                500,
                                350,
                                true,
                                true,
                                true);

                javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(
                                image);

                imageView.setFitWidth(420);
                imageView.setFitHeight(210);
                imageView.setPreserveRatio(true);

                StackPane imageBox = new StackPane();

                imageBox.setPrefHeight(210);

                imageBox.setStyle(
                                "-fx-background-color: #F4F4F4;" +
                                                "-fx-background-radius: 5px;");

                imageBox.getChildren().add(
                                imageView);

                HBox titleRow = new HBox(8);

                Label name = new Label(
                                rental.getRentalName());

                name.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label category = new Label(
                                rental.getCategory());

                category.setStyle(
                                "-fx-background-color: #DCE7FF;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-padding: 5px 8px;" +
                                                "-fx-background-radius: 10px;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                titleRow.getChildren().addAll(
                                name,
                                spacer,
                                category);

                HBox rating = new HBox(7);

                Label star = new Label("★  4.9");

                star.setStyle(
                                "-fx-text-fill: #B05D17;" +
                                                "-fx-font-size: 12px;");

                Label reviews = new Label(
                                "(124 reviews)");

                reviews.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + BLUE + ";");

                rating.getChildren().addAll(
                                star,
                                reviews);

                Separator separator = new Separator();

                HBox owner = new HBox(8);

                Circle ownerCircle = new Circle(
                                15,
                                Color.web("#DCE2EF"));

                Label ownerName = new Label(
                                "Owner ID: "
                                                + rental.getOwnerId()
                                                + "\nOwner");

                ownerName.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + DARK + ";");

                owner.getChildren().addAll(
                                ownerCircle,
                                ownerName);

                Label location = new Label(
                                "⌖  "
                                                + rental.getCity()
                                                + ", "
                                                + rental.getState());

                location.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                card.getChildren().addAll(
                                imageBox,
                                titleRow,
                                rating,
                                separator,
                                owner,
                                location);

                return card;
        }

        private static VBox createRentalDetails(
                        Stage stage,
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate) {

                VBox wrapper = new VBox(12);

                VBox details = new VBox(14);

                details.setPadding(
                                new Insets(18));

                details.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;");

                Label heading = new Label(
                                "Rental Details");

                heading.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                DatePicker startPicker = new DatePicker(
                                startDate);

                DatePicker endPicker = new DatePicker(
                                endDate);

                VBox startBox = createDateField(
                                "Start Date",
                                startPicker);

                VBox endBox = createDateField(
                                "End Date",
                                endPicker);

                HBox dates = new HBox(10);

                dates.getChildren().addAll(
                                startBox,
                                endBox);

                HBox.setHgrow(
                                startBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                endBox,
                                Priority.ALWAYS);

                HBox availability = new HBox(8);

                Label available = new Label(
                                "✓ Available");

                available.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + BLUE + ";");

                availability.getChildren().add(
                                available);

                long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(
                                startDate,
                                endDate);

                HBox durationQuantity = new HBox(10);

                VBox duration = createTextField(
                                "Duration",
                                rentalDays + " days");

                VBox quantityBox = new VBox(5);

                Label quantityLabel = new Label("Quantity");

                quantityLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + DARK + ";");

                TextField quantityField = new TextField("1");

                quantityField.setPrefHeight(35);

                quantityField.setStyle(
                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                quantityBox.getChildren().addAll(
                                quantityLabel,
                                quantityField);

                durationQuantity.getChildren().addAll(
                                duration,
                                quantityBox);

                HBox.setHgrow(
                                duration,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                quantityBox,
                                Priority.ALWAYS);

                Label fulfillment = new Label(
                                "Fulfillment Method");

                fulfillment.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + DARK + ";");

                ToggleButton pickup = new ToggleButton(
                                "Pickup");

                ToggleButton deliveryOption = new ToggleButton(
                                "Delivery");

                pickup.setPrefHeight(34);
                deliveryOption.setPrefHeight(34);

                pickup.setMaxWidth(
                                Double.MAX_VALUE);

                deliveryOption.setMaxWidth(
                                Double.MAX_VALUE);

                javafx.scene.control.ToggleGroup fulfillmentGroup = new javafx.scene.control.ToggleGroup();

                pickup.setToggleGroup(
                                fulfillmentGroup);

                deliveryOption.setToggleGroup(
                                fulfillmentGroup);

                pickup.setSelected(true);

                HBox fulfillmentBox = new HBox(
                                pickup,
                                deliveryOption);

                HBox.setHgrow(
                                pickup,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                deliveryOption,
                                Priority.ALWAYS);

                styleFulfillmentButtons(
                                pickup,
                                deliveryOption);

                pickup.setOnAction(event -> {

                        selectedFulfillmentMethod = "Pickup";

                        styleFulfillmentButtons(
                                        pickup,
                                        deliveryOption);
                });

                deliveryOption.setOnAction(event -> {

                        System.out.println(
                                        "DELIVERY BUTTON CLICKED");

                        selectedFulfillmentMethod = "Delivery";

                        System.out.println(
                                        "SELECTED FULFILLMENT = ["
                                                        + selectedFulfillmentMethod
                                                        + "]");

                        styleFulfillmentButtons(
                                        pickup,
                                        deliveryOption);
                });

                Label deliveryLabel = new Label(
                                "Delivery Address");

                deliveryLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + DARK + ";");

                TextArea deliveryAddress = new TextArea();

                deliveryAddress.setPromptText(
                                "Enter delivery address...");

                deliveryAddress.setPrefRowCount(2);

                deliveryAddress.setWrapText(true);

                deliveryAddress.setStyle(
                                "-fx-border-color: #D84A4A;" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                Label error = new Label(
                                "ⓘ Delivery address is required.");

                error.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #D32626;");

                Label instructionsLabel = new Label(
                                "Special Instructions (Optional)");

                instructionsLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + DARK + ";");

                TextArea instructions = new TextArea();

                instructions.setPromptText(
                                "Any specific details for the owner?");

                instructions.setPrefRowCount(2);

                instructions.setWrapText(true);

                instructions.setStyle(
                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                details.getChildren().addAll(
                                heading,
                                dates,
                                availability,
                                durationQuantity,
                                fulfillment,
                                fulfillmentBox,
                                deliveryLabel,
                                deliveryAddress,
                                error,
                                instructionsLabel,
                                instructions);

                VBox priceSummary = createPriceSummary(
                                stage,
                                rental,
                                startDate,
                                endDate);

                wrapper.getChildren().addAll(
                                details,
                                priceSummary);

                return wrapper;
        }

        private static VBox createDateField(
                        String label,
                        DatePicker picker) {

                VBox box = new VBox(5);

                Label title = new Label(label);

                title.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + DARK + ";");

                picker.setMaxWidth(
                                Double.MAX_VALUE);

                picker.setPrefHeight(38);

                picker.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BLUE + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                box.getChildren().addAll(
                                title,
                                picker);

                return box;
        }

        private static VBox createTextField(
                        String label,
                        String value) {

                VBox box = new VBox(5);

                Label title = new Label(label);

                title.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + DARK + ";");

                TextField field = new TextField(value);

                field.setPrefHeight(35);

                field.setStyle(
                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                box.getChildren().addAll(
                                title,
                                field);

                return box;
        }

        private static void styleFulfillmentButtons(
                        ToggleButton pickup,
                        ToggleButton delivery) {

                if (pickup.isSelected()) {

                        pickup.setStyle(
                                        "-fx-background-color: #E0E3ED;" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-size: 12px;");

                        delivery.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-size: 12px;");

                } else {

                        delivery.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " + BLUE + ";" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-size: 12px;");

                        pickup.setStyle(
                                        "-fx-background-color: #E0E3ED;" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-size: 12px;");
                }
        }

        private static VBox createPriceSummary(
                        Stage stage,
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate) {

                VBox box = new VBox(12);

                long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(
                                startDate,
                                endDate);

                double rentalAmount = rental.getPricePerDay() * rentalDays;

                box.setPadding(
                                new Insets(18));

                box.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;");

                Label heading = new Label(
                                "Price Summary");

                heading.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                HBox rentalRow = priceRow(
                                "Rental price (₹"
                                                + rental.getPricePerDay()
                                                + "/day × "
                                                + rentalDays
                                                + ")",
                                "₹"
                                                + rentalAmount);

                HBox delivery = priceRow(
                                "Delivery fee",
                                "₹15.00");

                HBox service = priceRow(
                                "Service fee",
                                "₹10.50");

                Separator separator = new Separator();

                HBox deposit = priceRow(
                                "Security deposit (Refundable)",
                                "₹" + rental.getSecurityDeposit());

                Separator separator2 = new Separator();

                Label totalLabel = new Label("Total");

                totalLabel.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                double deliveryFee = 15.00;
                double serviceFee = 10.50;

                double totalAmount = rentalAmount
                                + deliveryFee
                                + serviceFee
                                + rental.getSecurityDeposit();

                Label total = new Label(
                                "₹" + totalAmount);

                total.setStyle(
                                "-fx-font-size: 26px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                HBox totalRow = new HBox(
                                totalLabel,
                                new Region(),
                                total);

                HBox.setHgrow(
                                totalRow.getChildren().get(1),
                                Priority.ALWAYS);

                Button checkout = new Button(
                                "Continue to Checkout");

                checkout.setMaxWidth(
                                Double.MAX_VALUE);

                checkout.setPrefHeight(40);

                checkout.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-cursor: hand;");

                checkout.setOnAction(
                                event -> {

                                        String fulfillmentMethod = selectedFulfillmentMethod;

                                        CheckoutScreen.show(
                                                        stage,
                                                        rental,
                                                        startDate,
                                                        endDate,
                                                        fulfillmentMethod);
                                });
                box.getChildren().addAll(
                                heading,
                                rentalRow,
                                delivery,
                                service,
                                separator,
                                deposit,
                                separator2,
                                totalRow,
                                checkout);

                return box;
        }

        private static HBox priceRow(
                        String leftText,
                        String rightText) {

                Label left = new Label(leftText);

                left.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label right = new Label(rightText);

                right.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + DARK + ";");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                HBox row = new HBox(
                                left,
                                spacer,
                                right);

                return row;
        }
}