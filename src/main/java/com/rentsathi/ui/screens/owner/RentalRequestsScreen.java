package com.rentsathi.ui.screens.owner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import com.rentsathi.ui.screens.OwnerLoginScreen;
import java.util.List;

import com.google.gson.JsonObject;
import com.rentsathi.firebase.firestore.FirestoreService;

import com.rentsathi.model.rental.RentalRequest;
import com.rentsathi.model.rental.RentalRequestStore;

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
        private static String selectedStatus = "PENDING";

        private static HBox cardsContainer;

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                // SIDEBAR
                VBox sidebar = createSidebar(stage);

                // MAIN AREA
                VBox main = new VBox();

                HBox topBar = createTopBar();

                VBox content = createContent(stage);

                VBox.setVgrow(
                                content,
                                Priority.ALWAYS);

                main.getChildren().addAll(
                                topBar,
                                content);

                root.setLeft(sidebar);
                root.setCenter(main);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                stage.setTitle(
                                "RentSathi - Rental Requests");

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
                                new Insets(18, 14, 15, 14));

                sidebar.setSpacing(6);

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1px 0 0;");

                // -------------------------
                // LOGO
                // -------------------------

                HBox logoBox = new HBox(12);

                logoBox.setAlignment(
                                Pos.CENTER_LEFT);

                logoBox.setPadding(
                                new Insets(5, 8, 20, 8));

                StackPane logo = new StackPane();

                Circle circle = new Circle(
                                25,
                                Color.web(BLUE));

                Label rs = new Label("RS");

                rs.setStyle(
                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                logo.getChildren().addAll(
                                circle,
                                rs);

                VBox brand = new VBox(2);

                Label rentSathi = new Label("RentSathi");

                rentSathi.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label owner = new Label("Owner Portal");

                owner.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                brand.getChildren().addAll(
                                rentSathi,
                                owner);

                logoBox.getChildren().addAll(
                                logo,
                                brand);

                // -------------------------
                // POST NEW LISTING
                // -------------------------

                Button postListing = new Button(
                                "+  Post New Listing");

                stylePrimaryButton(
                                postListing,
                                225,
                                42);

                postListing.setOnAction(
                                event -> AddNewRentalScreen.show(stage));

                // -------------------------
                // DASHBOARD
                // -------------------------

                Button dashboard = navigationButton(
                                "▦",
                                "Dashboard",
                                false);

                dashboard.setOnAction(
                                event -> OwnerDashboardScreen.show(stage));

                // -------------------------
                // MANAGE LISTINGS
                // -------------------------

                Button bookings = navigationButton(
                                "▣",
                                "Bookings",
                                true);

                bookings.setOnAction(
                                event -> RentalRequestsScreen.show(stage));

                // -------------------------
                // DELIVERIES
                // -------------------------

                Button deliveries = navigationButton(
                                "▱",
                                "Owner Deliveries",
                                false);

                deliveries.setOnAction(
                                event -> OwnerDeliveriesScreen.show(stage));

                // -------------------------
                // ANALYTICS
                // -------------------------

                Button analytics = navigationButton(
                                "▥",
                                "Analytics",
                                false);

                VBox navigation = new VBox(5);

                navigation.getChildren().addAll(
                                postListing,
                                dashboard,
                                bookings,
                                deliveries,
                                analytics);

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                Separator separator = new Separator();

                // -------------------------
                // SETTINGS
                // -------------------------

                Button settings = navigationButton(
                                "⚙",
                                "Settings",
                                false);

                // -------------------------
                // LOGOUT
                // -------------------------

                Button logout = navigationButton(
                                "↪",
                                "Logout",
                                false);

                logout.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #D93030;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 10px 12px;" +
                                                "-fx-cursor: hand;");

                logout.setOnAction(
                                event -> OwnerLoginScreen.show(stage));

                sidebar.getChildren().addAll(
                                logoBox,
                                navigation,
                                spacer,
                                separator,
                                settings,
                                logout);

                return sidebar;
        }

        // =========================
        // TOP BAR
        // =========================

        private static HBox createTopBar() {

                HBox top = new HBox();

                top.setPrefHeight(70);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                top.setPadding(
                                new Insets(
                                                0,
                                                25,
                                                0,
                                                25));

                top.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1px 0;");

                TextField search = new TextField();

                search.setPromptText(
                                "Search requests...");

                search.setPrefWidth(330);
                search.setPrefHeight(40);

                search.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #C9CFDE;" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 0 15px;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label notification = new Label("♧");

                notification.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label help = new Label("?");

                help.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";" +
                                                "-fx-border-color: " + TEXT + ";" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-padding: 1px 6px;");

                top.getChildren().addAll(
                                search,
                                spacer,
                                notification,
                                new Region(),
                                help);

                return top;
        }

        // =========================
        // CONTENT
        // =========================

        private static VBox createContent(
                        Stage stage) {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                28,
                                                30,
                                                30,
                                                30));

                // -------------------------
                // TITLE
                // -------------------------

                VBox titleBox = new VBox(4);

                Label title = new Label(
                                "Rental Requests");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label subtitle = new Label(
                                "Manage incoming booking requests for your listed items.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                // -------------------------
                // TABS
                // -------------------------

                HBox tabs = createTabs();

                // -------------------------
                // REQUEST CARDS
                // -------------------------

                cardsContainer = new HBox(20);

                List<RentalRequest> requests = RentalRequestStore.getRequestsByStatus(
                                selectedStatus);

                for (RentalRequest request : requests) {

                        boolean pending = "PENDING".equalsIgnoreCase(
                                        request.getStatus());

                        String rentalPeriod = request.getStartDate()
                                        + " - "
                                        + request.getEndDate();

                        String amount = "₹"
                                        + String.format(
                                                        "%.2f",
                                                        request.getTotalAmount());

                        String logistics = request.getFulfillmentMethod();

                        String requestedBy = "Requested by "
                                        + request.getCustomerId();

                        String rentalName = "Unknown Rental";

                        if (request.getRental() != null) {

                                rentalName = request.getRental().getRentalName();
                        }

                        VBox requestCard = createRequestCard(
                                        request,
                                        rentalName,
                                        requestedBy,
                                        rentalPeriod,
                                        amount,
                                        logistics,
                                        request.getRequestedAt(),
                                        pending);

                        HBox.setHgrow(
                                        requestCard,
                                        Priority.ALWAYS);

                        cardsContainer.getChildren().add(
                                        requestCard);
                }

                VBox.setVgrow(
                                cardsContainer,
                                Priority.ALWAYS);

                content.getChildren().addAll(
                                titleBox,
                                tabs,
                                cardsContainer);

                return content;
        }

        // =========================
        // TABS
        // =========================

        private static HBox createTabs() {

                HBox tabs = new HBox(28);

                tabs.setAlignment(
                                Pos.CENTER_LEFT);

                Button pending = tabButton(
                                "Pending",
                                "PENDING".equalsIgnoreCase(selectedStatus));

                Button accepted = tabButton(
                                "Accepted",
                                "ACCEPTED".equalsIgnoreCase(selectedStatus));

                Button preparing = tabButton(
                                "Preparing",
                                "PREPARING".equalsIgnoreCase(selectedStatus));

                Button delivery = tabButton(
                                "Out for Delivery",
                                "OUT_FOR_DELIVERY".equalsIgnoreCase(selectedStatus));

                Button completed = tabButton(
                                "Completed",
                                "COMPLETED".equalsIgnoreCase(selectedStatus));

                Button rejected = tabButton(
                                "Rejected",
                                "REJECTED".equalsIgnoreCase(selectedStatus));

                pending.setOnAction(event -> selectTab(
                                pending,
                                accepted,
                                preparing,
                                delivery,
                                completed,
                                rejected,
                                "PENDING"));

                accepted.setOnAction(event -> selectTab(
                                accepted,
                                pending,
                                preparing,
                                delivery,
                                completed,
                                rejected,
                                "ACCEPTED"));

                preparing.setOnAction(event -> selectTab(
                                preparing,
                                pending,
                                accepted,
                                delivery,
                                completed,
                                rejected,
                                "PREPARING"));

                delivery.setOnAction(event -> selectTab(
                                delivery,
                                pending,
                                accepted,
                                preparing,
                                completed,
                                rejected,
                                "OUT_FOR_DELIVERY"));

                completed.setOnAction(event -> selectTab(
                                completed,
                                pending,
                                accepted,
                                preparing,
                                delivery,
                                rejected,
                                "COMPLETED"));

                rejected.setOnAction(event -> selectTab(
                                rejected,
                                pending,
                                accepted,
                                preparing,
                                delivery,
                                completed,
                                "REJECTED"));

                tabs.getChildren().addAll(
                                pending,
                                accepted,
                                preparing,
                                delivery,
                                completed,
                                rejected);

                return tabs;
        }

        private static Button tabButton(
                        String text,
                        boolean selected) {

                Button button = new Button(text);

                button.setPrefHeight(38);

                button.setPadding(
                                new Insets(
                                                0,
                                                7,
                                                0,
                                                7));

                button.setStyle(
                                tabStyle(selected));

                return button;
        }

        private static void selectTab(
                        Button selected,
                        Button a,
                        Button b,
                        Button c,
                        Button d,
                        Button e,
                        String status) {

                selected.setStyle(
                                tabStyle(true));

                a.setStyle(
                                tabStyle(false));

                b.setStyle(
                                tabStyle(false));

                c.setStyle(
                                tabStyle(false));

                d.setStyle(
                                tabStyle(false));

                e.setStyle(
                                tabStyle(false));

                selectedStatus = status;

                refreshRequests();
        }

        private static void refreshRequests() {

                if (cardsContainer == null) {
                        return;
                }

                cardsContainer.getChildren().clear();

                List<RentalRequest> requests = RentalRequestStore.getRequestsByStatus(
                                selectedStatus);

                for (RentalRequest request : requests) {

                        boolean pending = "PENDING".equalsIgnoreCase(
                                        request.getStatus());

                        String rentalPeriod = request.getStartDate()
                                        + " - "
                                        + request.getEndDate();

                        String amount = "₹"
                                        + String.format(
                                                        "%.2f",
                                                        request.getTotalAmount());

                        String logistics = request.getFulfillmentMethod();

                        String requestedBy = "Requested by "
                                        + request.getCustomerId();

                        String rentalName = "Unknown Rental";

                        if (request.getRental() != null) {

                                rentalName = request.getRental().getRentalName();

                        }

                        VBox requestCard = createRequestCard(
                                        request,
                                        rentalName,
                                        requestedBy,
                                        rentalPeriod,
                                        amount,
                                        logistics,
                                        request.getRequestedAt(),
                                        pending);

                        HBox.setHgrow(
                                        requestCard,
                                        Priority.ALWAYS);

                        cardsContainer.getChildren().add(
                                        requestCard);
                }
        }

        private static String tabStyle(
                        boolean selected) {

                if (selected) {

                        return "-fx-background-color: transparent;" +
                                        "-fx-text-fill: " + BLUE + ";" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-border-color: transparent transparent " +
                                        BLUE + " transparent;" +
                                        "-fx-border-width: 0 0 2px 0;" +
                                        "-fx-cursor: hand;";
                }

                return "-fx-background-color: transparent;" +
                                "-fx-text-fill: #394B6A;" +
                                "-fx-cursor: hand;";
        }

        // =========================
        // REQUEST CARD
        // =========================

        private static VBox createRequestCard(
                        RentalRequest request,
                        String item,
                        String requestedBy,
                        String period,
                        String amount,
                        String logistics,
                        String requestDate,
                        boolean camera) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(20));

                card.setPrefHeight(270);

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;");

                // -------------------------
                // TOP
                // -------------------------

                HBox top = new HBox(12);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                StackPane image = createProductImage(camera);

                VBox itemInfo = new VBox(4);

                HBox.setHgrow(
                                itemInfo,
                                Priority.ALWAYS);

                Label itemName = new Label(item);

                itemName.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label requester = new Label(
                                "♙  " + requestedBy);

                requester.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #405171;");

                itemInfo.getChildren().addAll(
                                itemName,
                                requester);

                String requestStatus = request.getStatus() == null
                                ? "PENDING"
                                : request.getStatus();

                Label statusLabel = new Label();

                if ("ACCEPTED".equalsIgnoreCase(requestStatus)) {

                        statusLabel.setText("✓ Accepted");

                        statusLabel.setStyle(
                                        "-fx-background-color: #E4F7E8;" +
                                                        "-fx-text-fill: " + GREEN + ";" +
                                                        "-fx-font-size: 11px;" +
                                                        "-fx-padding: 6px 10px;" +
                                                        "-fx-background-radius: 12px;");

                } else if ("REJECTED".equalsIgnoreCase(requestStatus)) {

                        statusLabel.setText("✕ Rejected");

                        statusLabel.setStyle(
                                        "-fx-background-color: #FFE5E5;" +
                                                        "-fx-text-fill: " + RED + ";" +
                                                        "-fx-font-size: 11px;" +
                                                        "-fx-padding: 6px 10px;" +
                                                        "-fx-background-radius: 12px;");

                } else if ("COMPLETED".equalsIgnoreCase(requestStatus)) {

                        statusLabel.setText("✓ Completed");

                        statusLabel.setStyle(
                                        "-fx-background-color: #E4F7E8;" +
                                                        "-fx-text-fill: " + GREEN + ";" +
                                                        "-fx-font-size: 11px;" +
                                                        "-fx-padding: 6px 10px;" +
                                                        "-fx-background-radius: 12px;");

                } else {

                        statusLabel.setText("◷ Pending");

                        statusLabel.setStyle(
                                        "-fx-background-color: #FFF3D6;" +
                                                        "-fx-text-fill: #D48A00;" +
                                                        "-fx-font-size: 11px;" +
                                                        "-fx-padding: 6px 10px;" +
                                                        "-fx-background-radius: 12px;");
                }

                top.getChildren().addAll(
                                image,
                                itemInfo,
                                statusLabel);

                Separator separator1 = new Separator();

                // -------------------------
                // DETAILS
                // -------------------------

                GridPane details = new GridPane();

                details.setHgap(35);
                details.setVgap(14);

                VBox periodBox = detail(
                                "RENTAL PERIOD",
                                "▣  " + period);

                VBox amountBox = detail(
                                "TOTAL AMOUNT",
                                "▣  " + amount);

                VBox logisticsBox = detail(
                                "LOGISTICS",
                                "▱  " + logistics);

                VBox dateBox = detail(
                                "REQUEST DATE",
                                "◷  " + requestDate);

                ColumnConstraints c1 = new ColumnConstraints();

                c1.setPercentWidth(50);

                ColumnConstraints c2 = new ColumnConstraints();

                c2.setPercentWidth(50);

                details.getColumnConstraints()
                                .addAll(c1, c2);

                details.add(
                                periodBox,
                                0,
                                0);

                details.add(
                                amountBox,
                                1,
                                0);

                details.add(
                                logisticsBox,
                                0,
                                1);

                details.add(
                                dateBox,
                                1,
                                1);

                Separator separator2 = new Separator();

                // -------------------------
                // ACTIONS
                // -------------------------

                HBox actions = new HBox(8);

                actions.setAlignment(
                                Pos.CENTER_RIGHT);

                Region actionSpacer = new Region();

                HBox.setHgrow(
                                actionSpacer,
                                Priority.ALWAYS);

                Button detailsButton = new Button(
                                "◉ Details");

                styleDetailsButton(
                                detailsButton);

                Button reject = new Button(
                                "× Reject");

                styleRejectButton(
                                reject);

                Button accept = new Button(
                                camera
                                                ? "✓ Accept Request"
                                                : "✓ Accept");

                styleAcceptButton(
                                accept);

                Button preparing = new Button(
                                "✓ Start Preparing");

                Button delivery = new Button(
                                "🚚 Send for Delivery");
                if ("Pickup".equalsIgnoreCase(
                                request.getFulfillmentMethod())) {

                        delivery.setText(
                                        "✓ Ready for Pickup");
                }

                styleAcceptButton(
                                delivery);

                styleAcceptButton(
                                preparing);

                if ("PENDING".equalsIgnoreCase(requestStatus)) {

                        // Show Accept and Reject
                        accept.setVisible(true);
                        accept.setManaged(true);

                        reject.setVisible(true);
                        reject.setManaged(true);

                        // Hide Start Preparing
                        preparing.setVisible(false);
                        preparing.setManaged(false);

                        // Hide Send for Delivery
                        delivery.setVisible(false);
                        delivery.setManaged(false);

                } else if ("ACCEPTED".equalsIgnoreCase(requestStatus)) {

                        // Hide Accept and Reject
                        accept.setVisible(false);
                        accept.setManaged(false);

                        reject.setVisible(false);
                        reject.setManaged(false);

                        // Show Start Preparing
                        preparing.setVisible(true);
                        preparing.setManaged(true);

                        // Hide Send for Delivery
                        delivery.setVisible(false);
                        delivery.setManaged(false);

                } else if ("PREPARING".equalsIgnoreCase(requestStatus)) {

                        // Hide Accept and Reject
                        accept.setVisible(false);
                        accept.setManaged(false);

                        reject.setVisible(false);
                        reject.setManaged(false);

                        // Hide Start Preparing
                        preparing.setVisible(false);
                        preparing.setManaged(false);

                        // Show Send for Delivery
                        delivery.setVisible(true);
                        delivery.setManaged(true);

                } else if ("OUT_FOR_DELIVERY".equalsIgnoreCase(requestStatus)) {

                        // Hide all action buttons except Details
                        accept.setVisible(false);
                        accept.setManaged(false);

                        reject.setVisible(false);
                        reject.setManaged(false);

                        preparing.setVisible(false);
                        preparing.setManaged(false);

                        delivery.setVisible(false);
                        delivery.setManaged(false);

                } else if ("REJECTED".equalsIgnoreCase(requestStatus)) {

                        // Hide all action buttons except Details
                        accept.setVisible(false);
                        accept.setManaged(false);

                        reject.setVisible(false);
                        reject.setManaged(false);

                        preparing.setVisible(false);
                        preparing.setManaged(false);

                        delivery.setVisible(false);
                        delivery.setManaged(false);

                } else if ("COMPLETED".equalsIgnoreCase(requestStatus)) {

                        // Hide all action buttons except Details
                        accept.setVisible(false);
                        accept.setManaged(false);

                        reject.setVisible(false);
                        reject.setManaged(false);

                        preparing.setVisible(false);
                        preparing.setManaged(false);

                        delivery.setVisible(false);
                        delivery.setManaged(false);

                } else {

                        // Unknown status - hide workflow buttons
                        accept.setVisible(false);
                        accept.setManaged(false);

                        reject.setVisible(false);
                        reject.setManaged(false);

                        preparing.setVisible(false);
                        preparing.setManaged(false);

                        delivery.setVisible(false);
                        delivery.setManaged(false);
                }
                reject.setOnAction(event -> {

                        request.setStatus("REJECTED");

                        JsonObject fields = new JsonObject();

                        fields.add(
                                        "status",
                                        FirestoreService.stringField(
                                                        "REJECTED"));

                        boolean success = FirestoreService.updateDocument(
                                        "rental_requests",
                                        request.getRequestId(),
                                        fields);

                        if (success) {

                                showMessage(
                                                "Request Rejected",
                                                "The rental request has been rejected.");

                                refreshRequests();

                        } else {

                                // Restore previous status if Firestore update fails
                                request.setStatus("PENDING");

                                showMessage(
                                                "Error",
                                                "Could not reject the request. Please try again.");
                        }
                });

                accept.setOnAction(event -> {

                        request.setStatus("ACCEPTED");

                        JsonObject fields = new JsonObject();

                        fields.add(
                                        "status",
                                        FirestoreService.stringField("ACCEPTED"));

                        boolean success = FirestoreService.updateDocument(
                                        "rental_requests",
                                        request.getRequestId(),
                                        fields);

                        if (success) {

                                showMessage(
                                                "Request Accepted",
                                                "The rental request has been accepted.");

                                refreshRequests();

                        } else {

                                // Revert local status if Firestore update failed
                                request.setStatus("PENDING");

                                showMessage(
                                                "Error",
                                                "Could not update the request. Please try again.");
                        }
                });
                preparing.setOnAction(event -> {

                        request.setStatus("PREPARING");

                        JsonObject fields = new JsonObject();

                        fields.add(
                                        "status",
                                        FirestoreService.stringField(
                                                        "PREPARING"));

                        boolean success = FirestoreService.updateDocument(
                                        "rental_requests",
                                        request.getRequestId(),
                                        fields);

                        if (success) {

                                showMessage(
                                                "Rental Preparing",
                                                "The rental request is now being prepared.");

                                refreshRequests();

                        } else {

                                request.setStatus("ACCEPTED");

                                showMessage(
                                                "Error",
                                                "Could not update the request.");
                        }
                });
                delivery.setOnAction(event -> {

                        boolean isPickup = "Pickup".equalsIgnoreCase(
                                        request.getFulfillmentMethod());

                        String newStatus;

                        if (isPickup) {

                                newStatus = "READY_FOR_PICKUP";

                        } else {

                                newStatus = "OUT_FOR_DELIVERY";
                        }

                        request.setStatus(
                                        newStatus);

                        if ("OUT_FOR_DELIVERY".equalsIgnoreCase(
                                        newStatus)) {

                                request.setDeliveryStatus(
                                                "PENDING");
                        }

                        JsonObject fields = new JsonObject();

                        fields.add(
                                        "status",
                                        FirestoreService.stringField(
                                                        newStatus));
                        if ("OUT_FOR_DELIVERY".equalsIgnoreCase(
                                        newStatus)) {

                                fields.add(
                                                "deliveryStatus",
                                                FirestoreService.stringField(
                                                                "PENDING"));
                        }

                        boolean success = FirestoreService.updateDocument(
                                        "rental_requests",
                                        request.getRequestId(),
                                        fields);

                        if (success) {

                                if (isPickup) {

                                        showMessage(
                                                        "Ready for Pickup",
                                                        "The rental is now ready for customer pickup.");

                                } else {

                                        showMessage(
                                                        "Out for Delivery",
                                                        "The rental has been marked as out for delivery.");
                                }

                                refreshRequests();

                        } else {

                                request.setStatus(
                                                "PREPARING");

                                showMessage(
                                                "Error",
                                                "Could not update the request.");
                        }
                });

                detailsButton.setOnAction(
                                event -> showMessage(
                                                "Rental Details",
                                                item +
                                                                "\n\n" +
                                                                requestedBy +
                                                                "\n" +
                                                                period +
                                                                "\n" +
                                                                amount));

                actions.getChildren().addAll(
                                detailsButton,
                                reject,
                                accept,
                                preparing,
                                delivery);

                card.getChildren().addAll(
                                top,
                                separator1,
                                details,
                                separator2,
                                actions);

                return card;
        }

        // =========================
        // PRODUCT IMAGE
        // =========================

        private static StackPane createProductImage(
                        boolean camera) {

                StackPane box = new StackPane();

                box.setPrefSize(
                                62,
                                62);

                box.setMinSize(
                                62,
                                62);

                box.setMaxSize(
                                62,
                                62);

                box.setStyle(
                                "-fx-background-color: #EEF0F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: #D0D5E2;" +
                                                "-fx-border-radius: 7px;");

                Label icon = new Label(
                                camera
                                                ? "▣"
                                                : "♧");

                icon.setStyle(
                                "-fx-font-size: 27px;" +
                                                "-fx-text-fill: #53627F;");

                box.getChildren().add(
                                icon);

                return box;
        }

        // =========================
        // DETAIL
        // =========================

        private static VBox detail(
                        String title,
                        String value) {

                VBox box = new VBox(5);

                Label heading = new Label(title);

                heading.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + MUTED + ";");

                Label valueLabel = new Label(value);

                valueLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #34496B;");

                box.getChildren().addAll(
                                heading,
                                valueLabel);

                return box;
        }

        // =========================
        // BUTTON STYLES
        // =========================

        private static void stylePrimaryButton(
                        Button button,
                        double width,
                        double height) {

                button.setPrefWidth(width);
                button.setPrefHeight(height);

                button.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-cursor: hand;");
        }

        private static void styleDetailsButton(
                        Button button) {

                button.setPrefHeight(34);

                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #304B79;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-cursor: hand;");
        }

        private static void styleRejectButton(
                        Button button) {

                button.setPrefHeight(34);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: " + RED + ";" +
                                                "-fx-border-color: " + RED + ";" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-cursor: hand;");
        }

        private static void styleAcceptButton(
                        Button button) {

                button.setPrefHeight(34);

                button.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-cursor: hand;");
        }

        // =========================
        // NAVIGATION BUTTON
        // =========================

        private static Button navigationButton(
                        String icon,
                        String text,
                        boolean selected) {

                Button button = new Button(
                                icon + "    " + text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(42);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setPadding(
                                new Insets(
                                                0,
                                                12,
                                                0,
                                                12));

                if (selected) {

                        button.setStyle(
                                        "-fx-background-color: " +
                                                        LIGHT_BLUE + ";" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-font-size: 14px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #34496B;" +
                                                        "-fx-font-size: 14px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                }

                return button;
        }

        // =========================
        // MESSAGE
        // =========================

        private static void showMessage(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle("RentSathi");
                alert.setHeaderText(title);
                alert.setContentText(message);

                alert.showAndWait();
        }
}
