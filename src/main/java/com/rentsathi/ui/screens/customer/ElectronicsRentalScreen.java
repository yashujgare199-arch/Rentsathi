package com.rentsathi.ui.screens.customer;

import com.rentsathi.ui.screens.CustomerLoginScreen;

import com.rentsathi.dao.rental.RentalDAO;
import com.rentsathi.model.rental.RentalModel;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class ElectronicsRentalScreen {

        private static final String BLUE = "#3657C8";
        private static final String DARK = "#111827";
        private static final String TEXT = "#52688C";
        private static final String BG = "#F8F8FD";
        private static final String BORDER = "#D0D4E2";

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                VBox sidebar = createSidebar(stage);

                BorderPane mainArea = new BorderPane();

                HBox topBar = createTopBar();

                mainArea.setTop(topBar);

                VBox content = createContent(stage);

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
                                "RentSathi - Electronics");

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
                                new Insets(18, 10, 15, 10));

                sidebar.setStyle(
                                "-fx-background-color: #FAFAFF;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 0 0;");

                HBox logoBox = new HBox(8);

                logoBox.setAlignment(
                                Pos.CENTER_LEFT);

                StackPane logo = new StackPane();

                logo.setPrefSize(35, 35);
                logo.setMaxSize(35, 35);

                logo.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-background-radius: 6px;");

                Label logoText = new Label("RS");

                logoText.setTextFill(Color.WHITE);

                logoText.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                logo.getChildren().add(logoText);

                VBox brand = new VBox(0);

                Label brandName = new Label(
                                "RentSathi");

                brandName.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label tagline = new Label(
                                "Rental Marketplace");

                tagline.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                brand.getChildren().addAll(
                                brandName,
                                tagline);

                logoBox.getChildren().addAll(
                                logo,
                                brand);

                VBox navigation = new VBox(3);

                navigation.setPadding(
                                new Insets(20, 0, 0, 0));

                Button explore = createNavButton(
                                "◉",
                                "Explore",
                                true);

                Button dashboard = createNavButton(
                                "▦",
                                "Dashboard",
                                false);

                Button listings = createNavButton(
                                "▣",
                                "Rental Listings",
                                false);

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
                                explore,
                                dashboard,
                                listings,
                                history,
                                earnings,
                                support);

                explore.setOnAction(
                                event -> BrowseCategoriesScreen.show(stage));

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

                newListing.setPrefHeight(30);

                newListing.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
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

                button.setPrefHeight(35);

                HBox box = new HBox(12);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;");

                Label textLabel = new Label(text);

                textLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                (selected
                                                                ? "-fx-font-weight: bold;"
                                                                : ""));

                box.getChildren().addAll(
                                iconLabel,
                                textLabel);

                button.setGraphic(box);

                if (selected) {

                        button.setStyle(
                                        "-fx-background-color: #E0E8FF;" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 0 10px;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " + TEXT + ";" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 0 10px;");
                }

                return button;
        }

        private static Button createBottomButton(
                        String icon,
                        String text) {

                Button button = new Button();

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(34);

                HBox box = new HBox(12);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-size: 16px;");

                Label textLabel = new Label(text);

                textLabel.setStyle(
                                "-fx-font-size: 12px;" +
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

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label notification = new Label(
                                "♧");

                notification.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label help = new Label("?");

                help.setMinSize(
                                18,
                                18);

                help.setAlignment(
                                Pos.CENTER);

                help.setStyle(
                                "-fx-border-color: " + TEXT + ";" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label support = new Label(
                                "Support");

                support.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + DARK + ";");

                Button profile = new Button(
                                "●  Profile");

                profile.setPrefHeight(34);

                profile.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #C8CEDD;" +
                                                "-fx-border-radius: 18px;" +
                                                "-fx-background-radius: 18px;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-cursor: hand;");

                topBar.getChildren().addAll(
                                spacer,
                                notification,
                                help,
                                support,
                                profile);

                HBox.setMargin(
                                notification,
                                new Insets(0, 20, 0, 0));

                HBox.setMargin(
                                help,
                                new Insets(0, 20, 0, 0));

                HBox.setMargin(
                                support,
                                new Insets(0, 20, 0, 0));

                return topBar;
        }

        private static VBox createContent(Stage stage) {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                35,
                                                35,
                                                45,
                                                35));

                content.setStyle(
                                "-fx-background-color: " + BG + ";");

                HBox heading = new HBox();

                heading.setAlignment(
                                Pos.CENTER_LEFT);

                VBox titleBox = new VBox(5);

                Label title = new Label(
                                "Electronics");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label subtitle = new Label(
                                "Find electronics available for rent.");

                subtitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label count = new Label(
                                "24 items found");

                count.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-text-fill: " + DARK + ";");

                titleBox.getChildren().addAll(
                                title,
                                subtitle,
                                count);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label sortLabel = new Label(
                                "Sort by:");

                sortLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                ComboBox<String> sortBox = new ComboBox<>();

                sortBox.getItems().addAll(
                                "Relevance",
                                "Price: Low to High",
                                "Price: High to Low",
                                "Rating");

                sortBox.setValue(
                                "Relevance");

                sortBox.setPrefWidth(170);

                HBox sortContainer = new HBox(
                                10,
                                sortLabel,
                                sortBox);

                sortContainer.setAlignment(
                                Pos.CENTER);

                heading.getChildren().addAll(
                                titleBox,
                                spacer,
                                sortContainer);

                GridPane grid = new GridPane();

                grid.setHgap(18);
                grid.setVgap(22);

                String[][] products = {

                };
                List<RentalModel> rentals = RentalDAO.getRentalsByCategory(
                                "Electronics");

                System.out.println(
                                "Electronics rentals loaded: "
                                                + rentals.size());

                int cardIndex = 0;

                for (RentalModel rental : rentals) {

                        VBox card = createProductCard(
                                        stage,
                                        rental);

                        grid.add(
                                        card,
                                        cardIndex % 4,
                                        cardIndex / 4);
                        cardIndex++;
                }

                content.getChildren().addAll(
                                heading,
                                grid);

                return content;
        }

        private static VBox createProductCard(
                        Stage stage,
                        RentalModel rental

        ) {

                VBox card = new VBox();

                card.setPrefWidth(250);
                card.setMinWidth(220);
                card.setMaxWidth(290);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9px;" +
                                                "-fx-background-radius: 9px;");

                StackPane imageContainer = new StackPane();

                imageContainer.setPrefHeight(190);

                imageContainer.setStyle(
                                "-fx-background-color: #EEF1F7;" +
                                                "-fx-background-radius: 9px 9px 0 0;");

                Image image = new Image(
                                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32",
                                400,
                                250,
                                true,
                                true,
                                true);

                ImageView imageView = new ImageView(image);

                imageView.setFitWidth(250);
                imageView.setFitHeight(190);
                imageView.setPreserveRatio(true);

                imageContainer.getChildren().add(
                                imageView);

                Button favorite = new Button("♡");

                favorite.setPrefSize(
                                35,
                                35);

                favorite.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-border-color: #D8DCE8;" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-font-size: 21px;" +
                                                "-fx-text-fill: #75809A;" +
                                                "-fx-cursor: hand;");

                StackPane.setAlignment(
                                favorite,
                                Pos.TOP_RIGHT);

                StackPane.setMargin(
                                favorite,
                                new Insets(10));

                imageContainer.getChildren().add(
                                favorite);

                VBox details = new VBox(8);

                details.setPadding(
                                new Insets(13));

                Label nameLabel = new Label(
                                rental.getRentalName());

                nameLabel.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                HBox ratingBox = new HBox(5);

                ratingBox.setAlignment(
                                Pos.CENTER_LEFT);

                Label star = new Label("★");

                star.setStyle(
                                "-fx-text-fill: #D66A1F;" +
                                                "-fx-font-size: 16px;");

                Label ratingLabel = new Label("0.0");

                ratingLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                ratingBox.getChildren().addAll(
                                star,
                                ratingLabel);

                HBox nameRow = new HBox();

                nameRow.setAlignment(
                                Pos.CENTER_LEFT);

                Region nameSpacer = new Region();

                HBox.setHgrow(
                                nameSpacer,
                                Priority.ALWAYS);

                nameRow.getChildren().addAll(
                                nameLabel,
                                nameSpacer,
                                ratingBox);

                Label ownerLabel = new Label(
                                "♙  Owned by Owner");

                ownerLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label locationLabel = new Label(
                                "⌖  "
                                                + rental.getCity()
                                                + ", "
                                                + rental.getState());

                locationLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Region separator = new Region();

                separator.setPrefHeight(1);

                separator.setStyle(
                                "-fx-background-color: #D8DCE6;");

                HBox bottom = new HBox();

                bottom.setAlignment(
                                Pos.CENTER_LEFT);

                Label priceLabel = new Label(
                                "₹"
                                                + rental.getPricePerDay()
                                                + "/day");

                priceLabel.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Region bottomSpacer = new Region();

                HBox.setHgrow(
                                bottomSpacer,
                                Priority.ALWAYS);

                Button detailsButton = new Button("View Details");

                detailsButton.setOnAction(
                                event -> RentalDetailsScreen.show(
                                                stage,
                                                rental));

                detailsButton.setPrefHeight(38);

                detailsButton.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                bottom.getChildren().addAll(
                                priceLabel,
                                bottomSpacer,
                                detailsButton);

                details.getChildren().addAll(
                                nameRow,
                                ownerLabel,
                                locationLabel,
                                separator,
                                bottom);

                card.getChildren().addAll(
                                imageContainer,
                                details);

                return card;
        }
}