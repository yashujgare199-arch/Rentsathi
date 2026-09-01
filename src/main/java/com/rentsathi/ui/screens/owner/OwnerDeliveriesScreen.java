package com.rentsathi.ui.screens.owner;

import com.google.gson.JsonObject;
import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.model.rental.RentalRequest;
import com.rentsathi.model.rental.RentalRequestStore;
import com.rentsathi.ui.screens.OwnerLoginScreen;
import javafx.scene.control.Alert;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class OwnerDeliveriesScreen {

        private static final String BG = "#F6F7FB";
        private static final String BLUE = "#3659C9";
        private static final String TEXT = "#111827";
        private static final String MUTED = "#64748B";
        private static final String BORDER = "#D5DCE8";
        private static final String GREEN = "#218739";

        // ============================================================
        // SHOW
        // ============================================================

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                root.setLeft(
                                createSidebar(stage));

                BorderPane mainArea = new BorderPane();

                mainArea.setTop(
                                createTopBar());

                VBox content = createContent();

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                mainArea.setCenter(
                                scrollPane);

                root.setCenter(
                                mainArea);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                stage.setTitle(
                                "RentSathi - Owner Deliveries");

                stage.setScene(scene);

                stage.setWidth(1500);
                stage.setHeight(830);

                stage.setMinWidth(1100);
                stage.setMinHeight(700);

                stage.centerOnScreen();

                stage.show();
        }

        // ============================================================
        // SIDEBAR
        // ============================================================

        private static VBox createSidebar(
                        Stage stage) {

                VBox sidebar = new VBox(10);

                sidebar.setPrefWidth(290);
                sidebar.setMinWidth(290);
                sidebar.setMaxWidth(290);

                sidebar.setPadding(
                                new Insets(
                                                25,
                                                16,
                                                25,
                                                16));

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 0 0;");

                Label brand = new Label(
                                "RentSathi");

                brand.setStyle(
                                "-fx-font-size: 24px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label portal = new Label(
                                "Owner Portal");

                portal.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                VBox brandBox = new VBox(3);

                brandBox.getChildren().addAll(
                                brand,
                                portal);

                Button postListing = new Button(
                                "+  Post New Listing");

                postListing.setMaxWidth(
                                Double.MAX_VALUE);

                postListing.setPrefHeight(48);

                postListing.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                Button dashboard = navigationButton(
                                "▦",
                                "Dashboard");

                Button bookings = navigationButton(
                                "▣",
                                "Bookings");

                Button deliveries = navigationButton(
                                "▱",
                                "Owner Deliveries");

                Button analytics = navigationButton(
                                "▤",
                                "Analytics");

                deliveries.setStyle(
                                navigationSelectedStyle());

                dashboard.setOnAction(
                                event -> OwnerDashboardScreen.show(stage));

                bookings.setOnAction(
                                event -> RentalRequestsScreen.show(stage));

                deliveries.setOnAction(
                                event -> OwnerDeliveriesScreen.show(stage));

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                Button settings = navigationButton(
                                "⚙",
                                "Settings");

                Button logout = navigationButton(
                                "↪",
                                "Logout");

                logout.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #D32626;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 11px 14px;" +
                                                "-fx-cursor: hand;");

                logout.setOnAction(
                                event -> OwnerLoginScreen.show(stage));

                sidebar.getChildren().addAll(
                                brandBox,
                                postListing,
                                dashboard,
                                bookings,
                                deliveries,
                                analytics,
                                spacer,
                                settings,
                                logout);

                return sidebar;
        }

        // ============================================================
        // TOP BAR
        // ============================================================

        private static HBox createTopBar() {

                HBox topBar = new HBox();

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                topBar.setPadding(
                                new Insets(
                                                16,
                                                28,
                                                16,
                                                28));

                topBar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Label search = new Label(
                                "Search deliveries...");

                search.setStyle(
                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 12px 18px;" +
                                                "-fx-text-fill: #A0A9B8;" +
                                                "-fx-min-width: 370px;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label help = new Label("?");
                help.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BLUE + ";" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 5px 9px;");

                topBar.getChildren().addAll(
                                search,
                                spacer,
                                help);

                return topBar;
        }

        // ============================================================
        // CONTENT
        // ============================================================

        private static VBox createContent() {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                28,
                                                30,
                                                35,
                                                30));

                Label title = new Label(
                                "Owner Deliveries");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label subtitle = new Label(
                                "Manage rental fulfillment and delivery requests.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                content.getChildren().addAll(
                                title,
                                subtitle);

                // --------------------------------------------------------
                // LOAD OUT-FOR-DELIVERY REQUESTS
                // --------------------------------------------------------

                List<RentalRequest> deliveryRequests = new java.util.ArrayList<>();

                List<RentalRequest> outForDelivery = RentalRequestStore.getRequestsByStatus(
                                "OUT_FOR_DELIVERY");

                List<RentalRequest> readyForPickup = RentalRequestStore.getRequestsByStatus(
                                "READY_FOR_PICKUP");

                deliveryRequests.addAll(
                                outForDelivery);

                deliveryRequests.addAll(
                                readyForPickup);

                if (deliveryRequests.isEmpty()) {

                        VBox emptyCard = createEmptyCard();

                        content.getChildren().add(
                                        emptyCard);

                } else {

                        VBox cards = new VBox(15);

                        for (RentalRequest request : deliveryRequests) {

                                cards.getChildren().add(
                                                createDeliveryCard(request));
                        }

                        content.getChildren().add(
                                        cards);
                }

                return content;
        }

        // ============================================================
        // DELIVERY CARD
        // ============================================================

        private static VBox createDeliveryCard(
                        RentalRequest request) {

                VBox card = new VBox(15);

                card.setPadding(
                                new Insets(20));

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;");

                // --------------------------------------------------------
                // HEADER
                // --------------------------------------------------------

                HBox header = new HBox(12);

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox itemInfo = new VBox(4);

                Label item = new Label(
                                request.getRental() != null
                                                ? request.getRental().getRentalName()
                                                : "Unknown Rental");

                item.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label customer = new Label(
                                "♙  Customer: "
                                                + request.getCustomerId());

                customer.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #405171;");

                itemInfo.getChildren().addAll(
                                item,
                                customer);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                String deliveryStatus = request.getDeliveryStatus() == null
                                ? "PENDING"
                                : request.getDeliveryStatus();

                String fulfillmentStatus;

                if ("ACCEPTED".equalsIgnoreCase(
                                deliveryStatus)) {

                        fulfillmentStatus = "✓ Accepted";

                } else {

                        fulfillmentStatus = "◷ Pending";
                }

                Label status = new Label(
                                fulfillmentStatus);

                status.setStyle(
                                "-fx-background-color: #FFF3D6;" +
                                                "-fx-text-fill: #D48A00;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-padding: 6px 10px;" +
                                                "-fx-background-radius: 12px;");

                header.getChildren().addAll(
                                itemInfo,
                                spacer,
                                status);

                // --------------------------------------------------------
                // DETAILS
                // --------------------------------------------------------

                HBox row1 = new HBox(60);

                VBox period = deliveryDetail(
                                "RENTAL PERIOD",
                                request.getStartDate()
                                                + " - "
                                                + request.getEndDate());

                VBox amount = deliveryDetail(
                                "TOTAL AMOUNT",
                                "₹"
                                                + String.format(
                                                                "%.2f",
                                                                request.getTotalAmount()));

                HBox.setHgrow(
                                period,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                amount,
                                Priority.ALWAYS);

                row1.getChildren().addAll(
                                period,
                                amount);

                HBox row2 = new HBox(60);

                VBox logistics = deliveryDetail(
                                "LOGISTICS",
                                request.getFulfillmentMethod());

                VBox payment = deliveryDetail(
                                "PAYMENT METHOD",
                                request.getPaymentMethod());

                HBox.setHgrow(
                                logistics,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                payment,
                                Priority.ALWAYS);

                row2.getChildren().addAll(
                                logistics,
                                payment);

                HBox row3 = new HBox(60);

                String address = "Not available";

                if (request.getRental() != null) {

                        address = request.getRental().getAddress()
                                        + ", "
                                        + request.getRental().getCity()
                                        + ", "
                                        + request.getRental().getState()
                                        + " - "
                                        + request.getRental().getPinCode();
                }

                VBox deliveryAddress = deliveryDetail(
                                "DELIVERY ADDRESS",
                                address);

                VBox requestedAt = deliveryDetail(
                                "REQUEST DATE",
                                request.getRequestedAt());

                HBox.setHgrow(
                                deliveryAddress,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                requestedAt,
                                Priority.ALWAYS);

                row3.getChildren().addAll(
                                deliveryAddress,
                                requestedAt);

                // --------------------------------------------------------
                // PAYMENT STATUS
                // --------------------------------------------------------

                String paymentStatus = "Cash on Delivery".equalsIgnoreCase(
                                request.getPaymentMethod())
                                                ? "Pending"
                                                : "Paid";

                VBox paymentStatusBox = deliveryDetail(
                                "PAYMENT STATUS",
                                paymentStatus);

                // --------------------------------------------------------
                // ACTIONS
                // --------------------------------------------------------

                HBox actions = new HBox(10);

                actions.setAlignment(
                                Pos.CENTER_RIGHT);

                Button details = new Button(
                                "◉ Details");

                details.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-padding: 8px 16px;" +
                                                "-fx-cursor: hand;");

                Button acceptDelivery = new Button(
                                "✓ Accept Delivery");

                acceptDelivery.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-padding: 8px 16px;" +
                                                "-fx-cursor: hand;");
                boolean isDelivery = "Delivery".equalsIgnoreCase(
                                request.getFulfillmentMethod());

                if (!isDelivery) {

                        acceptDelivery.setVisible(false);
                        acceptDelivery.setManaged(false);
                }

                acceptDelivery.setOnAction(event -> {

                        request.setDeliveryStatus(
                                        "ACCEPTED");

                        JsonObject fields = new JsonObject();

                        fields.add(
                                        "deliveryStatus",
                                        FirestoreService.stringField(
                                                        "ACCEPTED"));

                        boolean success = FirestoreService.updateDocument(
                                        "rental_requests",
                                        request.getRequestId(),
                                        fields);

                        if (success) {

                                showMessage(
                                                "Delivery Accepted",
                                                "The delivery request has been accepted.");

                                acceptDelivery.setVisible(false);
                                acceptDelivery.setManaged(false);

                                status.setText(
                                                "✓ Accepted");

                        } else {

                                request.setDeliveryStatus(
                                                "PENDING");

                                showMessage(
                                                "Error",
                                                "Could not accept the delivery request.");
                        }
                });

                actions.getChildren().addAll(
                                details,
                                acceptDelivery);

                card.getChildren().addAll(
                                header,
                                separator(),
                                row1,
                                row2,
                                row3,
                                paymentStatusBox,
                                separator(),
                                actions);

                return card;
        }

        // ============================================================
        // DETAIL BOX
        // ============================================================

        private static VBox deliveryDetail(
                        String heading,
                        String value) {

                VBox box = new VBox(5);

                Label headingLabel = new Label(
                                heading);

                headingLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label valueLabel = new Label(
                                value == null
                                                ? "Not available"
                                                : value);

                valueLabel.setWrapText(true);

                valueLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: #405171;");

                box.getChildren().addAll(
                                headingLabel,
                                valueLabel);

                return box;
        }

        // ============================================================
        // EMPTY CARD
        // ============================================================

        private static VBox createEmptyCard() {

                VBox card = new VBox(10);

                card.setAlignment(
                                Pos.CENTER);

                card.setPrefHeight(300);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                Label title = new Label(
                                "No Delivery Requests");

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label message = new Label(
                                "Requests marked as Out for Delivery will appear here.");

                message.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                card.getChildren().addAll(
                                title,
                                message);

                return card;
        }

        // ============================================================
        // SIDEBAR BUTTON
        // ============================================================

        private static Button navigationButton(
                        String icon,
                        String text) {

                Button button = new Button(
                                icon + "    " + text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(44);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #405171;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-padding: 10px 14px;" +
                                                "-fx-cursor: hand;");

                return button;
        }

        private static String navigationSelectedStyle() {

                return "-fx-background-color: #DDE7FF;" +
                                "-fx-text-fill: " + BLUE + ";" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 7px;" +
                                "-fx-padding: 10px 14px;";
        }

        // ============================================================
        // SEPARATOR
        // ============================================================

        private static Region separator() {

                Region line = new Region();

                line.setPrefHeight(1);

                line.setMaxWidth(
                                Double.MAX_VALUE);

                line.setStyle(
                                "-fx-background-color: " + BORDER + ";");

                return line;
        }

        // ============================================================
        // MESSAGE
        // ============================================================

        private static void showMessage(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                title);

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }
}